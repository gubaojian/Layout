//
//  HybirdView.m
//  kingfisher
//
//  Created by baobao on 15/10/6.
//  Copyright (c) 2015年 world. All rights reserved.
//

#import "GCellView.h"

@implementation GCellView




-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
}





-(void)setXmlView:(UIView *)xmlView{
    if (xmlView != nil) {
        self.frame = [xmlView frame];
    }else{
       CGRect frame =  self.frame;
       frame.size.height = 0;
        self.frame = frame;
    }
}

@end
