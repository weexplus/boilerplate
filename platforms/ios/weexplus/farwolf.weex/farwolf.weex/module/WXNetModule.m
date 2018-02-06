//
//  WXNetModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/19.
//
//

#import "WXNetModule.h"
#import "FileReader.h"

@implementation WXNetModule
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(post:param:header:start:success:compelete:exception:))
WX_EXPORT_METHOD(@selector(postJson:param:header:start:success:compelete:exception:))
WX_EXPORT_METHOD(@selector(get:param:header:start:success:compelete:exception:))
WX_EXPORT_METHOD(@selector(postFile:param:header:path:start:success:compelete:exception:))
-(void)fetch:(BOOL)usepost url:(NSString*)url param:(NSDictionary*)param header:(NSDictionary*)header start:(WXModuleKeepAliveCallback)start exception:(WXModuleKeepAliveCallback)exception success:(WXModuleKeepAliveCallback)success compelete:(WXModuleKeepAliveCallback)compelete
{
    JsonReader *j=[JsonReader new];
    j.url=url;
    j.header=header;
    j.param=param;
   
    [j excuteNoLimit:^{
        start(@{},false);
    } success:^(Json *j) {
        NSMutableDictionary *dic=j.resHeader;
        if(dic==nil)
        {
            dic=[NSMutableDictionary new];
        }
        if(j.sessionId==nil)
        {
            j.sessionId=@"";
        }
        success(@{@"res":j.data,@"sessionid":j.sessionId,@"headers":dic},false);
    } exception:^{
        exception(@{},false);
    } compelete:^{
         compelete(@{},false);
    } usePost:usepost];
}




-(void)post:(NSString*)url param:(NSDictionary*)param header:(NSDictionary*)header start:(WXModuleKeepAliveCallback)start  success:(WXModuleKeepAliveCallback)success  compelete:(WXModuleKeepAliveCallback)compelete exception:(WXModuleKeepAliveCallback)exception
{
    [self fetch:true url:url param:param header:header start:start exception:exception success:success compelete:compelete];
}


-(void)get:(NSString*)url param:(NSDictionary*)param header:(NSDictionary*)header start:(WXModuleKeepAliveCallback)start  success:(WXModuleKeepAliveCallback)success  compelete:(WXModuleKeepAliveCallback)compelete exception:(WXModuleKeepAliveCallback)exception
{
    [self fetch:false url:url param:param header:header start:start exception:exception success:success compelete:compelete];
}


-(void)postFile:(NSString*)url param:(NSDictionary*)param header:(NSDictionary*)header path:(NSDictionary*)path start:(WXModuleKeepAliveCallback)start  success:(WXModuleKeepAliveCallback)success  compelete:(WXModuleKeepAliveCallback)compelete exception:(WXModuleKeepAliveCallback)exception
{
    FileReader *f=[[FileReader alloc]initWith:@""];;
    f.param=param;
    f.header=header;
    NSMutableDictionary *d=   path;
    for(NSString *key in d.allKeys)
    {
        NSString *v=d[key];
        v=[v replace:@"sdcard:" withString:@""];
        UIImage *img=[self getDocumentImage:v];
        [f addParam:key file:img];
        
    }
    [f excuteFile:url start:^{
         start(@{},false);
    } success:^(NSString *s,NSString *sessionid) {
        
        NSData* jsondata = [s dataUsingEncoding:NSUTF8StringEncoding];
        
         id dx=   [NSJSONSerialization JSONObjectWithData:jsondata options:NSJSONReadingMutableLeaves error:nil];
          success(@{@"res":dx,@"sessionid":sessionid},false);
    } exception:^{
        exception(@{},false);
    } compelete:^{
        compelete(@{},false);
    }];
    
}


-(void)postJson:(NSString*)url param:(NSDictionary*)param header:(NSDictionary*)header start:(WXModuleKeepAliveCallback)start  success:(WXModuleKeepAliveCallback)success  compelete:(WXModuleKeepAliveCallback)compelete exception:(WXModuleKeepAliveCallback)exception
{
    

//     NSString *url=p[@"url"];
//     NSDictionary *param=p[@"param"];
//     NSDictionary *header=p[@"header"];
//     WXModuleKeepAliveCallback start=p[@"start"];
//     WXModuleKeepAliveCallback success=p[@"success"];
//     WXModuleKeepAliveCallback compelete=p[@"compelete"];
//     WXModuleKeepAliveCallback exception=p[@"exception"];
 
    
    NSData *postData =    [NSJSONSerialization dataWithJSONObject:param options:NSJSONWritingPrettyPrinted error:nil];
    AFURLSessionManager *manager = [[AFURLSessionManager alloc] initWithSessionConfiguration:[NSURLSessionConfiguration defaultSessionConfiguration]];
     [[AFJSONRequestSerializer serializer]  setHTTPShouldHandleCookies:YES];
    
//     AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    
    NSMutableURLRequest *request = [[AFJSONRequestSerializer serializer] requestWithMethod:@"POST" URLString:url parameters:nil error:nil];
    request.timeoutInterval= [[[NSUserDefaults standardUserDefaults] valueForKey:@"timeoutInterval"] longValue];
    [request setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    [request setValue:@"application/json" forHTTPHeaderField:@"Accept"];
    [request setHTTPBody:postData];
    
    start(@{},false);
    [[manager dataTaskWithRequest:request completionHandler:^(NSURLResponse * _Nonnull response, id  _Nullable responseObject, NSError * _Nullable error) {
        @try {
            //            NSLog(@"Success: %@", [responseObject class]);
            
            NSData *data =    [NSJSONSerialization dataWithJSONObject:responseObject options:NSJSONWritingPrettyPrinted error:nil];
            NSString *result = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
            NSLog(result);
//            NSHTTPURLResponse* response = operation.response;
        
//            NSString  *cookie=response.allHeaderFields[@"Set-Cookie"];
            NSData* jsondata = [result dataUsingEncoding:NSUTF8StringEncoding];
            id dx=   [NSJSONSerialization JSONObjectWithData:jsondata options:NSJSONReadingMutableLeaves error:nil];
            success(@{@"res":dx,@"sessionid":@""},false);
            
        }
        @catch (NSException *excep) {
            
            exception(@{},false);
        }
        @finally {
            compelete(@{},false);
        }

    }] resume];
}


// 读取并存贮到相册
-(UIImage *)getDocumentImage:(NSString*)path{
    // 读取沙盒路径图片
    //    NSString *aPath3=[NSString stringWithFormat:@"%@/Documents/%@.png",NSHomeDirectory(),@"test"];
    // 拿到沙盒路径图片
    UIImage *imgFromUrl3=[[UIImage alloc]initWithContentsOfFile:path];
    // 图片保存相册
//    UIImageWriteToSavedPhotosAlbum(imgFromUrl3, self, nil, nil);
    return imgFromUrl3;
}
@end
