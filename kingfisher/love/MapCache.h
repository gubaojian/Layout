//
//  GULruCache.h
//  kingfisher
//
//  Created by jianbai on 14/11/13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MapCache : NSObject{
    @private NSUInteger  _size;
    @private NSMutableDictionary* _map;
}

-(MapCache*)initWithSize:(NSUInteger)size;

-(id)get:(NSString*)key;
-(void)put:(NSString*)key value:(id)value;

@end
