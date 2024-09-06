package com.netease.yunxin.artemis.Network;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: NetworkManager.java */
/* loaded from: classes.dex */
public final class b {
    private static b f;
    public Context a;
    private int c = 20000;
    private int d = 20000;
    private boolean e = true;
    private final Map<String, String> b = new LinkedHashMap();

    private b() {
    }

    public static b a() {
        if (f == null) {
            f = new b();
        }
        return f;
    }

    private void a(String str, HttpRequestMethod httpRequestMethod, c cVar, a aVar, HashMap<String, String> hashMap) {
        HttpURLConnection httpURLConnection;
        byte[] bytes;
        if (cVar == null) {
            cVar = new c();
        }
        if (httpRequestMethod != HttpRequestMethod.POST && httpRequestMethod != HttpRequestMethod.PUT && cVar.b.size() > 0) {
            str = str + "?" + cVar.a();
        }
        HttpURLConnection httpURLConnection2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (Throwable th) {
            th = th;
        }
        try {
            httpURLConnection.setConnectTimeout(this.c);
            httpURLConnection.setReadTimeout(this.d);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(this.e);
            httpURLConnection.setRequestMethod(httpRequestMethod.toString());
            httpURLConnection.setDoInput(true);
            for (Map.Entry<String, String> entry : this.b.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            aVar.onStart(httpURLConnection);
            if (hashMap != null) {
                for (Map.Entry<String, String> entry2 : hashMap.entrySet()) {
                    httpURLConnection.setRequestProperty(entry2.getKey(), entry2.getValue());
                }
            }
            if (httpRequestMethod == HttpRequestMethod.POST) {
                httpURLConnection.setDoOutput(true);
                if (cVar.c == null) {
                    bytes = cVar.b().getBytes();
                } else {
                    bytes = cVar.c;
                }
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(bytes);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } finally {
                }
            }
            aVar.a(httpURLConnection);
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection2 = httpURLConnection;
            try {
                aVar.onException(th);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                    aVar.onFinish();
                }
                return;
            } catch (Throwable th3) {
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                    aVar.onFinish();
                }
                throw th3;
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            aVar.onFinish();
        }
    }

    public final void a(String str, c cVar, a aVar, HashMap<String, String> hashMap) {
        a(str, HttpRequestMethod.GET, cVar, aVar, hashMap);
    }

    public final void b(String str, c cVar, a aVar, HashMap<String, String> hashMap) {
        a(str, HttpRequestMethod.POST, cVar, aVar, hashMap);
    }
}
