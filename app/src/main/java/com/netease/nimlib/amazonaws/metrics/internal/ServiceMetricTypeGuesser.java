package com.netease.nimlib.amazonaws.metrics.internal;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.metrics.AwsSdkMetrics;
import com.netease.nimlib.amazonaws.metrics.SimpleThroughputMetricType;
import com.netease.nimlib.amazonaws.metrics.ThroughputMetricType;

/* loaded from: classes.dex */
public enum ServiceMetricTypeGuesser {
    ;

    public static ThroughputMetricType guessThroughputMetricType(Request<?> request, String str, String str2) {
        if (!AwsSdkMetrics.isMetricsEnabled() || !request.getOriginalRequest().getClass().getName().startsWith("com.amazonaws.services.s3")) {
            return null;
        }
        return new SimpleThroughputMetricType("S3" + str, request.getServiceName(), "S3" + str2);
    }
}
