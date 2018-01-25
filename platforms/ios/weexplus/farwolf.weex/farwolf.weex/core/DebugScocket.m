//
//  DebugScocket.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/1/20.
//

#import "DebugScocket.h"
#import "weex.h"
static UInt64 lastTime=0;
@implementation DebugScocket


+(void)reload
{
    [[Weex getDebugScocket] open:[Weex getDebugIp] port:@"8088"];
     
}

-(void)close
{
    if(self.socket!=nil)
    {
        self.socket.delegate=nil;
        [self.socket close];
        self.socket=nil;
    }
    
}

-(void)open:(NSString*)ip port:(NSString*)port
{
    
    if(self.socket!=nil)
    {
        self.socket.delegate=nil;
        [self.socket close];
        self.socket=nil;
    }
    
    //    NSString *wsport = @"9897";
    NSURL *socketURL = [NSURL URLWithString:[NSString stringWithFormat:@"ws://%@:%@/page/entry", ip, port]];
//    self.socket = [[SRWebSocket alloc] initWithURL:socketURL protocols:@[@"echo-protocol"]];
   self.socket = [[SRWebSocket alloc] initWithURLRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"ws://%@:%@/page/entry", ip, port]]]];
    self.socket.delegate = self;
    [self.socket open];
    
}


- (void)webSocketDidOpen:(SRWebSocket *)webSocket
{
    
    NSDictionary *dic= @{@"method":@"WxDebug.applyChannelId"};
    NSData *data= [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:nil];
    [webSocket send:data];
}

- (void)webSocket:(SRWebSocket *)webSocket didReceiveMessage:(id)message
{
    
    NSData* jsondata = [message dataUsingEncoding:NSUTF8StringEncoding];
    
    NSMutableDictionary *dic= [NSJSONSerialization JSONObjectWithData:jsondata options:NSJSONReadingMutableLeaves error:nil];
 
    NSString *method=dic[@"method"];
    if([@"WxDebug.pushChannelId" isEqualToString:method])
    {
        NSMutableDictionary *params= dic[@"params"];
        NSString *channelId=params[@"channelId"];
        _success(channelId);
        self.socket.delegate=nil;
        self.socket=nil;
        [self.socket close];
        
    }
 
    
}

- (void)webSocket:(SRWebSocket *)webSocket didFailWithError:(NSError *)error
{
     NSLog(@"连接不上");
    UInt64 now = [[NSDate date] timeIntervalSince1970]*1000;
    if(now-lastTime<500)
    {
        return;
    }
    else
    {
         NSLog(@"请求打开debug");
        lastTime=now;
        [[Weex getRefreshManager] send:@"opendebug"];
    }
   
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1500 * NSEC_PER_MSEC)), dispatch_get_main_queue(), ^{
        [DebugScocket reload];
    });
    
    
}

- (void)webSocket:(SRWebSocket *)webSocket didCloseWithCode:(NSInteger)code reason:(NSString *)reason wasClean:(BOOL)wasClean
{
    NSLog(@"定时重连");
   
}
- (void)webSocket:(SRWebSocket *)webSocket didReceivePong:(NSData *)pongPayload
{
     NSLog(@"定时重连");
}

@end
