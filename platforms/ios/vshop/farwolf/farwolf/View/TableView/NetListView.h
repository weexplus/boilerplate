//
//  NetListView.h
//  oa
//
//  Created by 郑江荣 on 16/4/26.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "ListView.h"
#import "JsonProtocol.h"
#import "LockScreenProgress.h"
#import "JsonReader.h"
@interface NetListView : ListView<JsonProtocol>
 
 
@property (nonatomic) BOOL isLoading;
@property (nonatomic) BOOL hasMore;

@property (nonatomic,strong) AFHTTPRequestOperation*request;

-(Class)getClass;
@property(strong,nonatomic)LockScreenProgress* progress;
-(id<ProgressProtocol>)getProgress;



-(NSString*)getPage;
-(void)addPage:(JsonReader*)j;
@end
