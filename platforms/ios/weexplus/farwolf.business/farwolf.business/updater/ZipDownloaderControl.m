//
//  ZipDownloaderControl.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/4/30.
//

#import "ZipDownloaderControl.h"
#import "farwolf.h"
#import "ZipDownloader.h"
#import "URL.h"
#import "UpdateChecker.h"

@interface ZipDownloaderControl ()

@end

@implementation ZipDownloaderControl

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self updateJs:self.url];
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

-(void)updateJs:(NSString*)url
{
    
    UpdateChecker *up=[UpdateChecker new];
    
    
    [up updateJs:url version:self.jsVersion progress:^(float p) {
        self.progress.progress=p/100;
        self.percent.text=[[@"" addFloat:p]add:@"%"];
        if(p==100.0)
        {
            self.lable.text=@"解压中";
        }
    } compelete:^(NSString *path) {
        self.lable.text=@"解压完毕";
        //        [self dismiss:true];
        [self removeFromParentViewController];
        [self.view removeFromSuperview];
    }];
    
    
    
}

@end
