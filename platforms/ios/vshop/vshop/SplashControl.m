//
//  SplashControl.m
//  vshop
//
//  Created by 郑江荣 on 2017/6/15.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "SplashControl.h"
#import "Page.h"
#import "WeexFactory.h"
static NSMutableDictionary<NSString*,Page*> *cache;
   
@interface SplashControl ()


@end

@implementation SplashControl

+(Page*)getPage:(NSString*)key
{
    return cache[key];
}
-(UIViewController*)getSplash
{
    return self;
}
- (void)viewDidLoad {
    [super viewDidLoad];
    cache=[ NSMutableDictionary<NSString*,Page*> new];
    _count=0;
    NSMutableDictionary *d=[NSMutableDictionary new];
  
//    [d setValue:@"app/views/home/Home" forKey:@"推荐"];
//    [d setValue:@"app/views/message/Message" forKey:@"电视剧"];
//    [d setValue:@"app/views/task/Task" forKey:@"电影"];
//    [d setValue:@"app/views/contacts/Contacts" forKey:@"关注"];
//    [d setValue:@"app/views/me/Me" forKey:@"我的"];
    
    [d setValue:@"app/busi/tab/mainpage" forKey:@"推荐"];
    [d setValue:@"app/busi/tab/serial" forKey:@"电视剧"];
    [d setValue:@"app/busi/tab/movie" forKey:@"电影"];
    [d setValue:@"app/busi/tab/collection" forKey:@"关注"];
//    [d setValue:@"app/busi/tab/mine" forKey:@"我的"];
 
    for(NSString* key in   d.allKeys)
    {
     
        NSString *s=[d objectForKey:key];
        NSURL *url=[[NSBundle mainBundle] URLForResource:s withExtension:@"js"];
        [WeexFactory render:url compelete:^(Page *p) {
    
           
            [cache setObject:p forKey:key];
            _count++;
            if(_count==4)
            {
                 [self present:@"Main/TabControl" anim:false];
                [self back:true];
            }
        }];
    }
    
    
    
    
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
