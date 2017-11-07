//
//  DebugViewControl.h
//  Pods
//
//  Created by 郑江荣 on 2017/9/30.
//
//

#import "farwolf_weex.h"

@interface DebugViewControl : WXNormalViewContrller
@property(strong,nonatomic)UIButton *set;
@property(strong,nonatomic)UIButton *refresh;
-(NSString*)getUrl;
@end
