//
//  WXOSSModule.m
//  TOCropViewController-TOCropViewControllerBundle
//
//  Created by 郑江荣 on 2017/12/14.
//

#import "WXOSSModule.h"
#import <AliyunOSSiOS/OSSService.h>
#import <WeexSDK/WXSDKInstance.h>
#import <WeexSDK/WXSDKEngine.h>
#import "farwolf.h"

@implementation WXOSSModule

WX_EXPORT_METHOD(@selector(upload:progress:callback:))

+(void)initWXOSSModule
{
    [WXSDKEngine registerModule:@"oss" withClass:[WXOSSModule class]];
}


-(void)upload:(NSMutableDictionary*)params progress:(WXModuleKeepAliveCallback)progress callback:(WXModuleKeepAliveCallback)callback
{
    
    
    NSMutableDictionary *param=[params objectForKey:@"param"];
    NSString *url = [params objectForKey:@"url"];
    NSString *objectkey = [params objectForKey:@"objectkey"];
    NSString *endpoint = [param objectForKey:@"Endpoint"];
    NSString *AccessKeyId = [param objectForKey:@"AccessKeyId"];
    NSString *AccessKeySecret = [param objectForKey:@"AccessKeySecret"];
    NSString *SecurityToken = [param objectForKey:@"SecurityToken"];
    NSString *BucketName = [param objectForKey:@"BucketName"];
    
    id<OSSCredentialProvider> credential = [[OSSStsTokenCredentialProvider alloc] initWithAccessKeyId:AccessKeyId secretKeyId:AccessKeySecret securityToken:SecurityToken];
    OSSClient *client = [[OSSClient alloc] initWithEndpoint:endpoint credentialProvider:credential];
    
    OSSPutObjectRequest * put = [OSSPutObjectRequest new];
    // 必填字段
    put.bucketName = BucketName;
    put.objectKey =objectkey;
    put.uploadingFileURL =[NSURL URLWithString:url];
    
    
    
    // put.uploadingData = <NSData *>; // 直接上传NSData
    // 可选字段，可不设置
    put.uploadProgress = ^(int64_t bytesSent, int64_t totalByteSent, int64_t totalBytesExpectedToSend) {
        // 当前上传段长度、当前已经上传总长度、一共需要上传的总长度
        NSLog(@"%lld, %lld, %lld", bytesSent, totalByteSent, totalBytesExpectedToSend);
        
        progress(@{@"send":@(totalByteSent),@"total":@(totalBytesExpectedToSend)},true);
    };
    // 以下可选字段的含义参考： https://docs.aliyun.com/#/pub/oss/api-reference/object&PutObject
    // put.contentType = @"";
    // put.contentMd5 = @"";
    // put.contentEncoding = @"";
    // put.contentDisposition = @"";
    // put.objectMeta = [NSMutableDictionary dictionaryWithObjectsAndKeys:@"value1", @"x-oss-meta-name1", nil]; // 可以在上传时设置元信息或者其他HTTP头部
    OSSTask * putTask = [client putObject:put];
    [putTask continueWithBlock:^id(OSSTask *task) {
        if (!task.error) {
            
            callback(@{@"err":@0},true);
            
        } else {
            NSLog(@"upload object failed, error: %@" , task.error);
            callback(@{@"err":@1},true);
        }
        return nil;
    }];
}

@end


