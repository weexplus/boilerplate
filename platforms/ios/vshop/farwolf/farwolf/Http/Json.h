//
//  Json.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
@interface Json : NSObject
@property(nonatomic,strong)  NSString* sessionId;
@property(nonatomic,strong) NSDictionary *data;
@property(nonatomic,strong) NSDictionary *resHeader;
@property(nonatomic,strong) NSString *backString;
@property(nonatomic,strong) NSObject *tag;
-(NSArray*) toList:(Class) cls data :(NSArray*) data;
//-(Jastor*) toBean:(Class) cls data:(NSDictionary*) data;
-(BOOL)isSuccess;
-(NSInteger)getErrorCode;
-(NSString*)getErrorMsg;
-(id)initWithNSData:(NSData*)d;
-(id)initWithString:(NSString *)s;
-(id)initWithDict:(NSDictionary*)d;
-(NSString*)getString:(NSString*)key;
-(NSDictionary*)getDict:(NSString*)key;
-(NSArray*)getArray:(NSString*)key;
-(NSArray*)toList:(Class)cls;
-(NSArray*)toList:(Class)cls key:(NSString*)key;
-(BOOL)hasMore;
@end
