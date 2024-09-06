package com.netease.nimlib.m;

import android.os.SystemClock;
import org.json.JSONObject;

/* compiled from: OriginTimestamp.java */
/* loaded from: classes.dex */
public class h {
    private final long a;
    private final g b;

    public h(long j, g gVar) {
        this.a = j;
        this.b = gVar;
    }

    public g a() {
        return this.b;
    }

    public long b() {
        return this.b.a() + (this.b.b() / 2) + (SystemClock.elapsedRealtime() - this.a);
    }

    public long a(long j) {
        return this.b.a() + (this.b.b() / 2) + (j - this.a);
    }

    public static h a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            long j = jSONObject.getLong("JSON_KEY_LOCAL_TIMESTAMP");
            g a = g.a(jSONObject.getString("JSON_KEY_NTP_TIMESTAMP"));
            com.netease.nimlib.log.b.f("OriginTimestamp", String.format("fromJson NtpTimestamp null %s", str));
            if (a == null) {
                return null;
            }
            return new h(j, a);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("OriginTimestamp", String.format("fromJson Exception %s", e), e);
            return null;
        }
    }

    public JSONObject c() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("JSON_KEY_LOCAL_TIMESTAMP", this.a);
            jSONObject.put("JSON_KEY_NTP_TIMESTAMP", this.b.c().toString());
            return jSONObject;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("OriginTimestamp", String.format("toJson Exception %s %s", this, e), e);
            return null;
        }
    }

    public String toString() {
        return "OriginTimestamp{localTimestamp=" + this.a + ", ntpTimestamp=" + this.b + '}';
    }
}
