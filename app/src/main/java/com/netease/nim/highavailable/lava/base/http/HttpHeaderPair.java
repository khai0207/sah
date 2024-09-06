package com.netease.nim.highavailable.lava.base.http;

/* loaded from: classes.dex */
public class HttpHeaderPair {
    public String key;
    public String value;

    public static HttpHeaderPair obtain() {
        return new HttpHeaderPair();
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
