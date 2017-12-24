//
//  WXLogViewController.m
//  Pods
//
//  Created by 齐山 on 2017/7/10.
//
//

#import "WXEnviromentViewController.h"
#import "WXTracingViewControllerManager.h"
#import <WeexSDK/WXUtility.h>
#import <WeexSDK/WXAppConfiguration.h>

@interface WXEnviromentViewController ()<UITextViewDelegate>

@end

@implementation WXEnviromentViewController

- (instancetype)initWithFrame:(CGRect )frame
{
    if ((self = [super init])) {
        self.view.frame = frame;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    UITextView *textView = [UITextView new];
    textView.delegate = self;
    textView.frame = CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height-64-64);
    [self.view addSubview:textView];
    NSString *str = [NSString stringWithFormat:@"%@\r\njsfmVersin:%@",[WXUtility JSONString:[WXUtility getEnvironment]],[WXAppConfiguration JSFrameworkVersion]];
    textView.text = str;
    textView.font = [UIFont systemFontOfSize:16.0];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (BOOL)textViewShouldBeginEditing:(UITextView *)textView{
    [textView resignFirstResponder];
    return NO;
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
