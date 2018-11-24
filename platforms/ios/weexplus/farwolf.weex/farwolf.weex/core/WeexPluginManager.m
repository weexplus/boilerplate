//
//  WeexPluginManager.m
//  Weexplugin
//
//  Created by yangshengtao on 16/12/26.
//  Copyright © 2016年 Taobao. All rights reserved.
//

#import "WeexPluginManager.h"
//#import "WeexPluginLoader.h"
#import <WeexSDK/WeexSDK.h>

@implementation WeexPluginManager

+ (void)registerWeexPlugin
{
//    NSArray *pluginNames = [NSArray arrayWithArray:[WeexPluginLoader getPlugins]];
//    if (!pluginNames) {
//        return;
//    }
//    [pluginNames enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
//        NSDictionary *pluginInfo = (NSDictionary *)obj;
//        if ([pluginInfo[@"category"] isEqualToString:@"handler"] && pluginInfo[@"protocol"]) {
//
//            [WXSDKEngine registerHandler:[NSClassFromString(pluginInfo[@"ios-package"]) new]
//                            withProtocol:NSProtocolFromString(pluginInfo[@"protocol"])];
//        }else if ([pluginInfo[@"category"] isEqualToString:@"component"] && pluginInfo[@"ios-package"]) {
//            [WXSDKEngine registerComponent:pluginInfo[@"api"] withClass:NSClassFromString(pluginInfo[@"ios-package"])];
//        }else if ([pluginInfo[@"category"] isEqualToString:@"module"] && pluginInfo[@"ios-package"]) {
//            [WXSDKEngine registerModule:pluginInfo[@"api"] withClass:NSClassFromString(pluginInfo[@"ios-package"])];
//        }
//    }];
}
+(void)addEntry:(Class)cls  
{
   
    if(_wxp_entry==nil)
        _wxp_entry=[NSMutableArray new];
    [_wxp_entry addObject:cls];
}

+(void)initAllEntry:(NSDictionary*)lanchOption{

    dispatch_async(dispatch_get_main_queue(), ^{
        for(Class cls in _wxp_entry) {
            id car  = [[cls alloc] init];
            [car performSelector:(@selector(initEntry:)) withObject:lanchOption];
        }
    });
   
}

@end
