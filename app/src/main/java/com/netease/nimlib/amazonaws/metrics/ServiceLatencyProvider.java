package com.netease.nimlib.amazonaws.metrics;

import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.util.TimingInfo;

/* loaded from: classes.dex */
public class ServiceLatencyProvider {
    private long endNano;
    private final ServiceMetricType serviceMetricType;
    private final long startNano;

    public ServiceLatencyProvider(ServiceMetricType serviceMetricType) {
        long nanoTime = System.nanoTime();
        this.startNano = nanoTime;
        this.endNano = nanoTime;
        this.serviceMetricType = serviceMetricType;
    }

    public ServiceMetricType getServiceMetricType() {
        return this.serviceMetricType;
    }

    public ServiceLatencyProvider endTiming() {
        if (this.endNano != this.startNano) {
            throw new IllegalStateException();
        }
        this.endNano = System.nanoTime();
        return this;
    }

    public double getDurationMilli() {
        if (this.endNano == this.startNano) {
            LogFactory.getLog(getClass()).debug("Likely to be a missing invocation of endTiming().");
        }
        return TimingInfo.durationMilliOf(this.startNano, this.endNano);
    }

    public String getProviderId() {
        return super.toString();
    }

    public String toString() {
        return String.format("providerId=%s, serviceMetricType=%s, startNano=%d, endNano=%d", getProviderId(), this.serviceMetricType, Long.valueOf(this.startNano), Long.valueOf(this.endNano));
    }
}
