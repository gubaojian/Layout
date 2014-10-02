//
//  GUDataBinder.h
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GUDataBinder : NSObject

-(BOOL)doBindData:(id)data toView:(UIView*)view;

-(BOOL)doBindEvent:(id)eventData toView:(UIView*) view;

@end
