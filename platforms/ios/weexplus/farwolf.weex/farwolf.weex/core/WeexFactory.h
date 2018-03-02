//
//  WeexFactory.h
//  Pods
//
//  Created by 郑江荣 on 2017/6/1.
//
//

#import <Foundation/Foundation.h>
#import "Page.h"
#import "WXNormalViewContrller.h"
@interface WeexFactory : NSObject
+ (void)render:(NSURL *)sourceURL compelete:(void(^)(Page*))complete;
+ (void)renderNew:(NSURL *)sourceURL  compelete:(void(^)(WXNormalViewContrller*))complete  fail:(void(^)(NSString*))fail frame:(CGRect)frame isPortrait:(BOOL)isPortrait;
+(void)addCache:(NSString*)url vc:(WXNormalViewContrller*)vc;
+(WXNormalViewContrller*)getCache:(NSString*)url;
+(void)preRender:(NSURL *)sourceURL;
+(void)preRenderAll:(NSMutableArray*)urls  compelete:(void(^)())complete fail:(void(^)(NSString *))fail;
@end
