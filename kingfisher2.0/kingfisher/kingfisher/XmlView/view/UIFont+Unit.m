//
//  UIFont+Puti.m
//  layout
//
//  Created by jianbai on 14-9-5.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "UIFont+Unit.h"

@implementation UIFont (Unit)

+(UIFont*) fontWithStyle:(NSString*) fontStyle name:(NSString *)fontName size:(CGFloat)fontSize{
    if (fontStyle == nil && fontName == nil) {
        return [UIFont systemFontOfSize:fontSize];
    }
    if (fontStyle != nil) {
        NSString* fontStyleLowerCase = [fontStyle lowercaseString];
        if ([fontStyleLowerCase isEqualToString:@"bold"]) {
            return [UIFont boldSystemFontOfSize:fontSize];
        }else if([fontStyleLowerCase isEqualToString:@"italic"]){
            return [UIFont italicSystemFontOfSize:fontSize];
        }else{
           return [UIFont systemFontOfSize:fontSize];
        }
    }
    if (fontName != nil) {
        return [UIFont fontWithName:fontName size:fontSize];
    }
    return [UIFont systemFontOfSize:fontSize];
}

@end
