package com.netease.nimlib.amazonaws.metrics;

import com.netease.nimlib.amazonaws.internal.SdkFilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class MetricFilterInputStream extends SdkFilterInputStream {
    private final ByteThroughputHelper helper;

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, com.netease.nimlib.amazonaws.internal.MetricAware
    public final boolean isMetricActivated() {
        return true;
    }

    public MetricFilterInputStream(ThroughputMetricType throughputMetricType, InputStream inputStream) {
        super(inputStream);
        this.helper = new ByteThroughputHelper(throughputMetricType);
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        abortIfNeeded();
        long startTiming = this.helper.startTiming();
        int read = this.in.read(bArr, i, i2);
        if (read > 0) {
            this.helper.increment(read, startTiming);
        }
        return read;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.helper.reportMetrics();
        this.in.close();
        abortIfNeeded();
    }
}
