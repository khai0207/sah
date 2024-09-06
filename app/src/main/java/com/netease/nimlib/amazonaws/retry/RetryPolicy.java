package com.netease.nimlib.amazonaws.retry;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public final class RetryPolicy {
    private final BackoffStrategy backoffStrategy;
    private final boolean honorMaxErrorRetryInClientConfig;
    private final int maxErrorRetry;
    private final RetryCondition retryCondition;

    public RetryPolicy(RetryCondition retryCondition, BackoffStrategy backoffStrategy, int i, boolean z) {
        retryCondition = retryCondition == null ? PredefinedRetryPolicies.DEFAULT_RETRY_CONDITION : retryCondition;
        backoffStrategy = backoffStrategy == null ? PredefinedRetryPolicies.DEFAULT_BACKOFF_STRATEGY : backoffStrategy;
        if (i < 0) {
            throw new IllegalArgumentException("Please provide a non-negative value for maxErrorRetry.");
        }
        this.retryCondition = retryCondition;
        this.backoffStrategy = backoffStrategy;
        this.maxErrorRetry = i;
        this.honorMaxErrorRetryInClientConfig = z;
    }

    public RetryCondition getRetryCondition() {
        return this.retryCondition;
    }

    public BackoffStrategy getBackoffStrategy() {
        return this.backoffStrategy;
    }

    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public boolean isMaxErrorRetryInClientConfigHonored() {
        return this.honorMaxErrorRetryInClientConfig;
    }

    /* loaded from: classes.dex */
    public interface RetryCondition {
        public static final RetryCondition NO_RETRY_CONDITION = new RetryCondition() { // from class: com.netease.nimlib.amazonaws.retry.RetryPolicy.RetryCondition.1
            @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.RetryCondition
            public boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
                return false;
            }

            AnonymousClass1() {
            }
        };

        boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i);

        /* renamed from: com.netease.nimlib.amazonaws.retry.RetryPolicy$RetryCondition$1 */
        /* loaded from: classes.dex */
        static class AnonymousClass1 implements RetryCondition {
            @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.RetryCondition
            public boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
                return false;
            }

            AnonymousClass1() {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface BackoffStrategy {
        public static final BackoffStrategy NO_DELAY = new BackoffStrategy() { // from class: com.netease.nimlib.amazonaws.retry.RetryPolicy.BackoffStrategy.1
            @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.BackoffStrategy
            public long delayBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
                return 0L;
            }

            AnonymousClass1() {
            }
        };

        long delayBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i);

        /* renamed from: com.netease.nimlib.amazonaws.retry.RetryPolicy$BackoffStrategy$1 */
        /* loaded from: classes.dex */
        static class AnonymousClass1 implements BackoffStrategy {
            @Override // com.netease.nimlib.amazonaws.retry.RetryPolicy.BackoffStrategy
            public long delayBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i) {
                return 0L;
            }

            AnonymousClass1() {
            }
        }
    }
}
