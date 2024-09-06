package com.netease.nimlib.sdk.passthrough.model;

import com.netease.nimlib.push.packet.b.c;
import java.io.Serializable;

/* loaded from: classes.dex */
public class PassthroughNotifyData implements Serializable {
    private String body;
    private String fromAccid;
    private long time;

    public PassthroughNotifyData(String str, String str2, long j) {
        this.fromAccid = str;
        this.body = str2;
        this.time = j;
    }

    public String getFromAccid() {
        return this.fromAccid;
    }

    public String getBody() {
        return this.body;
    }

    public long getTime() {
        return this.time;
    }

    public static PassthroughNotifyData fromProperty(c cVar) {
        if (cVar == null) {
            return null;
        }
        return new PassthroughNotifyData(cVar.c(1), cVar.c(2), cVar.e(3));
    }
}
