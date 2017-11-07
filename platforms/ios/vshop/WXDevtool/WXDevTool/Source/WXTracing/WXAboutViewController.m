//
//  WXAboutViewController.m
//  Pods
//
//  Created by 齐山 on 2017/7/21.
//
//

#import "WXAboutViewController.h"

@interface WXAboutViewController ()

@end

@implementation WXAboutViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UIWebView *webview = [[UIWebView alloc] initWithFrame:self.view.frame];
    [self.view addSubview:webview];
    NSString *url = @"https://weex.apache.org/index.html";
    NSLocale *locale = [NSLocale currentLocale];
    NSString *localName = [locale localeIdentifier];
    if([localName isEqualToString:@"zh-Hans"]){
        url = @"https://weex.apache.org/cn/index.html";
    }
    NSURL *nsUrl = [NSURL URLWithString:url];
    NSURLRequest *request = [NSURLRequest requestWithURL:nsUrl cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData timeoutInterval:30];
    
    [webview loadRequest:request];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
