//
//  GUViewInfalter.h
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GUViewFactory.h"

@interface GUViewInfalter : NSObject<NSXMLParserDelegate>{
    UIView*  xmlView;
}

-(id) toView:(NSString*)filePath;

@end
