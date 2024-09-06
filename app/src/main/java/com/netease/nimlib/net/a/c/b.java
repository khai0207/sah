package com.netease.nimlib.net.a.c;

import android.text.TextUtils;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.d.g;
import com.netease.nimlib.o.w;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUtils.java */
/* loaded from: classes.dex */
public class b {
    public static String a(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return str;
        }
        try {
            StringBuilder sb = new StringBuilder(str);
            sb.append("?");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append(com.alipay.sdk.m.o.a.l);
            }
            return sb.toString().substring(0, r3.length() - 1);
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static boolean a(HttpURLConnection httpURLConnection) {
        return httpURLConnection != null && (httpURLConnection instanceof HttpsURLConnection);
    }

    public static HttpURLConnection a(String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        return httpURLConnection;
    }

    public static void a(HttpURLConnection httpURLConnection, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        a(httpURLConnection, arrayList);
    }

    public static void a(HttpURLConnection httpURLConnection, final List<String> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new HostnameVerifier() { // from class: com.netease.nimlib.net.a.c.b.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String str, SSLSession sSLSession) {
                            boolean z = false;
                            try {
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    z = HttpsURLConnection.getDefaultHostnameVerifier().verify((String) it.next(), sSLSession);
                                    if (z) {
                                        break;
                                    }
                                }
                                if (z) {
                                    com.netease.nimlib.log.b.c("HttpUtils", "HttpsIpSSLVerifier nos host name verify success");
                                } else {
                                    com.netease.nimlib.log.b.e("HttpUtils", "HttpsIpSSLVerifier nos host name verify fail");
                                }
                            } catch (Throwable unused) {
                            }
                            return z;
                        }
                    });
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.f("HttpUtils", "setHttpsIpSSLVerifier error: " + th.getMessage());
                return;
            }
        }
        com.netease.nimlib.log.b.c("HttpUtils", "setHttpsIpSSLVerifier, url=" + httpURLConnection.getURL() + ", wantedHostnames=" + list);
    }

    public static void a(HttpURLConnection httpURLConnection, String str, int i, int i2, String str2) {
        a(httpURLConnection, str, i, i2, str2, "");
    }

    public static void a(HttpURLConnection httpURLConnection, String str, int i, int i2, String str2, String str3) {
        httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, str);
        if (!TextUtils.isEmpty(str3)) {
            httpURLConnection.setRequestProperty(HttpHeader.HOST, str3);
        }
        httpURLConnection.setReadTimeout(i2);
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setUseCaches(false);
        if ("POST".equals(httpURLConnection.getRequestMethod())) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
    }

    public static void a(HttpURLConnection httpURLConnection, int i) {
        if ("POST".equals(httpURLConnection.getRequestMethod())) {
            if (i <= 0) {
                httpURLConnection.setChunkedStreamingMode(0);
            } else {
                httpURLConnection.setFixedLengthStreamingMode(i);
            }
        }
    }

    public static void a(HttpURLConnection httpURLConnection, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        httpURLConnection.addRequestProperty(str, str2);
    }

    public static void a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            a(httpURLConnection, str, map.get(str));
        }
    }

    public static void a(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        OutputStream dataOutputStream;
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if ("gzip".equals(httpURLConnection.getRequestProperty("Content-Encoding"))) {
            dataOutputStream = new GZIPOutputStream(new BufferedOutputStream(outputStream));
        } else {
            dataOutputStream = new DataOutputStream(outputStream);
        }
        try {
            dataOutputStream.write(bArr);
            dataOutputStream.flush();
        } finally {
            a(dataOutputStream);
        }
    }

    public static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                }
            }
        } finally {
            b(inputStream);
            a(byteArrayOutputStream);
        }
    }

    public static void b(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean a(String str) {
        InetAddress byName;
        try {
            String host = new URL(str).getHost();
            if (TextUtils.isEmpty(host) || (byName = InetAddress.getByName(host)) == null) {
                return false;
            }
            String hostAddress = byName.getHostAddress();
            if (TextUtils.isEmpty(hostAddress)) {
                return false;
            }
            return host.equals(hostAddress);
        } catch (MalformedURLException | UnknownHostException unused) {
            return false;
        }
    }

    public static String b(String str) {
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (allByName != null && allByName.length != 0) {
                String str2 = null;
                for (InetAddress inetAddress : allByName) {
                    if (inetAddress instanceof Inet6Address) {
                        return ((Inet6Address) inetAddress).getHostAddress();
                    }
                    if (w.a((CharSequence) str2)) {
                        str2 = inetAddress.getHostAddress();
                    }
                }
                return str2;
            }
            return null;
        } catch (UnknownHostException unused) {
            com.netease.nimlib.log.b.d("HttpUtils", "failed to resolveDnsToIpv6");
            return null;
        }
    }

    public static List<String> a() {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : g.h().split(";")) {
                arrayList.add(new URL(str).getHost());
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }
}
