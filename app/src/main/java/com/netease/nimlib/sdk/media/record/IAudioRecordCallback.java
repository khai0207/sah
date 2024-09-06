package com.netease.nimlib.sdk.media.record;

import java.io.File;

/* loaded from: classes.dex */
public interface IAudioRecordCallback {
    void onRecordCancel();

    void onRecordFail();

    void onRecordReachedMaxTime(int i);

    void onRecordReady();

    void onRecordStart(File file, RecordType recordType);

    void onRecordSuccess(File file, long j, RecordType recordType);
}
