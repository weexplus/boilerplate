/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import "WXDevTool.h"
#import "WXPonyDebugger.h"
#import "WXDebugDomain.h"
#import "WXDynamicDebuggerDomain.h"

@interface WXDebugDomainController : WXDomainController <WXCommandDelegate>

@property (nonatomic, strong) WXDebugDomain *domain;

+ (WXDebugDomainController *)defaultInstance;

- (void)registerDevice;

- (void)debugDomainRegisterCallNative:(WXJSCallNative)callNativeBlock;

- (void)debugDomainRegisterCallAddElement:(WXJSCallAddElement)callAddElement;

- (void)debugDomainRegisterCallCreateBody:(WXJSCallCreateBody)callCreateBody ;

- (void)debugDomainRegisterCallRemoveElement:(WXJSCallRemoveElement)callRemoveElement ;

- (void)debugDomainRegisterCallMoveElement:(WXJSCallMoveElement)callMoveElement;

- (void)debugDomainRegisterCallUpdateAttrs:(WXJSCallUpdateAttrs)callUpdateAttrs;

- (void)debugDomainRegisterCallUpdateStyle:(WXJSCallUpdateStyle)callUpdateStyle;

- (void)debugDomainRegisterCallAddEvent:(WXJSCallAddEvent)callAddEvent;

- (void)debugDomainRegisterCallRemoveEvent:(WXJSCallRemoveEvent)callRemoveEvent;
    
- (void)debugDomainRegisterCallCreateFinish:(WXJSCallCreateFinish)callCreateFinish;

- (void)debugDomainRegisterCallNativeModule:(WXJSCallNativeModule)callNativeModuleBlock;

- (void)debugDomainRegisterCallNativeComponent:(WXJSCallNativeComponent)callNativeComponentBlock;

- (void)clearGarbage;

@end
