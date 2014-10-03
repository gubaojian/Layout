//
//  GUDataBinder.h
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BinderCallback : NSObject

-(BOOL)doBindValue:(id)data toView:(UIView*)view;

-(BOOL)doBindEvent:(id)data toView:(UIView*) view;

@end
