package com.unionpay.sdk;

import android.preference.PreferenceManager;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
final class au {

    /* loaded from: classes.dex */
    static class a extends e {
        private static volatile a g;

        private a() {
        }

        public static a a(String str, String str2) {
            if (g == null) {
                synchronized (b.class) {
                    if (g == null) {
                        g = new a();
                    }
                }
                g.b(str, str2);
            }
            return g;
        }

        @Override // com.unionpay.sdk.au.e
        public final d a(byte[] bArr, URL url) {
            HttpURLConnection httpURLConnection;
            BufferedReader bufferedReader;
            OutputStream outputStream;
            OutputStream outputStream2 = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    outputStream = httpURLConnection.getOutputStream();
                    try {
                        outputStream.write(bArr);
                        outputStream.close();
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
                bufferedReader = null;
            }
            try {
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                    stringBuffer.append("\n");
                }
                d dVar = new d(httpURLConnection.getResponseCode(), stringBuffer.toString());
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused) {
                    }
                }
                try {
                    bufferedReader.close();
                } catch (Throwable unused2) {
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable unused3) {
                    }
                }
                return dVar;
            } catch (Throwable th4) {
                th = th4;
                outputStream2 = outputStream;
                try {
                    th.printStackTrace();
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable unused4) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused5) {
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable unused6) {
                        }
                    }
                    return new d(600, "");
                } catch (Throwable th5) {
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable unused7) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused8) {
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable unused9) {
                        }
                    }
                    throw th5;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class b extends e {
        private static volatile b g;

        private b() {
        }

        public static b a(String str, String str2) {
            if (g == null) {
                synchronized (b.class) {
                    if (g == null) {
                        g = new b();
                    }
                }
            }
            g.b(str, str2);
            return g;
        }

        @Override // com.unionpay.sdk.au.e
        final d a(byte[] bArr, URL url) {
            HttpsURLConnection httpsURLConnection;
            BufferedReader bufferedReader;
            OutputStream outputStream;
            OutputStream outputStream2 = null;
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, new TrustManager[]{new c(au.b(this.f))}, null);
                HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new av(this));
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                try {
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setDoOutput(true);
                    outputStream = httpsURLConnection.getOutputStream();
                    try {
                        outputStream.write(bArr);
                        outputStream.close();
                        bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
            } catch (Throwable th3) {
                th = th3;
                httpsURLConnection = null;
                bufferedReader = null;
            }
            try {
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                    stringBuffer.append('\r');
                }
                d dVar = new d(httpsURLConnection.getResponseCode(), stringBuffer.toString());
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused) {
                    }
                }
                try {
                    bufferedReader.close();
                } catch (Throwable unused2) {
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.disconnect();
                    } catch (Throwable unused3) {
                    }
                }
                return dVar;
            } catch (Throwable th4) {
                th = th4;
                outputStream2 = outputStream;
                try {
                    th.printStackTrace();
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable unused4) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused5) {
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.disconnect();
                        } catch (Throwable unused6) {
                        }
                    }
                    return new d(600, "");
                } catch (Throwable th5) {
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable unused7) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused8) {
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.disconnect();
                        } catch (Throwable unused9) {
                        }
                    }
                    throw th5;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class c implements X509TrustManager {
        X509Certificate a;

        c(X509Certificate x509Certificate) {
            this.a = x509Certificate;
        }

        @Override // javax.net.ssl.X509TrustManager
        public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                al.a("Current cert in cert chain: " + x509Certificate);
                if (x509Certificate.equals(this.a)) {
                    return;
                }
            }
            throw new CertificateException("No trusted cert found!");
        }

        @Override // javax.net.ssl.X509TrustManager
        public final X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    static class d {
        int a;
        String b;

        d() {
            this(600, "");
        }

        d(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    /* loaded from: classes.dex */
    static abstract class e {
        protected String a = null;
        protected String b = null;
        protected String c = null;
        protected String d = null;
        protected String e = null;
        protected String f = null;

        e() {
        }

        private d a(String str, byte[] bArr, String str2) {
            try {
                URL url = new URL(str);
                if (!q.a()) {
                    url = new URL(url.getProtocol(), str2, url.getPort(), url.getFile());
                }
                return a(bArr, url);
            } catch (Throwable unused) {
                return new d(600, "");
            }
        }

        protected final d a(String str, byte[] bArr) {
            d a;
            String str2;
            d dVar = new d();
            String str3 = this.d;
            if (str3 != null) {
                a = a(str, bArr, str3);
                if (a.a != 600) {
                    return a;
                }
                str2 = null;
            } else {
                String str4 = this.b;
                if (str4 != null) {
                    dVar = a(str, bArr, str4);
                    if (dVar.a != 600) {
                        String str5 = this.b;
                        this.d = str5;
                        if (!this.c.equalsIgnoreCase(str5) && ad.c != null) {
                            PreferenceManager.getDefaultSharedPreferences(ad.c).edit().putString(aw.d(this.e), this.b).commit();
                            this.c = this.b;
                        }
                    }
                }
                if (dVar.a == 600 && this.c.length() > 2) {
                    dVar = a(str, bArr, this.c);
                    if (dVar.a != 600) {
                        this.d = this.c;
                    }
                }
                if (dVar.a != 600) {
                    return dVar;
                }
                a = a(str, bArr, this.a);
                if (a.a == 600) {
                    return a;
                }
                str2 = this.a;
            }
            this.d = str2;
            return a;
        }

        abstract d a(byte[] bArr, URL url);

        protected final void b(String str, String str2) {
            this.e = str;
            try {
                this.b = InetAddress.getByName(str).getHostAddress();
            } catch (Throwable unused) {
            }
            this.a = str2;
            this.c = PreferenceManager.getDefaultSharedPreferences(ad.c).getString(aw.d(str), "");
        }
    }

    static e a(String str, String str2, String str3, String str4) {
        if (!str3.toLowerCase().startsWith("https") || str4.trim().isEmpty()) {
            if (str3.toLowerCase().startsWith(com.alipay.sdk.m.h.a.q)) {
                return a.a(str, str2);
            }
            return null;
        }
        b a2 = b.a(str, str2);
        a2.f = str4;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static X509Certificate b(String str) {
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
}
