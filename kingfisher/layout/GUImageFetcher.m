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

-(UIImage*) imageFromUrl:(NSString*) imageUrl{
    if([imageUrl hasPrefix:@"http://"]
        ||[imageUrl hasPrefix:@"https://"]){
        NSURL *url = [NSURL URLWithString:imageUrl];
        NSURLResponse* response = nil;
        NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:url];
        request.cachePolicy = NSURLRequestReturnCacheDataElseLoad;
        NSData * imageData= [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:nil];
        if (imageData && [imageData length] > 0) {
            return [UIImage imageWithData:imageData];;
        }
        return nil;
    }
    return [UIImage imageNamed:imageUrl];
}


@end
