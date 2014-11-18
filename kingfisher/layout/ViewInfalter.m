//
//  GUViewInfalter.m
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "ViewInfalter.h"

#define VIEW_INFLATER_HAS_MARK  @"mark"

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

-(NSMutableDictionary*)downloadIngMap{
    if (_downloadIngMap == nil) {
         _downloadIngMap = [[NSMutableDictionary alloc] initWithCapacity:4];
    }
    return _downloadIngMap;
}
-(NSMutableDictionary*)downloadSuccessMap{
    if (_downloadSuccessMap  == nil) {
        _downloadSuccessMap = [[NSMutableDictionary alloc] initWithCapacity:4];
    }
    return _downloadSuccessMap;
}

-(id) viewFromFile:(NSString*) fileName{
    if ([fileName hasPrefix:@"http://"]
        || [fileName hasPrefix:@"https://"]) {
        NSURL* url = [NSURL URLWithString:fileName];
        NSData* data = [NSData dataWithContentsOfURL:url];
        if (data == nil) {
            NSLog(@"viewFromFile Error %@ Not Exist", fileName);
            return nil;
        }
        NSInputStream* inputStream = [[NSInputStream alloc] initWithData:data];
        return [self viewFromInputStream:inputStream];
    }
    return [self toViewBundleName:fileName];
}


-(id) viewFrom:(NSString*) name version:(int)version downloadUrl:(NSString*)downloadUrl{
    if (name == nil) {
        return nil;
    }
    UIView* view = nil;
    NSString* filePath = [self documentPathFor:name version:version];
    view = [self viewFromLocalFilePath:filePath];
    if (view == nil && downloadUrl.length > 0) {
        [self download:downloadUrl toFile:filePath name:name version:version];
    }
    if (view == nil) {
        view = [self toViewBundleName:name];
    }
    return view;
}


-(void)download:(NSString*)downloadUrl toFile:(NSString*)filePath name:(NSString*)name version:(int) version{
    if (downloadUrl == nil || downloadUrl.length <= 0) {
        return;
    }
    if ([VIEW_INFLATER_HAS_MARK isEqual:[[self downloadIngMap] objectForKey:filePath]]) {
        return;
    }
    if ([VIEW_INFLATER_HAS_MARK isEqual:[[self downloadSuccessMap] objectForKey:filePath]]) {
        return;
    }
    [[self downloadIngMap] setObject:VIEW_INFLATER_HAS_MARK forKey:filePath];
    __weak ViewInfalter* weakSelf = self;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        __strong ViewInfalter* strongSelf = weakSelf;
        if (strongSelf) {
            NSURL *url = [NSURL URLWithString:downloadUrl];
            NSData *templateData = [NSData dataWithContentsOfURL:url];
            BOOL success = NO;
            if (templateData) {
                [[NSFileManager defaultManager] removeItemAtPath:filePath error:nil];
                success = [templateData writeToFile:filePath atomically:YES];
            }
            [[strongSelf downloadIngMap] removeObjectForKey:filePath];
            if (strongSelf) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if (success) {
                        [[NSNotificationCenter defaultCenter] postNotificationName:TEMPLATE_DOWNLOAD_SUCCESS_NOTIFICATION object:name userInfo:[NSDictionary dictionaryWithObjectsAndKeys:name, @"name", [NSNumber numberWithInt:version], @"version", nil]];
                        [[strongSelf downloadSuccessMap] setObject:@"mark" forKey:filePath];
                    }else{
                        [[NSNotificationCenter defaultCenter] postNotificationName:TEMPLATE_DOWNLOAD_FAILED_NOTIFICATION object:name];
                    }
                });
            }
        }
    });
}


-(NSString*) documentPathFor:(NSString*) name version:(int)version{
    NSString* fileName =[NSString stringWithFormat:@"view/%d/%@.xml", version, name];
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,   NSUserDomainMask, YES);
    NSString *directory = [paths objectAtIndex:0];
    NSString *filePath = [directory stringByAppendingPathComponent:fileName];
    return filePath;
}

-(id) toViewBundleName:(NSString*)bundleName{
    NSString* extension = [bundleName pathExtension];
    if (extension.length == 0) {
        extension = @"xml";
    }else{
        bundleName = [bundleName stringByDeletingPathExtension];
    }
    NSString* filePath = [[NSBundle mainBundle] pathForResource:bundleName ofType:@"xml"];
    return [self viewFromLocalFilePath:filePath];
}


-(id) viewFromLocalFilePath:(NSString*)filePath{
    if (viewNode != nil) {
        [NSException raise:@"Mutl Concurrent Infalter Exception" format:nil];
    }
    if (![[NSFileManager defaultManager] fileExistsAtPath:filePath]) {
        return nil;
    }
    return [self viewFromInputStream:[NSInputStream inputStreamWithFileAtPath:filePath]];
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
    UIView* elementView = [[GUViewFactory shareFactory] createViewWithElement:elementName attributes:attributeDict];
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
