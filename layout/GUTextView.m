//
//  GUTextView.m
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUTextView.h"
#import "UIColor+HexString.h"

@implementation GUTextView

-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
}


- (void)updateViewFromAttributes:(NSDictionary *)attrs {
      [super updateViewFromAttributes:attrs];
    self.text = [attrs objectForKey:@"text"];
    
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
    
    NSString*  lineBreakModeString = [attrs objectForKey:@"lineBreakMode"];
    if (lineBreakModeString != nil) {
        NSLineBreakMode lineBreakMode = NSLineBreakByWordWrapping;
        if ([lineBreakModeString isEqualToString:@"wordWrap"]) {
            lineBreakMode = NSLineBreakByWordWrapping;
        }else if ([lineBreakModeString isEqualToString:@"charWrap"]) {
            lineBreakMode = NSLineBreakByCharWrapping;
        }else if ([lineBreakModeString isEqualToString:@"clip"]) {
            lineBreakMode = NSLineBreakByClipping;
        }else if ([lineBreakModeString isEqualToString:@"truncateHead"]) {
            lineBreakMode = NSLineBreakByTruncatingHead;
        }else if ([lineBreakModeString isEqualToString:@"truncateTail"]) {
            lineBreakMode = NSLineBreakByTruncatingTail;
        }else if ([lineBreakModeString isEqualToString:@"truncateMiddle"]) {
            lineBreakMode = NSLineBreakByTruncatingMiddle;
        }
        self.lineBreakMode = lineBreakMode;
    }
    
    NSString *numberOfLines= [attrs objectForKey:@"numberOfLines"];
    if (numberOfLines != nil) {
        self.numberOfLines = [numberOfLines integerValue];
    }
    
    NSString* textColor = [attrs objectForKey:@"textColor"];
    if (textColor != nil) {
        self.textColor = [UIColor colorWithHexString:@"textColor"];
    }
    
    NSString* highlightedTextColor = [attrs objectForKey:@"highlightedTextColor"];
    if (highlightedTextColor != nil) {
        self.highlightedTextColor = [UIColor colorWithHexString:@"highlightedTextColor"];
    }
    
    
    NSString *fontName = [attrs objectForKey:@"font"];
    NSString *textSize = [attrs objectForKey:@"textSize"];
    if (fontName != nil) {
        CGFloat size = self.font.pointSize;
        if (textSize != nil) size = [textSize floatValue];
        self.font = [UIFont fontWithName:fontName size:size];
    } else if (textSize != nil) {
        CGFloat size = [textSize floatValue];
        self.font = [UIFont systemFontOfSize:size];
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
