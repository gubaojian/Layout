//
//  GUImageFetcher.h
//  layout
//
//  Created by lurina on 14-9-6.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FileStore.h"

@interface GImageFetcher : NSObject{
     @private NSCache* _imageCache;
   @private FileStore* _imageStore;
}


+(GImageFetcher*) shareFetcher;
+(void) setShareFetcher:(GImageFetcher*) fetcher;

-(UIImage*) imageFromUrl:(NSString*) imageUrl;

-(void)loadImage:(NSString*)imageUrl view:(UIView*)view;

-(void)loadHighlightedImageUrl:(NSString*)imageUrl view:(UIView*)view;

@end
