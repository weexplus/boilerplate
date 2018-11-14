/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import "WXPageDomainUtility.h"
#import <objc/runtime.h>
#import <WebKit/WebKit.h>

#define IsIOS8 [[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0

@implementation WXPageDomainUtility

@synthesize stopRunning;

+ (UIImage *)screencastDataScale:(float)scale
{
    return [self captureWithScale:scale];
}

+ (NSMutableDictionary *)screencastMetaDataWithScale:(float)scale
{
    NSMutableDictionary *metaDataDic = [[NSMutableDictionary alloc] initWithCapacity:6];
    [metaDataDic setObject:[NSNumber numberWithFloat:scale] forKey:@"pageScaleFactor"];
    [metaDataDic setObject:[NSNumber numberWithFloat:0] forKey:@"offsetTop"];
    [metaDataDic setObject:[NSNumber numberWithFloat:0] forKey:@"scrollOffsetX"];
    [metaDataDic setObject:[NSNumber numberWithFloat:0] forKey:@"scrollOffsetY"];
    
    return metaDataDic;
}

#pragma mark -
+ (UIImage *)captureWithScale:(float)scale
{
//    UIWindow * window = [UIApplication sharedApplication].keyWindow;
//    UIView *view = [[window subviews] objectAtIndex:0];
    UIView *view = [self getCurrentKeyController].view;
    UIGraphicsBeginImageContextWithOptions(view.frame.size, NO, [UIScreen mainScreen].scale);
    [view.layer renderInContext:UIGraphicsGetCurrentContext()];
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return [self scaleImage:image toScale:scale];
}

+ (UIImage *)scaleImage:(UIImage *)image toScale:(float)scaleSize

{
    UIGraphicsBeginImageContext(CGSizeMake(image.size.width * scaleSize, image.size.height * scaleSize));
    [image drawInRect:CGRectMake(0, 0, image.size.width * scaleSize, image.size.height * scaleSize)];
    UIImage *scaledImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return scaledImage;
}

+(UIViewController*)getCurrentKeyController
{
    UIViewController *result;
    UIWindow *topWindow = [[[UIApplication sharedApplication] delegate] window];
    if (topWindow.windowLevel != UIWindowLevelNormal)
    {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(topWindow in windows)
        {
            if (topWindow.windowLevel == UIWindowLevelNormal)
            break;
        }
    }
    id lenderClass = objc_getClass("UILayoutContainerView"); // 通过字符串名字，获取类
    id nextResponder;
    UIView *rootView = [[topWindow subviews] objectAtIndex:0];
        
    if(IsIOS8 && ![rootView isMemberOfClass:[lenderClass class]])
    {
        NSArray *arr = [rootView valueForKey:@"subviewCache"];
        if(arr.count>0)
        {
            UIView *v = [arr objectAtIndex:0];
            nextResponder = [v nextResponder];
        }
        else if([[rootView subviews] count] > 0)
        {
            nextResponder = [[[rootView subviews] objectAtIndex:0] nextResponder];
        }
    }
    else
    {
        nextResponder = [rootView nextResponder];
    }
    if ([nextResponder isKindOfClass:[UIViewController class]])
    {
        result = nextResponder;
    }
    else if ([topWindow respondsToSelector:@selector(rootViewController)] && topWindow.rootViewController != nil)
    {
        result = topWindow.rootViewController;
    }
    else
    {
            
        NSAssert(NO, @"ShareKit: Could not find a root view controller.  You can assign one manually by calling [[SHK currentHelper] setRootViewController:YOURROOTVIEWCONTROLLER].");
    }
    return result;
}
    
+(BOOL)isContainWebView:(UIView *)rootView
{
    for (UIView *subview in [rootView.subviews reverseObjectEnumerator]) {
        BOOL isWebView = [self isContainWebView:subview];
        if (isWebView) {
            return isWebView;
        }
    }
    if ([rootView isKindOfClass:[UIWebView class]] || [rootView isKindOfClass:[objc_getClass("WKWebView") class]]) {
        return YES;
    }
    return NO;
}
        
#pragma mark - 
static NSThread *WXScreencastThread;
#define WX_SCREENCAST_THREAD_NAME @"com.taobao.devtool.bridge"
+ (NSThread *)screencastThread
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        WXScreencastThread = [[NSThread alloc] initWithTarget:[[self class] alloc] selector:@selector(_runLoopThread) object:nil];
        [WXScreencastThread setName:WX_SCREENCAST_THREAD_NAME];
        if([[[UIDevice currentDevice] systemVersion] compare:@"8.0" options:NSNumericSearch] != NSOrderedAscending) {
            [WXScreencastThread setQualityOfService:[[NSThread mainThread] qualityOfService]];
        } else {
            [WXScreencastThread setThreadPriority:[[NSThread mainThread] threadPriority]];
        }
        
        [WXScreencastThread start];
    });
    
    return WXScreencastThread;
}

- (void)_runLoopThread
{
    [[NSRunLoop currentRunLoop] addPort:[NSMachPort port] forMode:NSDefaultRunLoopMode];
    while (!self.stopRunning) {
        [[NSRunLoop currentRunLoop] runMode:NSDefaultRunLoopMode beforeDate:[NSDate distantFuture]];
    }
}

void WXPerformBlockOnScreencastThread(void (^block)(void))
{
    [WXPageDomainUtility _performBlockOnScreencastThread:block];
}

+ (void)_performBlockOnScreencastThread:(void (^)(void))block
{
    if ([NSThread currentThread] == [self screencastThread]) {
        block();
    } else {
        [self performSelector:@selector(_performBlockOnScreencastThread:)
                     onThread:[self screencastThread]
                   withObject:[block copy]
                waitUntilDone:NO];
    }
}


@end
