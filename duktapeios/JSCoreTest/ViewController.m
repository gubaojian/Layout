//
//  ViewController.m
//  JSCoreTest
//
//  Created by Evgenii Neumerzhitckii on 3/11/2013.
//  Copyright (c) 2013 Evgenii Neumerzhitckii. All rights reserved.
//

#import "ViewController.h"
#import <JavaScriptCore/JavaScriptCore.h>
#import <objc/runtime.h>
#import <objc/message.h>
#import "JSEngine.h"
#import "TestTypes.h"

@interface ViewController ()  <UITextViewDelegate>

@property (weak, nonatomic) IBOutlet UILabel *resultLabel;
@property (weak, nonatomic) IBOutlet UITextView *javascriptText;
@property (weak, nonatomic) IBOutlet UITextField *argumentText;




@end

@implementation ViewController


#define OBJECT_MARK     @"__ob"

#define OBJECT_MARK_VALUE     @"y"

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.javascriptText.text = [self loadJsFromFile];
    [self runJavaScript];
}

- (NSString *)loadJsFromFile
{
    NSString *path = [[NSBundle mainBundle] pathForResource:@"test" ofType:@"js"];
    NSString *jsScript = [NSString stringWithContentsOfFile:path encoding:NSUTF8StringEncoding error:nil];
    return jsScript;
}


-(void) alert:(NSString*)alert{
    UIAlertController * alertController = [UIAlertController alertControllerWithTitle:nil message:alert preferredStyle:UIAlertControllerStyleAlert];
    //添加Button
    [alertController addAction: [UIAlertAction actionWithTitle: @"确定" style: UIAlertActionStyleDestructive handler:nil]];

    [alertController addAction: [UIAlertAction actionWithTitle: @"取消" style: UIAlertActionStyleCancel handler:nil]];
    
    [self presentViewController: alertController animated: YES completion: nil];
}

- (void)runJavaScript
{
    
    
    
    //NSLog(@"xxxx  %s  %s", @encode(NSString),  @encode(self));
    

    
    JSEngine  *context = [[JSEngine alloc] initEngine];
    TestTypes *typesTest = [[TestTypes alloc] init];
    

    [context setObject:self forKey:@"controller"];
    
    [context setObject:typesTest forKey:@"typesTest"];
    
    
    BlockTest blockTest = [typesTest getBlock];
    
    blockTest(@"100", @"100");
    
    
    /**
    context[@"alert"] = ^(NSString* content){
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"msg from js" message: content delegate:nil cancelButtonTitle:@"ok" otherButtonTitles:nil, nil];
        [alert show];
    };
    
    context[@"log"] = ^(){
        JSValue* value = [[JSContext currentArguments] objectAtIndex:0];
        if([value isNull]){
            NSLog(@"log nil");
        }else{
            NSLog(@"log %@", [value toString]);
        }
    };*/
    
    
    
    //[self.machines addObject:context];
    [context execute: self.javascriptText.text];
    //self.resultLabel.text = [result toString];

    
    /**
    self.machines = [[NSMutableArray alloc] init];
    for(int i=0; i<30; i++){
        JSEngine  *context = [[JSEngine alloc] init];
        
        [context engineInit];
        
        
        
        
        context[@"alert"] = ^(NSString* content){
            UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"msg from js" message: content delegate:nil cancelButtonTitle:@"ok" otherButtonTitles:nil, nil];
            [alert show];
        };
        
        context[@"log"] = ^(){
            JSValue* value = [[JSContext currentArguments] objectAtIndex:0];
            if([value isNull]){
                NSLog(@"log nil");
            }else{
                NSLog(@"log %@", [value toString]);
            }
        };
        
        
        
        [self.machines addObject:context];
        JSValue *result = [context evaluateScript: self.javascriptText.text];
        self.resultLabel.text = [result toString];
    
    }*/
   
    /**
    
    JSValue *result = [context evaluateScript: self.javascriptText.text];
    self.resultLabel.text = [result toString];
    
    NSLog(@"%@ ", [result toString]);
    
    
    */
    
}

- (void)msgSend:(int)num{
    NSLog(@"msgSendmsgSendmsgSendmsgSendmsgSendmsgSend %d", num);
    
}

- (void)textViewDidChange:(UITextView *)textView
{
    
    
    
    UITableView* tableView  = [[UITableView alloc] init];
    
    //[tableView rowHeight];
    
    
    //[tableView frame];
    
    //CGRect frame = [tableView frame];
    
    UIView *view =  [[UIView alloc] init];
    
    [tableView addSubview:view];
    //id f = frame;
    
   // NSLog(@"frame %@", frame);
    
    
    
    [self description];
    
    [self runJavaScript];
    
    
   
    
  
  
    /**
    objc_msgSend(self, @selector(msgSend:),  33);
    
    objc_msgSend(self, @selector(msgSend:),  (int)[@"4444" doubleValue]);
    
    
    objc_msgSend(self, @selector(msgSend:),  333.44);
     */
}

- (IBAction)argumentValueEditingChanged:(id)sender {
    [self runJavaScript];
}

@end
