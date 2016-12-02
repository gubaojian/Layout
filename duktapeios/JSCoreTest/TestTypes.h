//
//  TestTypes.h
//  JSCoreTest
//
//  Created by furture on 16/6/30.
//  Copyright © 2016年 Evgenii Neumerzhitckii. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void(^BlockTest)(id key, id obj);


@interface TestTypes : NSObject

@property(nonatomic) BlockTest blockTest;


-(NSString*)testString:(NSString*)value;

-(NSNumber*)testNumber:(NSNumber*)value;

-(CGRect)testCGRect:(CGRect)value;

-(void)testInt:(int)value;


-(BlockTest)getBlock;

-(void)testBlock:(void (^)(id key, id obj))block;

@end
