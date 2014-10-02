//
//  GUViewBinder.m
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "DataBinder.h"
#import "UIView+MathLayout.h"
#import "GUExpression.h"

@implementation DataBinder

+(void)bindView:(UIView*)view binderCallback:(BinderCallback*)binderCallback withData:(id) data{
    if([[view valueData] length] > 0){
        if ([[view valueData] hasPrefix:@"$"]) {
           id valueData =  [GUExpression valueForExpression:[view valueData] context:data];
            [binderCallback doBindValue:valueData toView:view];
        }
    }
    if ([[view eventData] length] > 0) {
        if ([[view eventData] hasPrefix:@"$"]) {
            id eventData =  [GUExpression valueForExpression:[view valueData] context:data];
            [binderCallback doBindEvent:eventData toView:view];
        }
    }
    NSArray* subviews = view.subviews;
    if (subviews == nil) {
        return;
    }
    for (UIView* subview in subviews) {
        [DataBinder bindView:subview binderCallback:binderCallback withData:data];
    }
}

@end
