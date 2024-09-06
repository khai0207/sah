package com.netease.yunxin.artemis.Network;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: RequestParams.java */
/* loaded from: classes.dex */
public final class c {
    public static final Charset a = StandardCharsets.UTF_8;
    HashMap<String, String> b;
    byte[] c;
    private Charset d;

    public c() {
        this.b = new HashMap<>();
        this.c = null;
        this.d = a;
    }

    public c(HashMap<String, String> hashMap) {
        this();
        this.b = hashMap;
    }

    public c(byte[] bArr) {
        this();
        this.c = bArr;
    }

    public final String a() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.b.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(com.alipay.sdk.m.o.a.l);
                }
                sb.append(URLEncoder.encode(entry.getKey(), this.d.name()));
                sb.append("=");
                sb.append(URLEncoder.encode(entry.getValue(), this.d.name()));
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public final String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, String> entry : this.b.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public final void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public final String a(String str) {
        return this.b.get(str);
    }
}
