//
//  ZipDownloader.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/4/30.
//

#import "ZipDownloader.h"

@implementation ZipDownloader


-(instancetype)initWidthUrl:(NSString*)url path:(NSString*)path  progress:(void(^)(float,NSInteger,NSInteger))progress compelete:(void(^)(NSString*))compelete exception:(void(^)(NSError*))exception
{
    if([super init])
    {
        self.path=path;
        [self prepareDownloadTask:url progress:progress compelete:compelete exception:exception];
       
    }
    return self;
}

- (AFURLSessionManager *)manager {
    if (!_manager) {
        NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
        
        _manager = [[AFURLSessionManager alloc] initWithSessionConfiguration:configuration];
    }
    return _manager;
}


- (NSURLSessionDataTask *)prepareDownloadTask:(NSString*)url  progress:(void(^)(float,NSInteger,NSInteger))progress compelete:(void(^)(NSString*))compelete exception:(void(^)(NSError*))exception{
    if (!_downloadTask) {
        // 1.创建下载URL
        NSURL *durl = [NSURL URLWithString:url];
        
        // 2.创建request请求
        NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:durl];
        
        // 设置HTTP请求头中的Range
        NSString *range = [NSString stringWithFormat:@"bytes=%zd-", self.currentLength];
        [request setValue:range forHTTPHeaderField:@"Range"];
        
        __weak typeof(self) weakSelf = self;
        
        _downloadTask = [self.manager dataTaskWithRequest:request completionHandler:^(NSURLResponse * _Nonnull response, id  _Nullable responseObject, NSError * _Nullable error) {
            
            // 下载完成回调block
            NSLog(@"完成");
            if(error!=nil)
            {
                exception(error);
            }
            
            // 清空长度
            weakSelf.currentLength = 0;
            weakSelf.totalLength = 0;
            
            // 关闭fileHandle
            [weakSelf.fileHandle closeFile];
            weakSelf.fileHandle = nil;
            compelete(_path);
            
        }];
        
        [self.manager setDataTaskDidReceiveResponseBlock:^NSURLSessionResponseDisposition(NSURLSession * _Nonnull session, NSURLSessionDataTask * _Nonnull dataTask, NSURLResponse * _Nonnull response) {
            NSLog(@"启动任务");
            // 每次唤醒task的时候会回调这个block
            
            // 获得下载文件的总长度：请求下载的文件长度 + 当前已经下载的文件长度
            weakSelf.totalLength = response.expectedContentLength + self.currentLength;
            
//            // 沙盒文件路径
//            NSString *path = [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:@"xxxxx"];
            
//            NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
//            NSString  *path= [paths objectAtIndex:0];
//            path=[path add:@"/zips/app.zip"];
            
            //NSLog(@"File downloaded to: %@",path);
            
            // 创建一个空的文件到沙盒中
            NSFileManager *manager = [NSFileManager defaultManager];
            
            if (![manager fileExistsAtPath:_path]) {
                // 如果没有下载文件的话，就创建一个文件。如果有下载文件的话，则不用重新创建(不然会覆盖掉之前的文件)
                [manager createFileAtPath:_path contents:nil attributes:nil];
            }
            
            // 创建文件句柄
            weakSelf.fileHandle = [NSFileHandle fileHandleForWritingAtPath:_path];
            
            // 允许处理服务器的响应，才会继续接收服务器返回的数据
            return NSURLSessionResponseAllow;
        }];
        
        [self.manager setDataTaskDidReceiveDataBlock:^(NSURLSession * _Nonnull session, NSURLSessionDataTask * _Nonnull dataTask, NSData * _Nonnull data) {
            //NSLog(@"setDataTaskDidReceiveDataBlock");
            // 一直回调，直到下载完成
            
            // 指定数据的写入位置 -- 文件内容的最后面
            [weakSelf.fileHandle seekToEndOfFile];
            
            // 向沙盒写入数据
            [weakSelf.fileHandle writeData:data];
            
            // 拼接文件总长度
            weakSelf.currentLength += data.length;
            
            // 获取主线程，不然无法正确显示进度。
            NSOperationQueue* mainQueue = [NSOperationQueue mainQueue];
            [mainQueue addOperationWithBlock:^{
                // 下载进度
                float p=100.0 * weakSelf.currentLength / weakSelf.totalLength;
                NSLog(@"当前下载进度:%.2f%%",p);
                if(progress!=nil)
                {
                    progress(p,weakSelf.currentLength,weakSelf.totalLength);
                }
            }];
        }];
    }
    return _downloadTask;
}

-(void)start
{
    [self.downloadTask resume];
}


-(void)stop
{
    [self.downloadTask suspend];
    self.downloadTask = nil;
}


@end
