//
//  GTableView.m
//  kingfisher
//
//  Created by baobao on 15/10/6.
//  Copyright (c) 2015年 world. All rights reserved.
//

#import "GTableView.h"

@implementation GTableView

-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
}


- (void)updateViewFromAttributes:(NSDictionary *)attrs {
    [super updateViewFromAttributes:attrs];
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
