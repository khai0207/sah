package com.netease.nimlib.amazonaws.internal;

import com.netease.nimlib.amazonaws.AbortedException;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public abstract class SdkInputStream extends InputStream implements MetricAware {
    protected void abort() throws IOException {
    }

    protected abstract InputStream getWrappedInputStream();

    @Override // com.netease.nimlib.amazonaws.internal.MetricAware
    @Deprecated
    public final boolean isMetricActivated() {
        Closeable wrappedInputStream = getWrappedInputStream();
        if (wrappedInputStream instanceof MetricAware) {
            return ((MetricAware) wrappedInputStream).isMetricActivated();
        }
        return false;
    }

    protected final void abortIfNeeded() {
        if (Thread.interrupted()) {
            try {
                abort();
            } catch (IOException e) {
                LogFactory.getLog(getClass()).debug("FYI", e);
            }
            throw new AbortedException();
        }
    }
}
