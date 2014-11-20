//
//  GUImageTask.m
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GImageTask.h"
#import "GImageFetcher.h"
#import "UIView+ImageTask.h"

@implementation GImageTask


-(GImageTask*)initWithView:(UIView*)view imageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder{
    return [self initWithView:view imageUrl:imageUrl placeHolder:placeHolder highlighted:NO];
}

-(GImageTask*)initWithView:(UIView*)view imageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder highlighted:(BOOL)highlighted{
    self = [super init];
    if (self) {
        _imageUrl = imageUrl;
        _imageView = view;
        _placeHolder = placeHolder;
        _highlighted = highlighted;
    }
    return self;
}


-(void)cancel{
    _cancel = YES;
}
-(BOOL)isCancel{
    if (_imageView) {
        return _cancel;
    }
    return YES;
}

-(NSString*)imageUrl{
    return _imageUrl;
}
-(NSString*)placeHolder{
    return _placeHolder;
}
-(UIView*)imageView{
    return _imageView;
}
-(BOOL) highlighted{
    return _highlighted;
}

-(void)execute{
    __weak  GImageTask* weakSelf = self;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        __strong  GImageTask* strongSelf = weakSelf;
        if ([strongSelf isCancel]) {
            return;
        }
        UIImage* placeHolder = [[GImageFetcher shareFetcher] imageFromUrl:[strongSelf placeHolder]];
        if (placeHolder) {
            dispatch_async(dispatch_get_main_queue(), ^{
                if ([strongSelf isCancel]) {
                    return;
                }
                if ([strongSelf highlighted]) {
                    [[strongSelf imageView] setViewHighlightedImage:placeHolder];
                }else{
                    [[strongSelf imageView] setViewImage:placeHolder];
                }
            });
        }
        if ([strongSelf isCancel]) {
            return;
        }
        UIImage* image = [[GImageFetcher shareFetcher] imageFromUrl:[strongSelf imageUrl]];
        if ([strongSelf isCancel]) {
            return;
        }
        dispatch_async(dispatch_get_main_queue(), ^{
                if ([strongSelf isCancel]) {
                    return;
                }
                if ([strongSelf highlighted]) {
                    [[strongSelf imageView] setViewHighlightedImage:image];
                }else{
                    [[strongSelf imageView] setViewImage:image];
                }
        });
    });
}
@end
