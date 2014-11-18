//
//  GUImageFetcher.m
//  layout
//
//  Created by lurina on 14-9-6.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "GUImageFetcher.h"
#import "UIView+ImageTask.h"
#import "GUHash.h"

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

-(void)loadImage:(NSString*)imageUrl view:(UIView*)view{
    [view loadImage:imageUrl];
}

-(void)loadHighlightedImageUrl:(NSString*)imageUrl view:(UIView*)view{
    [view loadHighlightedImageUrl:imageUrl];
}

-(UIImage*) imageFromUrl:(NSString*) imageUrl{
    if (imageUrl == nil) {
        return nil;
    }
    imageUrl = [imageUrl stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    UIImage* image = [[self imageCache] objectForKey:imageUrl];
    if (image == nil) {
        if([imageUrl hasPrefix:@"http://"]
           ||[imageUrl hasPrefix:@"https://"]){
            image = [self loadHttpImageUrl:imageUrl];
        }else{
            image = [self loadLocalImage:imageUrl];
        }
        if (image != nil) {
            [[self imageCache] setObject:image forKey:imageUrl];
        }
    }
    return image;
}

-(NSCache*)imageCache{
    if (_imageCache == nil) {
        _imageCache = [[NSCache alloc] init];
        [_imageCache setCountLimit:16];
    }
    return _imageCache;
}

-(FileStore*)imageStore{
    if (_imageStore == nil) {
        _imageStore = [FileStore from:NSCachesDirectory dir:@"images" size:256 lifetime:14*24*60*60];
    }
    return _imageStore;
}

-(UIImage*)loadLocalImage:(NSString*)imageUrl{
    UIImage* image =[UIImage imageNamed:imageUrl];
    if (image == nil) {
        NSString* fileName = [[self imageStore] toFullPath:imageUrl];
        image = [UIImage imageWithContentsOfFile:fileName];
    }
    if (image) {
        if ([imageUrl hasSuffix:@"?scale=true"]) {
            CGFloat horizontalInset  = (image.size.width/2 - 1);
            CGFloat verticalInset  = (image.size.height/2 - 1);
            image = [image resizableImageWithCapInsets:UIEdgeInsetsMake(verticalInset, horizontalInset, verticalInset, horizontalInset)];
        }
    }
    return image;
}

-(UIImage*)loadHttpImageUrl:(NSString*)imageUrl{
    NSString* md5 = [GUHash md5:imageUrl];
    NSString* filePath = [[self imageStore] toFullPath:md5];
    UIImage* image = [UIImage imageWithContentsOfFile:filePath];
    if (image != nil) {
        return image;
    }
    NSURL *url = [NSURL URLWithString:imageUrl];
    NSURLResponse* response = nil;
    NSError* error;
    NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:url];
    NSData * imageData= [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
    if (error != nil) {
        NSLog(@"loadHttpImageUrl %@ Error %@", imageUrl, error);
        return nil;
    }
    if (imageData && [imageData length] > 0) {
        [[self imageStore] write:imageData toFile:md5];
        return [UIImage imageWithData:imageData];
    }
    return nil;
}





@end
