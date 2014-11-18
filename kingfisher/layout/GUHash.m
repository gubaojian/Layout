//
//  GUHash.m
//  kingfisher
//
//  Created by jianbai on 14/11/13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUHash.h"
#import <CommonCrypto/CommonDigest.h>

@implementation GUHash

/**
 * hash functions copy from hashmap
 * */
+(NSUInteger)hashMapCode:(NSUInteger) h{
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >> 20) ^ (h >> 12);
    return h ^ (h >> 7) ^ (h >> 4);
}

+(NSInteger) hashCode:(NSString*)value{
    NSInteger h = 0;
    for (NSInteger i = 0; i < (NSInteger)value.length; i++) {
        h = (31 * h) + [value characterAtIndex:i];
    }
    return h;
}

+(NSString*)md5:(NSString*)key{

    const char *str = [key UTF8String];
    if (str == NULL) {
        str = "";
    }
    unsigned char r[CC_MD5_DIGEST_LENGTH];
    CC_MD5(str, (CC_LONG)strlen(str), r);
    NSString *md5 = [NSString stringWithFormat:@"%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x",
                          r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8], r[9], r[10], r[11], r[12], r[13], r[14], r[15]];
    return md5;
}

@end
