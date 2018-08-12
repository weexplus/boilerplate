//
//  ZipDownloader.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/4/30.
//

#import <Foundation/Foundation.h>
#import "farwolf.h"
@interface ZipDownloader : NSObject
@property (nonatomic , strong) AFURLSessionManager *manager;
@property (nonatomic , strong) NSURLSessionDataTask *downloadTask;
@property (nonatomic , assign) NSInteger currentLength;
@property (nonatomic , assign) NSInteger totalLength;
@property (nonatomic , strong) NSString *path;
@property (nonatomic , strong) NSFileHandle *fileHandle;
-(instancetype)initWidthUrl:(NSString*)url path:(NSString*)path  progress:(void(^)(float,NSInteger,NSInteger))progress compelete:(void(^)(NSString*))compelete exception:(void(^)(NSError*))exception;
-(void)start;
-(void)stop;
@end
