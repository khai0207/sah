package com.talkingdata.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* compiled from: td */
/* loaded from: classes.dex */
public class aq {
    public static int a = 60000;
    public static int b = 60000;
    private static final int d = 600;
    private static final int e = 60000;
    private static final int f = 60000;
    public static volatile HashMap c = new HashMap();
    private static Context g = null;
    private static volatile HashMap h = new HashMap();

    private static void a(HttpsURLConnection httpsURLConnection) {
    }

    public static d a(Context context, String str, String str2, String str3, String str4, byte[] bArr) {
        g = context;
        b(str, str2);
        return a(str, str2, str3, str4, bArr);
    }

    public static String a(String str, boolean z) {
        return b(str, null, z);
    }

    public static String a(String str, String str2, boolean z) {
        return b(str, str2, z);
    }

    public static SSLSocketFactory a(boolean z, X509Certificate x509Certificate) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            if (z && x509Certificate != null) {
                sSLContext.init(null, new TrustManager[]{new c(x509Certificate)}, null);
            } else {
                sSLContext.init(null, null, null);
            }
            return sSLContext.getSocketFactory();
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008b, code lost:
    
        if (r3 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008d, code lost:
    
        r3.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ad, code lost:
    
        if (r3 == null) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String b(java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            boolean r0 = com.talkingdata.sdk.bh.b(r6)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> La1
            r2.<init>(r6)     // Catch: java.lang.Throwable -> La1
            r3 = 0
            java.net.URLConnection r3 = a(r2, r1, r3)     // Catch: java.lang.Throwable -> La1
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch: java.lang.Throwable -> La1
            java.lang.String r6 = r6.toLowerCase()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r4 = "https"
            boolean r6 = r6.startsWith(r4)     // Catch: java.lang.Throwable -> L9f
            if (r6 == 0) goto L44
            boolean r6 = com.talkingdata.sdk.bh.b(r7)     // Catch: java.lang.Throwable -> L9f
            if (r6 != 0) goto L44
            java.util.HashMap r6 = com.talkingdata.sdk.aq.c     // Catch: java.lang.Throwable -> L9f
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L9f
            long r4 = r4.getId()     // Catch: java.lang.Throwable -> L9f
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r2 = r2.getHost()     // Catch: java.lang.Throwable -> L9f
            r6.put(r4, r2)     // Catch: java.lang.Throwable -> L9f
            javax.net.ssl.HttpsURLConnection r3 = a(r3, r7)     // Catch: java.lang.Throwable -> L9f
        L44:
            java.lang.String r6 = "GET"
            r3.setRequestMethod(r6)     // Catch: java.lang.Throwable -> L9f
            int r6 = r3.getResponseCode()     // Catch: java.lang.Throwable -> L9f
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L95
            if (r8 == 0) goto L66
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Throwable -> L9f
            java.io.InputStream r7 = r3.getInputStream()     // Catch: java.lang.Throwable -> L9f
            byte[] r7 = a(r7)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r8 = "utf-8"
            r6.<init>(r7, r8)     // Catch: java.lang.Throwable -> L9f
            r0.append(r6)     // Catch: java.lang.Throwable -> L9f
            goto L84
        L66:
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9f
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L9f
            java.io.InputStream r8 = r3.getInputStream()     // Catch: java.lang.Throwable -> L9f
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L9f
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L9f
        L74:
            java.lang.String r7 = r6.readLine()     // Catch: java.lang.Throwable -> L91
            if (r7 == 0) goto L83
            java.lang.String r8 = "/n"
            r0.append(r8)     // Catch: java.lang.Throwable -> L91
            r0.append(r7)     // Catch: java.lang.Throwable -> L91
            goto L74
        L83:
            r1 = r6
        L84:
            if (r1 == 0) goto L8b
            r1.close()     // Catch: java.lang.Throwable -> L8a
            goto L8b
        L8a:
        L8b:
            if (r3 == 0) goto Lb0
        L8d:
            r3.disconnect()     // Catch: java.lang.Throwable -> Lb0
            goto Lb0
        L91:
            r7 = move-exception
            r1 = r6
            r6 = r7
            goto La3
        L95:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L9f
            if (r3 == 0) goto L9e
            r3.disconnect()     // Catch: java.lang.Throwable -> L9e
        L9e:
            return r6
        L9f:
            r6 = move-exception
            goto La3
        La1:
            r6 = move-exception
            r3 = r1
        La3:
            com.talkingdata.sdk.ce.postSDKError(r6)     // Catch: java.lang.Throwable -> Lb5
            if (r1 == 0) goto Lad
            r1.close()     // Catch: java.lang.Throwable -> Lac
            goto Lad
        Lac:
        Lad:
            if (r3 == 0) goto Lb0
            goto L8d
        Lb0:
            java.lang.String r6 = r0.toString()
            return r6
        Lb5:
            r6 = move-exception
            if (r1 == 0) goto Lbd
            r1.close()     // Catch: java.lang.Throwable -> Lbc
            goto Lbd
        Lbc:
        Lbd:
            if (r3 == 0) goto Lc2
            r3.disconnect()     // Catch: java.lang.Throwable -> Lc2
        Lc2:
            goto Lc4
        Lc3:
            throw r6
        Lc4:
            goto Lc3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.aq.b(java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    private static byte[] a(InputStream inputStream) {
        byte[] bArr = null;
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
            byte[] bArr2 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = gZIPInputStream.read(bArr2, 0, 1024);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    return bArr;
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
            return bArr;
        }
    }

    private static URL a(String str, String str2) {
        URL url = new URL(str);
        return az.a() ? url : new URL(url.getProtocol(), str2, url.getPort(), url.getFile());
    }

    private static void a(String str) {
        Context context;
        String a2 = a(str, 1);
        if (a2 == null || a2.equalsIgnoreCase(a(str, 3)) || (context = g) == null) {
            return;
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(bh.d(str), a(str, 1)).apply();
        a(str, a(str, 1), 3);
    }

    private static d a(String str, String str2, String str3, String str4, byte[] bArr) {
        d dVar = new d(d);
        try {
            if (a(str, 2) != null) {
                dVar = a(a(str, 2), str3, str4, bArr, str);
                if (dVar.a == d) {
                    a(str, (String) null, 2);
                }
            } else {
                if (a(str, 1) != null) {
                    dVar = a(a(str, 1), str3, str4, bArr, str);
                    if (dVar.a != d) {
                        a(str, a(str, 1), 2);
                        a(str);
                    }
                }
                if (dVar.a == d && a(str, 3) != null) {
                    dVar = a(a(str, 3), str3, str4, bArr, str);
                    if (dVar.a != d) {
                        a(str, a(str, 3), 2);
                    }
                }
                if (dVar.a == d && a(str, 4) != null) {
                    dVar = a(a(str, 4), str3, str4, bArr, str);
                    if (dVar.a != d) {
                        a(str, a(str, 4), 2);
                    }
                }
            }
            return dVar;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return dVar;
        }
    }

    private static d a(String str, String str2, String str3, byte[] bArr, String str4) {
        URLConnection a2;
        try {
            if (str2.startsWith("https://")) {
                c.put(Long.valueOf(Thread.currentThread().getId()), str4);
            }
            if (str2.toLowerCase().startsWith("https") && str3.trim().isEmpty()) {
                a2 = a(new URL(str2), str4, true);
            } else {
                a2 = a(a(str2, str), str4, true);
            }
            if (a2 == null) {
                return new d(d, "");
            }
            if (str2.toLowerCase().startsWith("https") && !str3.trim().isEmpty()) {
                a2 = a(a2, str3);
            }
            return a(bArr, a2);
        } catch (Throwable th) {
            ce.postSDKError(th);
            return new d(d, "");
        }
    }

    private static URLConnection a(URL url, String str, boolean z) {
        try {
            URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty("Accept-Encoding", "");
            openConnection.setRequestProperty(HttpHeader.USER_AGENT, "");
            if (str != null) {
                openConnection.setRequestProperty(HttpHeader.HOST, str);
                openConnection.setRequestProperty("Content-Type", "");
            }
            openConnection.setDoInput(true);
            if (z) {
                openConnection.setDoOutput(true);
            }
            openConnection.setConnectTimeout(a);
            openConnection.setReadTimeout(b);
            return openConnection;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    private static HttpsURLConnection a(URLConnection uRLConnection, String str) {
        SSLContext sSLContext;
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            if (bh.a(16)) {
                sSLContext = SSLContext.getInstance("TLSv1.2");
            } else {
                sSLContext = SSLContext.getInstance("TLSv1");
            }
            sSLContext.init(null, new TrustManager[]{new c(b(str))}, null);
            httpsURLConnection.setHostnameVerifier(new ar());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            return httpsURLConnection;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    private static d a(byte[] bArr, URLConnection uRLConnection) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        int i = d;
        if (bArr == null || bArr.length == 0 || uRLConnection == null) {
            return new d(d, "");
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
        StringBuffer stringBuffer = new StringBuffer();
        OutputStream outputStream = null;
        try {
            httpURLConnection.setRequestMethod("POST");
            OutputStream outputStream2 = httpURLConnection.getOutputStream();
            try {
                outputStream2.write(bArr);
                outputStream2.close();
                i = httpURLConnection.getResponseCode();
                if (i > 400) {
                    inputStream = httpURLConnection.getErrorStream();
                } else {
                    inputStream = httpURLConnection.getInputStream();
                }
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer.append(readLine);
                            stringBuffer.append('\n');
                        } catch (Throwable unused) {
                            outputStream = outputStream2;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable unused2) {
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable unused3) {
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable unused4) {
                                }
                            }
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                } catch (Throwable th) {
                                    th = th;
                                    ce.postSDKError(th);
                                    a = 60000;
                                    b = 60000;
                                    a(httpURLConnection, bArr.length, elapsedRealtime, stringBuffer);
                                    return new d(i, stringBuffer.toString());
                                }
                            }
                            a = 60000;
                            b = 60000;
                            a(httpURLConnection, bArr.length, elapsedRealtime, stringBuffer);
                            return new d(i, stringBuffer.toString());
                        }
                    }
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable unused5) {
                        }
                    }
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused6) {
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable unused7) {
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th2) {
                            th = th2;
                            ce.postSDKError(th);
                            a = 60000;
                            b = 60000;
                            a(httpURLConnection, bArr.length, elapsedRealtime, stringBuffer);
                            return new d(i, stringBuffer.toString());
                        }
                    }
                } catch (Throwable unused8) {
                    bufferedReader = null;
                }
            } catch (Throwable unused9) {
                inputStream = null;
                bufferedReader = null;
            }
        } catch (Throwable unused10) {
            inputStream = null;
            bufferedReader = null;
        }
        a = 60000;
        b = 60000;
        a(httpURLConnection, bArr.length, elapsedRealtime, stringBuffer);
        return new d(i, stringBuffer.toString());
    }

    private static void a(HttpURLConnection httpURLConnection, long j, long j2, StringBuffer stringBuffer) {
        boolean z;
        if (httpURLConnection != null) {
            try {
                TreeMap treeMap = new TreeMap();
                URL url = httpURLConnection.getURL();
                treeMap.put("targetUrl", url.toString());
                treeMap.put("targetIP", InetAddress.getByName(url.getHost()).getHostAddress());
                if (httpURLConnection.getResponseCode() == 200) {
                    treeMap.put("reqSize", Long.valueOf(j));
                    treeMap.put("respTime", Long.valueOf(SystemClock.elapsedRealtime() - j2));
                    z = true;
                } else {
                    treeMap.put("errorMsg", stringBuffer.toString());
                    z = false;
                }
                ce.a(z, treeMap);
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
    }

    private static X509Certificate b(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            try {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream);
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused) {
                }
                return x509Certificate;
            } catch (Exception unused2) {
                byteArrayInputStream.close();
                return null;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused3) {
                }
                throw th;
            }
        } catch (Throwable unused4) {
            return null;
        }
    }

    private static synchronized void a(String str, String str2, int i) {
        synchronized (aq.class) {
            if (!bh.b(str) && h.containsKey(str)) {
                if (h == null) {
                    return;
                }
                a aVar = (a) h.get(str);
                if (i == 1) {
                    aVar.b = str2;
                } else if (i == 2) {
                    aVar.d = str2;
                } else if (i == 3) {
                    aVar.c = str2;
                } else if (i == 4) {
                    aVar.a = str2;
                }
            }
        }
    }

    private static synchronized void b(String str, String str2) {
        synchronized (aq.class) {
            if (!bh.b(str) && !h.containsKey(str)) {
                if (h == null) {
                    return;
                }
                try {
                    a aVar = new a();
                    aVar.e = str;
                    aVar.a = str2;
                    aVar.c = PreferenceManager.getDefaultSharedPreferences(g).getString(bh.d(str), null);
                    aVar.b = InetAddress.getByName(str).getHostAddress();
                    h.put(str, aVar);
                } catch (Throwable unused) {
                }
            }
        }
    }

    private static synchronized String a(String str, int i) {
        synchronized (aq.class) {
            if (!bh.b(str) && h.containsKey(str)) {
                if (h == null) {
                    return null;
                }
                a aVar = (a) h.get(str);
                if (aVar == null) {
                    return null;
                }
                if (i == 1) {
                    return aVar.b;
                }
                if (i == 2) {
                    return aVar.d;
                }
                if (i == 3) {
                    return aVar.c;
                }
                if (i != 4) {
                    return null;
                }
                return aVar.a;
            }
            return null;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static class d {
        public int a;
        public String b;

        d(int i, String str) {
            this.a = i;
            this.b = str;
        }

        d(int i) {
            this(i, "");
        }

        public int a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class c implements X509TrustManager {
        X509Certificate a;

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        c(X509Certificate x509Certificate) {
            this.a = x509Certificate;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            int indexOf;
            int length = x509CertificateArr.length;
            x509CertificateArr[0].getIssuerDN().equals(this.a.getSubjectDN());
            try {
                String name = x509CertificateArr[0].getSubjectDN().getName();
                int indexOf2 = name.indexOf("CN=");
                if (indexOf2 >= 0 && (indexOf = (name = name.substring(indexOf2 + 3)).indexOf(",")) >= 0) {
                    name = name.substring(0, indexOf);
                }
                String[] split = name.split("\\.");
                if (split.length >= 2) {
                    name = split[split.length - 2] + "." + split[split.length - 1];
                }
                if (!aq.c.containsKey(Long.valueOf(Thread.currentThread().getId()))) {
                    throw new CertificateException("No valid host provided!");
                }
                if (!((String) aq.c.get(Long.valueOf(Thread.currentThread().getId()))).endsWith(name)) {
                    throw new CertificateException("Server certificate has incorrect host name!");
                }
                x509CertificateArr[0].verify(this.a.getPublicKey());
                x509CertificateArr[0].checkValidity();
            } catch (Throwable th) {
                ce.postSDKError(th);
                boolean z = th instanceof CertificateException;
            }
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class a {
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        String e = null;

        a() {
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class b {
        static final int a = 1;
        static final int b = 2;
        static final int c = 3;
        static final int d = 4;

        b() {
        }
    }
}
