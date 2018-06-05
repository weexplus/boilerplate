//
//  WXPayModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/3.
//

#import "WXPayModule.h"
#import "WXApi.h"

@implementation WXPayModule
WX_EXPORT_METHOD(@selector(open:callback:))
-(void)open:(NSDictionary*)param  callback:(WXModuleCallback)callback
{

    PayReq *request =[PayReq yy_modelWithDictionary:param];
    request.de
    [WXApi sendReq:request];
 
}

-(void) onReq:(BaseReq*)req
{
    
}
@end
