package com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility;

import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import java.io.Serializable;

/* loaded from: classes.dex */
public class TransferUtilityOptions implements Serializable {
    private static final Log LOGGER = LogFactory.getLog((Class<?>) TransferUtilityOptions.class);
    private static final int MILLIS_IN_MINUTE = 60000;
    private static final long serialVersionUID = 1;
    private long minimumUploadPartSizeInBytes;
    protected TransferNetworkConnectionType transferNetworkConnectionType;

    @Deprecated
    private long transferServiceCheckTimeInterval;
    private int transferThreadPoolSize;

    @Deprecated
    static long getDefaultCheckTimeInterval() {
        return KV.GET_CODE_INTERVAL;
    }

    @Deprecated
    public void setTransferServiceCheckTimeInterval(long j) {
    }

    public TransferUtilityOptions() {
        this.transferServiceCheckTimeInterval = getDefaultCheckTimeInterval();
        this.transferThreadPoolSize = getDefaultThreadPoolSize();
        this.transferNetworkConnectionType = getDefaultTransferNetworkConnectionType();
        this.minimumUploadPartSizeInBytes = 5242880L;
    }

    public TransferUtilityOptions(int i, TransferNetworkConnectionType transferNetworkConnectionType) {
        this.transferServiceCheckTimeInterval = getDefaultCheckTimeInterval();
        this.transferThreadPoolSize = i;
        this.transferNetworkConnectionType = transferNetworkConnectionType;
        this.minimumUploadPartSizeInBytes = 5242880L;
    }

    @Deprecated
    public long getTransferServiceCheckTimeInterval() {
        return this.transferServiceCheckTimeInterval;
    }

    public int getTransferThreadPoolSize() {
        return this.transferThreadPoolSize;
    }

    public void setTransferThreadPoolSize(int i) {
        if (i < 0) {
            this.transferThreadPoolSize = getDefaultThreadPoolSize();
        } else {
            this.transferThreadPoolSize = i;
        }
    }

    public TransferNetworkConnectionType getTransferNetworkConnectionType() {
        return this.transferNetworkConnectionType;
    }

    static int getDefaultThreadPoolSize() {
        return (Runtime.getRuntime().availableProcessors() + 1) * 2;
    }

    protected long getMinimumUploadPartSizeInBytes() {
        return this.minimumUploadPartSizeInBytes;
    }

    public int getMinimumUploadPartSizeInMB() {
        return (int) (this.minimumUploadPartSizeInBytes / 1048576);
    }

    public void setMinimumUploadPartSizeInMB(int i) {
        long j = i * 1048576;
        if (j > 5368709120L) {
            LOGGER.warn("The provided minimumUploadPartSize is greater than the maximum upload part size limit. Setting upload part size to the maximum allowed value of5MB.");
            this.minimumUploadPartSizeInBytes = 5368709120L;
        } else if (j < 5242880) {
            LOGGER.warn("The provided minimumUploadPartSize is less than the minimum upload part size limit. Setting upload part size to the minimum allowed value of5MB.");
            this.minimumUploadPartSizeInBytes = 5242880L;
        } else {
            this.minimumUploadPartSizeInBytes = j;
        }
    }

    static TransferNetworkConnectionType getDefaultTransferNetworkConnectionType() {
        return TransferNetworkConnectionType.ANY;
    }
}
