//
//  WXDeviceInfoModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/5.
//
#import <SystemConfiguration/CaptiveNetwork.h>
#import "WXDeviceInfoModule.h"
#import "AppSysInfo.h"

@implementation WXDeviceInfoModule
WX_EXPORT_METHOD_SYNC(@selector(mac))
WX_EXPORT_METHOD_SYNC(@selector(tel:))
WX_EXPORT_METHOD_SYNC(@selector(deviceId))
WX_EXPORT_METHOD_SYNC(@selector(openUrl:))

-(NSString*)mac{
    NSDictionary *dict = [self SSIDInfo];
    NSString *mac = dict[@"BSSID"];　　 //无线网的MAC地址
    return mac;
}
-(NSString*)deviceId{
    return [AppSysInfo uuid];
}
- (NSDictionary *)SSIDInfo

{
    
    NSArray *ifs = (__bridge_transfer NSArray *)CNCopySupportedInterfaces();
    
    NSDictionary *info = nil;
    
    for (NSString *ifnam in ifs) {
        
        info = (__bridge_transfer NSDictionary *)CNCopyCurrentNetworkInfo((__bridge CFStringRef)ifnam);
        
        if (info && [info count]) {
            
            break;
            
        }
        
    }
    
    return info;
    
}

-(void)tel:(NSString*)tel{
    NSMutableString * str=[[NSMutableString alloc] initWithFormat:@"tel:%@",tel];
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:str] options:nil completionHandler:^(BOOL success) {
        
    }];
}

-(void)openUrl:(NSString*)url{
//    NSString *openURL = @"http://www.baidu.com/";
    NSURL *URL = [NSURL URLWithString:url];
    [[UIApplication sharedApplication] openURL:URL];
}



@end
