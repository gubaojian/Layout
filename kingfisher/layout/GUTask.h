//
//  GUTask.h
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GUTask : NSObject{
    BOOL _cancel;
    id   _result;
}

-(void)cancel;
-(BOOL)isCancel;

-(void)doBackground;
-(void)onMain;
-(void)onCancel;
-(void)execute;
-(void)result;

@end
