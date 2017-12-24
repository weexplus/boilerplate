//
//  UIView+Farwolf.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "UIView+Farwolf.h"
#import "Toast.h"
#import "Masonry.h"
#import "farwolf.h"


@implementation UIView (Farwolf)

static char oneclickKey = 'oneclickKey';
static char doubleclickKey = 'doubleclickKey';
static char threeclickKey = 'threeclickKey';

- (clickcallback )oneClick
{
    return objc_getAssociatedObject(self, &oneclickKey);
}

- (void)setOneClick:(clickcallback )oneClick
{
    objc_setAssociatedObject(self, &oneclickKey, oneClick, OBJC_ASSOCIATION_COPY_NONATOMIC);
}


- (clickcallback )dbClick
{
    return objc_getAssociatedObject(self, &doubleclickKey);
}

- (void)setDbClick:(clickcallback )dbClick
{
    objc_setAssociatedObject(self, &doubleclickKey, dbClick, OBJC_ASSOCIATION_COPY_NONATOMIC);
}



- (clickcallback )threeClick
{
    return objc_getAssociatedObject(self, &threeclickKey);
}

- (void)setThreeClick:(clickcallback )threeClick
{
    objc_setAssociatedObject(self, &threeclickKey, threeClick, OBJC_ASSOCIATION_COPY_NONATOMIC);
}




-(UIViewController*)getCurrentVc
{
    return [self getCurrentVc:[UIViewController class]];
    
}


-(CGFloat) screenHeight
{
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    return h;
}

-(CGFloat) screenWidth
{
    CGFloat h=[UIScreen mainScreen].bounds.size.width;
    return h;
}


-(UIViewController*)getCurrentVc:(Class)c
{
    UIResponder* obj= [self nextResponder];
    while (obj!=nil&&![obj isKindOfClass:c]) {
        obj=[obj nextResponder];
    }
    return (UIViewController*)obj;
}


-(NSMutableArray<UIView*>*)findViewByType:(Class)c
{
    
    NSMutableArray<UIView*> *l=[[NSMutableArray<UIView*> alloc]init];
    for(int i=0;i<[self subviews].count;i++)
    {
        
        UIView *v=[self subviews][i];
        if([v isKindOfClass:c])
        {
            [l addObject:v];
        }
        
    }
    return l;
    
}

-(NSMutableArray<UIView*>*)findAllViewByType:(Class)c
{
    
    NSMutableArray<UIView*> *l=[[NSMutableArray<UIView*> alloc]init];
    for(int i=0;i<[self subviews].count;i++)
    {
        
        UIView *v=[self subviews][i];
        if([v isKindOfClass:c])
        {
            [l addObject:v];
        }
        else{
            
            NSMutableArray<UIView*>* lv=[v findAllViewByType:c];
            [l addObjectsFromArray:lv];
        }
        
    }
    return l;
    
}


-(void)setImageBackground:(UIImage *)ImageBackground
{
    self.layer.contents = ( id) ImageBackground.CGImage;
}


-(UIView*)findOneViewByType:(Class)c
{
    
    
    for(int i=0;i<[self subviews].count;i++)
    {
        
        UIView *v=[self subviews][i];
        if([v isKindOfClass:c])
        {
            return v;
        }
        else{
            
            UIView *vp=  [v findOneViewByType:c];
            if(vp!=nil)
            {
                return vp;
            }
            
        }
        
    }
    return nil;
    
}

-(void)disMissKeyboard
{
    
    NSMutableArray<UIView*> *l=  [[self getCurrentVc].view findAllViewByType:[UITextField class]];
    for (UITextField* v in l) {
        [v endEditing:true];
        
    }
}

-(void)addSubviewFull:(UIView *)view
{
    view.frame=CGRectMake(0, 0, self.frame.size.width, self.frame.size.height);
    [self addSubview:view];
}

-(void)setCornerRadius:(CGFloat)CornerRadius
{
    
    [self.layer setCornerRadius:CornerRadius];
}


//-(CGFloat)getCornerRadius
//{
//    return self.layer.cornerRadius;
//}

-(void)setBorderWidth:(CGFloat)BorderWidth
{
    [self.layer setBorderWidth:BorderWidth];
}




-(void)setBorderColor:(UIColor *)BorderColor
{
    [self.layer setBorderColor:BorderColor.CGColor];
}

-(void) setLayerBackground:(UIColor *)LayerBackground
{
    [self.layer setBackgroundColor:LayerBackground.CGColor];
}



-(void)addClick:(SEL )method host:(NSObject *)host
{
    
    self.userInteractionEnabled=true;
    UITapGestureRecognizer *u=[[UITapGestureRecognizer alloc]initWithTarget:host action:method];
    [self addGestureRecognizer:u];
}

-(void)addClick:(clickcallback)click
{
    self.oneClick=click;
    self.userInteractionEnabled=true;
    UITapGestureRecognizer *u=[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(doClick)];
    [self addGestureRecognizer:u];
}


