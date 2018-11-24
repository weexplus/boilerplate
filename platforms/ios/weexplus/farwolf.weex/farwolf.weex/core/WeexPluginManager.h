//
//  WeexPluginManager.h
//  Weexplugin
//
//  Created by yangshengtao on 16/12/26.
//  Copyright © 2016年 Taobao. All rights reserved.
//

#import <Foundation/Foundation.h>
static NSMutableArray *_wxp_entry;
@interface WeexPluginManager : NSObject

+ (void)registerWeexPlugin;
+(void)initAllEntry:(NSDictionary*)lanchOption;
+(void)addEntry:(Class)cls;
@end
