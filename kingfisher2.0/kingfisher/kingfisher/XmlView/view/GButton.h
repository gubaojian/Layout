//
//  GUButton.h
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UIView+MathLayout.h"

@interface GButton : UIButton

- (GButton*)initWithAttributes:(NSDictionary *)attrs;


-(void)layoutSubviews;

@end
