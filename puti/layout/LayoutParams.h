//
//  LayoutParams.h
//  layout
//
//  Created by lurina on 14-8-14.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

static const int LayoutParams_MATCH_PARENT = -1;

static const int LayoutParams_WRAP_CONTENT = -2;



@interface LayoutParams : NSObject

@property int  width;

@property int  height;

@end
