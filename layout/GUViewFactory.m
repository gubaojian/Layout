//
//  GUViewFactory.m
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUViewFactory.h"
#import "UIView+MathLayout.h"
#import "GUView.h"
#import "GUButton.h"
#import "GUImageView.h"
#import "GUTextView.h"

@implementation GUViewFactory


+ (id)shareFactory {
    static GUViewFactory *shareFactory = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        shareFactory = [[self alloc] init];
    });
    return shareFactory;
}

- (id)init {
    self = [super init];
    if (self) {
        [self registerClass:[GUButton class] forElement:@"Button"];
        [self registerClass:[GUTextView class] forElement:@"TextView"];
        [self registerClass:[GUImageView class] forElement:@"ImageView"];
        [self registerClass:[UIView class] forElement:@"View"];
    }
    return self;
}


-(void)registerClass:(Class) viewClass  forElement:(NSString*) element{
    [[self classMap] setObject:viewClass forKey:element];
}


-(NSMutableDictionary*)classMap{
    if (_classMap == nil) {
        _classMap = [[NSMutableDictionary alloc] initWithCapacity:8];
    }
    return _classMap;
}

- (UIView *)createViewWithElement:(NSString *)name attributes:(NSDictionary *)attrs{
    Class viewClass = [[self classMap] objectForKey:name];
    if (viewClass == NULL) {
        viewClass = NSClassFromString(name);
        
#ifndef PRODUCT
        if (viewClass == NULL) {
            @throw [NSException exceptionWithName:@"ClassNotFoundException" reason:[NSString stringWithFormat:@"Class %@ could not be found", name] userInfo:nil];
        }
        
        if (![viewClass isSubclassOfClass:[UIView class]]) {
            @throw [NSException exceptionWithName:@"InvalidViewClass" reason:[NSString stringWithFormat:@"Class %@ is not a view.", name] userInfo:nil];
        }
        if (![viewClass instancesRespondToSelector:@selector(initWithAttributes:)]) {
            @throw [NSException exceptionWithName:@"InvalidViewClass" reason:[NSString stringWithFormat:@"Class %@ could not be instantiated. Missing selector initWithAttributes:", name] userInfo:nil];
        }
#endif
    }
    return [[viewClass alloc] initWithAttributes:attrs];
}

@end
