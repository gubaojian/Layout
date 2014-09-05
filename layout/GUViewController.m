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
#import "GUViewInfalter.h"
#import "GUPerson.h"

@interface GUViewController()

@property GUView*  node;

@end

@implementation GUViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    
    GUImageView* button = [[GUImageView alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"20", @"x", @"100", @"y",
                                                             @"100", @"width", @"44", @"height", @"height * 4 ", @"expressionWidth", @"http://www.baidu.com/img/bd_logo.png", @"imageUrl", [self class], @"OK", nil]];
    
    //[button setTitle:@"ok" forState:UIControlStateNormal];
    //[button setBackgroundColor:[UIColor redColor]];
    //[button setFrame:CGRectMake(40, 240, 120, 40)];
    //[self.view addSubview:button];
    //[button layoutSubviews];
    NSExpression* expression = [NSExpression expressionWithFormat:@"3 + 2"];
    id value =  [expression expressionValueWithObject:[NSMutableDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithInt:4], @"screen_width", nil] context:nil];
    
    
    NSLog(@"expression %@ ", value);
    
    
    
	// Do any additional setup after loading the view, typically from a nib.
    NSString * path = [[NSBundle mainBundle] pathForResource:@"market" ofType:@"xml"];
    NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    
 
    UIView* xmlView = [[[GUViewInfalter alloc] init] toView:path];
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
    
    NSLog(@"%d ", [person respondsToSelector:NSSelectorFromString(@"name")]);

    
    NSLog(@"%@ ", [person valueForKeyPath:@"skills[10].333"]);
    NSLog(@"%@ ", [person valueForKeyPath:@"name.333"]);
    // NSLog(@"%@ ", [person valueForKeyPath:@"nameLove"]);
   
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
