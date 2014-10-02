//
//  GUViewController.m
//  layout
//
//  Created by lurina on 14-8-13.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUViewController.h"
#import "GUView.h"
#import "RelativeLayout.h"
#import "GUButton.h"
#import "GUImageView.h"
#import "ViewInfalter.h"
#import "GUPerson.h"
#import "GUExpression.h"
#import "GUImageFetcher.h"
#import "GUXmlViewController.h"

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
    
    //[button setTitle:@"ok" forState:UIControlStateNormal];
    //[button setBackgroundColor:[UIColor redColor]];
    //[button setFrame:CGRectMake(40, 240, 120, 40)];
    //[self.view addSubview:button];
    //[button layoutSubviews];
    NSExpression* expression = [NSExpression expressionWithFormat:@"3 + 2"];
     NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    
 
    UIView* xmlView = [[ViewInfalter shareInflater] viewFromFile:@"market.xml"];
    /**
    UIView* firstBanner = [xmlView viewWithTag:1];
    [firstBanner setClickBlock:^(UIView *view) {
         [[[UIAlertView alloc] initWithTitle:@"Click" message:@"Click" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil] show];
    }];
    */
    //[xmlView setClickBlock:^(UIView * view) {
       // [[[UIAlertView alloc] initWithTitle:@"Click" message:@"Click" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil] show];
    //}];
   
    [self.view addSubview:xmlView];
    
    
    NSLog(@"%@  %f", xmlView, ( [[NSDate date] timeIntervalSinceReferenceDate] - start));

    GUPerson* person = [[GUPerson alloc] init];
    person.name = @"name";
    person.skills = [[NSArray alloc] initWithObjects:@"Love", nil];
    start = [[NSDate date] timeIntervalSinceReferenceDate];
    
    for (int i=0; i<1000; i++) {
        [GUExpression valueForExpression:@"skills[10]" context:person];
    }

    NSLog(@"%@  %f", xmlView, ( [[NSDate date] timeIntervalSinceReferenceDate] - start));

  //  NSLog(@"%@ ", [person valueForKeyPath:@"skills[10].333"]);
  //  NSLog(@"%@ ", [person valueForKeyPath:@"name.333"]);
   
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict{
    GUView* parserNode = nil;
    if ([elementName isEqualToString:@"RelativeLayout"]) {
        parserNode = [[RelativeLayout alloc] init];
    }
    
    NSLog(@"%@  %@ ", elementName, attributeDict);


}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName{
    NSLog(@"end %@  %@ ", elementName, qName);
    

}

@end