//
//  GUViewBinder.h
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BinderCallback.h"

@interface DataBinder : NSObject

+(void)bindView:(UIView*)view binderCallback:(BinderCallback*)binderCallback withData:(id) data;

@end
