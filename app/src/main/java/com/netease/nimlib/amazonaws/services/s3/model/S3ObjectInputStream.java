package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.internal.MetricAware;
import com.netease.nimlib.amazonaws.internal.SdkFilterInputStream;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.metrics.AwsSdkMetrics;
import com.netease.nimlib.amazonaws.metrics.MetricFilterInputStream;
import com.netease.nimlib.amazonaws.services.s3.metrics.S3ServiceMetric;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class S3ObjectInputStream extends SdkFilterInputStream {
    private boolean eof;

    public S3ObjectInputStream(InputStream inputStream) {
        super(wrapWithByteCounting(inputStream) ? new MetricFilterInputStream(S3ServiceMetric.S3_DOWLOAD_THROUGHPUT, inputStream) : inputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean wrapWithByteCounting(InputStream inputStream) {
        if (!AwsSdkMetrics.isMetricsEnabled()) {
            return false;
        }
        if (inputStream instanceof MetricAware) {
            return !((MetricAware) inputStream).isMetricActivated();
        }
        return true;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream
    public void abort() {
        doAbort();
    }

    private void doAbort() {
        try {
            close();
        } catch (IOException e) {
            LogFactory.getLog(getClass()).debug("FYI", e);
        }
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int available = super.available();
        if (available == 0) {
            return 1;
        }
        return available;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read == -1) {
            this.eof = true;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read == -1) {
            this.eof = true;
        }
        return read;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        this.eof = false;
    }
}
