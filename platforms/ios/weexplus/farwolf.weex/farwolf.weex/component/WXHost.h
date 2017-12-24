//
//  WXHost.h
//  Pods
//
//  Created by 郑江荣 on 2017/10/3.
//
//

#import <WeexSDK/WeexSDK.h>

@interface WXHost : WXComponent
@property(nonatomic,strong)NSMutableArray *items;
@property(nonatomic,strong)UIViewController* host;

@property(nonatomic)int index;
@end
