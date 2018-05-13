//
//  Weex.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/4.
//
//

#import "Weex.h"
#import "WXLoadingIndicator.h"
#import "WXLoadingComponent.h"
#import "WXFDivComponent.h"
#import "WXFImageComponent.h"
#import "WXStaticModule.h"
#import "WXFarwolfModule.h"
#import "WXFEmbedComponent.h"
#import "ProgressModule.h"
#import "WXPrefModule.h"
#import "WXFPicker.h"
#import "WXFPickerModule.h"
#import "WXFWebComponent.h"
#import "WXDevTool.h"
#import "WXFRecyclerComponent.h"
#import "WXAddressBookModule.h"
#import "WXSlidPopModule.h"
#import "WXQRModule.h"
#import "URL.h"
#import "WXHost.h"
#import "WXLooperText.h"
#import "EntryControl.h"
#import "RenderControl.h"
#import "WXSlidComponent.h"
#import "WXCenterPop.h"
#import "WXSlidPopModule.h"
#import "WXPageModule.h"
#import "WXFontModule.h"
#import "JSExceptionProtocolImpl.h"
#import "WXUpdateModule.h"
#import "WXTimePicker.h"
#import "WXLocationModule.h"
#import "WXEnvModule.h"

@implementation Weex

+(void)initWeex:(NSString*)group appName:(NSString*)appName appVersion:(NSString*)appVersion
{
    [WXAppConfiguration setAppGroup:group];
    [WXAppConfiguration setAppName:appName];
    [WXAppConfiguration setAppVersion:appVersion];
    
    [WXSDKEngine initSDKEnvironment];
    [WXSDKEngine registerModule:@"navigator" withClass:[WXNavigationModule class]];
    [WXSDKEngine registerModule:@"navbar" withClass:[WXNavBarModule class]];
    [WXSDKEngine registerModule:@"notify" withClass:[WXNotifyModule class]];
    [WXSDKEngine registerModule:@"photo" withClass:[WXPhotoModule class]];
    [WXSDKEngine registerModule:@"net" withClass:[WXNetModule class]];
    [WXSDKEngine registerModule:@"static" withClass:[WXStaticModule class]];
    [WXSDKEngine registerModule:@"farwolf" withClass:[WXFarwolfModule class]];
    [WXSDKEngine registerModule:@"progress" withClass:[ProgressModule class]];
    [WXSDKEngine registerModule:@"pref" withClass:[WXPrefModule class]];
    [WXSDKEngine registerModule:@"fpicker" withClass:[WXFPickerModule class]];
    [WXSDKEngine registerModule:@"addressBook" withClass:[WXAddressBookModule class]];
    [WXSDKEngine registerModule:@"slidpop" withClass:[WXSlidPopModule class]];
    [WXSDKEngine registerModule:@"qr" withClass:[WXQRModule class]];
    [WXSDKEngine registerModule:@"centerpop" withClass:[WXCenterPop class]];
    [WXSDKEngine registerModule:@"slidpop" withClass:[WXSlidPopModule class]];
    [WXSDKEngine registerModule:@"page" withClass:[WXPageModule class]];
    [WXSDKEngine registerModule:@"font" withClass:[WXFontModule class]];
    [WXSDKEngine registerModule:@"updater" withClass:[WXUpdateModule class]];
    [WXSDKEngine registerModule:@"timepicker" withClass:[WXTimePicker class]];
    [WXSDKEngine registerModule:@"location" withClass:[WXLocationModule class]];
    [WXSDKEngine registerModule:@"env" withClass:[WXEnvModule class]];
    
    
    
    [WXSDKEngine registerHandler:[WXEventModule new] withProtocol:@protocol(WXEventModuleProtocol)];
    [WXSDKEngine registerHandler:[WXImgLoaderDefaultImpl new] withProtocol:@protocol(WXImgLoaderProtocol)];
    [WXSDKEngine registerHandler:[JSExceptionProtocolImpl new] withProtocol:@protocol(WXJSExceptionProtocol)];
    
    [WXSDKEngine registerComponent:@"a" withClass:[WXPushComponent class]];
    [WXSDKEngine registerComponent:@"floading" withClass:[WXLoadingView class]];
    [WXSDKEngine registerComponent:@"image" withClass:[WXFImageComponent class]];
    [WXSDKEngine registerComponent:@"embed" withClass:[WXFEmbedComponent class]];
    [WXSDKEngine registerComponent:@"wheel" withClass:[WXFPicker class]];
    [WXSDKEngine registerComponent:@"web" withClass:[WXFWebComponent class]];
    [WXSDKEngine registerComponent:@"waterfall" withClass:[WXFRecyclerComponent class]];
    [WXSDKEngine registerComponent:@"host" withClass:[WXHost class]];
    [WXSDKEngine registerComponent:@"looper" withClass:[WXLooperText class]];
    [WXSDKEngine registerComponent:@"drawerlayout" withClass:[WXSlidComponent class]];
    
//    [WXLog setLogLevel: WXLogLevelOff];
       [WXLog setLogLevel: WXLogLevelError];
}


