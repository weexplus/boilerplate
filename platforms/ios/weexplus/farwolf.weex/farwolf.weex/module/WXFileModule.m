//
//  WXFileModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/8/22.
//

#import "WXFileModule.h"
#import "farwolf.h"
#import "URL.h"
#import <ZipZap/ZipZap.h>
#import "ZipArchive.h"
#import "farwolf_weex.h"


@implementation WXFileModule

WX_EXPORT_METHOD(@selector(unzip:callback:))
WX_EXPORT_METHOD(@selector(ls:callback:))
WX_EXPORT_METHOD_SYNC(@selector(diskSize))
WX_EXPORT_METHOD(@selector(del:))




-(void)unzip:(NSString*)path  callback:(WXModuleKeepAliveCallback)callback{
    ZipArchive* zip = [[ZipArchive alloc] init];
    path =[path replace:@"sdcard:" withString:@""];
    NSString *todir =  [URL loadFromDisk:@"download"].absoluteString;
    todir=[todir replace:@"file://" withString:@""];
    if( [zip UnzipOpenFile:path] ){
        BOOL result = [zip UnzipFileTo:todir overWrite:YES];
        if( NO==result ){
            //添加代码
        }
        [zip UnzipCloseFile];
    }
    NSLog(@"成功!");
    NSError *error;
    path =[path replace:@"sdcard:" withString:@""];
    ZZArchive *archive = [ZZArchive archiveWithURL:[NSURL fileURLWithPath:path] error:&error];
    NSMutableArray *arry=[NSMutableArray new];
    for (ZZArchiveEntry * ectry in archive.entries) {
        NSURL *url= [NSURL URLWithString:[@"./" add:ectry.fileName] relativeToURL:[NSURL fileURLWithPath:path]];
        NSString *fpath= [url.absoluteString replace:@"file://" withString:@""];
        if(![fpath isDirectory])
        {
            [arry addObject:[@"sdcard:" add: fpath]];
        }
    }
    callback(@{@"path":arry},false);
}
-(void)ls:(NSString*)path  callback:(WXModuleKeepAliveCallback)callback
{
    
}

-(void)del:(NSString*)path{
    if([path startWith: PREFIX_SDCARD]){
        path=[path replace:PREFIX_SDCARD withString:@""];
    }
    [path delete];
}

-(NSMutableDictionary*)diskSize{
    float totalsize = 0.0;
    /// 剩余大小
    float freesize = 0.0;
    /// 是否登录
    NSError *error = nil;
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSDictionary *dictionary = [[NSFileManager defaultManager] attributesOfFileSystemForPath:[paths lastObject] error: &error];
    if (dictionary)
    {
        NSNumber *_free = [dictionary objectForKey:NSFileSystemFreeSize];
        freesize = [_free unsignedLongLongValue]*1.0/(1024);
        
        NSNumber *_total = [dictionary objectForKey:NSFileSystemSize];
        totalsize = [_total unsignedLongLongValue]*1.0/(1024);
        return @{@"freeSize":@(freesize),@"totalSize":@(totalsize)};
    } else
    {
        NSLog(@"Error Obtaining System Memory Info: Domain = %@, Code = %ld", [error domain], (long)[error code]);
        return @{@"freeSize":@(0),@"totalSize":@(0)};
    }
    
}


@end
