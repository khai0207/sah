package com.netease.nimlib.amazonaws.metrics;

/* loaded from: classes.dex */
public abstract class ServiceMetricCollector {
    public static final ServiceMetricCollector NONE = new ServiceMetricCollector() { // from class: com.netease.nimlib.amazonaws.metrics.ServiceMetricCollector.1
        @Override // com.netease.nimlib.amazonaws.metrics.ServiceMetricCollector
        public void collectByteThroughput(ByteThroughputProvider byteThroughputProvider) {
        }

        @Override // com.netease.nimlib.amazonaws.metrics.ServiceMetricCollector
        public void collectLatency(ServiceLatencyProvider serviceLatencyProvider) {
        }

        @Override // com.netease.nimlib.amazonaws.metrics.ServiceMetricCollector
        public boolean isEnabled() {
            return false;
        }
    };

    /* loaded from: classes.dex */
    public interface Factory {
        ServiceMetricCollector getServiceMetricCollector();
    }

    public abstract void collectByteThroughput(ByteThroughputProvider byteThroughputProvider);

    public abstract void collectLatency(ServiceLatencyProvider serviceLatencyProvider);

    public boolean isEnabled() {
        return true;
    }
}
