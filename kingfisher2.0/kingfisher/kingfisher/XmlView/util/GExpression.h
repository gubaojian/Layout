//
//  GUExpression.h
//  layout
//
//  Created by lurina on 14-9-6.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GExpression : NSObject

+(id) valueForEL:(NSString*)expression context:(id)object;

@end
