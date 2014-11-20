//
//  GUImageTask.h
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GImageTask : NSObject{
    @private NSString* _imageUrl;
    @private NSString* _placeHolder;
    @private __weak UIView* _imageView;
    @private BOOL _highlighted;
    @private BOOL _cancel;
}

-(GImageTask*)initWithView:(UIView*)view imageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder;

-(GImageTask*)initWithView:(UIView*)view imageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder highlighted:(BOOL)highlighted;

-(void)cancel;
-(BOOL)isCancel;
-(void)execute;

-(NSString*)imageUrl;
-(NSString*)placeHolder;
-(UIView*)imageView;
-(BOOL) highlighted;


@end
