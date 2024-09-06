package com.netease.nimlib.net.a.d;

import android.text.TextUtils;
import com.netease.nimlib.c;
import com.netease.nimlib.n.b.g;
import com.netease.nimlib.n.b.j;
import com.netease.nimlib.n.e;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.misc.model.LogDesensitizationConfig;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: HttpClientWrapper.java */
/* loaded from: classes.dex */
public class a {

    /* compiled from: HttpClientWrapper.java */
    /* renamed from: com.netease.nimlib.net.a.d.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0047a<T> {
        public int a = 0;
        public Throwable b = null;
        public T c = null;
        public String d = "";
    }

    public static C0047a<String> a(String str, Map<String, String> map, Object obj) {
        return a(str, map, (Integer) null, obj);
    }

    /* JADX WARN: Type inference failed for: r14v12, types: [T, java.lang.String] */
    public static C0047a<String> a(String str, Map<String, String> map, Integer num, Object obj) {
        String str2;
        InputStream inputStream;
        LogDesensitizationConfig logDesensitizationConfig = c.i().logDesensitizationConfig;
        com.netease.nimlib.log.b.c("nim_http", "http get url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
        C0047a<String> c0047a = new C0047a<>();
        String a = a(str);
        e.a(a, a(obj));
        HttpURLConnection httpURLConnection = null;
        r15 = null;
        r15 = null;
        String str3 = null;
        try {
            HttpURLConnection a2 = a(str, map, num);
            try {
                try {
                    Map<String, List<String>> requestProperties = a2.getRequestProperties();
                    if (requestProperties != null) {
                        Set<String> keySet = requestProperties.keySet();
                        JSONObject jSONObject = new JSONObject();
                        for (String str4 : keySet) {
                            String requestProperty = a2.getRequestProperty(str4);
                            if (str4 == null) {
                                str4 = "";
                            }
                            jSONObject.put(str4, requestProperty);
                        }
                        str3 = jSONObject.toString();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                int responseCode = a2.getResponseCode();
                c0047a.a = responseCode;
                if (responseCode == 200) {
                    if (TextUtils.equals("gzip", a2.getContentEncoding())) {
                        inputStream = new GZIPInputStream(a2.getInputStream());
                    } else {
                        inputStream = a2.getInputStream();
                    }
                    c0047a.c = com.netease.nimlib.net.a.c.b.a(inputStream);
                    e.a(a);
                    com.netease.nimlib.log.b.c("nim_http", "http get success, url=" + str);
                } else {
                    e.a(a, j.kGet, str, responseCode, str3, null, "HttpClientWrapper#get failed");
                    com.netease.nimlib.log.b.f("nim_http", "http get failed, code=" + responseCode + ", url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
                }
                if (a2 != null) {
                    a2.disconnect();
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = str3;
                httpURLConnection = a2;
                try {
                    c0047a.b = th;
                    com.netease.nimlib.log.b.f("nim_http", "http get error, e=" + th.getMessage() + ", url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
                    j jVar = j.kGet;
                    StringBuilder sb = new StringBuilder();
                    sb.append("HttpClientWrapper#get exception = ");
                    sb.append(th);
                    e.a(a, jVar, str, 0, str2, null, sb.toString());
                    return c0047a;
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            str2 = null;
        }
        return c0047a;
    }

    private static String a(String str) {
        return (str == null || !str.startsWith("https://statistic.live.126.net/")) ? w.b() : "";
    }

    public static <T> C0047a<String> b(String str, Map<String, String> map, T t) {
        return a(str, map, t, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> C0047a<String> a(String str, Map<String, String> map, T t, Object obj) {
        String str2;
        InputStream inputStream;
        LogDesensitizationConfig logDesensitizationConfig = c.i().logDesensitizationConfig;
        com.netease.nimlib.log.b.c("nim_http", "http post url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
        C0047a<String> c0047a = new C0047a<>();
        String a = a(str);
        e.a(a, a(obj));
        HttpURLConnection httpURLConnection = null;
        r15 = null;
        r15 = null;
        String str3 = null;
        try {
            HttpURLConnection c = c(str, map, t);
            if (map != null) {
                try {
                    try {
                        str3 = new JSONObject(map).toString();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str2 = str3;
                    httpURLConnection = c;
                    try {
                        c0047a.b = th;
                        com.netease.nimlib.log.b.f("nim_http", "http post error, e=" + th.getMessage() + ", url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
                        j jVar = j.kPost;
                        StringBuilder sb = new StringBuilder();
                        sb.append("HttpClientWrapper#post exception = ");
                        sb.append(th);
                        e.a(a, jVar, str, 0, str2, null, sb.toString());
                        return c0047a;
                    } finally {
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                }
            }
            int responseCode = c.getResponseCode();
            c0047a.a = responseCode;
            c0047a.d = c.getHeaderField("Date");
            if (responseCode == 200) {
                if (TextUtils.equals("gzip", c.getContentEncoding())) {
                    inputStream = new GZIPInputStream(c.getInputStream());
                } else {
                    inputStream = c.getInputStream();
                }
                c0047a.c = (T) com.netease.nimlib.net.a.c.b.a(inputStream);
                com.netease.nimlib.log.b.c("nim_http", "http post success, url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
                e.a(a);
            } else {
                com.netease.nimlib.log.b.f("nim_http", "http post failed, code=" + responseCode + ", url=" + com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig));
                e.a(a, j.kPost, str, responseCode, str3, null, "HttpClientWrapper#post failed");
            }
            if (c != null) {
                c.disconnect();
            }
        } catch (Throwable th3) {
            th = th3;
            str2 = null;
        }
        return c0047a;
    }

    private static g a(Object obj) {
        return obj instanceof g ? (g) obj : g.UNKNOWN;
    }

    private static HttpURLConnection a(String str, Map<String, String> map, Integer num) throws IOException {
        HttpURLConnection a = com.netease.nimlib.net.a.c.b.a(str, "GET");
        a(a, num);
        a(a, map);
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> HttpURLConnection c(String str, Map<String, String> map, T t) throws IOException {
        HttpURLConnection a = com.netease.nimlib.net.a.c.b.a(str, "POST");
        byte[] bArr = null;
        a(a, (Integer) null);
        a(a, map);
        a(a, t);
        if (t instanceof String) {
            bArr = ((String) t).getBytes("UTF-8");
        } else if (t instanceof byte[]) {
            bArr = (byte[]) t;
        } else if ((t instanceof JSONObject) || (t instanceof JSONArray)) {
            bArr = t.toString().getBytes("UTF-8");
        }
        com.netease.nimlib.net.a.c.b.a(a, bArr);
        return a;
    }

    private static void a(HttpURLConnection httpURLConnection, Integer num) {
        int intValue = num == null ? 30000 : num.intValue();
        com.netease.nimlib.net.a.c.b.a(httpURLConnection, "NIM-Android-SDK-V9.17.0", intValue, intValue, null);
    }

    private static void a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        httpURLConnection.setRequestProperty("charset", "UTF-8");
        com.netease.nimlib.net.a.c.b.a(httpURLConnection, map);
    }

    private static <T> void a(HttpURLConnection httpURLConnection, T t) {
        if ((t instanceof JSONObject) || (t instanceof JSONArray)) {
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
        }
    }
}
