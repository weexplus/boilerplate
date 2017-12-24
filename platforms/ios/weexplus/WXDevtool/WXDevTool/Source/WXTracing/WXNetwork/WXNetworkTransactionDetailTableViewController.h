//
//  WXNetworkTransactionDetailTableViewController.h
//  Flipboard
//
//  Created by Ryan Olson on 2/10/15.
//  Copyright (c) 2015 Flipboard. All rights reserved.
//

#import <UIKit/UIKit.h>

@class WXNetworkTransaction;

@interface WXNetworkTransactionDetailTableViewController : UITableViewController

@property (nonatomic, strong) WXNetworkTransaction *transaction;

@end
