//
//  LFDownloadNetwork.h
//  NSURLSession
//
//  Created by 刘峰 on 2019/4/25.
//  Copyright © 2019年 Liufeng. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef void(^DownloadSuccess)(NSURL *url);
typedef void(^DownloadFailed)(NSError *error);
typedef void(^Progress)(float total,float download,float progress);

@interface LFDownloadNetwork : NSObject
/**< 最大并发数*/
@property (nonatomic, assign) NSInteger maxConcurrentCount;

+ (instancetype)shareManager;

/**< 开始下载*/
- (void)downloadWithUrl:(NSString *)urlStr progress:(Progress)progress success:(DownloadSuccess)success failed:(DownloadFailed)failed;

/**< 暂停下载*/
- (void)pauseDownloadWithUrl:(NSString *)urlStr;

/**< 继续下载*/
- (void)continueDownloadWithUrl:(NSString *)urlStr;

/**< 取消下载*/
- (void)cancelDownloadWithUrl:(NSString *)urlStr;

@end

NS_ASSUME_NONNULL_END
