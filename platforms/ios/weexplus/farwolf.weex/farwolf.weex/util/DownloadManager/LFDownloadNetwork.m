//
//  LFDownloadNetwork.m
//  NSURLSession
//
//  Created by 刘峰 on 2019/4/25.
//  Copyright © 2019年 Liufeng. All rights reserved.
//

#import "LFDownloadNetwork.h"
#import "LFDownloadDataModel.h"
#import "WXNetModule.h"
@interface LFDownloadNetwork () <NSURLSessionDelegate, NSURLSessionDownloadDelegate>

/***********block回调*************/
@property (nonatomic, copy) Progress downloadProgressBlock;//进度block
@property (nonatomic, copy) DownloadSuccess downloadSuccessBlock;//下载成功block
@property (nonatomic, copy) DownloadFailed downloadFailedBlock;//下载失败block

/***********文件下载需要的属性*************/
@property (nonatomic, strong) NSURLSession *session;
@property (nonatomic, strong) NSMutableDictionary *downloadTaskDic;
@property (nonatomic, strong) NSMutableDictionary *resumeDataDic;//保存在沙盒中的resumeData
@property (nonatomic, assign) NSInteger currentCount;

@end

@implementation LFDownloadNetwork

+ (instancetype)shareManager {
    static LFDownloadNetwork *network = nil;
    
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        network = [[self alloc] init];
    });
    
    return network;
}

- (instancetype)init {
    if (self = [super init]) {
        _currentCount = 0;
        _downloadTaskDic = [NSMutableDictionary dictionary];
        _resumeDataDic = [NSMutableDictionary dictionary];

        //NSURLSession的配置信息
        NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration backgroundSessionConfigurationWithIdentifier:@"BackgroundDownloadIdentifier"];
        //是否允许蜂窝下载
        configuration.allowsCellularAccess = NO;
        
        //默认单线程执行大文件下载
        NSOperationQueue *queue = [[NSOperationQueue alloc] init];
        queue.maxConcurrentOperationCount = 1;
        
        //创建会话
        self.session = [NSURLSession sessionWithConfiguration:configuration delegate:self delegateQueue:queue];
    }
    return self;
}

- (void)downloadWithUrl:(NSString *)urlStr progress:(Progress)progress success:(DownloadSuccess)success failed:(DownloadFailed)failed {
    self.downloadSuccessBlock = success;
    self.downloadFailedBlock = failed;
    self.downloadProgressBlock = progress;
    //判断url地址是否有效
    if (![self checkIsUrlAtString:urlStr]) {
        NSLog(@"无效的下载地址===%@", urlStr);
        return;
    }
    
    //创建NSURLSessionDownloadTask下载任务并启动之
    [self activeDownloadSessionTaskWithUrl:urlStr];
}

#pragma mark - Public
//暂停下载
- (void)pauseDownloadWithUrl:(NSString *)urlStr {
    [self cancelDownloadWithUrl:urlStr andRemoveOrNot:NO];
}

//继续下载
- (void)continueDownloadWithUrl:(NSString *)urlStr {
    [self activeDownloadSessionTaskWithUrl:urlStr];
}

//取消下载
- (void)cancelDownloadWithUrl:(NSString *)urlStr {
    [self cancelDownloadWithUrl:urlStr andRemoveOrNot:YES];
}

#pragma mark - Private
//获取downloadTask并激活它,可以从resumeData激活(断点续传),也可以重新下载
- (void)activeDownloadSessionTaskWithUrl:(NSString *)urlStr {
    
    NSString *base64Url = [self encode:urlStr];
    
    dispatch_group_t group = dispatch_group_create();
    dispatch_queue_t groupQueue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    __weak typeof(self) weakSelf = self;
    for (NSString *filePath in [self getResumeDataFilePathArray]) {
        //判断如果沙盒中有文件的resumeData数据,则把它存储到resumeDataDic中,用resumeData开启downloadTask
        NSString *fileName = [[filePath lastPathComponent] stringByDeletingPathExtension];
        if ([fileName isEqualToString:base64Url]) {
            dispatch_group_async(group, groupQueue, ^{
                NSData *resumeData = [NSData dataWithContentsOfFile:filePath options:NSDataReadingMappedIfSafe error:nil];
                [weakSelf.resumeDataDic setValue:resumeData forKey:base64Url];
            });
            break;
        }
    }
    
    dispatch_notify(group, groupQueue, ^{
        NSURLSessionDownloadTask *downloadTask = nil;

        //之前是否有过这个文件的下载任务
        if ([self.resumeDataDic.allKeys containsObject:base64Url]) {
            //到document中找找有没有之前下载过一些的resumeData文件
            //有resumeData就恢复下载
            downloadTask = [self.session downloadTaskWithResumeData:self.resumeDataDic[base64Url]];
        } else {
            //之前没下载过这个文件,就从头下载吧
            downloadTask = [self.session downloadTaskWithURL:[NSURL URLWithString:urlStr]];
        }
        
        NSLog(@"downloadTask = %@", downloadTask);
        
        //设置任务描述标签为文件url
        downloadTask.taskDescription = urlStr;
        //存储downloadTask对象
        self.downloadTaskDic[base64Url] = downloadTask;
        
        //启动任务
        [downloadTask resume];
    });
}

