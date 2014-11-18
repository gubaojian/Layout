//
//  GUFileStore.h
//  kingfisher
//
//  Created by ; on 14/11/14.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FileStore : NSObject{
    @private NSString* _dir;
    @private  NSSearchPathDirectory _path;
    @private NSString* _fullPath;
}

@property  NSString* dir;
@property  NSSearchPathDirectory path;

+(FileStore*)from:(NSSearchPathDirectory)path;

+(FileStore*)from:(NSSearchPathDirectory)path dir:(NSString*)dir;

+(FileStore*)from:(NSSearchPathDirectory)path dir:(NSString*)dir size:(NSUInteger)size;

+(FileStore*)from:(NSSearchPathDirectory)path dir:(NSString*)dir size:(NSUInteger)size lifetime:(NSUInteger)lifetime;


-(NSString*)getFullPath;

-(NSString*)toFullPath:(NSString*)fileName;

-(BOOL)exists:(NSString*)fileName;

-(NSData*)read:(NSString*)fileName;

-(NSString*)readString:(NSString*)fileName;

-(NSInputStream*)toInputStream:(NSString*)fileName;

-(BOOL)deleteFile:(NSString*)fileName;

-(void)write:(NSData*)data toFile:(NSString*)fileName;

-(void)writeString:(NSString*)content toFile:(NSString*)fileName;

-(NSOutputStream*)toOutputStream:(NSString*)fileName;

-(void)clean:(NSUInteger)lifeTime;

-(FileStore*)initWithPath:(NSSearchPathDirectory) path dir:(NSString*)dir;

#pragma @private

-(void)beforeWrite:(NSString*)filePath;
-(void) cleanDir:(NSString*) dir lifetime:(NSDate*)lifetime;

@end
