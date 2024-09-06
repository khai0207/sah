package com.netease.nimlib.net.a.a;

/* compiled from: HttpDownloadListener.java */
/* loaded from: classes.dex */
public interface f {
    void onCancel(e eVar);

    void onExpire(e eVar, String str);

    void onFail(e eVar, String str);

    void onGetLength(e eVar, long j);

    void onOK(e eVar);

    void onProgress(e eVar, long j);

    void onStart(e eVar);
}
