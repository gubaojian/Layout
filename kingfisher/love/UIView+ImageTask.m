//
//  UIView+ImageTask.m
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "UIView+ImageTask.h"
#include <objc/runtime.h>

static char imageTaskKey;
static char highlightedImageTaskKey;

@implementation UIView (ImageTask)

-(void)loadImage:(NSString*)imageUrl{
    [self loadHighlightedImageUrl:imageUrl placeHolder:nil];
}

-(void)loadImage:(NSString*)imageUrl placeHolder:(NSString*)placeHolder{
    if (imageUrl == nil || imageUrl.length == 0) {
        [self setViewImage:nil];
        return;
    }
    GImageTask* imageTask = [self imageTask];
    if (imageTask != nil) {
        [imageTask cancel];
        imageTask = nil;
    }
    imageTask = [[GImageTask alloc] initWithView:self imageUrl:imageUrl placeHolder:placeHolder];
    [self setImageTask:imageTask];
    [imageTask execute];
}

-(void)loadHighlightedImageUrl:(NSString*)imageUrl{
    [self loadHighlightedImageUrl:imageUrl placeHolder:nil];
}

-(void)loadHighlightedImageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder{
    if (imageUrl == nil || imageUrl.length == 0) {
        [self setViewHighlightedImage:nil];
        return;
    }
    GImageTask* highlightedImageTask = [self highlightedImageTask];
    if (highlightedImageTask != nil) {
        [highlightedImageTask cancel];
        highlightedImageTask = nil;
    }
    highlightedImageTask = [[GImageTask alloc] initWithView:self imageUrl:imageUrl placeHolder:placeHolder];
    [self setHighlightedImageTask:highlightedImageTask];
    [highlightedImageTask execute];
}


-(void)setViewImage:(UIImage*)image{
    if ([self isKindOfClass:[UIImageView class]]) {
        [((UIImageView*)self) setImage:image];
        return;
    }
    if ([self isKindOfClass:[UIButton class]]) {
        [((UIButton*)self)setImage:image forState:UIControlStateNormal];
        return;
    }
}

-(void)setViewHighlightedImage:(UIImage*)image{
    if ([self isKindOfClass:[UIImageView class]]) {
        [((UIImageView*)self) setHighlightedImage:image];
        return;
    }
    if ([self isKindOfClass:[UIButton class]]) {
        [((UIButton*)self)setImage:image forState:UIControlStateSelected];
        [((UIButton*)self)setImage:image forState:UIControlStateNormal];
        return;
    }
}


-(void)setImageTask:(GImageTask *)imageTask{
    objc_setAssociatedObject(self, &imageTaskKey, imageTask,  OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(GImageTask*)imageTask{
   return objc_getAssociatedObject(self, &imageTaskKey);
}

-(void)setHighlightedImageTask:(GImageTask *)highlightedImageTask{
    objc_setAssociatedObject(self, &highlightedImageTaskKey, highlightedImageTask,  OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(GImageTask*)highlightedImageTask{
     return objc_getAssociatedObject(self, &highlightedImageTaskKey);
}

@end
