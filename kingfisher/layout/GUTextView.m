//
//  GUTextView.m
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUTextView.h"
#import "UIColor+HexString.h"
#import "UIFont+Puti.h"
#import "ScreenUnit.h"

@implementation GUTextView

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
    
    NSString* highlightedTextColor = [attrs objectForKey:@"highlightedTextColor"];
    if (highlightedTextColor != nil) {
        self.highlightedTextColor = [UIColor colorWithHexString:@"highlightedTextColor"];
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
    
    
    NSString *numberOfLines= [attrs objectForKey:@"numberOfLines"];
    if (numberOfLines != nil) {
        self.numberOfLines = [numberOfLines integerValue];
    }

    
    NSString*  lineBreakModeString = [attrs objectForKey:@"lineBreakMode"];
    if (lineBreakModeString != nil) {
        NSLineBreakMode lineBreakMode = NSLineBreakByWordWrapping;
        if ([lineBreakModeString isEqualToString:@"wordWrap"]) {
            lineBreakMode = NSLineBreakByWordWrapping;
        }else if ([lineBreakModeString isEqualToString:@"charWrap"]) {
            lineBreakMode = NSLineBreakByCharWrapping;
        }else if ([lineBreakModeString isEqualToString:@"clip"]) {
            lineBreakMode = NSLineBreakByClipping;
        }else if ([lineBreakModeString isEqualToString:@"head"]) {
            lineBreakMode = NSLineBreakByTruncatingHead;
        }else if ([lineBreakModeString isEqualToString:@"tail"]) {
            lineBreakMode = NSLineBreakByTruncatingTail;
        }else if ([lineBreakModeString isEqualToString:@"middle"]) {
            lineBreakMode = NSLineBreakByTruncatingMiddle;
        }
        self.lineBreakMode = lineBreakMode;
    }
   
    
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
