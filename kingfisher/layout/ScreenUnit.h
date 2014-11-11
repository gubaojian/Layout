//
//  ScreenUnit.h
//  kingfisher
// https://developer.apple.com/library/ios/documentation/userexperience/conceptual/mobilehig/IconMatrix.html#//apple_ref/doc/uid/TP40006556-CH27-SW1
//  Created by jianbai on 14-11-10.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ScreenUnit : NSObject

+ (float) toUnit:(NSString*)unit;

+ (float) toTextSize:(NSString*)textSize;
@end
