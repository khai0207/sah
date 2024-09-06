package com.netease.nimlib.amazonaws.metrics;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.Response;

/* loaded from: classes.dex */
public abstract class RequestMetricCollector {
    public static final RequestMetricCollector NONE = new RequestMetricCollector() { // from class: com.netease.nimlib.amazonaws.metrics.RequestMetricCollector.1
        @Override // com.netease.nimlib.amazonaws.metrics.RequestMetricCollector
        public void collectMetrics(Request<?> request, Response<?> response) {
        }

        @Override // com.netease.nimlib.amazonaws.metrics.RequestMetricCollector
        public boolean isEnabled() {
            return false;
        }
    };

    /* loaded from: classes.dex */
    public interface Factory {
        RequestMetricCollector getRequestMetricCollector();
    }

    public abstract void collectMetrics(Request<?> request, Response<?> response);

    public boolean isEnabled() {
        return true;
    }
}
