/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */


#import "WXMonitorHandler.h"
#import <WeexSDK/WXUtility.h>

@implementation WXMonitorHandler

static WXMonitorHandler* _sharedInstance;

+ (instancetype)sharedInstance {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _sharedInstance = [[self alloc] init];
        _sharedInstance.monitorDictionary = [NSMutableDictionary new];
    });
    return _sharedInstance;
}

+ (void) resetMonitorData {
    [WXMonitorHandler.sharedInstance.monitorDictionary removeAllObjects];
}

- (void)transfer:(NSDictionary *)value {
    if (![value isKindOfClass:NSDictionary.class]) {
        return;
    }
    NSString* group = value[@"group"];
    NSString* instanceId = value[@"module"];
    if ([group isEqualToString:@"wxapm"]) {
        if (instanceId && [instanceId isKindOfClass:NSString.class]) {
            NSMutableArray *array = WXMonitorHandler.sharedInstance.monitorDictionary[instanceId];
            if (!array) {
                array = [NSMutableArray new];
                [WXMonitorHandler.sharedInstance.monitorDictionary setObject:array forKey:instanceId];
            }
            NSMutableDictionary *directory = [value mutableCopy];
            [directory removeObjectsForKeys:@[@"group",@"module"]];
            [array addObject:directory];
            [WXMonitorHandler.sharedInstance.monitorDictionary setObject:array forKey:instanceId];
        }
    }
    
}

@end