//停止下载的同时,是否删除resumeData
- (void)cancelDownloadWithUrl:(NSString *)urlStr andRemoveOrNot:(BOOL)isRemove {
    NSString *base64Url = [self encode:urlStr];
    
    if (![self.downloadTaskDic.allKeys containsObject:base64Url]) {
        return;
    }
    NSURLSessionDownloadTask *downloadTask = self.downloadTaskDic[base64Url];
    
    [downloadTask cancelByProducingResumeData:^(NSData * _Nullable resumeData) {
        
        //resumeData存到字典中
        self.resumeDataDic[base64Url] = resumeData;
        //当前正在下载的任务数减1
        if (self.currentCount > 0) {
            self.currentCount--;
        }
        
        if (!isRemove) {
            //将目前传递的文件数据resumeData存储到Document中
            [self saveDownloadTmpFileWithResumeData:resumeData url:urlStr];
        } else {
            [self removeDownloadTmpFileWithUrl:urlStr];
        }
        
#warning 开启等待下载的任务
    }];
}

//保存下载过程中的resumeData
- (void)saveDownloadTmpFileWithResumeData:(NSData *)resumeData url:(NSString *)urlStr {
    [resumeData writeToFile:[self getTmpPathWithUrl:urlStr] atomically:YES];
}

//删除之前保存的文件的resumeData
- (void)removeDownloadTmpFileWithUrl:(NSString *)urlStr {
    BOOL success = [[NSFileManager defaultManager] removeItemAtPath:[self getTmpPathWithUrl:urlStr] error:nil];
    
    if (!success) {
        NSLog(@"resumeData删除失败");
    }
    
    NSString *base64Url = [self encode:urlStr];
    [self.resumeDataDic removeObjectForKey:base64Url];
    [self.downloadTaskDic removeObjectForKey:base64Url];
}

#pragma mark - NSURLSessionDownloadDelegate
/**
 每次传一个包调用一次该方法,可以从中获得下载进度,速度和文件大小

 @param session 当前的session会话
 @param downloadTask 当前的下载任务NSURLSessionDownloadTask
 @param bytesWritten 这次传递过来的数据量
 @param totalBytesWritten 目前为止下载了多少的数据
 @param totalBytesExpectedToWrite 一共有多少的数据要下载
 */
- (void)URLSession:(NSURLSession *)session
      downloadTask:(NSURLSessionDownloadTask *)downloadTask
      didWriteData:(int64_t)bytesWritten
 totalBytesWritten:(int64_t)totalBytesWritten
totalBytesExpectedToWrite:(int64_t)totalBytesExpectedToWrite {
    //进度
    float progress = 1.0 * totalBytesWritten / totalBytesExpectedToWrite;
    self.downloadProgressBlock(totalBytesExpectedToWrite,totalBytesWritten,progress);
}

/**
 下载完成后，会调用该方法

 @param session 当前的session会话
 @param downloadTask 当前的下载任务NSURLSessionDownloadTask
 @param location 文件下载下来后系统自动保存的地址，location下的文件会被系统自动删除
 */
- (void)URLSession:(NSURLSession *)session
      downloadTask:(nonnull NSURLSessionDownloadTask *)downloadTask
didFinishDownloadingToURL:(nonnull NSURL *)location {
    //移动文件到自己想要保存的路径下,location下的文件会被系统自动删除
    NSError *saveError = nil;
    NSFileManager *fileManager = [NSFileManager defaultManager];
    NSString *savePath = [self savePathWithUrl:downloadTask.taskDescription];
    if ([fileManager fileExistsAtPath:savePath]) {
        [fileManager removeItemAtPath:savePath error:nil];
    }
    BOOL success = [fileManager moveItemAtPath:location.path toPath:savePath error:&saveError];
    if (success) {
        NSLog(@"文件下载完成,路径为 == %@", savePath);
        self.downloadSuccessBlock([NSURL URLWithString:savePath]);
        //删除之前保存的用来断点续传的resumeData
        [self removeDownloadTmpFileWithUrl:downloadTask.taskDescription];
    } else {
        NSLog(@"在转移文件时发生错误 %@", saveError);
    }
}

#pragma mark - NSURLSessionTaskDelegate
/**
 每次任务结束后调用，结束并不代表它下载完了，有以下几种情况
 1、没错误
 2、用户取消下载
 3、进程在后台被杀死了
 4、其他错误
 偷偷说一句，这个error里面有断点续传需要的resumeData
 @param session 当前的session会话
 @param task 当前的下载任务NSURLSessionTask，
            NSURLSessionDownloadTask继承它
 @param error 错误信息
 */
-(void)URLSession:(nonnull NSURLSession *)session
             task:(nonnull NSURLSessionTask *)task
