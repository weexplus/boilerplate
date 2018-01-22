//
//  WXLogViewController.m
//  Pods
//
//  Created by 齐山 on 2017/7/10.
//
//

#import "WXLogViewController.h"
#import "WXTracingViewControllerManager.h"

@interface WXLogViewController ()

@end

@implementation WXLogViewController

- (instancetype)initWithFrame:(CGRect )frame
{
    if ((self = [super init])) {
        self.view.frame = frame;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [WXTracingViewControllerManager sharedInstance].textView.frame = CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height-44);
    [WXTracingViewControllerManager sharedInstance].textView.scrollEnabled = YES;
    [WXTracingViewControllerManager sharedInstance].textView.delegate = self;
    [self.view addSubview:[WXTracingViewControllerManager sharedInstance].textView];
    self.view.backgroundColor = [UIColor redColor];
    [WXTracingViewControllerManager sharedInstance].textView.font = [UIFont systemFontOfSize:16.0];
//    [[WXTracingViewControllerManager sharedInstance].textView scrollRangeToVisible:NSMakeRange([WXTracingViewControllerManager sharedInstance].textView.text.length - 1, 1)];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark-
#pragma UITextViewDelegate
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
