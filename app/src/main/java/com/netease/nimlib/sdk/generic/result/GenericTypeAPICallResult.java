package com.netease.nimlib.sdk.generic.result;

import java.io.Serializable;

/* loaded from: classes.dex */
public class GenericTypeAPICallResult implements Serializable {
    private Object data;
    private final String name;
    private final String result;
    private long sn;

    public GenericTypeAPICallResult(String str, String str2) {
        this.sn = 0L;
        this.data = null;
        this.name = str;
        this.result = str2;
    }

    public GenericTypeAPICallResult(long j, Object obj, String str, String str2) {
        this.sn = 0L;
        this.data = null;
        this.sn = j;
        this.data = obj;
        this.name = str;
        this.result = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getResult() {
        return this.result;
    }

    public long getSn() {
        return this.sn;
    }

    public Object getData() {
        return this.data;
    }
}
