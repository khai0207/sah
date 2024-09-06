package com.netease.nimlib.amazonaws.services.s3.metrics;

import com.netease.nimlib.amazonaws.metrics.ServiceMetricType;
import com.netease.nimlib.amazonaws.metrics.SimpleMetricType;
import com.netease.nimlib.amazonaws.metrics.ThroughputMetricType;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* loaded from: classes.dex */
public class S3ServiceMetric extends SimpleMetricType implements ServiceMetricType {
    public static final S3ServiceMetric S3_UPLOAD_BYTE_COUNT;
    static final String SERVICE_NAME_PREFIX = "S3";
    private static final S3ServiceMetric[] VALUES;
    private final String name;
    public static final S3ThroughputMetric S3_DOWLOAD_THROUGHPUT = new S3ThroughputMetric(metricName(ServiceMetricType.DOWNLOAD_THROUGHPUT_NAME_SUFFIX)) { // from class: com.netease.nimlib.amazonaws.services.s3.metrics.S3ServiceMetric.1
        @Override // com.netease.nimlib.amazonaws.metrics.ThroughputMetricType
        public ServiceMetricType getByteCountMetricType() {
            return S3_DOWNLOAD_BYTE_COUNT;
        }
    };
    public static final S3ServiceMetric S3_DOWNLOAD_BYTE_COUNT = new S3ServiceMetric(metricName(ServiceMetricType.DOWNLOAD_BYTE_COUNT_NAME_SUFFIX));
    public static final S3ThroughputMetric S3_UPLOAD_THROUGHPUT = new S3ThroughputMetric(metricName(ServiceMetricType.UPLOAD_THROUGHPUT_NAME_SUFFIX)) { // from class: com.netease.nimlib.amazonaws.services.s3.metrics.S3ServiceMetric.2
        @Override // com.netease.nimlib.amazonaws.metrics.ThroughputMetricType
        public ServiceMetricType getByteCountMetricType() {
            return S3_UPLOAD_BYTE_COUNT;
        }
    };

    @Override // com.netease.nimlib.amazonaws.metrics.ServiceMetricType
    public String getServiceName() {
        return Constants.S3_SERVICE_DISPLAY_NAME;
    }

    private static final String metricName(String str) {
        return SERVICE_NAME_PREFIX + str;
    }

    static {
        S3ServiceMetric s3ServiceMetric = new S3ServiceMetric(metricName(ServiceMetricType.UPLOAD_BYTE_COUNT_NAME_SUFFIX));
        S3_UPLOAD_BYTE_COUNT = s3ServiceMetric;
        VALUES = new S3ServiceMetric[]{S3_DOWLOAD_THROUGHPUT, S3_DOWNLOAD_BYTE_COUNT, S3_UPLOAD_THROUGHPUT, s3ServiceMetric};
    }

    private S3ServiceMetric(String str) {
        this.name = str;
    }

    @Override // com.netease.nimlib.amazonaws.metrics.SimpleMetricType, com.netease.nimlib.amazonaws.metrics.MetricType
    public String name() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class S3ThroughputMetric extends S3ServiceMetric implements ThroughputMetricType {
        private S3ThroughputMetric(String str) {
            super(str);
        }
    }

    public static S3ServiceMetric[] values() {
        return (S3ServiceMetric[]) VALUES.clone();
    }

    public static S3ServiceMetric valueOf(String str) {
        for (S3ServiceMetric s3ServiceMetric : values()) {
            if (s3ServiceMetric.name().equals(str)) {
                return s3ServiceMetric;
            }
        }
        throw new IllegalArgumentException("No S3ServiceMetric defined for the name " + str);
    }
}
