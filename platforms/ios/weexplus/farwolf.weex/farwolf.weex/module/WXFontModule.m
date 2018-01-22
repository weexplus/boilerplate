//
//  WXFontModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/1/15.
//

#import "WXFontModule.h"
#import "WXRuleManager.h"
#import "WXThreadSafeMutableDictionary.h"
#import "WXUtility.h"
#import "WXConvert.h"
#import "WXHandlerFactory.h"
#import "WXURLRewriteProtocol.h"
#import "WXComponentManager.h"
#import "WXDefine.h"
#import "WXSDKEngine.h"
#import "Weex.h"
@implementation WXFontModule
@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(addFont:url:))

- (void)addFont:(NSString*)name url:(NSString*)Url
{
    
    
    WXThreadSafeMutableDictionary *fonts=  [[WXRuleManager sharedInstance] getRule:@"fontFace"];
    NSURL *url=  [Weex getFinalUrl:Url weexInstance:self.weexInstance];
    //            WX_REWRITE_URL(fontSrc, WXResourceTypeFont, self.weexInstance)
    
    if (!url.absoluteString) {
        return;
    }
    
    NSString *fontSrc = url.absoluteString;
    NSMutableDictionary * fontFamily = [fonts objectForKey:name];
    
    if (!fontFamily) {
        fontFamily = [NSMutableDictionary dictionary];
    }
    NSURL *fontURL = [NSURL URLWithString:fontSrc];
    if (!fontURL) {
        // if the fontSrc string is illegal, the fontURL will be nil
        return;
    }
    if([fontURL isFileURL]){
        [fontFamily setObject:fontSrc forKey:@"src"];
    }else {
        [fontFamily setObject:fontSrc forKey:@"tempSrc"];
    }
    
    [fonts setObject:fontFamily forKey:name];
    // remote font file
    NSString *fontfile = [NSString stringWithFormat:@"%@/%@",WX_FONT_DOWNLOAD_DIR,[WXUtility md5:[fontURL absoluteString]]];
    if ([WXUtility isFileExist:fontfile]) {
        // if has been cached, load directly
        [fontFamily setObject:[NSURL fileURLWithPath:fontfile] forKey:@"localSrc"];
        return;
    }
    //zjr add
    if([fontURL.absoluteString hasPrefix:@"file"])
    {
        [fontFamily setObject:fontURL forKey:@"localSrc"];
        return;
    }
    __weak typeof(self) weakSelf = self;
    [WXUtility getIconfont:fontURL completion:^(NSURL * _Nonnull url, NSError * _Nullable error) {
        if (!error && url) {
            // load success
            NSMutableDictionary * dictForFontFamily = [fonts objectForKey:name];
            NSString *fontSrc = [dictForFontFamily objectForKey:@"tempSrc"];
            if (fontSrc) {
                // only remote font will be mark as tempSrc
                [dictForFontFamily setObject:fontSrc forKey:@"src"];
            }
            [dictForFontFamily setObject:url forKey:@"localSrc"];
            
            [[NSNotificationCenter defaultCenter] postNotificationName:WX_ICONFONT_DOWNLOAD_NOTIFICATION object:nil userInfo:@{@"fontFamily":name}];
        } else {
            //there was some errors during loading
            WXLogError(@"load font failed %@",error.description);
        }
    }];
    
    
}

@end

