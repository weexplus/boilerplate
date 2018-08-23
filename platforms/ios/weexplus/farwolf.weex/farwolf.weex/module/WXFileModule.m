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



@implementation WXFileModule

WX_EXPORT_METHOD(@selector(unzip:callback:))
WX_EXPORT_METHOD(@selector(ls:callback:))
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
@end
