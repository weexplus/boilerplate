//
//  WXPageDomain.m
//  PonyDebuggerDerivedSources
//
//  Generated on 8/23/12
//
//  Licensed to Square, Inc. under one or more contributor license agreements.
//  See the LICENSE file distributed with this work for the terms under
//  which Square, Inc. licenses this file to you.
//

#import "WXObject.h"
#import "WXPageDomain.h"
#import "WXObject.h"
#import "WXPageTypes.h"
#import "WXPageDomainUtility.h"
#import "WXRuntimeTypes.h"

static BOOL observing = NO;
static NSString * const WXScreencastChangeNotification = @"WXScreencastChangeNotification";

@interface WXPageDomain ()
//Commands

@property (nonatomic, strong)WXPageFrameResourceTree *testFrameTree;

@property (nonatomic, strong)WXStartScreencast *startScreencast;

@end

@implementation WXPageDomain
@synthesize testFrameTree;
@synthesize startScreencast;
@synthesize screenScaleFactor;

@dynamic delegate;

+ (NSString *)domainName;
{
    return @"Page";
}

// Events
- (void)domContentEventFiredWithTimestamp:(NSNumber *)timestamp;
{
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

    if (timestamp != nil) {
        [params setObject:[timestamp WX_JSONObject] forKey:@"timestamp"];
    }
    
    [self.debuggingServer sendEventWithName:@"Page.domContentEventFired" parameters:params];
}
- (void)loadEventFiredWithTimestamp:(NSNumber *)timestamp;
{
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

    if (timestamp != nil) {
        [params setObject:[timestamp WX_JSONObject] forKey:@"timestamp"];
    }
    
    [self.debuggingServer sendEventWithName:@"Page.loadEventFired" parameters:params];
}

- (void)executionContextCreatedForRuntime {
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];
    WXRuntimeExecutionContextDescription *context = [[WXRuntimeExecutionContextDescription alloc] init];
    context.frameId = self.testFrameTree.frame.identifier;
    context.identifier = [NSNumber numberWithInteger:12];
    context.isDefault = [NSNumber numberWithBool:YES];
    context.name = @"";
    context.origin = self.testFrameTree.frame.url;
    [params setObject:[context WX_JSONObject] forKey:@"context"];
    [self.debuggingServer sendEventWithName:@"Runtime.executionContextCreated" parameters:params];
}

// Fired once navigation of the frame has completed. Frame is now associated with the new loader.
- (void)frameNavigatedWithFrame:(WXPageFrame *)frame;
{
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

    if (frame != nil) {
        [params setObject:[frame WX_JSONObject] forKey:@"frame"];
    }
    
    [self.debuggingServer sendEventWithName:@"Page.frameNavigated" parameters:params];
}

// Fired when frame has been detached from its parent.
- (void)frameDetachedWithFrameId:(NSString *)frameId;
{
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

    if (frameId != nil) {
        [params setObject:[frameId WX_JSONObject] forKey:@"frameId"];
    }
    
    [self.debuggingServer sendEventWithName:@"Page.frameDetached" parameters:params];
}

- (void)workerRegistrationUpdated {
    NSDictionary *registrations = @{@"registrations":[NSArray array]};
    [self.debuggingServer sendEventWithName:@"ServiceWorker.workerVersionUpdated" parameters:registrations];
}

- (void)workerVersionUpdated {
    NSDictionary *version = @{@"versions":[NSArray array]};
    [self.debuggingServer sendEventWithName:@"ServiceWorker.workerVersionUpdated" parameters:version];
}


- (void)screencastFormat:(NSString *)format Quality:(NSNumber *)quality scaleFactor:(CGFloat)scaleFactor
{
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];
    UIImage *screenImage = [WXPageDomainUtility screencastDataScale:scaleFactor];
    NSData *imageData = UIImageJPEGRepresentation(screenImage, [quality floatValue]);
    NSString *codeImage = [imageData base64EncodedStringWithOptions:0];
    [params setObject:codeImage forKey:@"data"];
    
    NSMutableDictionary *metaData = [WXPageDomainUtility screencastMetaDataWithScale:scaleFactor];
    [metaData setObject:[NSNumber numberWithInteger:screenImage.size.width] forKey:@"deviceWidth"];
    [metaData setObject:[NSNumber numberWithInteger:screenImage.size.height] forKey:@"deviceHeight"];
    [params setObject:metaData forKey:@"metadata"];
    
    [params setObject:[NSNumber numberWithInteger:1] forKey:@"sessionId"];
    
    [self.debuggingServer sendEventWithName:@"Page.screencastFrame" parameters:params];
}

