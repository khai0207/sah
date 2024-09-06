package com.netease.nimlib.m;

import java.io.Serializable;
import org.json.JSONObject;

/* compiled from: NtpTimestamp.java */
/* loaded from: classes.dex */
public class g implements Serializable {
    private final long a;
    private final long b;

    public g(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public long a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public static g a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new g(jSONObject.getLong("JSON_KEY_TIME"), jSONObject.getLong("JSON_KEY_RTT"));
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NtpTimestamp", String.format("fromJson Exception %s", e), e);
            return null;
        }
    }

    public JSONObject c() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("JSON_KEY_TIME", this.a);
            jSONObject.put("JSON_KEY_RTT", this.b);
            return jSONObject;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NtpTimestamp", String.format("toJson Exception %s %s", this, e), e);
            return null;
        }
    }

    public String toString() {
        return "NtpTimestamp{time=" + this.a + ", rtt=" + this.b + '}';
    }
}
