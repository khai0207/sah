package com.alipay.sdk.m.m;

import android.content.Context;
import com.alipay.sdk.m.k.a;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e extends com.alipay.sdk.m.l.e {
    @Override // com.alipay.sdk.m.l.e
    public String a(com.alipay.sdk.m.o.a aVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.m.l.e
    public Map<String, String> a(boolean z, String str) {
        return new HashMap();
    }

    @Override // com.alipay.sdk.m.l.e
    public JSONObject a() {
        return null;
    }

    @Override // com.alipay.sdk.m.l.e
    public boolean c() {
        return false;
    }

    @Override // com.alipay.sdk.m.l.e
    public com.alipay.sdk.m.l.b a(com.alipay.sdk.m.o.a aVar, Context context, String str) throws Throwable {
        com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, "mdap post");
        byte[] a = com.alipay.sdk.m.j.b.a(str.getBytes(Charset.forName("UTF-8")));
        HashMap hashMap = new HashMap();
        hashMap.put("utdId", com.alipay.sdk.m.o.b.d().c());
        hashMap.put("logHeader", "RAW");
        hashMap.put("bizCode", com.alipay.sdk.m.q.d.b);
        hashMap.put("productId", "alipaysdk_android");
        hashMap.put("Content-Encoding", "Gzip");
        hashMap.put("productVersion", "15.8.06");
        a.b a2 = com.alipay.sdk.m.k.a.a(context, new a.C0009a(com.alipay.sdk.m.h.a.d, hashMap, a));
        com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, "mdap got " + a2);
        if (a2 != null) {
            boolean a3 = com.alipay.sdk.m.l.e.a(a2);
            try {
                byte[] bArr = a2.c;
                if (a3) {
                    bArr = com.alipay.sdk.m.j.b.b(bArr);
                }
                return new com.alipay.sdk.m.l.b("", new String(bArr, Charset.forName("UTF-8")));
            } catch (Exception e) {
                com.alipay.sdk.m.q.d.a(e);
                return null;
            }
        }
        throw new RuntimeException("Response is null");
    }
}