didCompleteWithError:(nullable NSError *)error {
    if (error) {
        
        //用户取消下载会回调一个error,
        if ([error.localizedDescription isEqualToString:@"cancelled"]) {
            return;
        }
        
        //如果是在后台进程被杀死了,则保存一下resumeData
        if ([error.userInfo objectForKey:NSURLErrorBackgroundTaskCancelledReasonKey]) {
            [self saveDownloadTmpFileWithResumeData:[error.userInfo objectForKey:NSURLSessionDownloadTaskResumeData] url:task.taskDescription];
            return;
        }
        
        //暂时就是出任何错都让resumeData保存一下吧
        [self saveDownloadTmpFileWithResumeData:[error.userInfo objectForKey:NSURLSessionDownloadTaskResumeData] url:task.taskDescription];
        NSLog(@"error = %@", error);
        
    }

    NSString *base64Url = [self encode:task.taskDescription];
    [self.resumeDataDic removeObjectForKey:base64Url];
    [self.downloadTaskDic removeObjectForKey:base64Url];
}

#pragma mark - NSURLSessionDelegate
//应用处于后台,所有下载任务已经完成后调用
- (void)URLSessionDidFinishEventsForBackgroundURLSession:(NSURLSession *)session {
    NSLog(@"所有任务下载完成后调用");
}

#pragma mark - Tools
//判断url是否正常
- (BOOL)checkIsUrlAtString:(NSString *)url {
    NSString *pattern = @"http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    
    NSRegularExpression *regex = [[NSRegularExpression alloc] initWithPattern:pattern options:0 error:nil];
    NSArray *regexArray = [regex matchesInString:url options:0 range:NSMakeRange(0, url.length)];
    
    if (regexArray.count > 0) {
        return YES;
    }else {
        return NO;
    }
}

//保存文件地址
- (NSString *)savePathWithUrl:(NSString *)urlStr {
    //文件名
//    NSString *base64Url = [self encode:urlStr];
//    //文件后缀
//    NSString *extension = [[urlStr lastPathComponent] pathExtension];
//
//    NSString *homePath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
//
//    NSString *file = [homePath stringByAppendingPathComponent:[NSString stringWithFormat:@"%@.%@", base64Url, extension]];
    
    
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [paths objectAtIndex:0];
    path=[path add:@"/download"];
    [path mkdir];
    NSString *filename= [WXNetModule fileNameWithURL:[NSURL URLWithString:urlStr]];
    path=[[path add:@"/"] add:filename];
    
    return path;
}

//下载的文件数据resumeData的存储地址
- (NSString *)getTmpPathWithUrl:(NSString *)urlStr {
    NSString *base64Url = [self encode:urlStr];
    
    NSString *homePath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
    NSString *tmpDirectory = [homePath stringByAppendingPathComponent:@"resumeData"];
    
    NSFileManager *defaultManager = [NSFileManager defaultManager];
    if (![defaultManager fileExistsAtPath:tmpDirectory]) {
        [defaultManager createDirectoryAtPath:tmpDirectory withIntermediateDirectories:YES attributes:nil error:nil];
    }
    
    NSString *tmpFile = [tmpDirectory stringByAppendingPathComponent:[NSString stringWithFormat:@"%@.tmp", base64Url]];
    return tmpFile;
}

//遍历resumeData文件夹目录下的文件,并返回它们的文件路径数组
- (NSArray *)getResumeDataFilePathArray {
    NSMutableArray *urls = [NSMutableArray array];
    //目录
    NSString *homePath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
    NSString *tmpDirectory = [homePath stringByAppendingPathComponent:@"resumeData"];
    NSFileManager *myFileManager = [NSFileManager defaultManager];
    NSDirectoryEnumerator *myDirectoryEnumerator = [myFileManager enumeratorAtPath:tmpDirectory];
    
    BOOL isDir = NO;
    BOOL isExist = NO;
    
    //列举目录内容，可以遍历子目录
    for (NSString *path in myDirectoryEnumerator.allObjects) {
        NSString *filePath = [NSString stringWithFormat:@"%@/%@", tmpDirectory, path];
        isExist = [myFileManager fileExistsAtPath:filePath isDirectory:&isDir];
        if (isDir) {
            // 目录路径
            NSLog(@"这是个目录%@", path);
        } else {
            //文件名
            [urls addObject:filePath];
        }
    }
    return urls;
}

//base64加密
- (NSString *)encode:(NSString *)string {
    //先将string转换成data
    NSData *data = [string dataUsingEncoding:NSUTF8StringEncoding];
    
    NSData *base64Data = [data base64EncodedDataWithOptions:0];
    
    NSString *baseString = [[NSString alloc]initWithData:base64Data encoding:NSUTF8StringEncoding];
    
    return baseString;
}

//base64解密
- (NSString *)dencode:(NSString *)base64String
{
    //NSData *base64data = [string dataUsingEncoding:NSUTF8StringEncoding];
    
    NSData *data = [[NSData alloc]initWithBase64EncodedString:base64String options:NSDataBase64DecodingIgnoreUnknownCharacters];
    
    NSString *string = [[NSString alloc]initWithData:data encoding:NSUTF8StringEncoding];
    
    return string;
}

- (void)dealloc {
    [self.session invalidateAndCancel];
}

@end
