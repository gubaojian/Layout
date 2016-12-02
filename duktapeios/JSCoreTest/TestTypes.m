//
//  TestTypes.m
//  JSCoreTest
//
//  Created by furture on 16/6/30.
//  Copyright © 2016年 Evgenii Neumerzhitckii. All rights reserved.
//

#import "TestTypes.h"

@implementation TestTypes


-(NSString*)testString:(NSString*)value{
    NSLog(@"testString %@", value);
    return value;
}

-(NSNumber*)testNumber:(NSNumber*)value{
    NSLog(@"testString %@", value);
    return value;
}

-(CGRect)testCGRect:(CGRect)value{
    NSLog(@"testCGRect %@",  NSStringFromCGRect(value));
    return value;
}

-(void)testInt:(int)value{
    NSLog(@"testCGRect %d",  value);
}


-(BlockTest)getBlock{
    self.blockTest = ^(id key, id obj){
        NSLog(@"block test call block in getBlock");
    };
   return self.blockTest;
}

-(void)testBlock:(void (^)(id key, id obj))block{
   // NSLog(@"testBlock %p",  block);
}

@end
