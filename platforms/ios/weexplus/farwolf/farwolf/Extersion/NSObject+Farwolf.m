//
//  NSObject+Farwolf.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "NSObject+Farwolf.h"

@implementation NSObject (Farwolf)


-(void)regist:(NSString*)key method:(SEL) method
{
    [[NSNotificationCenter defaultCenter]addObserver:self selector:method name:key object:nil];
}


-(void)unregist{
    [[NSNotificationCenter defaultCenter]removeObserver:self];
  
}

-(void)unregist:(NSString*)key
{
 
    [[NSNotificationCenter defaultCenter] removeObserver:self name:key object:nil];
}

-(void)notify:(NSString*)key value:(NSObject*)value
{
    [[NSNotificationCenter defaultCenter]postNotificationName:key object:value];
    
}



-(void)notifyDict:(NSString*)key value:(NSDictionary*)value
{
    NSNotification *n=[[NSNotification alloc]initWithName:key object:nil userInfo:value];
    [[NSNotificationCenter defaultCenter]postNotification:n];
}



-(void)save:(NSString*)key
{
    NSUserDefaults *n=[self getUserDefaults];
    
    [n setValue:self forKey:key];
    [n synchronize];
}

-(void)remove:(NSString*)key
{
    NSUserDefaults *n=[self getUserDefaults];
    
    [n removeObjectForKey:key];
    [n synchronize];
    
}
-(nullable id)getSaveValue:(NSString*)key
{
    return  [[self getUserDefaults] objectForKey:key];
}

-(NSUserDefaults*)getUserDefaults
{
    return [NSUserDefaults standardUserDefaults];
}


@end
