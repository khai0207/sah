package com.netease.nim.highavailable.lava.base.http;

/* loaded from: classes.dex */
public class HttpStackResponse {
    public int code;
    public String headers;
    public long lastModified;
    public String result;

    public String toString() {
        return "code:" + this.code + ", headers:" + this.headers + ", res:" + this.result;
    }

    public int getCode() {
        return this.code;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public String getResult() {
        return this.result;
    }

    public String getHeaderFields() {
        return this.headers;
    }
}
