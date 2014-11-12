//
//  GUTask.m
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUTask.h"

@implementation GUTask

-(void)cancel{
    _cancel = YES;
}
-(BOOL)isCancel{
    return _cancel;
}

-(void)doBackground{

}
-(void)onMain{

}
-(void)onCancel{

}

-(void)execute{
    __weak  GUTask* weakSelf = self;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        __strong  GUTask* strongSelf = weakSelf;
        if ([strongSelf isCancel]) {
            return;
        }
        [strongSelf doBackground];
        if ([strongSelf isCancel]) {
            [strongSelf onCancel];
        }else{
            dispatch_async(dispatch_get_main_queue(), ^{
                if ([strongSelf isCancel]) {
                    [strongSelf onCancel];
                }else{
                    [strongSelf onMain];
                }
            });
        }
    });
}

-(void)result;



@end
