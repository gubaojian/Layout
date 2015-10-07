//
//  GUViewInfalter.h
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "GViewFactory.h"
#import "UIView+MathLayout.h"

@interface ViewInfalter : NSObject<NSXMLParserDelegate>{
    UIView*  viewNode;
    UIView*  rootView;
}

+ (id)shareInflater;


-(id) viewFromInputStream:(NSInputStream*) inputStream;

-(void)viewFromUrl:(NSURL*)url callback:(void(^)(UIView*)) callbackBlock;


@end
