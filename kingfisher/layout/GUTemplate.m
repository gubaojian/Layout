//
//  GUTemplate.m
//  layout
//
//  Created by jianbai on 14-9-10.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUTemplate.h"

@implementation GUTemplate

-(GUTemplate*)initWithName:(NSString*)name version:(NSInteger)version{
    return [self initWithName:name version:version downloadUrl:nil];
}

-(GUTemplate*)initWithName:(NSString*)name version:(NSInteger)version downloadUrl:(NSString*)downloadUrl{
    self = [super init];
    if (self) {
        self.name = name;
        self.version = version;
        self.downloadUrl = downloadUrl;
    }
    return self;

}

@end
