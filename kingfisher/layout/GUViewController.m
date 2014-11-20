//
//  GUViewController.m
//  layout
//
//  Created by lurina on 14-8-13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUViewController.h"
#import "GView.h"
#import "GButton.h"
#import "GImageView.h"
#import "ViewInfalter.h"
#import "GUPerson.h"
#import "GExpression.h"
#import "GImageFetcher.h"
#import "GUXmlViewController.h"
#import "ViewBinder.h"
#import "GHash.h"

@interface GUViewController()

@property GView*  node;

@end

@implementation GUViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    GUViewController* __weak weakSelf = self;
    [self.view setClickBlock:^(UIView *view) {
       GUXmlViewController* xmlViewContainner =  [[GUXmlViewController alloc] init];
       xmlViewContainner.xmlUrl = @"https://raw.githubusercontent.com/gubaojian/Layout/master/server/market.xml";
       [weakSelf presentViewController:xmlViewContainner animated:YES completion:nil];
    }];
    
    GImageView* button = [[GImageView alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"100", @"y",
                                                             @"100", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"http://www.baidu.com/img/bd_logo.png", @"imageUrl", [self class], @"OK", nil]];
    
     [button setBackgroundColor:[UIColor redColor]];
     [self.view addSubview:button];
    
     NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    
     //used for development
     UIView* xmlView = [[ViewInfalter shareInflater] viewFromFile:@"Home.xml"];
     [self.view addSubview:xmlView];
    
    //used for product, suggest way, will download template automically
    UIView* marketView = [[ViewInfalter shareInflater] viewFrom:@"market" version:0 downloadUrl:@"http://127.0.0.1:8080/market.xml"];
    marketView.autoresizingMask = UIViewAutoresizingNone;
    [self.view addSubview:marketView];
    
    BinderCallback* binderCallback = [[BinderCallback alloc] init];
    [ViewBinder doBind:[NSDictionary dictionaryWithObjectsAndKeys:@"动态数据",@"name", nil] toView:marketView withCallback:binderCallback];
    
    
     NSLog(@"Inflate view used %f", ( [[NSDate date] timeIntervalSinceReferenceDate] - start));

    GUPerson* person = [[GUPerson alloc] init];
    person.name = @"name";
    person.skills = [[NSArray alloc] initWithObjects:@"Love", nil];
    start = [[NSDate date] timeIntervalSinceReferenceDate];
    int count = 1000;
    for (int i=0; i<count; i++) {
        [GExpression valueForExpression:@"skills[10]" context:person];
    }
    NSLog(@"%d expression used %f", count, ( [[NSDate date] timeIntervalSinceReferenceDate] - start));
    
    
    
    for (int i=0; i<10; i++) {
        NSInteger hashCode = [GHash hashCode:[NSString stringWithFormat:@"%da", i]];
        NSLog(@"hash %d  %d ", hashCode, ([GHash hashMapCode:hashCode] & 7));
    }
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
