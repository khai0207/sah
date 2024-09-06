package com.netease.nimlib.amazonaws.services.s3;

import com.netease.nimlib.amazonaws.services.s3.internal.FileDeletionEvent;

/* loaded from: classes.dex */
public interface OnFileDelete {
    void onFileDelete(FileDeletionEvent fileDeletionEvent);
}
