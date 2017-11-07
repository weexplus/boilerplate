//
//  WXNormalViewContrller.h
//  farwolf.weex
//
//  Created by 郑江荣 on 2017/5/3.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <WeexSDK/WXSDKInstance.h>
#import "WeexSDK/WXSDKEngine.h"
#import "WeexSDK/WXAppConfiguration.h"
#import "WeexSDK/WXLog.h"
#import "WeexSDK/WXImgLoaderProtocol.h"
#import "farwolf.h"
#import "WeexSDK/WXBaseViewController.h"
#import <SRWebSocket.h>
#import "ErrorControl.h"
#import "Page.h"
@interface WXNormalViewContrller : UIViewController<SRWebSocketDelegate>
//- (instancetype)initWith:(NSString *)url;

@property (strong,nonatomic)   typeof(void(^)(NSObject*)) nativeCallback;
 
@property (nonatomic, strong) WXSDKInstance *instance;
@property (nonatomic, strong) UIView *weexView;
@property (nonatomic, strong) NSString *pageid;
@property (nonatomic, strong) NSURL *sourceURL;
@property (nonatomic, strong) SRWebSocket *hotReloadSocket;
@property (nonatomic)BOOL freeFrame;
@property (nonatomic, strong) UIView *fail_layout;
@property (nonatomic, strong) NSMutableDictionary *param;
@property (nonatomic, strong) Page *page;
@property (nonatomic) NSString* navbarVisibility;
@property (strong,nonatomic)  WXModuleKeepAliveCallback callback;

- (instancetype)initWithSourceURL:(NSURL *)sourceURL;
- (void)preRender:(NSString *)url finish:(void(^)())finish;

 
- (void)refreshWeex;
-(void)openWatch:(NSString*)ip;
-(void)openQR;
-(void)loadCompelete;
-(void)onaddWeexView;
-(void)onCreateWeexView;
-(void)resetFrame;
@end
