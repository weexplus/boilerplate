//
//  NetTableViewController.h
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "farwolf.h"
#import "JsonProtocol.h"
#import "LockScreenProgress.h"
@interface NetTableViewController : UITableViewController<JsonProtocol>

@property(strong,nonatomic)LockScreenProgress* progress;
-(id<ProgressProtocol>)getProgress;
-(BOOL)usePullToRefresh;
-(void)loadData;
-(NSString*)getPullrefreshColor;
@end
