//
//  GScrollView.m
//  kingfisher
//
//  Created by baobao on 15/10/6.
//  Copyright (c) 2015年 world. All rights reserved.
//

#import "GScrollView.h"
#import "UIView+MathLayout.h"

@implementation GScrollView

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
}

@end
