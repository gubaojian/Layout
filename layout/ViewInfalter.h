//
//  GUViewInfalter.h
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GUViewFactory.h"
#import "GUTemplate.h"

@interface ViewInfalter : NSObject<NSXMLParserDelegate>{
    UIView*  xmlView;
    NSMutableDictionary* _localTemplates;
}

+ (id)shareViewInfalter;

-(id) toViewBundleFile:(NSString*) bundleFileName;

-(id) toViewTemplate:(GUTemplate*) viewTemplate;

-(void)registerTemplate:(GUTemplate*) localTemplate;


@end
