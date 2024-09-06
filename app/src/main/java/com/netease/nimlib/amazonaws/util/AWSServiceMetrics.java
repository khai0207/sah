package com.netease.nimlib.amazonaws.util;

import com.netease.nimlib.amazonaws.metrics.ServiceMetricType;

@Deprecated
/* loaded from: classes.dex */
public enum AWSServiceMetrics implements ServiceMetricType {
    HttpClientGetConnectionTime("HttpClient");

    private final String serviceName;

    AWSServiceMetrics(String str) {
        this.serviceName = str;
    }

    @Override // com.netease.nimlib.amazonaws.metrics.ServiceMetricType
    public String getServiceName() {
        return this.serviceName;
    }
}
