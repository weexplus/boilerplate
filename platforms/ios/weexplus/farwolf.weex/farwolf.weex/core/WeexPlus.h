//
//  WeexPlus.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/9.
//

#import <Foundation/Foundation.h>
#import "URL.h"
#import "Weex.h"
//#import "WXTracingManager.h"
#import <UIKit/UIKit.h>


@interface WeexPlus : NSObject
+(UIWindow*)init:(NSDictionary*)lanch;
+(void)addDebugBtn;
@end


