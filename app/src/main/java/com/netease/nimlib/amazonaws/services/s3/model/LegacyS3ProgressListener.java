package com.netease.nimlib.amazonaws.services.s3.model;

/* loaded from: classes.dex */
public class LegacyS3ProgressListener implements com.netease.nimlib.amazonaws.event.ProgressListener {
    private final ProgressListener listener;

    public LegacyS3ProgressListener(ProgressListener progressListener) {
        this.listener = progressListener;
    }

    public ProgressListener unwrap() {
        return this.listener;
    }

    @Override // com.netease.nimlib.amazonaws.event.ProgressListener
    public void progressChanged(com.netease.nimlib.amazonaws.event.ProgressEvent progressEvent) {
        ProgressListener progressListener = this.listener;
        if (progressListener == null) {
            return;
        }
        progressListener.progressChanged(transform(progressEvent));
    }

    private ProgressEvent transform(com.netease.nimlib.amazonaws.event.ProgressEvent progressEvent) {
        return new ProgressEvent(progressEvent.getEventCode(), progressEvent.getBytesTransferred());
    }
}
