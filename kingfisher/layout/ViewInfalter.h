//
//  GUViewInfalter.h
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GUViewFactory.h"

#define TEMPLATE_DOWNLOAD_SUCCESS_NOTIFICATION  @"PUTI_TEMPLATE_DOWNLOAD_SUCCESS_NOTIFICATION"
#define TEMPLATE_DOWNLOAD_FAILED_NOTIFICATION   @"PUTI_TEMPLATE_DOWNLOAD_FAILED_NOTIFICATION"

@interface ViewInfalter : NSObject<NSXMLParserDelegate>{
    UIView*  viewNode;
    UIView*  rootView;
}

+ (id)shareInflater;

-(id) viewFrom:(NSString*) name version:(int)version downloadUrl:(NSString*)downloadUrl;

/**
 * fileName bundleName or HttpUrl
 */
-(id) viewFromFile:(NSString*) fileName;


@end
