//
//  WXFLogModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/4/11.
//

#import "WXFLogModule.h"
#import "farwolf.h"
#import "Weex.h"

#import <WeexSDK/WXEventModuleProtocol.h>
#import <WeexSDK/WXModuleProtocol.h>

@implementation WXFLogModule
WX_EXPORT_METHOD(@selector(log:))
-(void)log:(NSMutableDictionary*)param{
    NSString *msg=param[@"msg"];
    
    NSString *level=level;
    if(param[@"level"]){
        level=param[@"level"];
    }
    NSLog([@"weexplus:" add:msg]);
    NSString *m=[[[@"log:" add:level]add:@"level:"]add:msg];
//    NSString *m=[@"log:" add:msg];
   
    [[Weex getRefreshManager] send:m];
}
@end
