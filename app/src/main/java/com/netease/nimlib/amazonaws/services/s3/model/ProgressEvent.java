package com.netease.nimlib.amazonaws.services.s3.model;

@Deprecated
/* loaded from: classes.dex */
public class ProgressEvent extends com.netease.nimlib.amazonaws.event.ProgressEvent {
    public ProgressEvent(int i) {
        super(i);
    }

    public ProgressEvent(int i, long j) {
        super(i, j);
    }

    @Deprecated
    public void setBytesTransfered(int i) {
        setBytesTransferred(i);
    }

    @Deprecated
    public int getBytesTransfered() {
        return (int) getBytesTransferred();
    }
}
