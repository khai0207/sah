package com.alipay.sdk.m.m;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b extends com.alipay.sdk.m.l.e {
    @Override // com.alipay.sdk.m.l.e
    public JSONObject a() throws JSONException {
        return com.alipay.sdk.m.l.e.a("sdkConfig", "obtain");
    }

    @Override // com.alipay.sdk.m.l.e
    public String b() {
        return "5.0.0";
    }

    @Override // com.alipay.sdk.m.l.e
    public boolean c() {
        return true;
    }

    @Override // com.alipay.sdk.m.l.e
    public String a(com.alipay.sdk.m.o.a aVar, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        if (hashMap2 == null) {
            hashMap2 = new HashMap<>();
        }
        hashMap2.putAll(com.alipay.sdk.m.q.a.a(aVar));
        com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, "cf " + hashMap2);
        return super.a(aVar, hashMap, hashMap2);
    }
}
