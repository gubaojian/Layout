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
#import "GButton.h"


@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    ViewController* __weak weakSelf = self;
    
    
    GButton* button1 = [[GButton alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"40", @"y",
                                                            @"100", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"查看测试1", @"text",[self class], @"OK", nil]];
    
    [button1 setBackgroundColor:[UIColor lightGrayColor]];
    [button1 setClickBlock:^(UIView *view) {
        GUXmlViewController* xmlViewContainner =  [[GUXmlViewController alloc] init];
        xmlViewContainner.xmlUrl = @"bundle:///Home.xml";
        [weakSelf presentViewController:xmlViewContainner animated:YES completion:nil];
    }];
    [self.view addSubview:button1];
    
    GButton* button2 = [[GButton alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"120", @"y",
                                                            @"100", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"查看测试2", @"text",[self class], @"OK", nil]];
    
    [button2 setBackgroundColor:[UIColor lightGrayColor]];
    [button2 setClickBlock:^(UIView *view) {
        GUXmlViewController* xmlViewContainner =  [[GUXmlViewController alloc] init];
        xmlViewContainner.xmlUrl = @"bundle:///market.xml";
        [weakSelf presentViewController:xmlViewContainner animated:YES completion:nil];
    }];
    [self.view addSubview:button2];
    
    GButton* button3 = [[GButton alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"200", @"y",
                                                                 @"100", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"查看测试3", @"text",[self class], @"OK", nil]];
    
    [button3 setBackgroundColor:[UIColor lightGrayColor]];
    [button3 setClickBlock:^(UIView *view) {
        GUXmlViewController* xmlViewContainner =  [[GUXmlViewController alloc] init];
        xmlViewContainner.xmlUrl = @"https://raw.githubusercontent.com/gubaojian/Layout/master/server/market.xml";
        [weakSelf presentViewController:xmlViewContainner animated:YES completion:nil];
    }];
    [self.view addSubview:button3];
    
    
    /*
    
    GSwitch* switch3 = [[GSwitch alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"300", @"y",
                                                            @"200", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"查看测试3", @"text",[self class], @"OK", nil]];
    
    [switch3 setClickBlock:^(UIView *view) {
        GUXmlViewController* xmlViewContainner =  [[GUXmlViewController alloc] init];
        xmlViewContainner.xmlUrl = @"https://raw.githubusercontent.com/gubaojian/Layout/master/server/market.xml";
        [weakSelf presentViewController:xmlViewContainner animated:YES completion:nil];
    }];
    [self.view addSubview:switch3];
    
    */
    
    
    
    NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    
    //used for development
   // UIView* xmlView = [[ViewInfalter shareInflater] viewFromFile:@"bundle://Home.xml"];
    //[self.view addSubview:xmlView];
    
    /**
    //used for product, suggest way, will download template automically
    UIView* marketView = [[ViewInfalter shareInflater] viewFrom:@"market" downloadUrl:@"http://127.0.0.1:8080/market.xml"];
    marketView.autoresizingMask = UIViewAutoresizingNone;
    [self.view addSubview:marketView];
    
    BinderCallback* binderCallback = [[BinderCallback alloc] init];
    [ViewBinder doBind:[NSDictionary dictionaryWithObjectsAndKeys:@"动态数据",@"name", nil] toView:marketView withCallback:binderCallback];
    */
    
    NSLog(@"Inflate view used %f", ( [[NSDate date] timeIntervalSinceReferenceDate] - start));
    
    GUPerson* person = [[GUPerson alloc] init];
    person.name = @"name";
    person.skills = [[NSArray alloc] initWithObjects:@"Love", nil];
    start = [[NSDate date] timeIntervalSinceReferenceDate];
    int count = 1000;
    for (int i=0; i<count; i++) {
        [GExpression valueForEL:@"skills[10]" context:person];
        
    }
    NSLog(@"%d expression used %f", count, ( [[NSDate date] timeIntervalSinceReferenceDate] - start));
    
    
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
