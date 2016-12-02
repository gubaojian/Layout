//
//  JSRef.m
//  JSCoreTest
//
//  Created by furture on 16/6/27.
//  Copyright © 2016年 Evgenii Neumerzhitckii. All rights reserved.
//

#import "JSRef.h"

@implementation JSRef


-(id)initWithRef:(int)jsRef engine:(JSEngine*) engine{
    self = [super init];
    if(self){
        self.jsRef = jsRef;
        self.engine = engine;
    }
    return self;
}


@end
