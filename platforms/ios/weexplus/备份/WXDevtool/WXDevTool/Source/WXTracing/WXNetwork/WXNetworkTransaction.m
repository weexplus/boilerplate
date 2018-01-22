//
//  WXNetworkTransaction.m
//  Flipboard
//
//  Created by Ryan Olson on 2/8/15.
//  Copyright (c) 2015 Flipboard. All rights reserved.
//

#import "WXNetworkTransaction.h"

@interface WXNetworkTransaction ()

@property (nonatomic, strong, readwrite) NSData *cachedRequestBody;

@end

@implementation WXNetworkTransaction

- (NSString *)description
{
    NSString *description = [super description];

    description = [description stringByAppendingFormat:@" id = %@;", self.requestID];
    description = [description stringByAppendingFormat:@" url = %@;", self.request.URL];
    description = [description stringByAppendingFormat:@" duration = %f;", self.duration];
    description = [description stringByAppendingFormat:@" receivedDataLength = %lld", self.receivedDataLength];

    return description;
}

- (NSData *)cachedRequestBody {
    if (!_cachedRequestBody) {
        if (self.request.HTTPBody != nil) {
            _cachedRequestBody = self.request.HTTPBody;
        } else if ([self.request.HTTPBodyStream conformsToProtocol:@protocol(NSCopying)]) {
            NSInputStream *bodyStream = [self.request.HTTPBodyStream copy];
            const NSUInteger bufferSize = 1024;
            uint8_t buffer[bufferSize];
            NSMutableData *data = [NSMutableData data];
            [bodyStream open];
            NSInteger readBytes = 0;
            do {
                readBytes = [bodyStream read:buffer maxLength:bufferSize];
                [data appendBytes:buffer length:readBytes];
            } while (readBytes > 0);
            [bodyStream close];
            _cachedRequestBody = data;
        }
    }
    return _cachedRequestBody;
}

+ (NSString *)readableStringFromTransactionState:(WXNetworkTransactionState)state
{
    NSString *readableString = nil;
    switch (state) {
        case WXNetworkTransactionStateUnstarted:
            readableString = @"Unstarted";
            break;

        case WXNetworkTransactionStateAwaitingResponse:
            readableString = @"Awaiting Response";
            break;

        case WXNetworkTransactionStateReceivingData:
            readableString = @"Receiving Data";
            break;

        case WXNetworkTransactionStateFinished:
            readableString = @"Finished";
            break;

        case WXNetworkTransactionStateFailed:
            readableString = @"Failed";
            break;
    }
    return readableString;
}

@end
