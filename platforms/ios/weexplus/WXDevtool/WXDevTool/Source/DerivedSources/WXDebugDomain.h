/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import "WXDynamicDebuggerDomain.h"
#import "WXObject.h"
#import "WXDebugger.h"
#import "WXDevTool.h"

@protocol WXDebugCommandDelegate;

@interface WXDebugDomain : WXDynamicDebuggerDomain

@property (nonatomic, assign) id <WXDebugCommandDelegate, WXCommandDelegate>delegate;

@end

@protocol WXDebugCommandDelegate <WXCommandDelegate>
@optional
- (void)domain:(WXDebugDomain *)domain sendLogLevel:(NSString *)level withCallback:(void (^)(id error))callback;
- (void)domain:(WXDebugDomain *)domain setInspectorMode:(NSString *)mode withCallback:(void (^)(id error))callback;
- (void)domain:(WXDynamicDebuggerDomain *)domain refreshCallback:(void (^)(id error))callback;

- (void)domain:(WXDynamicDebuggerDomain *)domain reloadCallback:(void (^)(id error))callback;

- (void)domain:(WXDynamicDebuggerDomain *)domain enableNetwork:(BOOL)enable networkCallback:(void (^)(id error))callback;

- (void)domain:(WXDynamicDebuggerDomain *)domain callNative:(NSDictionary *)jsModule callBack:(void (^)(id error))callback;

- (void)domain:(WXDynamicDebuggerDomain *)domain callAddElement:(NSDictionary *)jsModule callBack:(void (^)(id error))callback;

- (void)domain:(WXDynamicDebuggerDomain *)domain callCreateBody:(NSDictionary *)jsModule callBack:(void (^)(id error))callback;

- (void)domain:(WXDynamicDebuggerDomain *)domain callRemoveElement:(NSDictionary *)jsModule callBack:(void (^)(id error))callback ;

- (void)domain:(WXDynamicDebuggerDomain *)domain callMoveElement:(NSDictionary *)jsModule callBack:(void (^)(id error))callback ;

- (void)domain:(WXDynamicDebuggerDomain *)domain callUpdateAttrs:(NSDictionary *)jsModule callBack:(void (^)(id error))callback ;

- (void)domain:(WXDynamicDebuggerDomain *)domain callUpdateStyle:(NSDictionary *)jsModule callBack:(void (^)(id error))callback ;

- (void)domain:(WXDynamicDebuggerDomain *)domain callAddEvent:(NSDictionary *)jsModule callBack:(void (^)(id error))callback ;

- (void)domain:(WXDynamicDebuggerDomain *)domain callRemoveEvent:(NSDictionary *)jsModule callBack:(void (^)(id error))callback ;
- (void)domain:(WXDynamicDebuggerDomain *)domain syncCall:(NSDictionary *)args callBack:(void (^)(NSDictionary *data, id error))callback;

@end
