/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import "WXDebugDomainController.h"
#import "WXDevToolType.h"
#import "WXDeviceInfo.h"
#import "WXDebuggerUtility.h"
#import <WeexSDK/WeexSDK.h>

#define SYNCRETURN @"WxDebug.syncReturn"

@implementation WXDebugDomainController {
    NSThread    *_bridgeThread;
    WXJSCallNative  _nativeCallBlock;
    WXJSCallAddElement _callAddElementBlock;
    WXJSCallCreateBody _callCreateBodyBlock;
    WXJSCallRemoveElement _callRemoveElementBlock;
    WXJSCallMoveElement _callMoveElementBlock;
    WXJSCallUpdateAttrs _callUpdateAttrsBlock;
    WXJSCallUpdateStyle _callUpdateStyleBlock;
    WXJSCallAddEvent _callAddEventBlock;
    WXJSCallRemoveEvent _callRemoveEventBlock;
    WXJSCallCreateFinish _callCreateFinishBlock;
    WXJSCallNativeModule _nativeModuleBlock;
    WXJSCallNativeComponent _nativeComponentBlock;
}

@dynamic domain;

+ (WXDebugDomainController *)defaultInstance {
    static WXDebugDomainController *defaultInstance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        defaultInstance = [[WXDebugDomainController alloc] init];
    });
    return defaultInstance;
}

- (id)init;
{
    self = [super init];
    if (!self) {
        return nil;
    }
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(notificationIsDebug:) name:@"WXDevtoolDebug" object:nil];
    
    return self;
}

+ (Class)domainClass {
    return [WXDebugDomain class];
}

+ (NSDictionary *)getLogLevelMap {
    NSDictionary *logLevelEnumToString =
    @{
      @"all":@(WXLogLevelDebug),
      @"error":@(WXLogLevelError),
      @"warn":@(WXLogLevelWarning),
      @"info":@(WXLogLevelInfo),
      @"log":@(WXLogLevelLog),
      @"debug":@(WXLogLevelDebug),
      @"off":@(WXLogLevelOff)
      };
    return logLevelEnumToString;
}

- (void)registerDevice {
    NSString *deviceID = [WXDeviceInfo getDeviceID];
    NSString *machine = [WXDeviceInfo deviceName] ? : @"";
    NSString *appName = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleDisplayName"] ?: [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleName"];
    NSMutableDictionary *parameters = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                                       deviceID, @"deviceId",
                                       @"iOS", @"platform",
                                       machine, @"model",
                                       [WXSDKEngine SDKEngineVersion],@"weexVersion",
                                       [WXDevTool WXDevtoolVersion],@"devtoolVersion",
                                       appName, @"name",
                                       [WXLog logLevelString] ?: @"error",@"logLevel",
                                       [NSNumber numberWithBool:[WXDevToolType isDebug]],@"remoteDebug",
                                       nil];
    [self _registerDeviceWithParams:parameters];
}

- (void)debugDomainRegisterCallNative:(WXJSCallNative)callNativeBlock {
    [self _initBridgeThread];
    _nativeCallBlock = callNativeBlock;
}

- (void)debugDomainRegisterCallAddElement:(WXJSCallAddElement)callAddElement {
    _callAddElementBlock = callAddElement;
}

- (void)debugDomainRegisterCallCreateBody:(WXJSCallCreateBody)callCreateBody {
    _callCreateBodyBlock = callCreateBody;
}

- (void)debugDomainRegisterCallRemoveElement:(WXJSCallRemoveElement)callRemoveElement {
    _callRemoveElementBlock = callRemoveElement;
}

- (void)debugDomainRegisterCallMoveElement:(WXJSCallMoveElement)callMoveElement {
    _callMoveElementBlock = callMoveElement;
}

- (void)debugDomainRegisterCallUpdateAttrs:(WXJSCallUpdateAttrs)callUpdateAttrs {
    _callUpdateAttrsBlock = callUpdateAttrs;
}

- (void)debugDomainRegisterCallUpdateStyle:(WXJSCallUpdateStyle)callUpdateStyle {
    _callUpdateStyleBlock = callUpdateStyle;
}

- (void)debugDomainRegisterCallAddEvent:(WXJSCallAddEvent)callAddEvent {
    _callAddEventBlock = callAddEvent;
}

- (void)debugDomainRegisterCallRemoveEvent:(WXJSCallRemoveEvent)callRemoveEvent {
    _callRemoveEventBlock = callRemoveEvent;
}
    
- (void)debugDomainRegisterCallCreateFinish:(WXJSCallCreateFinish)callCreateFinish {
    _callCreateFinishBlock = callCreateFinish;
}

- (void)debugDomainRegisterCallNativeModule:(WXJSCallNativeModule)callNativeModuleBlock {
    _nativeModuleBlock = callNativeModuleBlock;
}

- (void)debugDomainRegisterCallNativeComponent:(WXJSCallNativeComponent)callNativeComponentBlock {
    _nativeComponentBlock = callNativeComponentBlock;
}

