//
//  GUViewInfalter.m
//  layout
//
//  Created by jianbai on 14-9-1.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "ViewInfalter.h"

@implementation ViewInfalter


+ (id)shareViewInfalter{
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

-(id) toViewBundleFile:(NSString*) bundleFileName{
    return [self toViewBundleName:bundleFileName];
}


-(id) toViewTemplate:(GUTemplate*) viewTemplate{
    if (viewTemplate == nil) {
        return nil;
    }
    GUTemplate* localTemplate = [[self localTemplates] objectForKey:[viewTemplate name]];
    UIView* view = nil;
    if (localTemplate != nil && localTemplate.version >= viewTemplate.version) {
        view = [self toViewBundleName:localTemplate.name];
    }
    if (view == nil) {
        NSString* filePath = [self documentPathForTemplate:viewTemplate];
        view = [self toViewFilePath:filePath];
        if (view == nil) {
            dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
                NSURL *url = [NSURL URLWithString:viewTemplate.downloadUrl];
                NSData *templateData = [NSData dataWithContentsOfURL:url];
                [[NSFileManager defaultManager] removeItemAtPath:filePath error:nil];
                [templateData writeToFile:filePath atomically:YES];
            });
        }
    }
    return nil;
}


-(void)registerTemplate:(GUTemplate*) localTemplate{
    if (localTemplate == nil || localTemplate.name.length == 0) {
        return;
    }
    NSString* path = [[NSBundle mainBundle] pathForResource:[localTemplate name] ofType:@"xml"];
    
    if (![[NSFileManager defaultManager] fileExistsAtPath:path]) {
        [NSException raise:@"Template %@ not Exist In Main Bundle" format:[localTemplate name], nil];
    }
    
    [[self localTemplates] setObject:localTemplate forKey:[localTemplate name]];
}


-(NSMutableDictionary*)localTemplates{
    if (_localTemplates == nil) {
        _localTemplates = [[NSMutableDictionary alloc] initWithCapacity:8];
    }
    return _localTemplates;
}

-(NSString*) documentPathForTemplate:(GUTemplate*)viewTemplate{
    NSString* fileName =[NSString stringWithFormat:@"%@_%ld.xml", viewTemplate.name, viewTemplate.version];
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
    return [self toViewFilePath:filePath];
}


-(id) toViewFilePath:(NSString*)filePath{
    if (xmlView != nil) {
        [NSException raise:@"Mutl Concurrent Infalter Exception" format:nil];
    }
    if (![[NSFileManager defaultManager] fileExistsAtPath:filePath]) {
        return nil;
    }
    NSXMLParser* xmlParser = nil;
    @try {
        xmlParser = [[NSXMLParser alloc] initWithStream:[NSInputStream inputStreamWithFileAtPath:filePath]];
    }
    @catch (NSException *exception) {
        NSLog(@"Create NSXMLParser Error %@", [exception description]);
    }
    if (xmlParser == nil) {
        return nil;
    }
    xmlParser.delegate = self;
    [xmlParser parse];
    UIView* view = xmlView;
    xmlView = nil;
    return view;
}

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict{
    UIView* elementView = [[GUViewFactory shareFactory] createViewWithElement:elementName attributes:attributeDict];
    if (xmlView != nil) {
        [xmlView addSubview:elementView];
    }
    xmlView = elementView;
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName{
    if (xmlView.superview != nil) {
        xmlView = xmlView.superview;
    }
}


- (void)parser:(NSXMLParser *)parser parseErrorOccurred:(NSError *)parseError{
    @throw [NSException exceptionWithName:@"GUViewInfalter Parse Error" reason:[parseError description] userInfo:nil];
}

- (void)parser:(NSXMLParser *)parser validationErrorOccurred:(NSError *)validationError{
        @throw [NSException exceptionWithName:@"GUViewInfalter validationErrorOccurred" reason:[validationError description] userInfo:nil];
}






@end
