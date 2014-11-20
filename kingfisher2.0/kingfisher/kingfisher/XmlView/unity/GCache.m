//
//  GULruCache.m
//  kingfisher
//
//  Created by jianbai on 14/11/13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GCache.h"
#import "GHash.h"

@implementation GCache

-(GCache*)initWithSize:(NSUInteger)size{
    self = [super init];
    if (self) {
        _size = size;
        _map = [[NSMutableDictionary alloc] initWithCapacity:size];
    }
    return self;
}

-(id)get:(NSString*)key{
    return [_map objectForKey:[self toKey:key]];
}

-(void)put:(NSString*)key value:(id)value{
    [_map setObject:value forKey:[self toKey:key]];
}

-(NSNumber*) toKey:(NSString*)key{
    NSInteger hashCode = [GHash hashCode:key];
    NSUInteger index = [GHash hashMapCode:hashCode] & (_size - 1);
    return [NSNumber numberWithInteger:index];
}

@end