- (void)clearGarbage {
    _nativeCallBlock = nil;
    _callAddElementBlock = nil;
    _nativeModuleBlock = nil;
    _nativeComponentBlock = nil;
}

#pragma mark - private methods
- (void)_initBridgeThread {
    _bridgeThread = [NSThread currentThread];
}

- (void)_executeBridgeThead:(dispatch_block_t)block
{
    if([NSThread currentThread] == _bridgeThread) {
        block();
    } else if (_bridgeThread){
        [self performSelector:@selector(_executeBridgeThead:)
                     onThread:_bridgeThread
                   withObject:[block copy]
                waitUntilDone:NO];
    }
}

- (void)_registerDeviceWithParams:(id)params {
    NSDictionary *obj = [[NSDictionary alloc] initWithObjectsAndKeys:
                         @"WxDebug.registerDevice", @"method",
                         [params WX_JSONObject], @"params",
                         [NSNumber numberWithInt:0],@"id",
                         nil];
    
    NSData *data = [NSJSONSerialization dataWithJSONObject:obj options:0 error:nil];
    NSString *encodedData = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
    [[WXDebugger defaultInstance] sendDebugMessage:encodedData onBridgeThread:_bridgeThread ? YES : NO];
}

#pragma mark - notification

- (void)notificationIsDebug:(NSNotification *)notification {
    if ([notification.object boolValue]) {
        _bridgeThread = nil;
    }
}

#pragma mark - WXCommandDelegate
- (void)domain:(WXDynamicDebuggerDomain *)domain enableWithCallback:(void (^)(id error))callback {
    dispatch_async(dispatch_get_main_queue(), ^{
        [WXDevToolType setDebug:YES];
        [WXSDKEngine restart];
    });
    [self performSelector:@selector(enableWithCallback:) withObject:callback afterDelay:1];
}

-(void)enableWithCallback:(void (^)(id error))callback{
    [[NSNotificationCenter defaultCenter] postNotificationName:@"RefreshInstance" object:nil];
    callback(nil);
}

- (void)domain:(WXDynamicDebuggerDomain *)domain disableWithCallback:(void (^)(id error))callback {
    dispatch_async(dispatch_get_main_queue(), ^{
        [WXDevToolType setDebug:NO];
        [WXSDKEngine restart];
        [self clearGarbage];
    });
    [self performSelector:@selector(disableWithCallback:) withObject:callback afterDelay:1];
}

-(void)disableWithCallback:(void (^)(id error))callback{
    [[NSNotificationCenter defaultCenter] postNotificationName:@"RefreshInstance" object:nil];
    callback(nil);
}

- (void)domain:(WXDebugDomain *)domain sendLogLevel:(NSString *)level withCallback:(void (^)(id error))callback {
    NSDictionary *logLevelMap = [WXDebugDomainController getLogLevelMap];
    WXLogLevel wxLogLevel = [[logLevelMap objectForKey:level] integerValue];
    [WXLog setLogLevel:wxLogLevel];
    callback(nil);
}

- (void)domain:(WXDebugDomain *)domain setInspectorMode:(NSString *)mode withCallback:(void (^)(id error))callback {
    if ([mode isEqualToString:@"native"]) {
        [WXDebugger setVDom:NO];
    } else if ([mode isEqualToString:@"vdom"]) {
        [WXDebugger setVDom:YES];
    }
}

- (void)domain:(WXDynamicDebuggerDomain *)domain refreshCallback:(void (^)(id error))callback {
    dispatch_async(dispatch_get_main_queue(), ^{
        [[NSNotificationCenter defaultCenter] postNotificationName:@"RefreshInstance" object:nil];
    });
    callback(nil);
}

- (void)domain:(WXDynamicDebuggerDomain *)domain reloadCallback:(void (^)(id error))callback {
    dispatch_async(dispatch_get_main_queue(), ^{
        [WXSDKEngine restart];
        [[NSNotificationCenter defaultCenter] postNotificationName:@"RefreshInstance" object:nil];
    });
}

- (void)domain:(WXDynamicDebuggerDomain *)domain enableNetwork:(BOOL)enable networkCallback:(void (^)(id error))callback {
    WXDebugger *debugger = [WXDebugger defaultInstance];
    if (enable) {
        [debugger enableNetworkTrafficDebugging];
        [debugger forwardAllNetworkTraffic];
    }
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callNative:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    [self _executeBridgeThead:^{
        NSString *instanceId = jsModule[@"instance"];
        NSArray *methods = jsModule[@"tasks"];
        NSString *callbackId = jsModule[@"callback"];
        
        // params parse
        if(!methods || methods.count <= 0){
            return;
        }
        //call native
        WXLogInfo(@"Calling native... instancdId:%@, methods:%@, callbackId:%@", instanceId, [WXUtility JSONString:methods], callbackId);
        if(_nativeCallBlock){
            _nativeCallBlock(instanceId, methods, callbackId);
        }
        callback(nil);
    }];
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callAddElement:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    [self _executeBridgeThead:^{
        NSString *instanceId = jsModule[@"instance"] ? : @"";
        NSDictionary *componentData = jsModule[@"dom"] ? : [NSDictionary dictionary];
        NSString *parentRef = jsModule[@"ref"] ? : @"";
        NSNumber *index = jsModule[@"index"] ? : [NSNumber numberWithInteger:0];
        NSInteger insertIndex = index.integerValue;
        
        WXLogDebug(@"callAddElement...%@, %@, %@, %ld", instanceId, parentRef, componentData, (long)insertIndex);
        _callAddElementBlock(instanceId, parentRef, componentData, insertIndex);
        callback(nil);
    }];
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callCreateBody:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callRemoveElement:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callMoveElement:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callUpdateAttrs:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callUpdateStyle:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callAddEvent:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    
}

