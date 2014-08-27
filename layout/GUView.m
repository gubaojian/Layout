//
//  GUView.m
//  layout
//
//  Created by lurina on 14-8-14.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUView.h"

@implementation GUView

@synthesize containerView = _containerView;

@synthesize padding = _padding;
@synthesize margin = _margin;
@synthesize backgroundImageView = _backgroundImageView;
@synthesize layoutParams = _layoutParams;

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

-(void)layoutSubviews{
    CGRect  bounds = [self bounds];
    bounds = UIEdgeInsetsInsetRect(bounds, _margin);
    if (_backgroundImageView) {
        [_backgroundImageView setFrame:bounds];
    }
    bounds = UIEdgeInsetsInsetRect(bounds, _padding);
    if (_containerView) {
        [_containerView setFrame:bounds];
    }
    
    [super layoutSubviews];
}


-(LayoutParams*) generateLayoutParams{
    LayoutParams* params = [[LayoutParams alloc] init];
    params.height = LayoutParams_WRAP_CONTENT;
    params.width = LayoutParams_WRAP_CONTENT;
    return params;
}


-(UIImageView*)backgroundImageView{
    if (_backgroundImageView == nil) {
        _backgroundImageView = [[UIImageView alloc] init];
        [self addSubview:_backgroundImageView];
    }
    return _backgroundImageView;
}



-(void) setContainerView:(UIView *)containerView{
    if (_containerView) {
        [_containerView removeFromSuperview];
        _containerView = nil;
    }
    _containerView = containerView;
    if (_containerView) {
        [self addSubview:_containerView];
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
