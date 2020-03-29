//
//  WXPhotoplusComponent.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/5/29.
//

#import "WXPhotoplusComponent.h"
//注册组件名称为photoplus
WX_PlUGIN_EXPORT_COMPONENT(photoplus, WXPhotoplusComponent)


@implementation WXPhotoplusComponent

    //组件暴露方法给前端，在前端用this.$refs.photoplus.log()调用，
    //注意不能导出同步方法，如果想要返回值，请通过传入callback的方式来接收值
    WX_EXPORT_METHOD(@selector(log:))



    //构造函数，
    //attributes 属性值的初始值在这取然后在viewDidLoad中做首次渲染
   - (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
    {
        if (self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance]) {
        }
        return self;
    }


    -(UIView*)loadView
    {
        UIWebView *web=[UIWebView new];
        return  web;
    }


    //初始化完成调用
    -(void)viewDidLoad{
        [super viewDidLoad];
        //这里演示如何想前端发送事件，前端通过@load=“onload”来接受这个事件
        [self fireEvent:@"load" params:@{@"key":@"value"}];
    }


     //属性值发生变化，请在这里做界面渲染操作
    -(void)updateAttributes:(NSDictionary *)attributes{
        [super updateAttributes:attributes];
    }
    
    //可以在这里根据subcomponent类型加载指定类型的子元素，注意这个方法会在viewDidLoad之后调用
    -(void)insertSubview:(WXComponent *)subcomponent atIndex:(NSInteger)index
    {
        [super insertSubview:subcomponent atIndex:index];
    }
   -(void)log:(WXModuleCallback)callback{
        NSLog(@"log");
        if(callback){
            callback(@{});
        }
    }
    
    
    
@end
