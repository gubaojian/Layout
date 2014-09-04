//
//  GUViewInfalter.m
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUViewInfalter.h"

@implementation GUViewInfalter



-(id) toView:(NSString*)filePath{
   NSXMLParser* xmlParser = [[NSXMLParser alloc] initWithStream:[NSInputStream inputStreamWithFileAtPath:filePath]];
    xmlParser.delegate = self;
    [xmlParser parse];
    UIView* view = xmlView;
    xmlView = nil;
    return view;
}

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict{
    UIView* elementView = [[GUViewFactory shareFactory] createViewWithElement:elementName attributes:attributeDict];
    if (xmlView != nil) {
        [xmlView addSubview:elementView];
    }
    xmlView = elementView;
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName{
    if (xmlView.superview != nil) {
        xmlView = xmlView.superview;
    }
}






@end
