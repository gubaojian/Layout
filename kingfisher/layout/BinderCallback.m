//
//  GUDataBinder.m
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "BinderCallback.h"

@implementation BinderCallback

-(BOOL)doBindValue:(id)data toView:(UIView*)view{
    if ([view isKindOfClass:[UILabel class]]) {
        [(UILabel*)view setText:data];
        return YES;
    }
    return NO;
}

-(BOOL)doBindEvent:(id)data toView:(UIView*) view{
    return NO;
}

-(BOOL)doBindChildView:(UIView*) view{
    return YES;
}

@end
