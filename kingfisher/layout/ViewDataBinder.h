//
//  GUViewBinder.h
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BinderCallback.h"

@interface ViewDataBinder : NSObject

+(void)doBindData:(id) data toView:(UIView*)view withCallback:(BinderCallback*)binderCallback;

@end
