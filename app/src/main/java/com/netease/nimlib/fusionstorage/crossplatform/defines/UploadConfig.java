package com.netease.nimlib.fusionstorage.crossplatform.defines;

import java.util.List;

/* loaded from: classes.dex */
public class UploadConfig {
    private List<String> endpoints;
    private RetryStrategy retryStrategy;

    public UploadConfig(List<String> list, RetryStrategy retryStrategy) {
        this.endpoints = list;
        this.retryStrategy = retryStrategy;
    }

    public List<String> getEndpoints() {
        return this.endpoints;
    }

    public void setEndpoints(List<String> list) {
        this.endpoints = list;
    }

    public RetryStrategy getRetryStrategy() {
        return this.retryStrategy;
    }

    public void setRetryStrategy(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    public String toString() {
        return "UploadConfig{endpoints=" + this.endpoints + ", retryStrategy=" + this.retryStrategy + '}';
    }
}
