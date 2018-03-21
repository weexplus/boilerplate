//
//  WXPrefModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/7/13.
//
//

#import "WXPrefModule.h"
#import "farwolf.h"


@implementation WXPrefModule
WX_EXPORT_METHOD_SYNC(@selector(getString:))
WX_EXPORT_METHOD_SYNC(@selector(get:))
WX_EXPORT_METHOD(@selector(remove:))
WX_EXPORT_METHOD(@selector(setString:value:))
WX_EXPORT_METHOD(@selector(set:value:))
-(void)setString:(NSString*)key value:(NSString*)v
{
    [v save:key];
}

-(void)set:(NSString*)key value:(id)v
{
     
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:v
                                                       options:NSJSONWritingPrettyPrinted // Pass 0 if you don't care about the readability of the generated string
                                                         error:nil];
    NSString *result  = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    [result save:key];
}

-(NSString*)getString:(NSString*)key
{
    return  [self getSaveValue:key];
}


-(id)get:(NSString*)key
{
    NSString *s=  [self getSaveValue:key];
    if(s==nil)
        return nil;
     NSData* jsondata = [s dataUsingEncoding:NSUTF8StringEncoding];
    
  
    NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:jsondata
                                                        options:NSJSONReadingMutableContainers
                                                          error:nil];
    return dic;
}

-(void)remove:(NSString*)key
{
//    [self remove:key];
    [@"" remove:key];
}



@end
