//
//  FileReader.m
//  oa
//
//  Created by 郑江荣 on 16/5/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "FileReader.h"
#import "AFNetworking.h"
#import "FileJson.h"


NSString * const _URL = @"http://doc2.renturbo.com/upload/";
@implementation FileReader

-(NSString*)getUrl
{
    return  [_URL stringByAppendingString:self.weg];
}

-(id)initWith:(NSString*)weg
{
    self=[super init];
    if(self)
    {
        self.weg=weg;
    }
    return self;
}

 

-(void)postFile
{
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    // 这行最好加上
//    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObject:@"multipart/form-data"];
    
    // 这里的image就是我从相册拿到的图片，压缩了一下
//    NSData *data = UIImageJPEGRepresentation(image, 0.7);
    
    
 NSString* url=[self getUrl];
    
    
    
    // 发送请求 urlstr是地址
    [manager POST:url parameters:self.param constructingBodyWithBlock:^(id<AFMultipartFormData> formData) {
        
        NSArray *nx= self.stream.allKeys;
        
        for(NSString *key in nx)
        {
            NSObject *o= self.stream[key];
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            formatter.dateFormat = @"yyyyMMddHHmmss";
            NSString *str = [formatter stringFromDate:[NSDate date]];
            NSString *fileName = [NSString stringWithFormat:@"%@.png", str];
            NSData *data = UIImageJPEGRepresentation((UIImage*)o, 1.0);
            [formData appendPartWithFileData:data name:key fileName:fileName mimeType:@"image/png"];
            
            
        }
        // 上传文件设置
//        [formData appendPartWithFileData:data name:@"image" fileName:@"image" mimeType:@"image/jpg"];
        
    } success:^(AFHTTPRequestOperation *operation, id responseObject) {
        
        // 成功
        
        NSLog(@"Success: %@", responseObject);
        
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        
        NSLog(@"Error: %@", error.userInfo[@"NSLocalizedDescription"]);
    }];
}

-(Json*)getDecoder
{
    return [[FileJson alloc]init];
}


-(void)excuteFileFull:(void(^)())start
          success:(void(^)(Json*j))success
             fail:(void(^)(Json*j,NSInteger code,NSString* msg))fail
        exception:(void(^)())exception
        compelete:(void(^)())compelete

{
    start();
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    NSString* url=[self getUrl];
    NSLog(@"url=%@",url);
    NSLog(@"param=%@",self.param);
    NSArray *n= self.param.allKeys;
    [manager POST:url parameters:self.param constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        
        NSArray *nx= self.stream.allKeys;
        
        for(NSString *key in nx)
        {
            NSObject *o= self.stream[key];
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            formatter.dateFormat = @"yyyyMMddHHmmss";
            NSString *str = [formatter stringFromDate:[NSDate date]];
            NSString *fileName = [NSString stringWithFormat:@"%@.png", str];
            NSData *data = UIImageJPEGRepresentation((UIImage*)o, 1.0);
            [formData appendPartWithFileData:data name:key fileName:fileName mimeType:@"image/png"];
            
            
        }
        
    } success:^(AFHTTPRequestOperation * _Nonnull operation, id  _Nonnull responseObject) {
        
        @try {
            //            NSLog(@"Success: %@", [responseObject class]);
            NSLog(@"Success: %@", responseObject );
            
            FileJson *res=[[self getDecoder] initWithDict:responseObject];
            
            
            if([res isSuccess])
            {
                success(res);
            }
            else
            {
                
                fail(res,[res getErrorCode],[res getErrorMsg]);
                
//                [j fail:[res getErrorCode] errmsg:[res getErrorMsg] json:res];
            }
            
        }
        @catch (NSException *excep) {
            
            exception();
        }
        @finally {
            compelete();
        }
        
    } failure:^(AFHTTPRequestOperation * _Nullable operation, NSError * _Nonnull error) {
        NSLog(@"失败");
        NSLog(@"%@", error.userInfo[@"NSLocalizedDescription"]);
        exception();
        compelete();
    }];

}


-(void)excuteFile:(NSString*)url   start:(void(^)())start
              success:(void(^)(NSString*s,NSString*sessionid))success
            exception:(void(^)())exception
            compelete:(void(^)())compelete

