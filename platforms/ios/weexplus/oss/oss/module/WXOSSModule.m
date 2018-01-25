//
//  WXOSSModule.m
//  TOCropViewController-TOCropViewControllerBundle
//
//  Created by 郑江荣 on 2017/12/14.
//

#import "WXOSSModule.h"
#import <AliyunOSSiOS/OSSService.h>

@implementation WXOSSModule

WX_EXPORT_METHOD(@selector(upload:param:))



-(void)upload:(NSURL*)url param:(NSMutableDictionary*)param
{
    
    NSString *endpoint = [param objectForKey:@"endpoint"];
    NSString *AccessKeyId = [param objectForKey:@"AccessKeyId"];
    NSString *AccessKeySecret = [param objectForKey:@"AccessKeySecret"];
    NSString *SecurityToken = [param objectForKey:@"SecurityToken"];
    
    
    // 由阿里云颁发的AccessKeyId/AccessKeySecret构造一个CredentialProvider。
    // 移动端建议使用STS方式初始化OSSClient。可以通过sample中STS使用说明了解更多(https://github.com/aliyun/aliyun-oss-ios-sdk/tree/master/DemoByOC)
    id<OSSCredentialProvider> credential = [[OSSStsTokenCredentialProvider alloc] initWithAccessKeyId:AccessKeyId secretKeyId:AccessKeySecret securityToken:SecurityToken];
   OSSClient *client = [[OSSClient alloc] initWithEndpoint:endpoint credentialProvider:credential];
    
    OSSPutObjectRequest * put = [OSSPutObjectRequest new];
    // 必填字段
    put.bucketName = @"<bucketName>";
    put.objectKey = @"<objectKey>";
    put.uploadingFileURL =url;
    // put.uploadingData = <NSData *>; // 直接上传NSData
    // 可选字段，可不设置
    put.uploadProgress = ^(int64_t bytesSent, int64_t totalByteSent, int64_t totalBytesExpectedToSend) {
        // 当前上传段长度、当前已经上传总长度、一共需要上传的总长度
        NSLog(@"%lld, %lld, %lld", bytesSent, totalByteSent, totalBytesExpectedToSend);
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
            NSLog(@"upload object success!");
        } else {
            NSLog(@"upload object failed, error: %@" , task.error);
        }
        return nil;
    }];
}

@end
