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

WX_EXPORT_METHOD(@selector(addRule:rule:))

- (void)addRule:(NSString*)type rule:(NSDictionary *)rule
{
    if ([type isEqualToString:@"fontFace"] && [rule[@"src"] isKindOfClass:[NSString class]]) {
        if (rule[@"src"] && rule[@"fontFamily"] && ![WXUtility isBlankString:rule[@"src"]]) {
            NSString *ruleSrc = [WXConvert NSString:rule[@"src"]];
            if (!ruleSrc) {
                WXLogError(@"%@ is illegal for font src",rule[@"src"]);
                return;
            }
            NSUInteger start = [rule[@"src"] rangeOfString:@"url('"].location + @"url('".length;
            NSUInteger end  = [rule[@"src"] rangeOfString:@"')" options:NSBackwardsSearch].location;
            if (end <= start || start == NSNotFound || end == NSNotFound) {
                WXLogWarning(@"font url is not specified");
                return;
            }
           WXThreadSafeMutableDictionary *fonts=  [[WXRuleManager sharedInstance] getRule:@"fontFace"];
            
            NSString *fontSrc = [rule[@"src"] substringWithRange:NSMakeRange(start, end-start)];
            NSString *newURL = [fontSrc copy];
          NSURL *url=  [Weex getFinalUrl:newURL weexInstance:self.weexInstance];
//            WX_REWRITE_URL(fontSrc, WXResourceTypeFont, self.weexInstance)
            newURL =url.absoluteString;
            if (!newURL) {
                return;
            }
            
            fontSrc = newURL;
            NSMutableDictionary * fontFamily = [fonts objectForKey:rule[@"fontFamily"]];
            if (fontFamily && [fontFamily[@"src"] isEqualToString:fontSrc]) {
                // if the new src is same as src in dictionary , ignore it, or update it
                return;
            }
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
            
            [fonts setObject:fontFamily forKey:rule[@"fontFamily"]];
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
                    NSMutableDictionary * dictForFontFamily = [fonts objectForKey:rule[@"fontFamily"]];
                    NSString *fontSrc = [dictForFontFamily objectForKey:@"tempSrc"];
                    if (fontSrc) {
                        // only remote font will be mark as tempSrc
                        [dictForFontFamily setObject:fontSrc forKey:@"src"];
                    }
                    [dictForFontFamily setObject:url forKey:@"localSrc"];
                    
                    [[NSNotificationCenter defaultCenter] postNotificationName:WX_ICONFONT_DOWNLOAD_NOTIFICATION object:nil userInfo:@{@"fontFamily":rule[@"fontFamily"]}];
                } else {
                    //there was some errors during loading
                    WXLogError(@"load font failed %@",error.description);
                }
            }];
        }
    }
}

@end
