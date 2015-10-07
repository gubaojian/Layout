//
//  GUViewInfalter.m
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "ViewInfalter.h"
#import "GHash.h"

@implementation ViewInfalter


+ (id)shareInflater{
    static ViewInfalter *viewInfalter = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        viewInfalter = [[self alloc] init];
    });
    return viewInfalter;
}

- (id)init {
    self = [super init];
    if (self) {
    }
    return self;
}




-(void)viewFromUrl:(NSURL*)url callback:(void(^)(UIView*)) callbackBlock{
    NSString* path = [url path];
    NSString* extension = [path pathExtension];
    NSString* bundleName = [path stringByDeletingPathExtension];
    NSString* filePath = [[NSBundle mainBundle] pathForResource:bundleName ofType:extension];
    if ([[NSFileManager defaultManager] fileExistsAtPath:filePath]) {
        NSInputStream* stream =[NSInputStream inputStreamWithFileAtPath:filePath];
        UIView* view = [self viewFromInputStream:stream];
        if (view != nil) {
            callbackBlock(view);
            return;
        }
    }
    NSString* localFilePath = [self documentPathFor:path];
    if ([[NSFileManager defaultManager] fileExistsAtPath:localFilePath]) {
        NSInputStream* stream =[NSInputStream inputStreamWithFileAtPath:localFilePath];
        UIView* view = [self viewFromInputStream:stream];
        if (view != nil) {
            callbackBlock(view);
            return;
        }
    }
    __weak ViewInfalter* weakSelf = self;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            __strong ViewInfalter* strongSelf = weakSelf;
           if (strongSelf) {
               NSData *data = [NSData dataWithContentsOfURL:url];
               dispatch_async(dispatch_get_main_queue(), ^{
                   if (data == nil) {
                       callbackBlock(nil);
                       return;
                   }
                   NSInputStream* inputStream = [NSInputStream inputStreamWithData:data];
                   UIView* view = [self viewFromInputStream:inputStream];
                   callbackBlock(view);
                   
               });
           }
    });
}

-(NSString*) documentPathFor:(NSString*) name{
    NSString* fileName =[NSString stringWithFormat:@"/xml_view/%@", name];
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,   NSUserDomainMask, YES);
    NSString *directory = [paths objectAtIndex:0];
    NSString *filePath = [directory stringByAppendingPathComponent:fileName];
    return filePath;
}




-(id) viewFromInputStream:(NSInputStream*) inputstream{
    NSXMLParser* xmlParser = nil;
    @try {
        xmlParser = [[NSXMLParser alloc] initWithStream:inputstream];
    }
    @catch (NSException *exception) {
        NSLog(@"Create NSXMLParser Error %@", [exception description]);
        if (inputstream != nil) {
            [inputstream close];
        }
        return nil;
    }
    if (xmlParser == nil) {
        return nil;
    }
    xmlParser.delegate = self;
    [xmlParser parse];
    if (inputstream != nil) {
        [inputstream close];
    }
    UIView* view = rootView;
    rootView = nil;
    viewNode = nil;
    return view;
}



- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict{
    UIView* elementView = [[GViewFactory shareFactory] createViewWithElement:elementName attributes:attributeDict];
    if (viewNode != nil) {
        [viewNode addSubview:elementView];
    }
    if (rootView == nil) {
        rootView = elementView;
    }
    viewNode = elementView;
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName{
    if (viewNode.superview != nil) {
        viewNode = viewNode.superview;
    }
}


- (void)parser:(NSXMLParser *)parser parseErrorOccurred:(NSError *)parseError{
    @throw [NSException exceptionWithName:@"GUViewInfalter Parse Error" reason:[parseError description] userInfo:nil];
}

- (void)parser:(NSXMLParser *)parser validationErrorOccurred:(NSError *)validationError{
        @throw [NSException exceptionWithName:@"GUViewInfalter validationErrorOccurred" reason:[validationError description] userInfo:nil];
}






@end
