//
//  JSEngine.h
//  JSCoreTest
//
//  Created by furture on 16/6/23.
//  Copyright © 2016年 Evgenii Neumerzhitckii. All rights reserved.
//  二期计划，支持block，但是不推荐使用。




#import <Foundation/Foundation.h>
#import <objc/runtime.h>
#import <objc/message.h>

@interface JSEngine : NSObject



-(id)initEngine;



-(void)execute:(NSString*)script;


- (void)setObject:(id)object forKey:(NSString*)key;


- (id)objectForKey:(NSString*)key;


@end
