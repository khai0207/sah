package com.netease.yunxin.artemis.Network;

/* loaded from: classes.dex */
public enum HttpRequestMethod {
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    POST("POST"),
    PUT("PUT"),
    TRACE("TRACE");

    String key;

    HttpRequestMethod(String str) {
        this.key = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.key;
    }
}
