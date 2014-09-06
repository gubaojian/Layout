//
//  GUExpression.m
//  layout
//
//  Created by lurina on 14-9-6.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUExpression.h"

@implementation GUExpression


+(id) valueForExpression:(NSString*)expression context:(id)object{
    if (expression == nil  || object == nil) {
        return nil;
    }
    id value = object;
    @try {
        NSCharacterSet* operatorSet = [NSCharacterSet characterSetWithCharactersInString:@"@{}.[]$ "];
        NSArray*  components = [expression componentsSeparatedByCharactersInSet:operatorSet];
        id value = object;
        for (NSString*  component in  components) {
            if (component.length == 0) {
                continue;
            }
            value = [GUExpression valueForComponent: component context:value];
        }
        if (value == object) {
            value = nil;
        }
    }
    @catch (NSException *exception) {
        value = nil;
#ifndef PRODUCTION
        NSLog(@"Expression %@ Error %@", expression, [exception description]);
#endif
    }
    return value;
}


+(id) valueForComponent:(NSString*)expression context:(id)object{
    if (object == nil) {
        return nil;
    }
    
    if ([object isKindOfClass:[NSArray class]]) {
        NSInteger index = [expression integerValue];
        if (0<= index && index < [object count]) {
            return [object objectAtIndex:index];
        }
        return nil;
    }
    if ([object isKindOfClass:[NSDictionary class]]) {
        return [object objectForKey:expression];
    }
    
    SEL sel = NSSelectorFromString(expression);
    if ([object respondsToSelector:sel]) {
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"
        return [object performSelector:sel withObject:object];
#pragma clang diagnostic pop
    }
    return nil;
    
    return nil;
}


@end
