//
//  NSObject+Farwolf.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject (Farwolf)


-(void)regist:(NSString*_Nonnull)key method:(SEL _Nonnull ) method;
-(void)unregist;
-(void)unregist:(NSString*)key;
-(void)notify:(NSString*_Nonnull)key value:(NSObject*_Nullable)value;
-(void)notifyDict:(NSString*_Nonnull)key value:(NSDictionary*_Nullable)value;
-(void)save:(NSString*_Nonnull)key;
-(void)remove:(NSString*_Nonnull)key;
-(nullable id)getSaveValue:(NSString*_Nonnull)key;
-(NSUserDefaults*_Nullable)getUserDefaults;


@end