- (void)screencastFrame:(NSNotification *)notification
{
    if (self.startScreencast) {
        UIView *view = [WXPageDomainUtility getCurrentKeyController].view;
        if ([WXPageDomainUtility isContainWebView:view]) {
            __weak typeof(self) weakSelf = self;
            WXPerformBlockOnMainThread(^{
                [weakSelf screencastFormat:weakSelf.startScreencast.format
                                   Quality:weakSelf.startScreencast.quality
                               scaleFactor:weakSelf.screenScaleFactor];
            });
        }else {
            __weak typeof(self) weakSelf = self;
            WXPerformBlockOnScreencastThread(^{
                [weakSelf screencastFormat:weakSelf.startScreencast.format
                               Quality:weakSelf.startScreencast.quality
                           scaleFactor:weakSelf.screenScaleFactor];
            });
        }
    }
}

#pragma mark -
- (void)startObserving
{
    if (observing == NO) {
        [self swizzledFrameChangeMethod];
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(screencastFrame:) name:WXScreencastChangeNotification object:nil];
        observing = YES;
    }
}

- (void)stopObserving
{
    if (observing == YES) {
        [self swizzledFrameChangeMethod];
        [[NSNotificationCenter defaultCenter] removeObserver:self name:WXScreencastChangeNotification object:nil];
        observing = NO;
    }
}

- (void)swizzledFrameChangeMethod
{
    // I think setFrame has enough. From gurisxie
    Method original, swizzle;
    Class componentClass = [WXComponent class];
    original = class_getInstanceMethod(componentClass, @selector(setNeedsLayout));
    swizzle = class_getInstanceMethod(componentClass, sel_registerName("devtool_setNeedsLayout"));
    method_exchangeImplementations(original, swizzle);
}

#pragma mark - handleMethod

