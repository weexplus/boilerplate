//
//  URL.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/11/6.
//

#import "URL.h"
#import "farwolf.h"

#import "Weex.h"
@implementation URL

+(NSURL*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance
{
    if(weexInstance==nil)
    {
        int k=0;
        k++;
    }
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl:weexInstance]];
        
        return [NSURL URLWithString:url];
    }
    NSURL *n= [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL];
    return n;
}


+(NSMutableArray*)getFinalUrls:(NSMutableArray*)urls weexInstance:(WXSDKInstance*)weexInstance
{
 
    NSMutableArray *n=[NSMutableArray new];
    for(NSString* url in urls)
    {
        [n addObject:[URL getFinalUrl:url weexInstance:weexInstance]];
    }
    return n;
//
    
}






@end
