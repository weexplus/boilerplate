//
//  RenderControl.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/4.
//

#import "RenderControl.h"
#import "farwolf.h"
#import "Masonry.h"
#import "EntryControl.h"
@interface RenderControl ()

@end

@implementation RenderControl

-(id)initWithImage:(NSString*)url img:(NSString*)img
{
    self=[super init];
    self.url=url;
    self.img=img;
    return self;
}

-(void)initView
{
    
    UIImageView *img=[UIImageView new];
    UIImage *mg=[UIImage imageNamed:self.img];
    img.contentMode=UIViewContentModeScaleAspectFill;
    img.image=mg;
    [self.view addSubview:img];
    [img mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
        make.top.equalTo(self.view);
        make.bottom.equalTo(self.view);
    }];
    EntryControl *vc=[[EntryControl alloc]initWithImage:_url img:_img];
     
    UINavigationController *nvc=[[UINavigationController alloc]initWithRootViewController:vc];
    [self.navigationController presentViewController:nvc animated:false completion:^{
        
    }];
}
-(void)viewWillAppear:(BOOL)animated
{
    [self.navigationController.navigationBar setHidden:true];
    [[UIApplication sharedApplication] setStatusBarHidden:false];
    //    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}
- (void)viewDidLoad {
    [super viewDidLoad];
    [self initView];
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