- (void)handleMethodWithName:(NSString *)methodName parameters:(NSDictionary *)params responseCallback:(WXResponseCallback)responseCallback;
{
    if ([methodName isEqualToString:@"enable"] && [self.delegate respondsToSelector:@selector(domain:enableWithCallback:)]) {
        [self.delegate domain:self enableWithCallback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"disable"] && [self.delegate respondsToSelector:@selector(domain:disableWithCallback:)]) {
        [self.delegate domain:self disableWithCallback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"addScriptToEvaluateOnLoad"] && [self.delegate respondsToSelector:@selector(domain:addScriptToEvaluateOnLoadWithScriptSource:callback:)]) {
        [self.delegate domain:self addScriptToEvaluateOnLoadWithScriptSource:[params objectForKey:@"scriptSource"] callback:^(NSString *identifier, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (identifier != nil) {
                [params setObject:identifier forKey:@"identifier"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"removeScriptToEvaluateOnLoad"] && [self.delegate respondsToSelector:@selector(domain:removeScriptToEvaluateOnLoadWithIdentifier:callback:)]) {
        [self.delegate domain:self removeScriptToEvaluateOnLoadWithIdentifier:[params objectForKey:@"identifier"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"reload"] && [self.delegate respondsToSelector:@selector(domain:reloadWithIgnoreCache:scriptToEvaluateOnLoad:callback:)]) {
        [self.delegate domain:self reloadWithIgnoreCache:[params objectForKey:@"ignoreCache"] scriptToEvaluateOnLoad:[params objectForKey:@"scriptToEvaluateOnLoad"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"navigate"] && [self.delegate respondsToSelector:@selector(domain:navigateWithUrl:callback:)]) {
        [self.delegate domain:self navigateWithUrl:[params objectForKey:@"url"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"getCookies"] && [self.delegate respondsToSelector:@selector(domain:getCookiesWithCallback:)]) {
        [self.delegate domain:self getCookiesWithCallback:^(NSArray *cookies, NSString *cookiesString, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:2];

            if (cookies != nil) {
                [params setObject:cookies forKey:@"cookies"];
            }
            if (cookiesString != nil) {
                [params setObject:cookiesString forKey:@"cookiesString"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"deleteCookie"] && [self.delegate respondsToSelector:@selector(domain:deleteCookieWithCookieName:domain:callback:)]) {
        [self.delegate domain:self deleteCookieWithCookieName:[params objectForKey:@"cookieName"] domain:[params objectForKey:@"domain"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"getResourceTree"] && [self.delegate respondsToSelector:@selector(domain:getResourceTreeWithCallback:)]) {
        [self.delegate domain:self getResourceTreeWithCallback:^(WXPageFrameResourceTree *frameTree, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (frameTree != nil) {
                [params setObject:frameTree forKey:@"frameTree"];
                self.testFrameTree = frameTree;
            }
//            NSMutableDictionary *newDic = @{@"frameTree":frameTree};
            responseCallback(params, error);
            [self executionContextCreatedForRuntime];
        }];
    } else if ([methodName isEqualToString:@"getResourceContent"] && [self.delegate respondsToSelector:@selector(domain:getResourceContentWithFrameId:url:callback:)]) {
        [self.delegate domain:self getResourceContentWithFrameId:[params objectForKey:@"frameId"] url:[params objectForKey:@"url"] callback:^(NSString *content, NSNumber *base64Encoded, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:2];

            if (content != nil) {
                [params setObject:content forKey:@"content"];
            }
            if (base64Encoded != nil) {
                [params setObject:base64Encoded forKey:@"base64Encoded"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"searchInResource"] && [self.delegate respondsToSelector:@selector(domain:searchInResourceWithFrameId:url:query:caseSensitive:isRegex:callback:)]) {
        [self.delegate domain:self searchInResourceWithFrameId:[params objectForKey:@"frameId"] url:[params objectForKey:@"url"] query:[params objectForKey:@"query"] caseSensitive:[params objectForKey:@"caseSensitive"] isRegex:[params objectForKey:@"isRegex"] callback:^(NSArray *result, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (result != nil) {
                [params setObject:result forKey:@"result"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"searchInResources"] && [self.delegate respondsToSelector:@selector(domain:searchInResourcesWithText:caseSensitive:isRegex:callback:)]) {
        [self.delegate domain:self searchInResourcesWithText:[params objectForKey:@"text"] caseSensitive:[params objectForKey:@"caseSensitive"] isRegex:[params objectForKey:@"isRegex"] callback:^(NSArray *result, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (result != nil) {
                [params setObject:result forKey:@"result"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"setDocumentContent"] && [self.delegate respondsToSelector:@selector(domain:setDocumentContentWithFrameId:html:callback:)]) {
        [self.delegate domain:self setDocumentContentWithFrameId:[params objectForKey:@"frameId"] html:[params objectForKey:@"html"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"canOverrideDeviceMetrics"] && [self.delegate respondsToSelector:@selector(domain:canOverrideDeviceMetricsWithCallback:)]) {
        [self.delegate domain:self canOverrideDeviceMetricsWithCallback:^(NSNumber *result, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (result != nil) {
                [params setObject:result forKey:@"result"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"setDeviceMetricsOverride"] && [self.delegate respondsToSelector:@selector(domain:setDeviceMetricsOverrideWithWidth:height:fontScaleFactor:fitWindow:callback:)]) {
        [self.delegate domain:self setDeviceMetricsOverrideWithWidth:[params objectForKey:@"width"] height:[params objectForKey:@"height"] fontScaleFactor:[params objectForKey:@"fontScaleFactor"] fitWindow:[params objectForKey:@"fitWindow"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"setShowPaintRects"] && [self.delegate respondsToSelector:@selector(domain:setShowPaintRectsWithResult:callback:)]) {
        [self.delegate domain:self setShowPaintRectsWithResult:[params objectForKey:@"result"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"getScriptExecutionStatus"] && [self.delegate respondsToSelector:@selector(domain:getScriptExecutionStatusWithCallback:)]) {
        [self.delegate domain:self getScriptExecutionStatusWithCallback:^(NSString *result, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (result != nil) {
                [params setObject:result forKey:@"result"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"setScriptExecutionDisabled"] && [self.delegate respondsToSelector:@selector(domain:setScriptExecutionDisabledWithValue:callback:)]) {
        [self.delegate domain:self setScriptExecutionDisabledWithValue:[params objectForKey:@"value"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"setGeolocationOverride"] && [self.delegate respondsToSelector:@selector(domain:setGeolocationOverrideWithLatitude:longitude:accuracy:callback:)]) {
        [self.delegate domain:self setGeolocationOverrideWithLatitude:[params objectForKey:@"latitude"] longitude:[params objectForKey:@"longitude"] accuracy:[params objectForKey:@"accuracy"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"clearGeolocationOverride"] && [self.delegate respondsToSelector:@selector(domain:clearGeolocationOverrideWithCallback:)]) {
        [self.delegate domain:self clearGeolocationOverrideWithCallback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"canOverrideGeolocation"] && [self.delegate respondsToSelector:@selector(domain:canOverrideGeolocationWithCallback:)]) {
        [self.delegate domain:self canOverrideGeolocationWithCallback:^(NSNumber *result, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (result != nil) {
                [params setObject:result forKey:@"result"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"setDeviceOrientationOverride"] && [self.delegate respondsToSelector:@selector(domain:setDeviceOrientationOverrideWithAlpha:beta:gamma:callback:)]) {
        [self.delegate domain:self setDeviceOrientationOverrideWithAlpha:[params objectForKey:@"alpha"] beta:[params objectForKey:@"beta"] gamma:[params objectForKey:@"gamma"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"clearDeviceOrientationOverride"] && [self.delegate respondsToSelector:@selector(domain:clearDeviceOrientationOverrideWithCallback:)]) {
        [self.delegate domain:self clearDeviceOrientationOverrideWithCallback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"canOverrideDeviceOrientation"] && [self.delegate respondsToSelector:@selector(domain:canOverrideDeviceOrientationWithCallback:)]) {
        [self.delegate domain:self canOverrideDeviceOrientationWithCallback:^(NSNumber *result, id error) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:1];

            if (result != nil) {
                [params setObject:result forKey:@"result"];
            }

            responseCallback(params, error);
        }];
    } else if ([methodName isEqualToString:@"setTouchEmulationEnabled"] && [self.delegate respondsToSelector:@selector(domain:setTouchEmulationEnabledWithEnabled:callback:)]) {
        [self.delegate domain:self setTouchEmulationEnabledWithEnabled:[params objectForKey:@"enabled"] callback:^(id error) {
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"startScreencast"] && [self.delegate respondsToSelector:@selector(domain:startScreencastWithcallback:)]) {
        [self.delegate domain:self startScreencastWithcallback:^(id error) {
            NSNumber *maxWidth = [params objectForKey:@"maxWidth"];
            NSNumber *maxHeight = [params objectForKey:@"maxHeight"];
            if (!self.startScreencast) {
                self.startScreencast = [[WXStartScreencast alloc] init];
            }
            self.startScreencast.quality = [params objectForKey:@"quality"];
            self.startScreencast.format = [params objectForKey:@"format"];
            
            CGRect rect = [WXPageDomainUtility getCurrentKeyController].view.frame;
            CGFloat deviceWidth = rect.size.width;
            CGFloat deviceHeight = rect.size.height;
            CGFloat scaleFactorWidth = maxWidth.floatValue/deviceWidth;
            CGFloat scaleFactorHeight = maxHeight.floatValue/deviceHeight;
            CGFloat scaleFactor = (scaleFactorWidth < scaleFactorHeight) ? scaleFactorWidth : scaleFactorHeight;
            self.screenScaleFactor = scaleFactor;
            
            UIView *view = [WXPageDomainUtility getCurrentKeyController].view;
            if ([WXPageDomainUtility isContainWebView:view]){
                __weak typeof(self) weakSelf = self;
                WXPerformBlockOnMainThread(^{
                    [weakSelf screencastFormat:[params objectForKey:@"format"] Quality:[params objectForKey:@"format"] scaleFactor:scaleFactor];
                });
            }else {
                __weak typeof(self) weakSelf = self;
                WXPerformBlockOnScreencastThread(^{
                    [weakSelf screencastFormat:[params objectForKey:@"format"] Quality:[params objectForKey:@"format"] scaleFactor:scaleFactor];
                });
            }
            
            
            [self startObserving];
            responseCallback(nil, error);
        }];
    } else if ([methodName isEqualToString:@"stopScreencast"] && [self.delegate respondsToSelector:@selector(domain:stopScreencastWithcallback:)]) {
        [self.delegate domain:self stopScreencastWithcallback:^(id error) {
            responseCallback(nil, error);
            [self stopObserving];
        }];
    } else {
        [super handleMethodWithName:methodName parameters:params responseCallback:responseCallback];
    }
}

@end


@implementation WXDebugger (WXPageDomain)

- (WXPageDomain *)pageDomain;
{
    return [self domainForName:@"Page"];
}

@end

@implementation WXComponent (ScreencastChange)

- (void)devtool_setNeedsLayout
{
    [self devtool_setNeedsLayout];
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(postNotificationScreencastChangeNotification) object:nil];
    [self performSelector:@selector(postNotificationScreencastChangeNotification) withObject:nil afterDelay:2];
}

- (void)devtool_setNeedsDisplay
{
    [self devtool_setNeedsDisplay];
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(postNotificationScreencastChangeNotification) object:nil];
    [self performSelector:@selector(postNotificationScreencastChangeNotification) withObject:nil afterDelay:2];
}

- (void)devtool_setFrame:(CGRect)frame
{
    [self devtool_setFrame:frame];
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(postNotificationScreencastChangeNotification) object:nil];
    [self performSelector:@selector(postNotificationScreencastChangeNotification) withObject:nil afterDelay:2];
}

- (void)postNotificationScreencastChangeNotification
{
    [[NSNotificationCenter defaultCenter] postNotificationName:WXScreencastChangeNotification object:nil];
}

@end
