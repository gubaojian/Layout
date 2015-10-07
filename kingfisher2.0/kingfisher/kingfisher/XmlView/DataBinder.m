//
//  GUViewBinder.m
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "DataBinder.h"
#import "UIView+MathLayout.h"
#import "GExpression.h"

@implementation DataBinder


+(void)bind:(id) data toView:(UIView*)view valueData:(void(^)(id valueData, UIView* view))valueCallback eventData:(void(^)(id eventData, UIView* view))eventCallback{
    if([[view valueData] length] > 0){
        if ([[view valueData] hasPrefix:@"$"]) {
            id valueData =  [GExpression valueForEL:[view valueData] context:data];
            valueCallback(valueData, view);
        }
    }
    if ([[view eventData] length] > 0) {
        if ([[view eventData] hasPrefix:@"$"]) {
            id eventData =  [GExpression valueForEL:[view valueData] context:data];
            eventCallback(eventData, view);
        }
    }
    NSArray* subviews = view.subviews;
    if (subviews == nil) {
        return;
    }
    for (UIView* subview in subviews) {
        [DataBinder bind:data toView:subview valueData:valueCallback eventData:eventCallback];
    }
}

@end
