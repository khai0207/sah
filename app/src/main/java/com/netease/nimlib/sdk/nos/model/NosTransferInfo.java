package com.netease.nimlib.sdk.nos.model;

import com.netease.nimlib.sdk.nos.constant.NosTransferStatus;
import java.io.Serializable;

/* loaded from: classes.dex */
public class NosTransferInfo implements Serializable {
    protected String extension;
    protected String md5;
    protected String path;
    protected long size;
    protected NosTransferStatus status = NosTransferStatus.def;
    protected TransferType transferType;
    protected String url;

    /* loaded from: classes.dex */
    public enum TransferType {
        UPLOAD,
        DOWNLOAD
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public NosTransferStatus getStatus() {
        return this.status;
    }

    public void setStatus(NosTransferStatus nosTransferStatus) {
        this.status = nosTransferStatus;
    }

    public TransferType getTransferType() {
        return this.transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public String getKey() {
        if (this.transferType == TransferType.UPLOAD) {
            return getPath();
        }
        return getUrl();
    }
}
