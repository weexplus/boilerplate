//
//  JsonReader.h
//  pod
//
//  Created by 郑江荣 on 16/3/9.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "JsonProtocol.h"
#import "LockScreenProgress.h"
#import "AFNetworking.h"
@interface JsonReader : NSObject

@property (nonatomic) NSDictionary *param;
@property (nonatomic) NSDictionary *stream;
@property (nonatomic) NSDictionary *header;
@property (nonatomic,strong)  LockScreenProgress *p;
@property (nonatomic,strong)  NSString *url;
@property (nonatomic) NSObject *tag;
-(void)addParam:(NSString *)key value:(NSString *)value;
-(void) addParam:(NSString *)key file:(NSObject *)value;
-(void)addHeader:(NSString *)key value:(NSString *)value;
-(NSMutableArray*)toList:(NSString *)key;
-(NSString*)getUrl;
-(AFHTTPRequestOperation*)excute:( id<JsonProtocol> )j;
-(AFHTTPRequestOperation*)excute:( id<JsonProtocol>) j usePost:(BOOL)usePost;
-(AFHTTPRequestOperation*)excute:(UIViewController*)vc
      success:(void(^)(Json*j))success;
-(AFHTTPRequestOperation*)excute:(UIViewController*)vc
      success:(void(^)(Json*j))success usePost:(BOOL)usePost;
-(AFHTTPRequestOperation*)excuteFile:( id<JsonProtocol>) j;
-(AFHTTPRequestOperation*)excuteFull:(void(^)())start
          success:(void(^)(Json*j))success
             fail:(void(^)(Json*j,NSInteger code,NSString* msg))fail
        exception:(void(^)())exception
        compelete:(void(^)())compelete;
-(AFHTTPRequestOperation*)excuteFull:(void(^)())start
          success:(void(^)(Json*j))success
             fail:(void(^)(Json*j,NSInteger code,NSString* msg))fail
        exception:(void(^)())exception
        compelete:(void(^)())compelete
          usePost:(BOOL)usePost;
-(Json*)getDecoder;
-(void)setSessionId:(NSString*)sessionId;
-(AFHTTPRequestOperation*)excuteNoLimit:(void(^)())start
             success:(void(^)(Json*j))success
           exception:(void(^)())exception
           compelete:(void(^)())compelete
             usePost:(BOOL)usePost;
-(AFHTTPRequestOperation*)excuteNoLimit:(UIViewController*)vc
             success:(void(^)(Json*j))success
             usePost:(BOOL)usePost;
-(void)success:(AFHTTPRequestOperation *)operation responseObject:(id)responseObject start: (void(^)())start
       success:(void(^)(Json*j))success
          fail:(void(^)(Json*j,NSInteger code,NSString* msg))fail
     exception:(void(^)())exception
     compelete:(void(^)())compelete;
-(AFHTTPRequestOperation*)excute:(UIViewController*)vc
                         success:(void(^)(Json*j))success fail:(void(^)(Json*,NSInteger,NSString*))fail usePost:(BOOL)usePost;
@end