{
    start();
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
//    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObject:@"multipart/form-data"];
//    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"multipart/form-data",@"application/json", @"text/html",@"text/json",@"text/javascript", nil];
    

//    NSString* url=[self getUrl];
    NSLog(@"url=%@",url);
    NSLog(@"param=%@", self.param);
    NSArray *n= self.param.allKeys;
    NSMutableDictionary *d=   self.header;
    for(NSString *key in d.allKeys)
    {
        NSString *v=d[key];
        [manager.requestSerializer setValue:v forHTTPHeaderField:key];
    }
//    [manager.requestSerializer setValue:@"multipart/form-data;charset=utf-8" forHTTPHeaderField:@"Content-Type"];
    [manager POST:url parameters:self.param constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        
        
        
        NSArray *nx= self.stream.allKeys;
        
        for(NSString *key in nx)
        {
            NSObject *o= self.stream[key];
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            formatter.dateFormat = @"yyyyMMddHHmmss";
            NSString *str = [formatter stringFromDate:[NSDate date]];
            NSString *fileName = [NSString stringWithFormat:@"%@.png", str];
            NSData *data = UIImageJPEGRepresentation((UIImage*)o, 1.0);
            [formData appendPartWithFileData:data name:key fileName:fileName mimeType:@"image/png"];
            
//            [self postUpload:(UIImage*)o];
        }
        
    } success:^(AFHTTPRequestOperation * _Nonnull operation, id  _Nonnull responseObject) {
        
        @try {
          
            
          
            NSLog(@"Success: %@", responseObject );
            NSHTTPURLResponse* response = operation.response;
            NSString* sessionId = [NSString stringWithFormat:@"%@",[[response.allHeaderFields[@"Set-Cookie"]componentsSeparatedByString:@";"]objectAtIndex:0]];

            NSData *jsonData = [NSJSONSerialization dataWithJSONObject:responseObject options:NSJSONWritingPrettyPrinted error:nil];
            
            NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
 
            
//            NSString *result = [[NSString alloc] initWithData:responseObject encoding:NSUTF8StringEncoding];
            //            NSLog(result);
            
            success(jsonString,sessionId);
        
            
        }
        @catch (NSException *excep) {
            
            exception();
        }
        @finally {
            compelete();
        }
        
    } failure:^(AFHTTPRequestOperation * _Nullable operation, NSError * _Nonnull error) {
        NSLog(@"失败");
        NSLog(@"%@", error.userInfo[@"NSLocalizedDescription"]);
        exception();
        compelete();
    }];
    
}



- (void)postUpload:(UIImage*)img
{
    // 本地上传给服务器时,没有确定的URL,不好用MD5的方式处理
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    
    [manager POST:@"http://127.0.0.1:8080/imgupload.do" parameters:nil constructingBodyWithBlock:^(id<AFMultipartFormData> formData) {
        
        NSURL *fileURL = [[NSBundle mainBundle] URLForResource:@"头像1.png" withExtension:nil];
        
        // 要上传保存在服务器中的名称
        // 使用时间来作为文件名 2014-04-30 14:20:57.png
        // 让不同的用户信息,保存在不同目录中
        NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
        // 设置日期格式
        formatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
        NSString *fileName = [formatter stringFromDate:[NSDate date]];
        NSData *data = UIImageJPEGRepresentation((UIImage*)img, 1.0);
//        [formData appendPartWithFileData:data name:@"file" fileName:fileName mimeType:@"image/png" error:NULL];
        
        [formData appendPartWithFileData:data name:@"file" fileName:fileName mimeType:@"image/png"];
        
        
    } success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog(@"OK");
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"error");
    }];
}


-(void)excuteFile:(UIViewController*)vc
success:(void(^)(Json*j))success
{
    
    if(self.p==nil)
        self.p=[[LockScreenProgress alloc]initWith:vc.view];;
    [self excuteFileFull:^{
        [self.p show];
    } success:^(Json *j) {
         success(j);
    } fail:^(Json *j, NSInteger code, NSString *msg) {
        [vc toast:msg];
    } exception:^{
         [vc toast:@"网络异常！"];
    } compelete:^{
        [self.p hide];
    }];
    
    
    
    
}
-(void)excuteFile:(id<JsonProtocol>)j
{
 
    
 
    
    
    [j start];
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    NSString* url=[self getUrl];
    NSLog(@"url=%@",url);
    NSLog(@"param=%@",self.param);
    NSArray *n= self.param.allKeys;
    [manager POST:url parameters:self.param constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        
        NSArray *nx= self.stream.allKeys;
        
        for(NSString *key in nx)
        {
            NSObject *o= self.stream[key];
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            formatter.dateFormat = @"yyyyMMddHHmmss";
            NSString *str = [formatter stringFromDate:[NSDate date]];
            NSString *fileName = [NSString stringWithFormat:@"%@.png", str];
            NSData *data = UIImageJPEGRepresentation((UIImage*)o, 1.0);
            [formData appendPartWithFileData:data name:key fileName:fileName mimeType:@"image/png"];
 
 
        }
        
    } success:^(AFHTTPRequestOperation * _Nonnull operation, id  _Nonnull responseObject) {
        
        @try {
//            NSLog(@"Success: %@", [responseObject class]);
            NSLog(@"Success: %@", responseObject );
         
            FileJson *res=[[self getDecoder] initWithDict:responseObject];
        
 
            if([res isSuccess])
            {
                [j success:res];
            }
            else
            {
       
                [j fail:[res getErrorCode] errmsg:[res getErrorMsg] json:res];
            }
            
        }
        @catch (NSException *exception) {
            
            [j exception];
        }
        @finally {
            [j compelete];
        }
        
    } failure:^(AFHTTPRequestOperation * _Nullable operation, NSError * _Nonnull error) {
        NSLog(@"失败");
        NSLog(@"%@", error.userInfo[@"NSLocalizedDescription"]);
        [j exception];
        [j compelete];
    }];
    
}



@end
