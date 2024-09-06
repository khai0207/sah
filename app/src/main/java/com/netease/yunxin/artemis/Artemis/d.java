package com.netease.yunxin.artemis.Artemis;

import android.os.Build;
import android.text.TextUtils;
import com.netease.yunxin.artemis.Network.a;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: YXArtemisReporter.java */
/* loaded from: classes.dex */
public final class d {
    static String a;
    private static d c;
    com.netease.yunxin.artemis.Network.a b = new com.netease.yunxin.artemis.Network.a() { // from class: com.netease.yunxin.artemis.Artemis.d.1
        @Override // com.netease.yunxin.artemis.Network.a
        public /* synthetic */ void a(HttpURLConnection httpURLConnection) {
            a.CC.$default$a(this, httpURLConnection);
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public /* synthetic */ byte[] a(InputStream inputStream, long j) {
            return a.CC.$default$a(this, inputStream, j);
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public final void onFailure(int i, Map<String, List<String>> map, byte[] bArr) {
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public final void onFinish() {
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public final void onProgressChanged(long j, long j2) {
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public final void onStart(HttpURLConnection httpURLConnection) {
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public final void onSuccess(int i, URL url, Map<String, List<String>> map, byte[] bArr) {
            d.a = url.toString();
            new String(bArr);
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public final void onException(Throwable th) {
            new StringBuilder("onException").append(th.toString());
        }
    };

    private d() {
    }

    public static d a() {
        if (c == null) {
            c = new d();
        }
        return c;
    }

    public final void a(b bVar, String str, String str2, String str3) {
        String concat;
        com.netease.yunxin.artemis.Network.c cVar = new com.netease.yunxin.artemis.Network.c();
        cVar.a("task_id", bVar.getId());
        cVar.a("task_type", String.valueOf(bVar.getTaskType()));
        cVar.a(com.alipay.sdk.m.h.b.h, com.netease.yunxin.artemis.a.c.a);
        cVar.a("eid", com.netease.yunxin.artemis.a.c.f);
        cVar.a("device_id", com.netease.yunxin.artemis.a.c.e);
        cVar.a("sdk_ver", com.netease.yunxin.artemis.a.c.c);
        cVar.a("sdk_type", com.netease.yunxin.artemis.a.c.d);
        cVar.a("os_version", "Android " + Build.VERSION.SDK_INT);
        cVar.a("platform", "Android");
        cVar.a("network_type", com.netease.yunxin.artemis.a.c.b);
        cVar.a("client_ipv4", bVar.getClient_ipv4());
        cVar.a("client_ipv6", bVar.getClient_ipv6());
        cVar.a("requestId", bVar.getRequestId() == null ? "" : bVar.getRequestId());
        if (str == null) {
            str = "";
        }
        cVar.a("raw_data", str);
        if (str2 == null) {
            str2 = "";
        }
        cVar.a("summary_data", str2);
        cVar.a("report_time", String.valueOf(System.currentTimeMillis()));
        try {
            if (bVar.mCallback != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("task_id", cVar.a("task_id"));
                jSONObject.put("task_type", cVar.a("task_type"));
                jSONObject.put("raw_data", cVar.a("raw_data"));
                jSONObject.put("summary_data", cVar.a("summary_data"));
                bVar.mCallback.onCompleted(jSONObject.toString());
            }
        } catch (Throwable unused) {
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sdktype", "detect");
        hashMap.put("ver", com.netease.yunxin.artemis.a.c.c);
        hashMap.put(com.alipay.sdk.m.o.a.p, com.netease.yunxin.artemis.a.c.a);
        if (!TextUtils.isEmpty(a) && TextUtils.isEmpty(str3)) {
            concat = a;
        } else {
            if (TextUtils.isEmpty(str3)) {
                str3 = new String(com.netease.yunxin.artemis.a.a.a("ZGV0ZWN0LXN0YXRpc3RpYy5saXZlLjEyNi5uZXQ="));
            }
            concat = !str3.startsWith(com.alipay.sdk.m.h.a.q) ? "https://".concat(str3).concat("/statics/report/detect/log") : str3;
        }
        com.netease.yunxin.artemis.Network.b.a().b(concat, cVar, this.b, hashMap);
    }
}
