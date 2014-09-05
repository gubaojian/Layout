//
//  GUView.m
//  layout
//
//  Created by lurina on 14-8-14.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUView.h"
#import "UIView+MathLayout.h"

@implementation GUView


-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
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
