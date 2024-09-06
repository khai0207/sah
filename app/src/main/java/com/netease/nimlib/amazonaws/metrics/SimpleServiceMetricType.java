package com.netease.nimlib.amazonaws.metrics;

/* loaded from: classes.dex */
public class SimpleServiceMetricType extends SimpleMetricType implements ServiceMetricType {
    private final String name;
    private final String serviceName;

    public SimpleServiceMetricType(String str, String str2) {
        this.name = str;
        this.serviceName = str2;
    }

    @Override // com.netease.nimlib.amazonaws.metrics.SimpleMetricType, com.netease.nimlib.amazonaws.metrics.MetricType
    public String name() {
        return this.name;
    }

    @Override // com.netease.nimlib.amazonaws.metrics.ServiceMetricType
    public String getServiceName() {
        return this.serviceName;
    }
}
