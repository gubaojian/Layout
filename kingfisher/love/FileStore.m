//
//  GUFileStore.m
//  kingfisher
//
//  Created by jianbai on 14/11/14.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "FileStore.h"
#import "LimitFileStore.h"

@implementation FileStore

@synthesize dir = _dir;

@synthesize path = _path;

+(FileStore*)from:(NSSearchPathDirectory)path{
    return [FileStore from:path dir:@"files"];
}

+(FileStore*)from:(NSSearchPathDirectory)path dir:(NSString*)dir{
   return [[FileStore alloc] initWithPath:path dir:dir];
}

+(FileStore*)from:(NSSearchPathDirectory)path dir:(NSString*)dir size:(NSUInteger)size{
   return [FileStore from:path dir:dir size:size lifetime:0];
}

+(FileStore*)from:(NSSearchPathDirectory)path dir:(NSString*)dir size:(NSUInteger)size lifetime:(NSUInteger)lifetime{
    return [[LimitFileStore alloc] initWithPath:path dir:dir size:size lifetime:lifetime];
}


-(FileStore*)initWithPath:(NSSearchPathDirectory) path dir:(NSString*)dir{
    self = [self init];
    if (self) {
        self.path = path;
        self.dir = dir;
    }
    return self;
}

-(NSString*)getFullPath{
    if (_fullPath == nil) {
        NSArray *paths = NSSearchPathForDirectoriesInDomains(_path, NSUserDomainMask, YES);
        NSString *directory = [paths objectAtIndex:0];
        _fullPath =  [directory stringByAppendingPathComponent:_dir];
        if (![[NSFileManager defaultManager] fileExistsAtPath:_fullPath]) {
             NSError* error;
            [[NSFileManager defaultManager] createDirectoryAtPath:_fullPath withIntermediateDirectories:YES attributes:nil error:&error];
            if (error) {
                NSLog(@"createDirectoryAtPath %@ error %@", _fullPath, error);
            }
        }
        if (_path == NSDocumentationDirectory
            || _path == NSDocumentDirectory
            || _path == NSUserDirectory) {
            NSURL* pathUrl = [[NSURL alloc] initFileURLWithPath:_fullPath];
            NSError* error;
            [pathUrl setResourceValue:[NSNumber numberWithBool: YES] forKey:NSURLIsExcludedFromBackupKey error:&error];
            if (error) {
                NSLog(@"add %@ to exclude backup path error %@", _fullPath, error);
            }
        }
    }
    return _fullPath;
}

-(NSString*)toFullPath:(NSString*)fileName{
    return [[self getFullPath] stringByAppendingPathComponent:fileName];
}

-(BOOL)exists:(NSString*)fileName{
    NSString* filePath = [self toFullPath:fileName];
    return [[NSFileManager defaultManager] fileExistsAtPath:filePath];
}

-(NSData*)read:(NSString*)fileName{
    return [NSData dataWithContentsOfFile:[self toFullPath:fileName]];
}

-(NSString*)readString:(NSString*)fileName{
    NSData* data = [self read:fileName];
    if (data== nil || data.length == 0) {
        return nil;
    }
    return [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
}

-(NSInputStream*)toInputStream:(NSString*)fileName{
    return [NSInputStream inputStreamWithFileAtPath:[self toFullPath:fileName]];
}

-(BOOL)deleteFile:(NSString*)fileName{
    return [[NSFileManager defaultManager] removeItemAtPath:[self toFullPath:fileName] error:nil];
}

-(void)write:(NSData*)data toFile:(NSString*)fileName{
    if (data == nil || data.length == 0) {
        [self deleteFile:fileName];
        return;
    }
    NSString* filePath = [self toFullPath:fileName];
    [self beforeWrite:filePath];
    [data writeToFile:filePath atomically:YES];
}
-(void)writeString:(NSString*)content toFile:(NSString*)fileName{
    NSString* filePath = [self toFullPath:fileName];
    [content writeToFile:filePath atomically:YES encoding:NSUTF8StringEncoding error:nil];
}
-(NSOutputStream*)toOutputStream:(NSString*)fileName{
    NSString* filePath = [self toFullPath:fileName];
    [self beforeWrite:filePath];
    return [[NSOutputStream alloc] initToFileAtPath:filePath append:NO];
}

- (void)beforeWrite:(NSString*)filePath{
    NSString* parentPath = [filePath stringByDeletingLastPathComponent];
    if (![[NSFileManager defaultManager] fileExistsAtPath:parentPath]) {
        [[NSFileManager defaultManager] createDirectoryAtPath:parentPath withIntermediateDirectories:YES attributes:nil error:nil];
    }
}

-(void)clean:(NSUInteger)lifeTime{
     NSString* dir = [self getFullPath];
     NSDate*  minModifiedTime = [NSDate dateWithTimeIntervalSinceNow:-lifeTime];
     [self cleanDir:dir lifetime:minModifiedTime];
}

-(void) cleanDir:(NSString*) dir lifetime:(NSDate*)lifetime{
    if (![[NSFileManager defaultManager] fileExistsAtPath:dir]) {
        return;
    }
    NSError* error;
    NSArray* files = [[NSFileManager defaultManager] contentsOfDirectoryAtPath:dir error:&error];
    if (files == nil || [files count] == 0) {
        NSError* error;
        [[NSFileManager defaultManager] removeItemAtPath:dir error:&error];
        if (error) {
            NSLog(@"Remove File %@ Error %@", dir, error);
        }
        return;
    }
    for (NSString* file in files) {
        NSError* error;
        NSDictionary * attrs = [[NSFileManager defaultManager] attributesOfItemAtPath: file error:&error];
        NSDate* date = [attrs fileModificationDate];
        if ([date compare:lifetime] == NSOrderedDescending) {
            continue;
        }
        [attrs fileGroupOwnerAccountName];
        BOOL isDir ;
        [[NSFileManager defaultManager] fileExistsAtPath:file isDirectory:&isDir];
        if (isDir) {
            [self cleanDir:file lifetime:lifetime];
        }else{
            NSError* error;
            [[NSFileManager defaultManager] removeItemAtPath:file error:&error];
            if (error) {
                NSLog(@"Remove File %@ Error %@", file, error);
            }
        }
    }
}


@end
