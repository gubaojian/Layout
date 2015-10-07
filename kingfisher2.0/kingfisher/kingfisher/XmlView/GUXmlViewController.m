//
//  GUTemplateViewController.m
//  layout
//
//  Created by jianbai on 14-10-2.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUXmlViewController.h"
#import "ViewInfalter.h"
#import "UIView+MathLayout.h"


@interface GUXmlViewController (){
    @private GScrollView* containerView;
}

@end

@implementation GUXmlViewController



-(void)loadView{
    [super loadView];
    [self  loadXmlView];
}


- (void)viewDidLoad
{
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    UISwipeGestureRecognizer* swipeGesture = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(handleSwipeGesture:)];
    [self.view addGestureRecognizer:swipeGesture];
    
    
    UITapGestureRecognizer* doubleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(loadXmlView)];
    [self.view addGestureRecognizer:doubleTap];
    // Do any additional setup after loading the view.
}


-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}





-(GScrollView*) containerView{
    if (containerView == nil) {
        containerView = [[GScrollView alloc] initWithAttributes:[NSDictionary dictionaryWithObjectsAndKeys:@"320", @"width",
                                                                 [NSString stringWithFormat:@"%f", self.view.frame.size.height], @"height",
                                                                 @"parent_height", @"expressionHeight",nil]];
        [self.view addSubview:containerView];
    }
    return containerView;
}

-(void)loadXmlView{
    if ([self xmlUrl]) {
        [[ViewInfalter shareInflater] viewFromUrl:[NSURL URLWithString:[self xmlUrl]] callback:^(UIView *xmlView) {
            [[self containerView] removeAllViews];
            if (xmlView != nil) {
                [[self containerView] addSubview:xmlView];
                NSLog(@"Load Xml View %@ Succss", [self xmlUrl]);
            }else{
                NSLog(@"Load Xml View %@ Failed", [self xmlUrl]);
            }
        }];
    }
}

-(void)handleSwipeGesture:(UISwipeGestureRecognizer*)swipeRecognizer{
    if ([swipeRecognizer direction] == UISwipeGestureRecognizerDirectionRight) {
        if (self.navigationController) {
            [self.navigationController popViewControllerAnimated:YES];
        }else{
            [self dismissViewControllerAnimated:YES completion:nil];
        }
        return;
    }
}


@end
