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

-(UIImage*) imageFromUrl:(NSString*) imageUrl;



@end
