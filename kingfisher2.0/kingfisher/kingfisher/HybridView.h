//
//  HybridViewController.h
//  kingfisher
//
//  Created by jianbai on 14/12/3.
//  Copyright (c) 2014年 world. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <UIKit/UITableView.h>
#import "ViewBinder.h"
#import "LoadMore.h"

@interface HybridView : UIView<UITableViewDataSource, UITableViewDelegate>{
     @protected NSString* _baseUrl;
     @protected BinderCallback* _binderCallback;
     @protected UITableView* _tableView;
     @protected LoadMore* _loadMore;
}

@property (strong, nonatomic)NSString* baseUrl;

@property (strong, nonatomic)BinderCallback* binderCallback;

@property(strong, nonatomic)UITableView* tableView;

@property(strong,nonatomic)LoadMore* loadMore;

-(HybridView*)initWithBaseUrl:(NSString*)baseUrl;


-(void)loadData;

-(void)loadData:(NSString*)url forPage:(int)pageNum;

-(NSString*)buildPageUrl:(NSString*)url forPage:(int)pageNum;



@end
