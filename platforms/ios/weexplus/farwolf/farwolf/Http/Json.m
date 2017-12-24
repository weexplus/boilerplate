//
//  Json.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "YYModel.h"
#import "Json.h"
@implementation Json


-(id)initWithDict:(NSDictionary*)d
{
    self.data=d;
    return  self;
}

-(id)initWithNSData:(NSData *)d
{
    self=[super init];
    if(self)
    {
        self.data= [NSJSONSerialization JSONObjectWithData:d options:NSJSONReadingMutableLeaves error:nil];
    }
    return  self;
}

-(id)initWithString:(NSString *)s
{
    self=[super init];
    self.backString=s;
    NSData* jsondata = [s dataUsingEncoding:NSUTF8StringEncoding];
    if(self)
    {
        self.data= [NSJSONSerialization JSONObjectWithData:jsondata options:NSJSONReadingMutableLeaves error:nil];
    }
    return  self;
}
-(NSDictionary*)getDict:(NSString*)key
{
    return [self.data objectForKey:key];
}

-(NSString*)getString:(NSString*)key
{
    return [self.data objectForKey:key];
}

-(NSArray*)getArray:(NSString*)key
{
    return [self.data objectForKey:key];
}


-(BOOL)isSuccess
{
    NSInteger d=[self getErrorCode];
    //     NSLog(@"%d",d);
    return 0==d;
}

-(BOOL)hasMore
{
    return false;
}




-(NSArray*) toList:(Class) cls data :(NSArray*) data
{
    //    NSMutableArray<Jastor*> *l=[[NSMutableArray<Jastor *> alloc]init];
    //    for(int i=0;i<[data count];i++)
    //    {
    //
    //        Jastor *j=[self toBean:cls data:[data objectAtIndex:i]];
    //        [l addObject:j];
    //
    //    }
    //    return l;
    
    return  [NSArray yy_modelArrayWithClass:cls json:data];;
}
-(NSArray*)toList:(Class)cls
{
    return [NSMutableArray new];
}
-(NSArray*)toList:(Class)cls key:(NSString*)key
{
    return [NSMutableArray new];
}

//-(NSObject*) toBean:(Class) cls  data :(NSDictionary*) data
//{
//     NSObject *n= [cls new];
//
//    Jastor *j= (Jastor*)[cls new];
//    [j initWithDictionary:data];
//    return j;
//}

@end
