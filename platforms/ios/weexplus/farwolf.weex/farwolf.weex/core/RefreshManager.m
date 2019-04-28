//
//  RefreshManager.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/22.
//

#import "RefreshManager.h"
#import "farwolf.h"
#import "Weex.h"

@implementation RefreshManager

+(void)reload
{
    [[Weex getRefreshManager] open:[Weex getDebugIp] port:[Weex socketPort]];
//    [self notify:@"refreshpage" value:nil];
}
-(void)send:(NSString*)msg
{
    
    if(self.hotReloadSocket.readyState==SR_OPEN){
        [self.hotReloadSocket  send:msg];
    }
}
-(void)registLog{
    [self regist:@"weexLog" method:@selector(onLogRecieve:)];
}
-(void)onLogRecieve:(NSNotification *)no{
    NSString *msg= no.userInfo[@"msg"];
    NSString *level= no.userInfo[@"level"];
    NSDate *now=[NSDate new];;
    NSString *time= [now format:@"yyyy-MM-dd HH:mm:ss"];
    msg=[[time add:@"    "]add:msg];
    NSString *m=[[[@"log:" add:level]add:@"level:"]add:msg];
    [[Weex getRefreshManager] send:m];
}
-(void)open:(NSString*)ip port:(NSString*)port
{
    
    if(self.hotReloadSocket!=nil)
    {
        self.hotReloadSocket.delegate=nil;
        [self.hotReloadSocket close];
        self.hotReloadSocket=nil;
    }
    
    //    NSString *wsport = @"9897";
    NSURL *socketURL = [NSURL URLWithString:[NSString stringWithFormat:@"ws://%@:%@", ip, port]];
    self.hotReloadSocket = [[SRWebSocket alloc] initWithURL:socketURL protocols:@[@"echo-protocol"]];
    self.hotReloadSocket.delegate = self;
    [self.hotReloadSocket open];
}

- (void)webSocket:(SRWebSocket *)webSocket didReceiveMessage:(id)message
{
    if ([@"refresh" isEqualToString:message]) {
 
        UInt64 recordTime = [[NSDate date] timeIntervalSince1970]*1000;
        if(recordTime-_lastrefresh>300)
        {
            _lastrefresh=recordTime;
            [self notify:@"refreshpage" value:nil];
        }
        else
        {
            NSLog(@"多余:d%",recordTime-_lastrefresh);
        }
        
    }
}
- (void)webSocket:(SRWebSocket *)webSocket didFailWithError:(NSError *)error
{
    NSLog(@"定时重连");
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [RefreshManager reload];
    });
    
   
}

- (void)webSocket:(SRWebSocket *)webSocket didCloseWithCode:(NSInteger)code reason:(NSString *)reason wasClean:(BOOL)wasClean
{
    NSLog(@"定时重连");
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [RefreshManager reload];
    });
}
- (void)webSocket:(SRWebSocket *)webSocket didReceivePong:(NSData *)pongPayload
{
    
}


@end

