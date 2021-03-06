//
//  GUViewFactory.h
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "UIView+MathLayout.h"
#import "GView.h"
#import "GButton.h"
#import "GImageView.h"
#import "GTextView.h"
#import "GTableView.h"
#import "GScrollView.h"
#import "GTextField.h"
#import "GSwitch.h"

@interface GViewFactory : NSObject{
     NSMutableDictionary* _classMap;
}

+(GViewFactory*) shareFactory;

-(void)registerClass:(Class) viewClass  forElement:(NSString*) element;

- (UIView *)createViewWithElement:(NSString *)element attributes:(NSDictionary *)attrs;


@end
