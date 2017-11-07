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

+(NSString*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance
{
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl]];
        return url;
    }
    return [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL].absoluteString;
}








@end
