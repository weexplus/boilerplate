//
//  WXNetworkHistoryTableViewController.h
//  Flipboard
//
//  Created by Ryan Olson on 2/8/15.
//  Copyright (c) 2015 Flipboard. All rights reserved.
//

#import <UIKit/UIKit.h>

extern NSString *const kWXNetworkRecorderSearchbarDisableNotification;


@interface WXNetworkHistoryTableViewController : UITableViewController
-(void)refreshData;
@end
