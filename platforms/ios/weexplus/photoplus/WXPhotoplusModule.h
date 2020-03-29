//
//  WXPhotoplusModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2019/5/28.
//

#import <Foundation/Foundation.h>
#import "farwolf.h"
#import "farwolf_weex.h"
#import <WeexPluginLoader/WeexPluginLoader.h>
#import <WeexSDK/WXEventModuleProtocol.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "UploadImage.h"
@interface WXPhotoplusModule : NSObject <WXModuleProtocol,ImageSelectDelegate>
@property(nonatomic,strong)UploadImage *uploadImage;
@property(nonatomic,strong)WXModuleCallback callback;
@property(nonatomic)CGFloat maxSize;
@end


