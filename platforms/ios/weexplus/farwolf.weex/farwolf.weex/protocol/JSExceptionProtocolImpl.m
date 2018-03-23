//
//  JSExceptionProtocol.m
//  Pods
//
//  Created by 郑江荣 on 2018/3/23.
//

#import "JSExceptionProtocolImpl.h"
#import "farwolf.h"
@implementation JSExceptionProtocolImpl




- (void)onJSException:(WXJSExceptionInfo*) exception
{
 
    NSLog([@"异常:" add:exception.functionName]);
}

- (void)onRuntimeCheckException:(WXRuntimeCheckException*)exception
{
     NSLog([@"异常:" add:exception.functionName]);
}
@end
