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

@interface GUViewController()

@property GUView*  node;

@end

@implementation GUViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    
    
    GUView* view = [[GUView alloc] initWithFrame:CGRectMake(20, 20, 120, 44)];
    view.backgroundImageView.backgroundColor = [UIColor blueColor];
    
    UITextField*  field = [[UITextField alloc] init];
    [field setText:@"ok"];
    view.containerView = field;
    [self.view addSubview:view];
    
    UIEdgeInsets  insets  = view.padding;
    insets.left = 10;
    view.padding = insets;
    
    [view addConstraint:<#(NSLayoutConstraint *)#>];
    
	// Do any additional setup after loading the view, typically from a nib.
    NSString * path = [[NSBundle mainBundle] pathForResource:@"ReplativeLayout" ofType:@"xml"];
    NSLog(@"path %@", path);
    NSXMLParser* parser = [[NSXMLParser alloc] initWithStream:[[NSInputStream alloc] initWithFileAtPath:path]];
    NSTimeInterval start = [[NSDate date] timeIntervalSinceReferenceDate];
    parser.delegate = self;
    
    
    [parser parse];
    
    NSLog(@"%@  %f", [parser parserError], ( [[NSDate date] timeIntervalSinceReferenceDate] - start));
    
    
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
    
    NSLog(@"%@  %@ %@", elementName, qName, attributeDict);


}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName{
    NSLog(@"end %@  %@ ", elementName, qName);
    

}

@end
