//
//  GUImageFetcher.h
//  layout
//
//  Created by lurina on 14-9-6.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GUImageFetcher : NSObject


+(GUImageFetcher*) shareFetcher;
+(void) setShareFetcher:(GUImageFetcher*) fetcher;

-(BOOL)setImageUrl:(NSString*)imageUrl forButton:(UIButton*) button forState:(UIControlState)state;
-(BOOL)setBackgroundImageUrl:(NSString*)imageUrl forButton:(UIButton*) button forState:(UIControlState)state;

-(BOOL)setImageUrl:(NSString*)imageUrl forImageView:(UIImageView*) button;

-(UIImage*) imageFromBundleUrl:(NSString*) url;



@end