- (void)domain:(WXDynamicDebuggerDomain *)domain callRemoveEvent:(NSDictionary *)jsModule callBack:(void (^)(id error))callback {
    
}

- (void)domain:(WXDynamicDebuggerDomain *)domain syncCall:(NSDictionary *)data callBack:(void (^)(NSDictionary *result, id error))callback; {
    [self _executeBridgeThead:^{
        NSArray *args = [data objectForKey:@"args"];
        NSString *method = [data objectForKey:@"method"];
        NSString *syncId = [data objectForKey:@"syncId"];
        NSMutableDictionary *result = [[NSMutableDictionary alloc] init];
        [result setObject:SYNCRETURN forKey:@"method"];
        NSError *error = nil;
        if ([method isEqualToString:@"callNativeModule"]) {
            NSString *instanceIdString = args[0] ? : @"";
            NSString *moduleNameString = args[1] ? : @"";
            NSString *methodNameString = args[2] ? : @"";
            NSArray *argsArray = args[3] ? : [NSArray array];
            NSDictionary *optionsDic = [args[4] isKindOfClass:[NSDictionary class]]? args[4]: [NSDictionary dictionary];
            
            WXLog(@"callNativeModule...%@,%@,%@,%@", instanceIdString, moduleNameString, methodNameString, argsArray);
            if(_nativeModuleBlock){
                NSInvocation *invocation = _nativeModuleBlock(instanceIdString, moduleNameString, methodNameString, argsArray, optionsDic);
                id object = [WXDebuggerUtility switchInvocationReture:invocation];
                NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
                [params setObject:syncId forKey:@"syncId"];
                if (object) {
                    [params setObject:object forKey:@"ret"];
                }else {
                    error = [[NSError alloc] init];
                }
                [result setObject:params forKey:@"params"];
            }else{
                error = [NSError errorWithDomain:(NSErrorDomain)@"callNativeModule error" code:500 userInfo:nil];
            }
        }else if ([method isEqualToString:@"callNativeComponent"]) {
            NSString *instanceIdString = args[0] ? : @"";
            NSString *componentNameString = args[1] ? : @"";
            NSString *methodNameString = args[2] ? : @"";
            NSArray *argsArray = args[3] ? : [NSArray array];
            NSDictionary *optionsDic = args[4] ? : [NSDictionary dictionary];
            
            WXLog(@"callNativeComponent...%@,%@,%@,%@", instanceIdString, componentNameString, methodNameString, argsArray);
            
            _nativeComponentBlock(instanceIdString, componentNameString, methodNameString, argsArray, optionsDic);
            NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
            [result setObject:params forKey:@"params"];
        }else if([method isEqualToString:@"extendCallNative"]) {
            id value = [NSDictionary new];
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"
            Class propertyClass = NSClassFromString(@"WXExtendCallNativeManager");
            SEL sel =NSSelectorFromString(@"sendExtendCallNativeEvent:");
            if(propertyClass && [propertyClass respondsToSelector:sel]){
                value = [propertyClass performSelector:sel withObject:data[@"value"]];
            }
#pragma clang diagnostic pop
            NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
            [params setObject:value forKey:@"ret"];
            [result setObject:params forKey:@"params"];
        } else if([method isEqualToString:@"btoa"]) {
            NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
            NSData *nsdata = [data[@"value"]
                              dataUsingEncoding:NSUTF8StringEncoding];
            NSString *base64Encoded = [nsdata base64EncodedStringWithOptions:0];
            [params setObject:base64Encoded forKey:@"ret"];
            [result setObject:params forKey:@"params"];
        } else if([method isEqualToString:@"atob"]) {
            NSData *nsdataFromBase64String = [[NSData alloc]
                                              initWithBase64EncodedString:data[@"value"] options:0];
            NSString *base64Decoded = [[NSString alloc]
                                       initWithData:nsdataFromBase64String encoding:NSUTF8StringEncoding];
            NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
            [params setObject:base64Decoded forKey:@"ret"];
            [result setObject:params forKey:@"params"];
        }
        callback(result, error);
    }];
}



@end
