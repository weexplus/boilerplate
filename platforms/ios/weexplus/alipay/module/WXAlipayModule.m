//
//  WXAlipayModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/31.
//

#import "WXAlipayModule.h"
#import <AlipaySDK/AlipaySDK.h>
@implementation WXAlipayModule
WX_EXPORT_METHOD(@selector(open:callback:))

-(void)open:(NSString*)signstr  callback:(WXModuleCallback)callback
{
    if (signstr != nil) {
        NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
        NSDictionary *bundleUrltypes = [infoDictionary objectForKey:@"CFBundleURLTypes"];
        NSString *urlSchemes = [NSString stringWithFormat:@"%@",[bundleUrltypes objectForKey:@"CFBundleURLSchemes"]];
        if ([urlSchemes containsString:@"("] || [urlSchemes containsString:@")"] || [urlSchemes containsString:@"\n"] || [urlSchemes containsString:@" "]) {
            
            urlSchemes = [urlSchemes stringByReplacingOccurrencesOfString:@" " withString:@""];
            
            urlSchemes = [urlSchemes stringByReplacingOccurrencesOfString:@"\n" withString:@""];
            
            urlSchemes = [urlSchemes stringByReplacingOccurrencesOfString:@"(" withString:@""];
            
            urlSchemes = [urlSchemes stringByReplacingOccurrencesOfString:@")" withString:@""];
            
        }
        
        
        
 
        
        [[AlipaySDK defaultService] payOrder:signstr fromScheme:urlSchemes callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);
            callback(resultDic);
        }];
    }
}
@end
