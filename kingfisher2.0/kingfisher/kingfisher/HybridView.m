//
//  HybridViewController.m
//  kingfisher
//
//  Created by jianbai on 14/12/3.
//  Copyright (c) 2014年 world. All rights reserved.
//

#import "HybridView.h"

@interface HybridView ()

@end

@implementation HybridView

@synthesize baseUrl = _baseUrl;

@synthesize binderCallback = _binderCallback;

@synthesize tableView = _tableView;

@synthesize loadMore = _loadMore;

-(HybridView*)initWithBaseUrl:(NSString*)baseUrl{
    self = [super init];
    if (self) {
        self.baseUrl = baseUrl;
    }
    return self;
}

-(void)loadData{
    [self loadData:_baseUrl forPage:[[self loadMore] currentPage]];
}

-(void)loadData:(NSString*)url forPage:(int)pageNum{
    
}

-(NSString*)buildPageUrl:(NSString*)url forPage:(int)pageNum{
    if ([url hasPrefix:@"http"]) {
        NSURL* pageUrl = [NSURL URLWithString:url];
        NSString* querys = pageUrl.query;
        if (querys.length <= 0) {

        }
    }else{
        return [url stringByAppendingFormat:@"%d", pageNum];
    }
    return @"";
}



-(LoadMore*)loadMore{
    if (_loadMore == nil) {
        _loadMore = [[LoadMore alloc] init];
    }
    return _loadMore;
}

-(BinderCallback*)binderCallback{
    if (_binderCallback == nil) {
        _binderCallback = [[BinderCallback alloc] init];
    }
    return _binderCallback;
}

-(UITableView*)tableView{
    if (_tableView == nil) {
        _tableView = [[UITableView alloc] initWithFrame:[self bounds]];
        [self addSubview:_tableView];
    }
    return _tableView;
}



/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
