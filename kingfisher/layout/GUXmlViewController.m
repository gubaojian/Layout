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
    @private UIView* containerView;
}

@end

@implementation GUXmlViewController

- (instancetype)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(templateDownloadSuccess:) name:TEMPLATE_DOWNLOAD_SUCCESS_NOTIFICATION object:nil];
    }
    return self;
}





- (void)viewDidLoad
{
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    UISwipeGestureRecognizer* swipeGesture = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(handleSwipeGesture:)];
    UITapGestureRecognizer* doubleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(loadXmlView)];
    [self.view addGestureRecognizer:swipeGesture];
    [self.view addGestureRecognizer:doubleTap];
    // Do any additional setup after loading the view.
}


-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self loadXmlView];
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

-(void) templateDownloadSuccess:(NSNotification*) notification{
    [self loadXmlView];
}

-(void) templateDownloadFailed:(NSNotification*) notification{
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Template Download Error" message:[[notification object] name] delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
    [alertView show];
}


-(UIView*) containerView{
    if (containerView == nil) {
        containerView = [[UIView alloc] initWithFrame:[[self view] bounds]];
        [self.view addSubview:containerView];
    }
    return containerView;
}

-(void)loadXmlView{
    if ([self xmlUrl]) {
        UIView* xmlView = [[ViewInfalter shareInflater] viewFromFile:[self xmlUrl]];
        if (xmlView != nil) {
            [[self containerView] removeAllViews];
            [[self containerView] addSubview:xmlView];
            NSLog(@"Load Xml View %@ Succss", [self xmlUrl]);
        }
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
    if ([swipeRecognizer direction] == UISwipeGestureRecognizerDirectionDown
         || [swipeRecognizer direction] == UISwipeGestureRecognizerDirectionUp) {
         [self loadXmlView];
    }
}


@end
