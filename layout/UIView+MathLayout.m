//
//  UIView+MathLayout.m
//  layout
//
//  Created by jianbai on 14-9-2.
//  Copyright (c) 2014年 baobao. All rights reserved.
//

#import "UIView+MathLayout.h"
#include <objc/runtime.h>
#import "UIColor+HexString.h"
@implementation UIView (MathLayout)

static char expressionXKey;
static char expressionYKey;
static char expressionWidthKey;
static char expressionHeightKey;
static char expressionObjectKey;
static char valueDataKey;
static char eventDataKey;
static char clickBlockKey;
static char tapGestureRecognizerKey;

- (id)initWithAttributes:(NSDictionary *)attrs {
    self = [self init];
    if (self) {
        [self updateViewFromAttributes:attrs];
    }
    return self;
}

- (void)updateViewFromAttributes:(NSDictionary *)attrs {
    NSString* valueData = [attrs objectForKey:@"valueData"];
    if (valueData) {
        [self setValueData:valueData];
    }
    NSString*  eventData = [attrs objectForKey:@"eventData"];
    if (eventData) {
        [self setEventData:eventData];
    }
    NSString* enabled = [attrs objectForKey:@"enabled"];
    if (enabled != nil) {
        NSString* lowserCaseEnabled = [enabled lowercaseString];
        if ([lowserCaseEnabled isEqualToString:@"yes"] || [lowserCaseEnabled isEqualToString:@"true"]) {
            self.userInteractionEnabled = YES;
        }else{
            self.userInteractionEnabled = NO;
        }
    }
    
    NSString *backgroundString = [attrs objectForKey:@"background"];
    if (backgroundString != nil) {
        self.backgroundColor = [UIColor colorWithHexString:backgroundString];
    }
    
    // alpha
    NSString *alpha = [attrs objectForKey:@"alpha"];
    if (alpha != nil) {
        self.alpha =  MIN(1.0, MAX(0.0, [alpha floatValue]));
    }

    //tag
    NSString* tag = [attrs objectForKey:@"tag"];
    if (tag != nil) {
        self.tag = [tag integerValue];
    }
    
    // border
    NSString *borderWidth = [attrs objectForKey:@"borderWidth"];
    if (borderWidth != nil) {
        self.layer.borderWidth = [borderWidth floatValue];
    }
    NSString *borderColor = [attrs objectForKey:@"borderColor"];
    if (borderColor != nil) {
        self.layer.borderColor = [[UIColor colorWithHexString:borderColor] CGColor];
    }
    NSString *cornerRadius = [attrs objectForKey:@"cornerRadius"];
    if (cornerRadius != nil) {
        self.layer.cornerRadius = [cornerRadius floatValue];
    }
    CGRect frame = [self frame];
    NSString* x = [attrs objectForKey:@"x"];
    if (x != nil) {
        frame.origin.x = [x floatValue];
    }
    
    NSString* y = [attrs objectForKey:@"y"];
    if (y != nil) {
        frame.origin.y = [y floatValue];
    }
    
    NSString*  width = [attrs objectForKey:@"width"];
    if (width != nil) {
        frame.size.width =  [width floatValue];
    }
    
    NSString* height = [attrs objectForKey:@"height"];
    if (height != nil) {
        frame.size.height = [height floatValue];
    }
    if (!CGRectEqualToRect(frame, self.frame)) {
        self.frame = frame;
    }
    
    NSString* expressionX = [attrs objectForKey:@"expressionX"];
    if (expressionX != nil) {
        [self setExpressionX:[NSExpression expressionWithFormat:expressionX]];
    }
    
    NSString* expressionY = [attrs objectForKey:@"expressionY"];
    if (expressionY != nil) {
        [self setExpressionY:[NSExpression expressionWithFormat:expressionY]];
    }
    
    NSString* expressionWidth = [attrs objectForKey:@"expressionWidth"];
    if (expressionWidth != nil) {
        [self setExpressionWidth:[NSExpression expressionWithFormat:expressionWidth]];
    }
    
    NSString* expressionHeight = [attrs objectForKey:@"expressionHeight"];
    if (expressionHeight != nil) {
        [self setExpressionHeight:[NSExpression expressionWithFormat:expressionHeight]];
    }
    [self layoutWithMathExpression];
}


