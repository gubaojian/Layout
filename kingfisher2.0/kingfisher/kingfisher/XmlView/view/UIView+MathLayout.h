//
//  UIView+MathLayout.h
//  layout
//
//  Created by jianbai on 14-9-2.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UIColor+HexString.h"
#import "UIFont+Unit.h"
#import "ScreenUnit.h"


/**
  *   View 加载XML
  *   数据绑定KVC
  */

@interface UIView (MathLayout)


@property (nonatomic, strong) NSExpression*  expressionX;
@property (nonatomic, strong) NSExpression*  expressionY;
@property (nonatomic, strong) NSExpression*  expressionWidth;
@property (nonatomic, strong) NSExpression*  expressionHeight;
@property (nonatomic, strong) NSMutableDictionary*  expressionContext;

@property (nonatomic, strong) NSString*  valueData;
@property (nonatomic, strong) NSString*  eventData;

@property (nonatomic, copy) void (^clickBlock)(UIView*);
@property (nonatomic, strong) UITapGestureRecognizer*  tapGestureRecognizer;

- (id)initWithAttributes:(NSDictionary *)attrs;

- (void)updateViewFromAttributes:(NSDictionary *)attrs;

-(void)layoutWithMathExpression;

-(void)removeAllViews;


@end
