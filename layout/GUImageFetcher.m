//
//  GUImageFetcher.m
//  layout
//
//  Created by lurina on 14-9-6.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUImageFetcher.h"

@implementation GUImageFetcher

static GUImageFetcher *shareFetcher = nil;


+(GUImageFetcher*) shareFetcher{
    if (shareFetcher == nil) {
        shareFetcher = [[GUImageFetcher alloc] init];
    }
    return shareFetcher;
}
+(void) setShareFetcher:(GUImageFetcher*) fetcher{
    shareFetcher = fetcher;
}


-(BOOL)setImageUrl:(NSString*)imageUrl forButton:(UIButton*) button forState:(UIControlState)state{
    UIImage* image = [self imageFromBundleUrl:imageUrl];
    if (image != nil) {
        [button setImage:image forState:state];
        return YES;
    }
    return NO;
}

-(BOOL)setBackgroundImageUrl:(NSString*)imageUrl forButton:(UIButton*) button forState:(UIControlState)state{
    UIImage* image = [self imageFromBundleUrl:imageUrl];
    if (image != nil) {
        [button setBackgroundImage: image forState:state];
        return YES;
    }
    return NO;
}


-(BOOL)setImageUrl:(NSString*)imageUrl forImageView:(UIImageView*) imageView{
    UIImage* image = [self imageFromBundleUrl:imageUrl];
    if (image != nil) {
        imageView.image = image;
        return YES;
    }
    return NO;
}


-(UIImage*) imageFromBundleUrl:(NSString*) url{
    if (![url hasPrefix:@"bundle://"]) {
        return nil;
    }
    NSString* fileName = [url substringFromIndex:9];
    if (fileName.length < 4) {
        return nil;
    }
    NSString* extension = [fileName pathExtension];
    NSString* bundleName = fileName;
    if (extension.length > 0) {
        bundleName = [fileName substringToIndex:[fileName length] - [extension length] - 1];
    }
    if ([bundleName hasSuffix:@"@2x"]) {
        bundleName = [bundleName substringToIndex:[bundleName length] - 3];
    }
    NSLog(@"Bundle Name%@", bundleName);
    return [UIImage imageNamed:bundleName];
}





@end
