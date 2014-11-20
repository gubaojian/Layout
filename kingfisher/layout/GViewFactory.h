//
//  GUViewFactory.h
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GViewFactory : NSObject{
     NSMutableDictionary* _classMap;
}

+(GViewFactory*) shareFactory;

-(void)registerClass:(Class) viewClass  forElement:(NSString*) element;

- (UIView *)createViewWithElement:(NSString *)element attributes:(NSDictionary *)attrs;


@end
