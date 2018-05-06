//
//  URL.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/11/6.
//

#import "URL.h"
#import "farwolf.h"

#import "Weex.h"

#import "ZipArchive.h"


@implementation URL

+(NSURL*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance
{
    if(weexInstance==nil)
    {
        int k=0;
        k++;
    }
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl:weexInstance]];
        
        return [NSURL URLWithString:url];
    }
    NSURL *n= [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL];
    return n;
}


+(NSMutableArray*)getFinalUrls:(NSMutableArray*)urls weexInstance:(WXSDKInstance*)weexInstance
{
 
    NSMutableArray *n=[NSMutableArray new];
    for(NSString* url in urls)
    {
        [n addObject:[URL getFinalUrl:url weexInstance:weexInstance]];
    }
    return n;
    
}


+(NSURL*)loadLocal:(NSString*)url
{
    NSString *s= [self loadFromDisk:@"app"].absoluteString;
    s=[s replace:@"file://" withString:@""];
    if([s isExist])
    {
       return [self loadFromDisk:url];
    }
    else
    {
        return [self loadFromBundle:url ext:@"js"];
    }
}

+(NSURL*)loadFromDisk:(NSString*)path
{
    
    NSString *documentsDirectory = [self documentDirectory];
    NSString *pathx = [documentsDirectory stringByAppendingPathComponent:path];
    pathx=[pathx replace:@"file://" withString:@""];
    NSURL *url = [NSURL fileURLWithPath:pathx];
    return url;
}

+(NSURL*)loadFromBundle:(NSString*)path ext:(NSString*)ext
{
 
     return  [[NSBundle mainBundle] URLForResource:[path replace:[@"." add:ext] withString:@""]  withExtension:ext];
}

+(BOOL)isDiskExist
{
    NSString *base = [self documentDirectory];
    NSString *app=[base stringByAppendingString:@"/app"];
    NSLog(base);
    NSFileManager *m=[NSFileManager defaultManager];
  
    BOOL isDir=true;
    return [m fileExistsAtPath:app isDirectory:&isDir];
    
}



//-(void)loadLocal
//{
//    NSString *base = [self documentDirectory];
//    NSString *app=[base stringByAppendingString:@"/app"];
//    NSLog(base);
//    NSFileManager *m=[NSFileManager defaultManager];
//    [m removeItemAtPath:app error:nil];
//    BOOL isDir=true;
//    if(![m fileExistsAtPath:app isDirectory:&isDir])
//    {
//        NSLog(@"解压开始");
//        //       [self.indicator setHidden:false];
//        dispatch_async(dispatch_get_global_queue(0, 0), ^{
//
//            [self unzip];
//
//            dispatch_async(dispatch_get_main_queue(), ^{
//                NSLog(@"解压结束");
//
//                [self loadDocument];
//                [self.indicator setHidden:true];
//            });
//
//        });
//    }
//    else
//    {
//        [self loadDocument];
//    }
//
//
//}

+(NSString*)documentDirectory
{
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    return [paths objectAtIndex:0];
    
}

+(void)copyBundleToDisk
{
    NSString *bundle=[[NSBundle mainBundle]resourcePath];
    bundle=[bundle add:@"/app"];
    if(![bundle isExist])
    {
        return;
    }
    if(![URL isDiskExist]||[Config bundleJsVersion]<[Config bundleJsVersion])
    {
        NSString *disk= [self loadFromDisk:@"app"].absoluteString;
        disk=[disk replace:@"file://" withString:@""];
         [disk delete];
        [disk mkdir];
       
        [bundle copyToPath:disk];
    }
   
}


+(void)unzip:(NSString*)from to:(NSString*)to
{
    ZipArchive* zip = [[ZipArchive alloc] init];
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentPath = ([paths count] > 0) ? [paths objectAtIndex:0] : nil;
    //    NSString* zipFile = [documentPath stringByAppendingString:@"/images.zip"] ;
//    NSString*zipFile =[[NSBundle mainBundle] pathForResource:@"app" ofType:@"zip"];
  
//    NSString *zipFile =  [self loadFromBundle:from ext:@"zip"].absoluteString;
    NSString *zipFile =  [self loadFromDisk:from].absoluteString;
    zipFile=[zipFile replace:@"file://" withString:@""];
    NSString *todir =  [self loadFromDisk:to].absoluteString;
   
     todir=[todir replace:@"file://" withString:@""];
//     [todir delete];
//    NSString* unZipTo = [documentPath stringByAppendingString:@"/"] ;
    if( [zip UnzipOpenFile:zipFile] ){
        BOOL result = [zip UnzipFileTo:todir overWrite:YES];
        if( NO==result ){
            //添加代码
        }
        [zip UnzipCloseFile];
    }
    NSLog(@"成功!");
    //    [zip release];
}





@end
