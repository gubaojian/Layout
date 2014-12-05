//
//  LoadMore.m
//  kingfisher
//
//  Created by jianbai on 14/12/3.
//  Copyright (c) 2014年 world. All rights reserved.
//

#import "LoadMore.h"

@implementation LoadMore

-(LoadMore*)init{
    self = [super init];
    if (self) {
        self.currentPage = 1;
        self.state = LOAD_MORE_STATE_INIT;
    }
    return self;
}

-(BOOL)loadMore{
    if (self.state == LOAD_MORE_STATE_INIT
        || self.state == LOAD_MORE_STATE_COMPELTE) {
        self.state = LOAD_MORE_STATE_LOADING;
        self.currentPage++;
        return YES;
    }else if (self.state == LOAD_MORE_STATE_ERROR){
        self.state = LOAD_MORE_STATE_LOADING;
        return true;
    }
    return false;
}

-(void)reset{
    self.currentPage = 1;
    self.state = LOAD_MORE_STATE_INIT;
}

@end
