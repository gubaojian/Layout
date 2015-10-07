//
//  GUViewBinder.h
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "UIView+MathLayout.h"

@interface DataBinder : NSObject


/**
 *  绑定数据对象，到指定的view
 */
+(void)bind:(id) data toView:(UIView*)view valueData:(void(^)(id valueData, UIView* view))valueCallback eventData:(void(^)(id eventData, UIView* view))eventCallback;




@end
