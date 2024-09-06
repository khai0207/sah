package com.netease.nimlib.network.a;

/* compiled from: NetworkStatus.java */
/* loaded from: classes.dex */
public enum a {
    NONE(-1, "无网络连接"),
    UNKNOWN(0, "未知"),
    MOBILE(1, "移动网络连接"),
    WIFI(2, "WIFI连接");

    private int e;
    private String f;

    a(int i, String str) {
        this.e = i;
        this.f = str;
    }

    public int a() {
        return this.e;
    }
}
