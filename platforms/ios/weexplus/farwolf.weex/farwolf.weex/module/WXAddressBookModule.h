//
//  WXAddressBookModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/9/24.
//
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXEventModuleProtocol.h>
#import <WeexSDK/WXModuleProtocol.h>
#import <RHAddressBook/AddressBook.h>  
@interface WXAddressBookModule : NSObject<WXModuleProtocol>
@property(nonatomic,strong)WXModuleKeepAliveCallback readcallback;
@end