-(void)add3Click:(clickcallback)click
{
    self.threeClick=click;
    self.userInteractionEnabled=true;
    UITapGestureRecognizer *u=[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(do3Click)];
    [u setNumberOfTapsRequired:3];
    [self addGestureRecognizer:u];

}
-(void)addDoubleClick:(clickcallback)click
{
    
    self.dbClick=click;
    self.userInteractionEnabled=true;
    UITapGestureRecognizer *u=[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(doDoubleClick)];
    [u setNumberOfTapsRequired:2];
    [self addGestureRecognizer:u];
}

-(void)do3Click
{
    self.threeClick();
}
-(void)doDoubleClick
{
    self.dbClick();
}
-(void)doClick
{
    self.oneClick();
}

-(void)addDoubleClick:(SEL )method host:(NSObject *)host
{
    
    self.userInteractionEnabled=true;
    UITapGestureRecognizer *u=[[UITapGestureRecognizer alloc]initWithTarget:host action:method];
    [u setNumberOfTapsRequired:2];
    [self addGestureRecognizer:u];
}

-(void)toast:(NSString*)msg
{
    [[Toast create:[UIApplication sharedApplication].keyWindow] toast:msg];
}


-(void)equalDivideHorizontally:(NSArray<UIView*>*)n padding:(CGFloat)p
{
    
    
    for(UIView *v in n)
    {
        int i=[n indexOfObject:v];
        if(i==0)
        {
            [v mas_makeConstraints:^(MASConstraintMaker *make) {
                
                UIView *next=n[i+1];
                make.width.equalTo(next.mas_width);
                make.left.equalTo(self.mas_left);
                make.top.equalTo(self.mas_top);
                make.height.equalTo(self);
                make.right.equalTo(next.mas_left).with.insets(UIEdgeInsetsMake(0, 0, 0, p));
                
            }];
        }
        
        else if(i==n.count-1)
        {
            UIView *first=n[0];
            UIView *pre=n[i-1];
            [v mas_makeConstraints:^(MASConstraintMaker *make) {
                
                
                make.width.equalTo(first.mas_width);
                make.left.equalTo(pre.mas_right);
                make.top.equalTo(self.mas_top);
                make.height.equalTo(self);
                make.right.equalTo(self.mas_right);
                
            }];
        }
        else
        {
            UIView *first=n[0];
            UIView *pre=n[i-1];
            UIView *next=n[i+1];
            [v mas_makeConstraints:^(MASConstraintMaker *make) {
                
                
                make.width.equalTo(first.mas_width);
                make.left.equalTo(pre.mas_right);
                make.right.equalTo(next.mas_left).with.insets(UIEdgeInsetsMake(0, 0, 0, p));;
                make.top.equalTo(self.mas_top);
                make.height.equalTo(self);
                
            }];
        }
        
    }
}
- (void) distributeSpacingVerticallyWith:(NSArray*)views
{
    NSMutableArray *spaces = [NSMutableArray arrayWithCapacity:views.count+1];
    
    for ( int i = 0 ; i < views.count+1 ; ++i )
    {
        UIView *v = [UIView new];
        [spaces addObject:v];
        [self addSubview:v];
        
        [v mas_makeConstraints:^(MASConstraintMaker *make) {
            make.width.equalTo(v.mas_height);
        }];
    }
    
    
    UIView *v0 = spaces[0];
    
    __weak __typeof(&*self)ws = self;
    
    [v0 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(ws.mas_top);
        make.centerX.equalTo(((UIView*)views[0]).mas_centerX);
    }];
    
    UIView *lastSpace = v0;
    for ( int i = 0 ; i < views.count; ++i )
    {
        UIView *obj = views[i];
        UIView *space = spaces[i+1];
        
        [obj mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(lastSpace.mas_bottom);
        }];
        
        [space mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(obj.mas_bottom);
            make.centerX.equalTo(obj.mas_centerX);
            make.height.equalTo(v0);
        }];
        
        lastSpace = space;
    }
    
    [lastSpace mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(ws.mas_bottom);
    }];
}

-(void)distributeSpacingHorizontallyWith:(NSArray*)views
{
    NSMutableArray *spaces = [NSMutableArray arrayWithCapacity:views.count+1];
    
    for ( int i = 0 ; i < views.count+1 ; ++i )
    {
        UIView *v = [UIView new];
        [spaces addObject:v];
        [self addSubview:v];
        
        [v mas_makeConstraints:^(MASConstraintMaker *make) {
            make.width.equalTo(v.mas_height);
            make.height.equalTo(v.mas_height);
        }];
    }
    
    UIView *v0 = spaces[0];
    
    __weak __typeof(&*self)ws = self;
    
    [v0 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(ws.mas_left);
        make.centerY.equalTo(((UIView*)views[0]).mas_centerY);
        //        make.height.equalTo(self);
    }];
    
    UIView *lastSpace = v0;
    for ( int i = 0 ; i < views.count; ++i )
    {
        UIView *obj = views[i];
        UIView *space = spaces[i+1];
        
        [obj mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(lastSpace.mas_right);
        }];
        
        [space mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(obj.mas_right);
            make.centerY.equalTo(obj.mas_centerY);
            make.width.equalTo(v0);
            make.height.equalTo(v0);
        }];
        
        lastSpace = space;
    }
    
    [lastSpace mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(ws.mas_right);
    }];
    
}





@end
