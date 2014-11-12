//
//  GUImageTask.h
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GUTask.h"

@interface GUImageTask : GUTask{
    @private NSString* _imageUrl;
    @private NSUInteger _taskId;
    @private __weak id _imageView;
    @private BOOL  highlighted;
}

-(GUImageTask*)initWithView:(UIView*)view imageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder;

-(GUImageTask*)initWithView:(UIView*)view imageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder highlighted:(BOOL)highlighted;



@end
