package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.retry.RetryPolicy;
import com.netease.nimlib.amazonaws.services.s3.model.AmazonS3Exception;

/* loaded from: classes.dex */
public class CompleteMultipartUploadRetryCondition implements RetryPolicy.RetryCondition {
    private static final String ERROR_CODE = "InternalError";
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final String RETYABLE_ERROR_MESSAGE = "Please try again.";
    private final int maxCompleteMultipartUploadRetries;

    public CompleteMultipartUploadRetryCondition() {
        this(3);
    }

    CompleteMultipartUploadRetryCondition(int i) {
        this.maxCompleteMultipartUploadRetries = i;
    }

    @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.RetryCondition
    public boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
        return (amazonClientException instanceof AmazonS3Exception) && test((AmazonS3Exception) amazonClientException) && i < this.maxCompleteMultipartUploadRetries;
    }

    boolean test(AmazonS3Exception amazonS3Exception) {
        return (amazonS3Exception == null || amazonS3Exception.getErrorCode() == null || amazonS3Exception.getErrorMessage() == null || !amazonS3Exception.getErrorCode().contains(ERROR_CODE) || !amazonS3Exception.getErrorMessage().contains(RETYABLE_ERROR_MESSAGE)) ? false : true;
    }
}
