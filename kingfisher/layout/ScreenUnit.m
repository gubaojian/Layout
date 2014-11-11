//
//  ScreenUnit.m
//  kingfisher
//
//  Created by jianbai on 14-11-10.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "ScreenUnit.h"

@implementation ScreenUnit


+ (float) toUnit:(NSString*)unit{
    if ([unit hasSuffix:@"px"]) {
        unit = [unit stringByReplacingOccurrencesOfString:@"px" withString:@""];
        return [unit floatValue]/[UIScreen mainScreen].scale;
    }
    if ([unit hasSuffix:@"up"]) {
        unit = [unit stringByReplacingOccurrencesOfString:@"up" withString:@""];
    }
    return [unit floatValue]*[[UIScreen mainScreen] bounds].size.width/320.0f;
}
+ (float) toTextSize:(NSString*)textSize{
    return [textSize floatValue]*[[UIScreen mainScreen] bounds].size.width/320.0f;
}



@end