-(void)layoutWithMathExpression{
    if ([self expressionHeight] == nil
        && [self expressionWidth] == nil
        && [self expressionX] == nil
        && [self expressionY] == nil) {
        return;
    }
    NSMutableDictionary* expressionObject = [self expressionContext];
    if (expressionObject == nil) {
        CGRect bounds = [UIScreen mainScreen].bounds;
        expressionObject = [[NSMutableDictionary alloc] init];
        [expressionObject setObject:[NSNumber numberWithFloat:bounds.size.width] forKey:@"screen_height"];
        [expressionObject setObject:[NSNumber numberWithFloat:bounds.size.height] forKey:@"screen_height"];
        [self setExpressionContext:expressionObject];
    }
    CGRect frame = [self frame];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.origin.x] forKey:@"x"];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.origin.y] forKey:@"y"];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.size.width] forKey:@"width"];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.size.height] forKey:@"height"];
    
    CGRect superFrame = CGRectZero;
    if(self.superview  != nil) {
        superFrame = [[self superview] frame];
    }
    
    [expressionObject setObject:[NSNumber numberWithFloat:frame.origin.x] forKey:@"parent_x"];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.origin.y] forKey:@"parent_y"];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.size.width] forKey:@"parent_width"];
    [expressionObject setObject:[NSNumber numberWithFloat:frame.size.height] forKey:@"parent_height"];
    if ([self expressionX] != nil) {
        frame.origin.x = [[[self expressionX] expressionValueWithObject:expressionObject context:nil] floatValue];
    }
    if ([self expressionY] != nil) {
        frame.origin.y = [[[self expressionY] expressionValueWithObject:expressionObject context:nil] floatValue];
    }
    
    if ([self expressionWidth] != nil) {
        frame.size.width = [[[self expressionWidth] expressionValueWithObject:expressionObject context:nil] floatValue];
    }
    
    if ([self expressionHeight] != nil) {
        frame.size.height = [[[self expressionHeight] expressionValueWithObject:expressionObject context:nil] floatValue];
    }
    [self setFrame:frame];
}

- (void)setExpressionX:(NSExpression *)expressionX{
    objc_setAssociatedObject(self, &expressionXKey, expressionX,  OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(NSExpression*) expressionX{
    return objc_getAssociatedObject(self, &expressionXKey);
}

-(void)setExpressionY:(NSExpression *)expressionY{
    objc_setAssociatedObject(self, &expressionYKey, expressionY, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(NSExpression*) expressionY{
     return objc_getAssociatedObject(self, &expressionYKey);
}

-(void)setExpressionWidth:(NSExpression *)expressionWidth{
     objc_setAssociatedObject(self, &expressionWidthKey, expressionWidth, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(NSExpression*) expressionWidth{
    return objc_getAssociatedObject(self, &expressionWidthKey);
}

-(void)setExpressionHeight:(NSExpression *)expressionHeight{
    objc_setAssociatedObject(self, &expressionHeightKey, expressionHeight, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}


-(NSExpression*) expressionHeight{
   return  objc_getAssociatedObject(self, &expressionHeightKey);
}

-(void)setExpressionContext:(NSMutableDictionary *)expressionObject{
    objc_setAssociatedObject(self, &expressionObjectKey, expressionObject, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(NSExpression*)expressionContext{
     return  objc_getAssociatedObject(self, &expressionObjectKey);
}


-(void)setClickBlock:(void (^)(UIView *))clickBlock{
     objc_setAssociatedObject(self, &clickBlockKey, nil, OBJC_ASSOCIATION_COPY_NONATOMIC);
     objc_setAssociatedObject(self, &clickBlockKey, clickBlock, OBJC_ASSOCIATION_COPY_NONATOMIC);
    if ([self tapGestureRecognizer] == nil) {
        UITapGestureRecognizer* tapGestureRecognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handleTapGesture:)];
        [self addGestureRecognizer:tapGestureRecognizer];
        [self setTapGestureRecognizer:tapGestureRecognizer];
        self.userInteractionEnabled = YES;
    }
}

-(void (^)(UIView *))clickBlock{
     return objc_getAssociatedObject(self, &clickBlockKey);
}

-(void)setTapGestureRecognizer:(UITapGestureRecognizer *)tapGestureRecognizer{
     objc_setAssociatedObject(self, &tapGestureRecognizerKey, tapGestureRecognizer, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(UITapGestureRecognizer*)tapGestureRecognizer{
    return objc_getAssociatedObject(self, &tapGestureRecognizerKey);
}

-(void)setValueData:(NSString *)valueData{
    objc_setAssociatedObject(self, &valueDataKey, valueData, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(NSString*)valueData{
    return objc_getAssociatedObject(self, &valueDataKey);
}

-(void)setEventData:(NSString *)eventData{
    objc_setAssociatedObject(self, &eventDataKey, eventData, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

-(NSString*) eventData{
    return objc_getAssociatedObject(self, &eventDataKey);
}

-(void)handleTapGesture:(UIGestureRecognizer*)sender{
    __weak UIView* weakSelf = self;
    [self clickBlock](weakSelf);
}

@end
