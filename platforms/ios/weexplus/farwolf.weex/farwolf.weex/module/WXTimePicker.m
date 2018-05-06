//
//  WXTimerPicker.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/6.
//

#import "WXTimePicker.h"

#import "farwolf.h"

@implementation WXTimePicker

WX_EXPORT_METHOD(@selector(pickTime:callback:))


@synthesize weexInstance;

 

-(void)pickTime:(NSMutableDictionary*) param callback:(WXModuleCallback)callback
{
    _chooseTimeView = [[LMDisposeChooseTimeView alloc] initWithFrame: weexInstance.viewController.view.bounds];
    [_chooseTimeView showInView:weexInstance.viewController.view];
    _chooseTimeView.chooseDateWithTimerBlock = ^(NSString *dateWithTimerStr) {
        NSLog(@"dateWithTimerStr  %@",dateWithTimerStr);
        dateWithTimerStr=[dateWithTimerStr replace:@"年" withString:@"-"];
        dateWithTimerStr=[dateWithTimerStr replace:@"月" withString:@"-"];
        dateWithTimerStr=[dateWithTimerStr replace:@"日" withString:@""];
        NSMutableDictionary *res=[NSMutableDictionary new];
        res[@"result"]=@"success";
        res[@"data"]=dateWithTimerStr;
        callback(res);
    };
}


@end
