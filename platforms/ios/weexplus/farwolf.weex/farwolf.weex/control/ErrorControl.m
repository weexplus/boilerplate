//
//  ErrorControl.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/11.
//
//

#import "ErrorControl.h"
#import "farwolf.h"
#import "Masonry.h"
@interface ErrorControl ()

@end

@implementation ErrorControl

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initView];
    self.errText.text=self.errmsg;
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)closeClick:(id)sender {
    
//    [self dismiss:true];
    self.onClose();
}

-(void)initView
{
   
    self.btn=[UIButton new];
    [self.btn setTitle:@"关闭"forState:UIControlStateNormal];
    [self.btn setTitleColor:[@"ffffff" toColor] forState:UIControlStateNormal];
    [self.btn addClick:@selector(closeClick:) host:self];
    UIScrollView *scroll=[UIScrollView new];
    self.errText=[UITextView new];
    self.errText.backgroundColor=[UIColor clearColor];
    self.errText.textColor=[@"#ffffff" toColor];
    self.errText.font=[UIFont systemFontOfSize:14];
    self.btn.backgroundColor=[UIColor clearColor];
    self.view.backgroundColor=[@"#ff4444" toColor];
    
    UIView *line=[UIView new];
    line.backgroundColor=[@"#ffffff" toColor];
     [self.view addSubview:line];
    [self.view addSubview:self.btn];
    [self.view addSubview:scroll];
    [scroll addSubview:self.errText];
    
    
    [line mas_makeConstraints:^(MASConstraintMaker *make) {
      
        make.height.mas_equalTo(@1);
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
    
    }];
    
    [self.btn mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.height.mas_equalTo(@50);
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
        make.bottom.equalTo(self.view);
        make.top.equalTo(line);
    }];
    
    [scroll mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.top.equalTo(self.view).offset(10);
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
        make.bottom.equalTo(line);
    }];
   
    
    [self.errText mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.top.equalTo(scroll);
        make.left.equalTo(scroll);
        make.right.equalTo(scroll);
        make.bottom.equalTo(scroll);
        make.height.equalTo(scroll);
        make.width.equalTo(scroll);
    }];
    
    
    
    
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
