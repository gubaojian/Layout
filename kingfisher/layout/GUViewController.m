//
//  GUViewController.m
//  layout
//
//  Created by lurina on 14-8-13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUViewController.h"
#import "GUView.h"
#import "GUButton.h"
#import "GUImageView.h"
#import "ViewInfalter.h"
#import "GUPerson.h"
#import "GUExpression.h"
#import "GUImageFetcher.h"
#import "GUXmlViewController.h"
#import "ViewDataBinder.h"

@interface GUViewController()

@property GUView*  node;

@end

@implementation GUViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    GUViewController* __weak weakSelf = self;
    [self.view setClickBlock:^(UIView *view) {
       GUXmlViewController* xmlViewContainner =  [[GUXmlViewController alloc] init];
       xmlViewContainner.xmlUrl = @"http://127.0.0.1:8080/market.xml";
       [weakSelf presentViewController:xmlViewContainner animated:YES completion:nil];
    }];
    
    GUImageView* button = [[GUImageView alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"100", @"y",
                                                             @"100", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"http://www.baidu.com/img/bd_logo.png", @"imageUrl", [self class], @"OK", nil]];
    
     [button setBackgroundColor:[UIColor redColor]];
     [self.view addSubview:button];
    
     NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    
     //used for development
     UIView* xmlView = [[ViewInfalter shareInflater] viewFromFile:@"Home.xml"];
     [self.view addSubview:xmlView];
    
    //used for product, suggest way, will download template automically
    [[ViewInfalter shareInflater] registerTemplate:[[GUTemplate alloc] initWithName:@"market" version:0]];
    UIView* marketView = [[ViewInfalter shareInflater] viewFromTemplate:[[GUTemplate alloc] initWithName:@"market" version:0 downloadUrl:@"http://127.0.0.1:8080/market.xml"]];
    marketView.autoresizingMask = UIViewAutoresizingNone;
    [self.view addSubview:marketView];
    
    BinderCallback* binderCallback = [[BinderCallback alloc] init];
    [ViewDataBinder doBindData:[NSDictionary dictionaryWithObjectsAndKeys:@"动态数据",@"name", nil] toView:marketView withCallback:binderCallback];
    
    
     NSLog(@"Inflate view used %f", ( [[NSDate date] timeIntervalSinceReferenceDate] - start));

    GUPerson* person = [[GUPerson alloc] init];
    person.name = @"name";
    person.skills = [[NSArray alloc] initWithObjects:@"Love", nil];
    start = [[NSDate date] timeIntervalSinceReferenceDate];
    int count = 1000;
    for (int i=0; i<count; i++) {
        [GUExpression valueForExpression:@"skills[10]" context:person];
    }
    NSLog(@"%d expression used %f", count, ( [[NSDate date] timeIntervalSinceReferenceDate] - start));
    
    
    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
