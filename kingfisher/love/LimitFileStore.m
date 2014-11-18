//
//  LimitFileStore.m
//  kingfisher
//
//  Created by jianbai on 14/11/18.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "LimitFileStore.h"
#import "GUHash.h"

@implementation LimitFileStore

@synthesize lifetime = _lifetime;

@synthesize size = _size;

-(LimitFileStore*)initWithPath:(NSSearchPathDirectory) path dir:(NSString*)dir size:(NSUInteger)size lifetime:(NSUInteger)lifetime{
    self = [super initWithPath:path dir:dir];
    if (self) {
        self.size = size;
        self.lifetime = lifetime;
    }
    return self;
}

-(void)beforeWrite:(NSString*)filePath{
    NSString* parentPath = [filePath stringByDeletingLastPathComponent];
    NSDate* date = [NSDate dateWithTimeIntervalSinceNow:-self.lifetime];
    [self cleanDir:parentPath lifetime:date];
    [super beforeWrite:filePath];
}


-(NSString*)toFullPath:(NSString*)fileName{
    NSUInteger code = [GUHash hashMapCode:[GUHash hashCode:fileName]] &(self.size - 1);
    NSString* md5 = fileName;
    return [[super getFullPath] stringByAppendingPathComponent:[NSString stringWithFormat:@"/%d/%@", code, md5]];
}

@end
