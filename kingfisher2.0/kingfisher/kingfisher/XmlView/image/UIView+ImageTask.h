//
//  UIView+ImageTask.h
//  kingfisher
//
//  Created by lurina on 14-11-12.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GImageTask.h"

@interface UIView (ImageTask)

@property (nonatomic, strong) GImageTask*  imageTask;

@property (nonatomic, strong) GImageTask*  highlightedImageTask;

-(void)loadImage:(NSString*)imageUrl;

-(void)loadImage:(NSString*)imageUrl placeHolder:(NSString*)placeHolder;

-(void)loadHighlightedImageUrl:(NSString*)imageUrl;

-(void)loadHighlightedImageUrl:(NSString*)imageUrl placeHolder:(NSString*)placeHolder;

-(void)setViewImage:(UIImage*)image;

-(void)setViewHighlightedImage:(UIImage*)image;


@end
