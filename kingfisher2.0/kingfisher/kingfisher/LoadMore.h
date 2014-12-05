//
//  LoadMore.h
//  kingfisher
//
//  Created by jianbai on 14/12/3.
//  Copyright (c) 2014年 world. All rights reserved.
//

#import <Foundation/Foundation.h>

#define LOAD_MORE_STATE_INIT   0
#define LOAD_MORE_STATE_LOADING   1
#define LOAD_MORE_STATE_COMPELTE   2
#define LOAD_MORE_STATE_ERROR   -1
#define LOAD_MORE_STATE_NONE_MORE   3

@interface LoadMore : NSObject


@property int currentPage;

@property int state;

-(LoadMore*)init;

-(BOOL)loadMore;

-(void)reset;



@end
