//
//  GTextField.m
//  kingfisher
//
//  Created by baobao on 15/10/6.
//  Copyright (c) 2015年 world. All rights reserved.
//

#import "GTextField.h"

@implementation GTextField

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/


-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
}



- (void)updateViewFromAttributes:(NSDictionary *)attrs {
    [super updateViewFromAttributes:attrs];
    self.text = [attrs objectForKey:@"text"];
    
    NSString* textColor = [attrs objectForKey:@"textColor"];
    if (textColor != nil) {
        self.textColor = [UIColor colorWithHexString:@"textColor"];
    }
    
    NSString* placeHolder = [attrs objectForKey:@"hint"];
    if (placeHolder != nil) {
        self.placeholder = placeHolder;
    }
    
    
    NSString* fontStyle = [attrs objectForKey:@"fontStyle"];
    NSString *fontName = [attrs objectForKey:@"font"];
    NSString *textSize = [attrs objectForKey:@"textSize"];
    if (textSize != nil
        ||  fontName != nil
        || fontStyle != nil) {
        CGFloat size = self.font.pointSize;
        if (textSize != nil) size = [ScreenUnit toTextSize:textSize];
        self.font = [UIFont fontWithStyle:fontStyle name: fontName size:size];
    }
    
    
    NSString* alignment = [attrs objectForKey:@"textAlignment"];
    if (alignment != nil) {
        NSTextAlignment textAlignment = NSTextAlignmentLeft;
        if ([alignment isEqualToString:@"left"]) {
            textAlignment = NSTextAlignmentLeft;
        }else if ([alignment isEqualToString:@"center"]){
            textAlignment = NSTextAlignmentCenter;
        }else if ([alignment isEqualToString:@"right"]){
            textAlignment = NSTextAlignmentRight;
        }
        self.textAlignment = textAlignment;
    }
}



@end
