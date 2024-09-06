package com.netease.nimlib.fusionstorage.crossplatform.defines;

/* loaded from: classes.dex */
public class RetryStrategy {
    private int circuitBreakTime;
    private int retryCount;
    private boolean retryNext;

    public RetryStrategy(int i, int i2, boolean z) {
        this.retryCount = i;
        this.circuitBreakTime = i2;
        this.retryNext = z;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setRetryCount(int i) {
        this.retryCount = i;
    }

    public int getCircuitBreakTime() {
        return this.circuitBreakTime;
    }

    public void setCircuitBreakTime(int i) {
        this.circuitBreakTime = i;
    }

    public boolean isRetryNext() {
        return this.retryNext;
    }

    public void setRetryNext(boolean z) {
        this.retryNext = z;
    }

    public String toString() {
        return "RetryStrategy{retryCount=" + this.retryCount + ", circuitBreakTime=" + this.circuitBreakTime + ", retryNext=" + this.retryNext + '}';
    }
}
