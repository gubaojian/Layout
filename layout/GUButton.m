//
//  GUButton.m
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUButton.h"
#import "UIView+MathLayout.h"

@implementation GUButton

-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
    
}


- (GUButton*)initWithAttributes:(NSDictionary *)attrs {
    self = [GUButton buttonWithType:UIButtonTypeCustom];
    if (self) {
        [self updateViewFromAttributes:attrs];
    }
    return self;
}

- (void)updateViewFromAttributes:(NSDictionary *)attrs {
    [super updateViewFromAttributes:attrs];
    NSString *paddingString = [attrs objectForKey:@"padding"];
    if (paddingString != nil) {
        CGFloat padding = [paddingString floatValue];
       self.imageEdgeInsets = UIEdgeInsetsMake(padding, padding, padding, padding);
    }
    UIEdgeInsets padding = self.imageEdgeInsets;
    NSString *paddingTopString = [attrs objectForKey:@"paddingTop"];
    NSString *paddingLeftString = [attrs objectForKey:@"paddingLeft"];
    NSString *paddingBottomString = [attrs objectForKey:@"paddingBottom"];
    NSString *paddingRightString = [attrs objectForKey:@"paddingRight"];
    if ([paddingTopString length] > 0) {
        padding.top = [paddingTopString floatValue];
    }
    if ([paddingLeftString length] > 0){
        padding.left = [paddingLeftString floatValue];
    }
    if ([paddingBottomString length] > 0){
        padding.bottom = [paddingBottomString floatValue];
    }
    if ([paddingRightString length] > 0){
        padding.right = [paddingRightString floatValue];
    }
     self.imageEdgeInsets = padding;
}

-(void)addSubview:(UIView *)view{
    [super addSubview:view];
}



@end
