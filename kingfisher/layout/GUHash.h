//
//  GUHash.h
//  kingfisher
//
//  Created by jianbai on 14/11/13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GUHash : NSObject

+(NSInteger) hashCode:(NSString*)value;
    
+(NSUInteger)hashMapCode:(NSUInteger) h;

+(NSString*)md5:(NSString*)key;

@end
