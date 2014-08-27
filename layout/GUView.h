//
//  GUView.h
//  layout
//
//  Created by lurina on 14-8-14.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LayoutParams.h"


@interface GUView : UIView{
    @private  UIImageView* _backgroundImageView;
    @private  UIView*  _containerView;
    @private  UIEdgeInsets   _padding;
    @private  UIEdgeInsets  _margin;
    @private  LayoutParams* _layoutParams;
}

@property(nonatomic, readonly) UIImageView* backgroundImageView;

@property(nonatomic) UIView*  containerView;

@property(nonatomic) LayoutParams* layoutParams;

@property(nonatomic) UIEdgeInsets padding;

@property(nonatomic) UIEdgeInsets margin;

-(LayoutParams*) generateLayoutParams;

@end
