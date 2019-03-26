/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import "WXImgLoaderDefaultImpl.h"
#import <SDWebImage/UIImageView+WebCache.h>

#define MIN_IMAGE_WIDTH 36
#define MIN_IMAGE_HEIGHT 36

#if OS_OBJECT_USE_OBJC
#undef  WXDispatchQueueRelease
#undef  WXDispatchQueueSetterSementics
#define WXDispatchQueueRelease(q)
#define WXDispatchQueueSetterSementics strong
#else
#undef  WXDispatchQueueRelease
#undef  WXDispatchQueueSetterSementics
#define WXDispatchQueueRelease(q) (dispatch_release(q))
#define WXDispatchQueueSetterSementics assign
#endif

@interface WXImgLoaderDefaultImpl()

@property (WXDispatchQueueSetterSementics, nonatomic) dispatch_queue_t ioQueue;

@end

@implementation WXImgLoaderDefaultImpl

@synthesize weexInstance;
- (id<WXImageOperationProtocol>)downloadImageWithURL:(NSString *)url imageFrame:(CGRect)imageFrame userInfo:(NSDictionary *)userInfo completed:(void(^)(UIImage *image,  NSError *error, BOOL finished))completedBlock
{
 
//     NSLog([@"loader:" add: url]);
    if([url contains:@"base64==="])
    {
        NSArray *n= [url split:@"base64==="];
        url=n[1];
    }
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl:[self getInstance:userInfo]]];
    }
    if([url startWith:PREFIX_SDCARD])
    {
        url=[url replace:PREFIX_SDCARD withString:@""];
        completedBlock([self getDocumentImage:url], nil, nil);
        return nil;
    }
    NSURL *nurl= [NSURL URLWithString:url];
    if(![url startWith:@"http"]&&![url startWith:@"file"])
    {
        NSArray *n=[url split:@"."];
        NSString *end=n[n.count-1];
        NSString *ss=@"";
        for(NSString *s in n)
        {
            int index=[n indexOfObject:s];
            if(index!=n.count-1)
            {
                ss=[ss add:s];
            }
            int c=n.count-2;
            if(index<c)
            {
                ss=[ss add:@"."];
            }
        }
       nurl= [[NSBundle mainBundle] URLForResource:ss withExtension:end];
    }
    
//    return (id<WXImageOperationProtocol>)[[SDWebImageManager sharedManager] downloadImageWithURL:nurl options:0 progress:^(NSInteger receivedSize, NSInteger expectedSize) {
//        
//    } completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
//        if (completedBlock) {
//            completedBlock(image, error, finished);
//        }
//    }];
    
    
    
    return (id<WXImageOperationProtocol>)[[SDWebImageManager sharedManager] loadImageWithURL:[NSURL URLWithString:url]  options:SDWebImageRetryFailed progress:^(NSInteger receivedSize, NSInteger expectedSize, NSURL * _Nullable targetURL) {
        
    } completed:^(UIImage * _Nullable image, NSData * _Nullable data, NSError * _Nullable error, SDImageCacheType cacheType, BOOL finished, NSURL * _Nullable imageURL) {
        if([url contains:@".gif"]){
            image=  [self getGitImageWithData:data];
        }
        if (completedBlock) {
            completedBlock(image, error, finished);
        }
    }];
}

// 读取并存贮到相册
-(UIImage *)getDocumentImage:(NSString*)path{
    // 读取沙盒路径图片
//    NSString *aPath3=[NSString stringWithFormat:@"%@/Documents/%@.png",NSHomeDirectory(),@"test"];
    // 拿到沙盒路径图片
    UIImage *imgFromUrl3=[[UIImage alloc]initWithContentsOfFile:path];
//     图片保存相册
//    UIImageWriteToSavedPhotosAlbum(imgFromUrl3, self, nil, nil);
    return imgFromUrl3;
}


-(WXSDKInstance*)getInstance:(NSDictionary*)options{
    NSString* instanceId = [options objectForKey:@"instanceId"];
    if (nil == instanceId) {
//        WXLogWarning(@"please set instanceId in userInfo,for url %@:",imgUrl.absoluteString);
        return nil;
    }
    WXSDKInstance* instance =[WXSDKManager instanceForID:instanceId];
    return instance;
}

- (UIImage *)getGitImageWithData:(NSData* )data
{
    CGImageSourceRef imageSource = CGImageSourceCreateWithData((__bridge CFDataRef)data, NULL);
    if (!imageSource) return nil;
    size_t count = CGImageSourceGetCount(imageSource);
    NSMutableArray *images = [NSMutableArray array];
    NSTimeInterval duration = 0;
    for (size_t i = 0; i < count; i++) {
        CGImageRef image = CGImageSourceCreateImageAtIndex(imageSource, i, NULL);
        if (!image) continue;
        duration += durationWithSourceAtIndex(imageSource, i);
        [images addObject:[UIImage imageWithCGImage:image]];
        CGImageRelease(image);
    }
    if (!duration) duration = 0.1 * count;
    if(imageSource)CFRelease(imageSource);
    return [UIImage animatedImageWithImages:images duration:duration];
}
#pragma mark 获取每一帧图片的时长
float durationWithSourceAtIndex(CGImageSourceRef source, NSUInteger index) {
    float duration = 0.1f;
    CFDictionaryRef propertiesRef = CGImageSourceCopyPropertiesAtIndex(source, index, nil);
    NSDictionary *properties = (__bridge NSDictionary *)propertiesRef;
    NSDictionary *gifProperties = properties[(NSString *)kCGImagePropertyGIFDictionary];
    
    NSNumber *delayTime = gifProperties[(NSString *)kCGImagePropertyGIFUnclampedDelayTime];
    if (delayTime) duration = delayTime.floatValue;
    else {
        delayTime = gifProperties[(NSString *)kCGImagePropertyGIFDelayTime];
        if (delayTime) duration = delayTime.floatValue;
    }
    if (duration < 0.011f) {duration = 0.100f;}
    if(propertiesRef)CFRelease(propertiesRef);
    return duration;
}

@end
