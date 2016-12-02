//
//  JSRef.h
//  JSCoreTest
//
//  Created by furture on 16/6/27.
//  Copyright © 2016年 Evgenii Neumerzhitckii. All rights reserved.
//

#import <Foundation/Foundation.h>

@class JSEngine;

@interface JSRef : NSObject

@property(nonatomic) int jsRef;

@property(strong,nonatomic) JSEngine* engine;


-(id)initWithRef:(int)jsRef engine:(JSEngine*) engine;


@end
