package com.alipay.sdk.m.m;

import android.content.Context;
import com.netease.nimlib.amazonaws.services.s3.util.Mimetypes;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d extends com.alipay.sdk.m.l.e {
    public static final String t = "log_v";

    @Override // com.alipay.sdk.m.l.e
    public String a(com.alipay.sdk.m.o.a aVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.m.l.e
    public Map<String, String> a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(com.alipay.sdk.m.l.e.c, String.valueOf(z));
        hashMap.put(com.alipay.sdk.m.l.e.f, Mimetypes.MIMETYPE_OCTET_STREAM);
        hashMap.put(com.alipay.sdk.m.l.e.i, "CBC");
        return hashMap;
    }

    @Override // com.alipay.sdk.m.l.e
    public JSONObject a() throws JSONException {
        return null;
    }

    @Override // com.alipay.sdk.m.l.e
    public boolean c() {
        return false;
    }

    @Override // com.alipay.sdk.m.l.e
    public String a(com.alipay.sdk.m.o.a aVar) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(com.alipay.sdk.m.l.e.k, "/sdk/log");
        hashMap.put(com.alipay.sdk.m.l.e.l, "1.0.0");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put(t, "1.0");
        return a(aVar, hashMap, hashMap2);
    }

    @Override // com.alipay.sdk.m.l.e
    public com.alipay.sdk.m.l.b a(com.alipay.sdk.m.o.a aVar, Context context, String str) throws Throwable {
        return a(aVar, context, str, com.alipay.sdk.m.h.a.c, true);
    }
}
