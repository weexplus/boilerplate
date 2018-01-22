//
//  DebugScocket.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/1/20.
//

#import <Foundation/Foundation.h>
#import <SocketRocket/SRWebSocket.h>
typedef void(^debug)(NSString*);
typedef void(^fail)();
@interface DebugScocket : NSObject<SRWebSocketDelegate>
@property (nonatomic, strong) SRWebSocket *socket;
@property (strong,nonatomic)   debug  success;
@property (strong,nonatomic)   fail  fail;
-(void)open:(NSString*)ip port:(NSString*)port;
+(void)reload;
-(void)close;
@end
