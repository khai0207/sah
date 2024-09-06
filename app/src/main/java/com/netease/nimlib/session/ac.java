package com.netease.nimlib.session;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TimeConsumingStatisticsImpl.java */
/* loaded from: classes.dex */
public class ac implements Serializable {
    private long a;
    private long b;
    private long c;
    private long d;

    public static ac a(String str) {
        JSONObject optJSONObject;
        ac acVar = new ac();
        try {
            optJSONObject = new JSONObject(str).optJSONObject("statistics");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.f("TimeConsumingStatisticsImpl", String.format("failed to init from clientExt %s. %s", str, th));
        }
        if (optJSONObject == null) {
            return acVar;
        }
        if (optJSONObject.has("apiCallingTime")) {
            acVar.a(optJSONObject.optLong("apiCallingTime"));
        }
        if (optJSONObject.has("sendTime")) {
            acVar.b(optJSONObject.optLong("sendTime"));
        }
        if (optJSONObject.has("attachUploadDuration")) {
            acVar.c(optJSONObject.optLong("attachUploadDuration"));
        }
        return acVar;
    }

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long c() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (this.a > 0) {
                jSONObject2.put("apiCallingTime", this.a);
            }
            if (this.b > 0) {
                jSONObject2.put("sendTime", this.b);
            }
            if (this.d > 0) {
                jSONObject2.put("attachUploadDuration", this.d);
            }
            jSONObject.put("statistics", jSONObject2);
        } catch (JSONException e) {
            com.netease.nimlib.log.b.f("TimeConsumingStatisticsImpl", "failed to parse to json " + e);
        }
        return jSONObject;
    }

    public String toString() {
        return "TimeConsumingStatisticsImpl{sendTagApiCallingTime=" + this.a + ", sendTagSendTime=" + this.b + ", sendTagAttachUploadStartTime=" + this.c + ", sendTagAttachUploadDuration=" + this.d + '}';
    }
}
