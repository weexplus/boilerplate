//
//  WXFImageComponent.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/13.
//
//

#import "WXFImageComponent.h"
#import "farwolf.h"
#import "Weex.h"

@implementation WXFImageComponent


- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    
    NSMutableDictionary *temp=[[NSMutableDictionary alloc]initWithDictionary:attributes];
    BOOL change=false;
//    if (attributes[@"placeholder"])
//    {
//        NSString *ps= [[WXConvert NSString:attributes[@"placeholder"]] stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
//        
//        if([ps startWith:@"root:"])
//        {
////            NSString *nurl= [self getUrl:ps weexInstance:weexInstance];
//             NSString *nurl=[ps replace:@"root:" withString:@"app/"];
//            if([temp.allKeys containsObject:@"placeholder"])
//            {
//                change=true;
//                [temp removeObjectForKey:@"placeholder"];
//                [temp setValue:nurl forKey:@"placeholder"];
//            }
//        }
//          [self configPlaceHolder:temp];
//        
//        
//    }
    
    if (attributes[@"src"])
    {
        NSString *s= [[WXConvert NSString:attributes[@"src"]] stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
        NSLog([@"image:" add: s]);
        if([s startWith:@"root:"])
        {
            NSString *url= [self getUrl:s weexInstance:weexInstance];
            
            if([temp.allKeys containsObject:@"src"])
            {
                change=true;
                [temp removeObjectForKey:@"src"];
                [temp setValue:url forKey:@"src"];
            }
        }
        
        
        
    }
    
    if(!change)
    {
        return [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance];
        
    }
    else
    {
        return [super initWithRef:ref type:type styles:styles attributes:temp events:events weexInstance:weexInstance];
    }
    
    return self;
}

-(NSString*)getUrl:(NSString*)s weexInstance:(WXSDKInstance *)weexInstance
{
    NSString *url= weexInstance.scriptURL.absoluteString;
    s=[s replace:@"root:" withString:[Weex getBaseUrl:weexInstance]];
    
    
    return s;
    
}
@end
