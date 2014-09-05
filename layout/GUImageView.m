//
//  GUImageView.m
//  layout
//
//  Created by jianbai on 14-9-4.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUImageView.h"
#import "UIColor+HexString.h"


@implementation GUImageView

-(void)layoutSubviews{
    [super layoutSubviews];
    [self layoutWithMathExpression];
}


- (void)updateViewFromAttributes:(NSDictionary *)attrs {
    [super updateViewFromAttributes:attrs];
    NSString* imageUrl = [attrs objectForKey:@"imageUrl"];
    
    if (imageUrl != nil) {
        GUImageView* __weak weakSelf = self;
       dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
           NSURL *url = [NSURL URLWithString:imageUrl];
           NSData *imageData = [NSData dataWithContentsOfURL:url];
           __block UIImage *image = [UIImage imageWithData:imageData];
           if (weakSelf) {
               dispatch_async(dispatch_get_main_queue(), ^{
                   if(weakSelf){
                       [weakSelf setImage:image];
                   }
               });
           }
       });
    }
    
    NSString* highlightedImageUrl = [attrs objectForKey:@"highlightedImageUrl"];
    if (highlightedImageUrl != nil) {
        GUImageView* __weak weakSelf = self;
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            NSURL *url = [NSURL URLWithString:highlightedImageUrl];
            NSData *imageData = [NSData dataWithContentsOfURL:url];
            UIImage *image = [UIImage imageWithData:imageData];
            if (weakSelf) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if (weakSelf) {
                        [weakSelf setHighlightedImage:image];
                    }
                });
            }
        });
    }
    
    NSString *scaleType = [attrs objectForKey:@"scaleType"];
    if (scaleType != nil) {
        if ([scaleType isEqualToString:@"center"]) {
            self.contentMode = UIViewContentModeCenter;
        } else if ([scaleType isEqualToString:@"centerCrop"]) {
            self.contentMode = UIViewContentModeScaleAspectFill;
            self.clipsToBounds = TRUE;
        } else if ([scaleType isEqualToString:@"centerInside"]) {
            self.contentMode = UIViewContentModeScaleAspectFit;
        } else if ([scaleType isEqualToString:@"fitXY"]) {
            self.contentMode = UIViewContentModeScaleToFill;
        } else if ([scaleType isEqualToString:@"top"]) {
            self.contentMode = UIViewContentModeTop;
        } else if ([scaleType isEqualToString:@"topLeft"]) {
            self.contentMode = UIViewContentModeTopLeft;
        } else if ([scaleType isEqualToString:@"topRight"]) {
            self.contentMode = UIViewContentModeTopRight;
        } else if ([scaleType isEqualToString:@"left"]) {
            self.contentMode = UIViewContentModeLeft;
        } else if ([scaleType isEqualToString:@"right"]) {
            self.contentMode = UIViewContentModeRight;
        } else if ([scaleType isEqualToString:@"bottom"]) {
            self.contentMode = UIViewContentModeBottom;
        } else if ([scaleType isEqualToString:@"bottomLeft"]) {
            self.contentMode = UIViewContentModeBottomLeft;
        } else if ([scaleType isEqualToString:@"bottomRight"]) {
            self.contentMode = UIViewContentModeBottomRight;
        }
    }
}


-(void)setImageWithURL:(NSString*)imageUrl{
    [self setImageWithURL:imageUrl placeHolder:nil];
}

-(void)setImageWithURL:(NSString*)imageUrl placeHolder:(UIImage*) placeHolder{
    self.image = placeHolder;
    if (imageUrl != nil) {
        GUImageView* __weak weakSelf = self;
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            NSURL *url = [NSURL URLWithString:imageUrl];
            NSData *imageData = [NSData dataWithContentsOfURL:url];
            UIImage *image = [UIImage imageWithData:imageData];
            if (weakSelf) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if(weakSelf){
                        [weakSelf setImage:image];
                    }
                });
            }
        });
    }
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
