//
//  WXDeviceInfoModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/5.
//
#import <SystemConfiguration/CaptiveNetwork.h>
#import "WXDeviceInfoModule.h"

@implementation WXDeviceInfoModule
WX_EXPORT_METHOD_SYNC(@selector(mac))
-(NSString*)mac{
    NSDictionary *dict = [self SSIDInfo];
    NSString *mac = dict[@"BSSID"];　　 //无线网的MAC地址
    return mac;
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
@end
