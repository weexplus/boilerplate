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
#import "IQKeyboardReturnKeyHandler.h"
@interface WXNormalViewContrller : UIViewController<SRWebSocketDelegate>
//- (instancetype)initWith:(NSString *)url;
//@property (nonatomic, strong) IQKeyboardReturnKeyHandler    *returnKeyHandler;
 
@property (strong, nonatomic) UIView *toolView;
@property(strong,nonatomic) UIButton *set;
@property(strong,nonatomic) UIButton *refresh;
@property(nonatomic)BOOL isInView;
@property(nonatomic)BOOL isLanscape;
@property(nonatomic)BOOL debug;

@property(nonatomic) CGPoint beginpoint;

@property (strong,nonatomic)   typeof(void(^)(NSObject*)) nativeCallback;
@property (nonatomic, weak) UIViewController *setVc;
@property (nonatomic, weak) WXSDKInstance *instance;
@property (nonatomic, strong) UIView *weexView;
@property (nonatomic, strong) NSString *pageid;
@property (nonatomic, strong) NSString *key;
@property (nonatomic, strong) NSURL *sourceURL;
@property (nonatomic, strong) SRWebSocket *hotReloadSocket;
@property (nonatomic) BOOL freeFrame;
@property (nonatomic, strong) UIView *fail_layout;
@property (nonatomic, strong) NSMutableDictionary *param;
@property (nonatomic, weak) Page *page;
@property (nonatomic) NSString* navbarVisibility;
@property (strong,nonatomic)  WXModuleKeepAliveCallback callback;
@property (nonatomic, strong) NSMutableArray *textfields;
@property (nonatomic) int naviIndex;
- (instancetype)initWithSourceURL:(NSURL *)sourceURL;
- (void)preRender:(NSString *)url finish:(void(^)())finish;
- (void)interfaceOrientation:(UIInterfaceOrientation)orientation;


- (void)refreshWeex;
-(void)openWatch:(NSString*)ip;
-(void)openQR;
-(void)loadCompelete;
-(void)onaddWeexView;
-(void)onCreateWeexView;
-(void)resetFrame;
-(void)loadtextfields;
+(BOOL)showError;
+(void)setShowError:(BOOL)show;
@end
