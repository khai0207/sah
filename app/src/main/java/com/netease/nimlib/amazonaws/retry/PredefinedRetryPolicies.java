package com.netease.nimlib.amazonaws.retry;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.retry.RetryPolicy;
import com.netease.nimlib.c;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Random;

/* loaded from: classes.dex */
public class PredefinedRetryPolicies {
    private static final int BASE_DELAY_IN_MILLISECONDS = 100;
    public static final int DEFAULT_MAX_ERROR_RETRY = 3;
    public static final int DYNAMODB_DEFAULT_MAX_ERROR_RETRY = 10;
    private static final int MAX_BACKOFF_IN_MILLISECONDS = 20000;
    public static final RetryPolicy NO_RETRY_POLICY = new RetryPolicy(RetryPolicy.RetryCondition.NO_RETRY_CONDITION, RetryPolicy.BackoffStrategy.NO_DELAY, 0, false);
    public static final RetryPolicy.RetryCondition DEFAULT_RETRY_CONDITION = new SDKDefaultRetryCondition();
    public static final RetryPolicy.BackoffStrategy DEFAULT_BACKOFF_STRATEGY = new SDKDefaultBackoffStrategy(100, 20000);
    public static final RetryPolicy DEFAULT = getDefaultRetryPolicy();
    public static final RetryPolicy DYNAMODB_DEFAULT = getDynamoDBDefaultRetryPolicy();

    public static RetryPolicy getDefaultRetryPolicy() {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION, DEFAULT_BACKOFF_STRATEGY, 3, true);
    }

    public static RetryPolicy getDynamoDBDefaultRetryPolicy() {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION, DEFAULT_BACKOFF_STRATEGY, 10, true);
    }

    public static RetryPolicy getDefaultRetryPolicyWithCustomMaxRetries(int i) {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION, DEFAULT_BACKOFF_STRATEGY, i, false);
    }

    public static RetryPolicy getDynamoDBDefaultRetryPolicyWithCustomMaxRetries(int i) {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION, DEFAULT_BACKOFF_STRATEGY, i, false);
    }

    /* loaded from: classes.dex */
    public static class SDKDefaultRetryCondition implements RetryPolicy.RetryCondition {
        @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.RetryCondition
        public boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
            if ((amazonClientException.getCause() instanceof IOException) && !(amazonClientException.getCause() instanceof InterruptedIOException)) {
                return true;
            }
            if (!(amazonClientException instanceof AmazonServiceException)) {
                return false;
            }
            AmazonServiceException amazonServiceException = (AmazonServiceException) amazonClientException;
            int statusCode = amazonServiceException.getStatusCode();
            return statusCode == 500 || statusCode == 503 || statusCode == 502 || statusCode == 504 || (c.P() && statusCode == 404) || RetryUtils.isThrottlingException(amazonServiceException) || RetryUtils.isClockSkewError(amazonServiceException);
        }
    }

    /* loaded from: classes.dex */
    private static final class SDKDefaultBackoffStrategy implements RetryPolicy.BackoffStrategy {
        private final int baseDelayMs;
        private final int maxDelayMs;
        private final Random random;

        private SDKDefaultBackoffStrategy(int i, int i2) {
            this.random = new Random();
            this.baseDelayMs = i;
            this.maxDelayMs = i2;
        }

        @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.BackoffStrategy
        public final long delayBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
            if (i <= 0) {
                return 0L;
            }
            return this.random.nextInt(Math.min(this.maxDelayMs, (1 << i) * this.baseDelayMs));
        }
    }
}
