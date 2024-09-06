package com.netease.nimlib.sdk;

import java.io.Serializable;

/* loaded from: classes.dex */
public class StatusCodeInfo implements Serializable {
    private String desc;
    private StatusCode status;

    public StatusCodeInfo(StatusCode statusCode, String str) {
        this.status = statusCode;
        this.desc = str;
    }

    public StatusCode getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.desc;
    }
}
