package com.netease.nimlib.sdk.generic.param;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class GenericTypeAPICallParam {
    private Object data;
    private final String name;
    private final String param;
    private long sn;

    public GenericTypeAPICallParam(String str, String str2) {
        this.sn = 0L;
        this.data = null;
        this.name = str;
        this.param = str2;
    }

    public GenericTypeAPICallParam(long j, Object obj, String str, String str2) {
        this.sn = 0L;
        this.data = null;
        this.sn = j;
        this.data = obj;
        this.name = str;
        this.param = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getParam() {
        return this.param;
    }

    public long getSn() {
        return this.sn;
    }

    public void setSn(long j) {
        this.sn = j;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.name) || TextUtils.isEmpty(this.param)) ? false : true;
    }
}
