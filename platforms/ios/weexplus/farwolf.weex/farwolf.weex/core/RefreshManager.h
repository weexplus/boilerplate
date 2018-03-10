//
//  RefreshManager.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/22.
//

#import <UIKit/UIKit.h>
#import <SocketRocket/SRWebSocket.h>

@interface RefreshManager :NSObject<SRWebSocketDelegate>

@property(nonatomic,strong) NSString* ip;
@property(nonatomic,strong) NSString* port;
@property(nonatomic) UInt64 lastrefresh;
@property (nonatomic, strong) SRWebSocket *hotReloadSocket;
//(NSString*)ip port:(NSString*)port
-(void)open:(NSString*)ip port:(NSString*)port;
+(void)reload;
-(void)send:(NSString*)msg;
@end
