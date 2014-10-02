//
//  GUViewBinder.h
//  layout
//
//  Created by jianbai on 14-9-11.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GUDataBinder.h"

@interface GUViewBinder : NSObject

+(void)bindView:(UIView*)view binder:(GUDataBinder*)dataBinder withData:(id) data;

@end
