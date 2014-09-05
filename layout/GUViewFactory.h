//
//  GUViewFactory.h
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GUViewFactory : NSObject{
     NSMutableDictionary* _classMap;
}

+(GUViewFactory*) shareFactory;

-(void)registerClass:(Class) viewClass  forElement:(NSString*) element;

- (UIView *)createViewWithElement:(NSString *)element attributes:(NSDictionary *)attrs;


@end
