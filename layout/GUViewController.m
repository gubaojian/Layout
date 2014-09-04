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
    
    GUView* view = [[GUView alloc] initWithFrame:CGRectMake(20, 20, 120, 44)];
  
    view.backgroundImageView.backgroundColor = [UIColor blueColor];
    
    //UITextField*  field = [[UITextField alloc] init];
    //[field setText:@"ok"];
    //view.containerView = field;
   // [self.view addSubview:view];
    
    UIEdgeInsets  insets  = view.padding;
    insets.left = 10;
    view.padding = insets;
    
    
	// Do any additional setup after loading the view, typically from a nib.
    NSString * path = [[NSBundle mainBundle] pathForResource:@"layout" ofType:@"xml"];
    NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    
    UIView* xmlView = [[[GUViewInfalter alloc] init] toView:path];
    UIView* firstBanner = [xmlView viewWithTag:1];
    [firstBanner setClickBlock:^(UIView *view) {
         [[[UIAlertView alloc] initWithTitle:@"Click" message:@"Click" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil] show];
    }];
    
    [xmlView setClickBlock:^(UIView * view) {
        [[[UIAlertView alloc] initWithTitle:@"Click" message:@"Click" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil] show];
    }];
    
    [self.view addSubview:xmlView];
    
    
    NSLog(@"%@  %f", view, ( [[NSDate date] timeIntervalSinceReferenceDate] - start));

    
    
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
