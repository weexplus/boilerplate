//
//  WXStaticModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/14.
//
//

#import "WXStaticModule.h"

@implementation WXStaticModule
@synthesize weexInstance;

WX_EXPORT_METHOD_SYNC(@selector(get:))
WX_EXPORT_METHOD(@selector(remove:))
WX_EXPORT_METHOD(@selector(set:value:))
WX_EXPORT_METHOD_SYNC(@selector(getString:))
WX_EXPORT_METHOD(@selector(setString:value:))
-(id)get:(NSString*)key
{
    if(map==nil)
        return nil;
    return [map objectForKey:key];
}

-(void)set:(NSString*)key value:(NSObject*)v
{
    if(map==nil)
        map=[NSMutableDictionary new];
    [map setValue:v forKey:key];
}


-(void)remove:(NSString*)key
{
    [map removeObjectForKey:key];
}

-(NSString*)getString:(NSString*)key
{
    if(map==nil)
        return nil;
    return [map objectForKey:key];
}

-(void)setString:(NSString*)key value:(NSString*)v
{
    if(map==nil)
        map=[NSMutableDictionary new];
    [map setValue:v forKey:key];
}

@end
