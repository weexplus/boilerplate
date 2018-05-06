//
//  WXNotifyModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/5/18.
//
//

#import <UIKit/UIKit.h>
#import "farwolf_weex.h"
static NSMutableDictionary *notifymap;
@interface WXNotifyModule: NSObject<WXModuleProtocol>
@property(nonatomic,strong)NSMutableDictionary<NSString*,WXModuleKeepAliveCallback>*callbacks;
@property(nonatomic,weak) WXSDKInstance *weexInstance;

@end

