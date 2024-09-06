package com.netease.nimlib.sdk.media.player;

/* loaded from: classes.dex */
public interface OnPlayListener {
    void onCompletion();

    void onError(String str);

    void onInterrupt();

    void onPlaying(long j);

    void onPrepared();
}
