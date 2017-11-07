//
//  WXNavBarModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/5/5.
//
//

#import <Foundation/Foundation.h>
#import "farwolf_weex.h"
@interface WXNavBarModule : NSObject<WXModuleProtocol>
@property(nonatomic,strong)WXModuleKeepAliveCallback rightClickCallback;
@property(nonatomic,strong)WXModuleKeepAliveCallback leftClickCallback;
@end
