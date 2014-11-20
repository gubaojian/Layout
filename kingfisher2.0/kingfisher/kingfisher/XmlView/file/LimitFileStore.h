//
//  LimitFileStore.h
//  kingfisher
//
//  Created by jianbai on 14/11/18.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "FileStore.h"

@interface LimitFileStore : FileStore{
      @private NSUInteger _size;
      @private NSUInteger _lifetime;
}

@property(nonatomic) NSUInteger size;

@property(nonatomic)NSUInteger lifetime;


-(LimitFileStore*)initWithPath:(NSSearchPathDirectory) path dir:(NSString*)dir size:(NSUInteger)size lifetime:(NSUInteger)lifetime;

@end