+(UIViewController*)start:(NSString*)image url:(NSString*)url
{
//    EntryControl *vc=[[EntryControl alloc]initWithImage:url img:image];
    RenderControl *vc=[[RenderControl alloc]initWithImage:url img:image];
    
    UINavigationController *nvc=[[UINavigationController alloc]initWithRootViewController:vc];
    return nvc;
}

+(NSMutableDictionary*)conifg
{

    if([URL isDiskExist])
    {
        return [self diskConifg];
    }
    else
    {
        return [self bundleConifg];
    }
   
}

+(NSMutableDictionary*)diskConifg
{
    NSString *path = @"app/config";
    NSURL *url=[URL loadFromDisk:[path add:@".json"]];
    NSError *err;
    NSString *str =[NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:&err];
    return [str toJson];
}

+(NSMutableDictionary*)bundleConifg
{
    NSString *path = @"app/config";
    NSURL *url=[URL loadFromBundle:path ext:@"json"];
    NSString *str =[NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    return [str toJson];
}

+(CGFloat)length:(CGFloat)length instance:(WXSDKInstance*)instance
{
    
     return length *instance.pixelScaleFactor;
   
}

+(RefreshManager*)getRefreshManager
{
   if(refreshManager==nil)
   {
       refreshManager=[RefreshManager new];
       refreshManager.lastrefresh=0;
   }
    return refreshManager;
}

 

+(CGFloat)fontSize:(CGFloat)fontsize instance:(WXSDKInstance*)instance
{
     return  [WXConvert WXPixelType:[@"" addFloat: fontsize] scaleFactor:instance.pixelScaleFactor];
}

+(NSString*)getBaseDir
{
    return basedir;
}
+(void)setBaseDir:(NSString*)dir
{
    basedir=dir;
}

//+(void)setBaseUrl:(NSString *)url
//{
//    //    NSString *url= weexInstance.scriptURL.absoluteString;
//    NSString *s=[s replace:@"root:" withString:@""];
//
//    if([url startWith:@"http"])
//    {
//        NSArray *n=  [url split:@":"];
//        if(n.count==3)
//        {
//            url=[[[[[[@"" add:n[0]] add:@":"] add:n[1]] add:@":"] add:[n[2] split:@"/"][0]] add:@"/"];
//        }
//        else if(n.count==3)
//        {
//            url=[[[@"" add:n[0]] add:[n[1] split:@"/"][0]] add:@"/"] ;
//        }
//
//        url=[url add:s];
//
//        if(![basedir isEqualToString:@""])
//            url=[[url add:basedir]add:@"/"];
//    }
//    else
//    {
//        NSArray *n= [url split:@"/app/"];
//        url=[n[0] add:@"/app/"] ;
////        url=@"app/";
//    }
//    baseurl=url;
//
//}
+(NSString*)getBaseUrl:(WXSDKInstance*)instance
{
    
    NSString *s=[s replace:@"root:" withString:@""];
    NSString *url=instance.scriptURL.absoluteString;
    if([url startWith:@"http"])
    {
        NSArray *n=  [url split:@":"];
        if(n.count==3)
        {
            url=[[[[[[@"" add:n[0]] add:@":"] add:n[1]] add:@":"] add:[n[2] split:@"/"][0]] add:@"/"];
        }
        else if(n.count==3)
        {
            url=[[[@"" add:n[0]] add:[n[1] split:@"/"][0]] add:@"/"] ;
        }
        
        url=[url add:s];
        
        if(![basedir isEqualToString:@""])
            url=[[url add:basedir]add:@"/"];
    }
    else
    {
        NSArray *n= [url split:@"/app/"];
        url=[n[0] add:@"/app/"] ;
        //        url=@"app/";
    }
    return url;
}

+(DebugScocket*)getDebugScocket
{
    if(debugScocket==nil)
        debugScocket=[DebugScocket new];
    return debugScocket;
}

+(void)startDebug:(NSString*)ip port:(NSString*)port
{
    
//    NSString *url=[[[[@"ws://" add:ip]add:@":"]add:port]add:@"/debugProxy/native/"];
//    [WXDevTool launchDevToolDebugWithUrl:url];
    
    DebugScocket  *debug=[Weex getDebugScocket];
    debug.success=^(NSString*channelId){


//        [WXDevTool setDebug:YES];
        NSString *url=[[[[[@"ws://" add:ip]add:@":"]add:port]add:@"/debugProxy/native/"] add:channelId];
        [WXDevTool launchDevToolDebugWithUrl:url];
        [[Weex getRefreshManager] send:[@"open=" add:channelId]];
    };
    debug.fail=^(){

    };
    [debug open:ip port:port];
    
    
 
     
//   [WXLog setLogLevel:WXLogLevelLog];
//     [WXDevTool setDebug:YES];
    
//     [WXDebugTool setDebug:YES];
}

+(NSString*)getEntry
{
    if(![Config isDebug])
    {
          NSString *entry=[self getSaveValue:@"mainurl"];
        if([@"" isEqualToString:entry]||entry==nil)
        {
              NSMutableDictionary *config=[Weex conifg];
            entry=[Config entry];
        }
      
        return entry;
    }
    NSString *s= [self getSaveValue:@"url"];
    if(s==nil||[s isEqualToString:@""])
    {
//        s=[Config entry];
    }
    return s;
}
+(NSString*)getDebugIp
{
    NSString *s= [self getSaveValue:@"url"];
    NSString *ip=@"";
    
    if(s!=nil&&![s isEqualToString:@""])
    {
      
        ip= [s findone:@"http://" end:@":"];
    }
    if(ip==nil||[ip isEqualToString:@""])
    {
        ip=[Config debugIp];
    }
    return ip;
}


+(NSURL*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance
{
    return [URL getFinalUrl:url weexInstance:weexInstance];
}

+(void)setImageSource:(NSString*)url compelete:(void(^)(UIImage *img))compelete
{
    if([url startWith:@"http"])
    {
        [[SDWebImageManager sharedManager] downloadImageWithURL:[NSURL URLWithString:url] options:0 progress:^(NSInteger receivedSize, NSInteger expectedSize) {
            
            
        } completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
            
      
            compelete(image);
            
        }];
    }
    else
    {
        
        NSArray *n= [url split:@"/app"];
        compelete([UIImage imageNamed:[@"app" add:n[1]]]);
    }
}


+(NSString*)socketPort
{
    //    NSString *port=  [[Weex conifg] objectForKey:@"socketPort"];
    NSString *port=[self getSaveValue:@"socketport"];
    if(port==nil||[@"" isEqualToString:port])
    {
        port=@"9897";
    }
    return port;
}



@end
