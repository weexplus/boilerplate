//
//  WXTracingUtility.h
//  Flipboard
//
//  Created by Ryan Olson on 4/18/14.
//  Copyright (c) 2014 Flipboard. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <objc/runtime.h>
#import <WeexSDK/WXTracingManager.h>

#define FLEXFloor(x) (floor([[UIScreen mainScreen] scale] * (x)) / [[UIScreen mainScreen] scale])
#define TracingResetLogDataNoti @"TracingResetLogDataNoti"

#define CREATE_RGBA_COLOR(r, g, b, a)                                                              \
[UIColor colorWithRed:r / 255.0 green:g / 255.0 blue:b / 255.0 alpha:a]
#define COLOR_TRACING_ERROR CREATE_RGBA_COLOR(149, 6, 27, 1)
#define COLOR_TRACING_WARN CREATE_RGBA_COLOR(232, 197, 4, 1)
#define COLOR_TRACING_EXCEPTION CREATE_RGBA_COLOR(248, 8, 13, 1)
#define COLOR_TRACING_SEARCBG CREATE_RGBA_COLOR(126, 209, 252, 1)
#define COLOR_TRACING_JSLOG CREATE_RGBA_COLOR(46, 5, 163, 1)

@interface WXTracingUtility : NSObject

+ (UIColor *)consistentRandomColorForObject:(id)object;
+ (NSString *)descriptionForView:(UIView *)view includingFrame:(BOOL)includeFrame;
+ (NSString *)stringForCGRect:(CGRect)rect;
+ (UIViewController *)viewControllerForView:(UIView *)view;
+ (UIViewController *)viewControllerForAncestralView:(UIView *)view;
+ (NSString *)detailDescriptionForView:(UIView *)view;
+ (UIImage *)circularImageWithColor:(UIColor *)color radius:(CGFloat)radius;
+ (UIColor *)scrollViewGrayColor;
+ (UIColor *)hierarchyIndentPatternColor;
+ (NSString *)applicationImageName;
+ (NSString *)applicationName;
+ (NSString *)safeDescriptionForObject:(id)object;
+ (UIFont *)defaultFontOfSize:(CGFloat)size;
+ (UIFont *)defaultTableViewCellLabelFont;
+ (NSString *)stringByEscapingHTMLEntitiesInString:(NSString *)originalString;
+ (UIInterfaceOrientationMask)infoPlistSupportedInterfaceOrientationsMask;
+ (NSString *)searchBarPlaceholderText;
+ (BOOL)isImagePathExtension:(NSString *)extension;
+ (UIImage *)thumbnailedImageWithMaxPixelDimension:(NSInteger)dimension fromImageData:(NSData *)data;
+ (NSString *)stringFromRequestDuration:(NSTimeInterval)duration;
+ (NSString *)statusCodeStringFromURLResponse:(NSURLResponse *)response;
+ (NSDictionary *)dictionaryFromQuery:(NSString *)query;
+ (NSString *)prettyJSONStringFromData:(NSData *)data;
+ (BOOL)isValidJSONData:(NSData *)data;
+ (NSData *)inflatedDataFromCompressedData:(NSData *)compressedData;

+ (NSArray *)allWindows;
+ (NSString *)tracingTime;
+ (NSArray *)formatTask:(WXTracingTask *)task;
+ (BOOL)isRemoteTracing;
+ (void)setRemoteTracing:(BOOL)isRemoteTracing;

// Swizzling utilities

+ (SEL)swizzledSelectorForSelector:(SEL)selector;
+ (BOOL)instanceRespondsButDoesNotImplementSelector:(SEL)selector class:(Class)cls;
+ (void)replaceImplementationOfKnownSelector:(SEL)originalSelector onClass:(Class)class withBlock:(id)block swizzledSelector:(SEL)swizzledSelector;
+ (void)replaceImplementationOfSelector:(SEL)selector withSelector:(SEL)swizzledSelector forClass:(Class)cls withMethodDescription:(struct objc_method_description)methodDescription implementationBlock:(id)implementationBlock undefinedBlock:(id)undefinedBlock;

@end
