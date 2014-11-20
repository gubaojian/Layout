//
//  ViewController.m
//  kingfisher
//
//  Created by jianbai on 14/11/18.
//  Copyright (c) 2014年 world. All rights reserved.
//

#import "ViewController.h"
#import "GUXmlViewController.h"
#import "UIView+MathLayout.h"
#import "GUPerson.h"
#import "GImageView.h"
#import "ViewInfalter.h"
#import "GExpression.h"
#import "GHash.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    ViewController* __weak weakSelf = self;
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
    UIView* marketView = [[ViewInfalter shareInflater] viewFrom:@"market" downloadUrl:@"http://127.0.0.1:8080/market.xml"];
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
        NSLog(@"hash %ld  %lu ", (long)hashCode, ([GHash hashMapCode:hashCode] & 7));
    }

    
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
