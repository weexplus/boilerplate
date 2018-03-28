//
//  UpdateDialogControl.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/3/27.
//

#import "UpdateDialogControl.h"

#import "Masonry.h"
#import "farwolf.h"

@interface UpdateDialogControl ()

@end

@implementation UpdateDialogControl

- (void)viewDidLoad {
    [super viewDidLoad];
//    [self initView];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)initBean:(Version*)v
{
    _version=v;
    self.versionname.text=[@"最新版本:" add:_version.versionName];
    self.sizename.text=[@"版本大小：" add:_version.size];
    self.desc.text=_version.desc;
    if(_version.level==1)
    {
          [self.ignoreroot setHidden:true];
          [self remove:@"lastchecktime"];
    }
    else if(_version.level==2)
    {
         [self.ignoreroot setHidden:true];
        [self.updateonly setHidden:false];
    }
    [self.check setOn:false];
    
}

- (IBAction)update:(id)sender {
  
    NSURL * url = [NSURL URLWithString:_version.downloadUrl];
     [[UIApplication sharedApplication] openURL:url];
    [self dismissPop];
}
- (IBAction)cancel:(id)sender {
    
    
    [self dismissPop];
    
    
}

-(void)dismissPop
{
    [self.view removeFromSuperview];
    [self removeFromParentViewController];
}

- (IBAction)checkChange:(id)sender {
    
    if(_check.on)
    {
        NSTimeInterval now=  [[NSDate date] timeIntervalSince1970]*1000;
        [[@"" addDouble:now] save:@"lastchecktime"];
        [self cancel:nil];
    }
}

-(void)initView
{

    if(_themeColor==nil)
    self.themeColor=@"#0891f1";
    
    self.view.backgroundColor=[@"#000000" toColor:0.4];
    
    UIView *layout=[UIView new];
    [layout setBackgroundColor:[@"#ffffff" toColor]];
    [self.view addSubview:layout];
    
    
    [layout mas_makeConstraints:^(MASConstraintMaker *make) {

        make.height.mas_equalTo(@400);
        make.centerX.equalTo(self.view);
        make.top.equalTo(self.view).offset(150);
        make.left.equalTo(self.view).offset(30);
//        make.right.equalTo(self.view).offset(30);

    }];
    
    
    UILabel *title=[UILabel new];
    [title setText:@"发现新版本"];
    [title setFont:[UIFont systemFontOfSize:20]];
    [title setTextColor:[_themeColor toColor]];
    [layout addSubview:title];
    [title mas_makeConstraints:^(MASConstraintMaker *make) {
        
        
        make.centerX.equalTo(layout);
        make.top.equalTo(layout).offset(20);
    
    }];
    UIView *line=[UIView new];
     [layout addSubview:line];
    [line setBackgroundColor: [_themeColor toColor]];
    [line mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.height.mas_equalTo(@1);
//        make.centerX.equalTo(layout);
         make.left.equalTo(layout).offset(10);
        make.right.equalTo(layout).offset(10);
        make.top.equalTo(title).offset(35);
        
    }];
    
}


@end
