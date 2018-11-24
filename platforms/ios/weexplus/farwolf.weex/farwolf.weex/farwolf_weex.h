//
//  farwolf_weex.h
//  farwolf.weex
//
//  Created by 郑江荣 on 2017/5/3.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "farwolf.h"
#import <WeexSDK/WXSDKInstance.h>
#import "WeexSDK/WXSDKEngine.h"
#import "WeexSDK/WXAppConfiguration.h"
#import "WeexSDK/WXImgLoaderProtocol.h"
#import "WeexSDK/WXModuleProtocol.h"
#import "WXImgLoaderDefaultImpl.h"
#import "WXNormalViewContrller.h"
#import "Weex.h"
#import "WXEventModule.h"
#import "WeexSDK/WXBaseViewController.h"
 #import "QRControl.h"
#import "WXNavBarModule.h"
#import "WXPage.h"
#import "QRControl.h"
#import "URL.h"
#import "Page.h"
#import "Config.h"
#import "WeexPluginManager.h"


#ifndef __FARWOLF_WEEX_PLUGIN_MACRO_H__
#define __FARWOLF_WEEX_PLUGIN_MACRO_H__


//#define WX_PLUGIN_Entry(name) _WX_PLUGIN_Entry(name)
//#define _WX_PLUGIN_Entry(name) \
//void __attribute__ ((constructor)) WX_PLUGIN_Entry(){ \
//}

///类注解
#define  WX_PLUGIN_Entry(ClassA) __WX_PLUGIN_Entry(ClassA)

#define __WX_PLUGIN_Entry(ClassA) \
void __attribute__ ((constructor)) WX_PLUGIN_Entry##ClassA##func(){  \
NSDictionary *params=nil;\
[WeexPluginManager addEntry: ClassA.class]; \
}


#define APP_didRegisterForRemoteNotificationsWithDeviceToken @"application_didRegisterForRemoteNotificationsWithDeviceToken"
#define APP_didReceiveRemoteNotification_fetchCompletionHandler @"app_didReceiveRemoteNotification_fetchCompletionHandler"
#define APP_didReceiveRemoteNotification @"application_didReceiveRemoteNotification"
#define APP_handleOpenURL @"application_handleOpenURL"
#define APP_openURL @"application_openURL"
#define APP_didFinishLaunchingWithOptions @"application_didFinishLaunchingWithOptions"
#define PREFIX_SDCARD @"sdcard:"

#endif
// [WeexPluginManager addEntry:ClassA]; \






@interface farwolf_weex : NSObject

@end
