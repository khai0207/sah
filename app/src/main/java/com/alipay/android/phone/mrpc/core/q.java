package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public final class q implements Callable<u> {
    public static final HttpRequestRetryHandler e = new ad();
    public l a;
    public Context b;
    public o c;
    public String d;
    public HttpUriRequest f;
    public CookieManager i;
    public AbstractHttpEntity j;
    public HttpHost k;
    public URL l;
    public String q;
    public HttpContext g = new BasicHttpContext();
    public CookieStore h = new BasicCookieStore();
    public int m = 0;
    public boolean n = false;
    public boolean o = false;
    public String p = null;

    public q(l lVar, o oVar) {
        this.a = lVar;
        this.b = lVar.a;
        this.c = oVar;
    }

    public static long a(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if ("max-age".equalsIgnoreCase(strArr[i])) {
                int i2 = i + 1;
                if (strArr[i2] != null) {
                    try {
                        return Long.parseLong(strArr[i2]);
                    } catch (Exception unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return 0L;
    }

    public static HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    private u a(HttpResponse httpResponse, int i, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        String str2;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        String str3 = null;
        if (entity == null || httpResponse.getStatusLine().getStatusCode() != 200) {
            if (entity != null) {
                return null;
            }
            httpResponse.getStatusLine().getStatusCode();
            return null;
        }
        new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            a(entity, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.o = false;
            this.a.c(System.currentTimeMillis() - currentTimeMillis);
            this.a.a(byteArray.length);
            new StringBuilder("res:").append(byteArray.length);
            p pVar = new p(a(httpResponse), i, str, byteArray);
            long b = b(httpResponse);
            Header contentType = httpResponse.getEntity().getContentType();
            if (contentType != null) {
                HashMap<String, String> a = a(contentType.getValue());
                str3 = a.get("charset");
                str2 = a.get("Content-Type");
            } else {
                str2 = null;
            }
            pVar.b(str2);
            pVar.a(str3);
            pVar.a(System.currentTimeMillis());
            pVar.b(b);
            try {
                byteArrayOutputStream.close();
                return pVar;
            } catch (IOException e2) {
                throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e3) {
                    throw new RuntimeException("ArrayOutputStream close error!", e3.getCause());
                }
            }
            throw th;
        }
    }

    public static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(";")) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream a = b.a(httpEntity);
        httpEntity.getContentLength();
        try {
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = a.read(bArr);
                    if (read == -1 || this.c.h()) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    this.c.f();
                }
                outputStream.flush();
            } catch (Exception e2) {
                e2.getCause();
                throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
            }
        } finally {
            r.a(a);
        }
    }

    public static long b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader(Headers.CACHE_CONTROL);
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader(Headers.EXPIRES);
        if (firstHeader2 != null) {
            return b.b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0L;
    }

    private URI b() {
        String a = this.c.a();
        String str = this.d;
        if (str != null) {
            a = str;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    private HttpUriRequest c() {
        HttpUriRequest httpUriRequest = this.f;
        if (httpUriRequest != null) {
            return httpUriRequest;
        }
        if (this.j == null) {
            byte[] b = this.c.b();
            String b2 = this.c.b("gzip");
            if (b != null) {
                if (TextUtils.equals(b2, "true")) {
                    this.j = b.a(b);
                } else {
                    this.j = new ByteArrayEntity(b);
                }
                this.j.setContentType(this.c.c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(b());
            httpPost.setEntity(abstractHttpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0106 A[Catch: Exception -> 0x0262, NullPointerException -> 0x0284, IOException -> 0x02a8, UnknownHostException -> 0x02d2, HttpHostConnectException -> 0x02fe, NoHttpResponseException -> 0x0322, SocketTimeoutException -> 0x034d, ConnectTimeoutException -> 0x0378, ConnectionPoolTimeoutException -> 0x03a2, SSLException -> 0x03cc, SSLPeerUnverifiedException -> 0x03f6, SSLHandshakeException -> 0x0420, URISyntaxException -> 0x044a, HttpException -> 0x0457, TryCatch #3 {HttpException -> 0x0457, NullPointerException -> 0x0284, SocketTimeoutException -> 0x034d, URISyntaxException -> 0x044a, UnknownHostException -> 0x02d2, SSLHandshakeException -> 0x0420, SSLPeerUnverifiedException -> 0x03f6, SSLException -> 0x03cc, NoHttpResponseException -> 0x0322, ConnectionPoolTimeoutException -> 0x03a2, ConnectTimeoutException -> 0x0378, HttpHostConnectException -> 0x02fe, IOException -> 0x02a8, Exception -> 0x0262, blocks: (B:4:0x0006, B:8:0x0032, B:10:0x003a, B:12:0x0040, B:13:0x0044, B:15:0x004a, B:17:0x0058, B:19:0x006c, B:21:0x0081, B:23:0x00c1, B:25:0x00d0, B:27:0x00d6, B:29:0x00e0, B:31:0x00e9, B:33:0x00f5, B:36:0x00ff, B:38:0x0106, B:39:0x0121, B:41:0x0129, B:42:0x0136, B:44:0x015c, B:45:0x0163, B:47:0x0169, B:48:0x016d, B:50:0x0173, B:53:0x017f, B:56:0x01ae, B:62:0x01ca, B:69:0x01e7, B:70:0x0200, B:73:0x0201, B:75:0x0209, B:77:0x020f, B:80:0x021b, B:82:0x021f, B:87:0x022f, B:89:0x0237, B:91:0x0241, B:94:0x0109, B:97:0x0256, B:98:0x0261, B:99:0x0017, B:101:0x001b, B:103:0x001f, B:105:0x0025, B:110:0x002d), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0129 A[Catch: Exception -> 0x0262, NullPointerException -> 0x0284, IOException -> 0x02a8, UnknownHostException -> 0x02d2, HttpHostConnectException -> 0x02fe, NoHttpResponseException -> 0x0322, SocketTimeoutException -> 0x034d, ConnectTimeoutException -> 0x0378, ConnectionPoolTimeoutException -> 0x03a2, SSLException -> 0x03cc, SSLPeerUnverifiedException -> 0x03f6, SSLHandshakeException -> 0x0420, URISyntaxException -> 0x044a, HttpException -> 0x0457, TryCatch #3 {HttpException -> 0x0457, NullPointerException -> 0x0284, SocketTimeoutException -> 0x034d, URISyntaxException -> 0x044a, UnknownHostException -> 0x02d2, SSLHandshakeException -> 0x0420, SSLPeerUnverifiedException -> 0x03f6, SSLException -> 0x03cc, NoHttpResponseException -> 0x0322, ConnectionPoolTimeoutException -> 0x03a2, ConnectTimeoutException -> 0x0378, HttpHostConnectException -> 0x02fe, IOException -> 0x02a8, Exception -> 0x0262, blocks: (B:4:0x0006, B:8:0x0032, B:10:0x003a, B:12:0x0040, B:13:0x0044, B:15:0x004a, B:17:0x0058, B:19:0x006c, B:21:0x0081, B:23:0x00c1, B:25:0x00d0, B:27:0x00d6, B:29:0x00e0, B:31:0x00e9, B:33:0x00f5, B:36:0x00ff, B:38:0x0106, B:39:0x0121, B:41:0x0129, B:42:0x0136, B:44:0x015c, B:45:0x0163, B:47:0x0169, B:48:0x016d, B:50:0x0173, B:53:0x017f, B:56:0x01ae, B:62:0x01ca, B:69:0x01e7, B:70:0x0200, B:73:0x0201, B:75:0x0209, B:77:0x020f, B:80:0x021b, B:82:0x021f, B:87:0x022f, B:89:0x0237, B:91:0x0241, B:94:0x0109, B:97:0x0256, B:98:0x0261, B:99:0x0017, B:101:0x001b, B:103:0x001f, B:105:0x0025, B:110:0x002d), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015c A[Catch: Exception -> 0x0262, NullPointerException -> 0x0284, IOException -> 0x02a8, UnknownHostException -> 0x02d2, HttpHostConnectException -> 0x02fe, NoHttpResponseException -> 0x0322, SocketTimeoutException -> 0x034d, ConnectTimeoutException -> 0x0378, ConnectionPoolTimeoutException -> 0x03a2, SSLException -> 0x03cc, SSLPeerUnverifiedException -> 0x03f6, SSLHandshakeException -> 0x0420, URISyntaxException -> 0x044a, HttpException -> 0x0457, TryCatch #3 {HttpException -> 0x0457, NullPointerException -> 0x0284, SocketTimeoutException -> 0x034d, URISyntaxException -> 0x044a, UnknownHostException -> 0x02d2, SSLHandshakeException -> 0x0420, SSLPeerUnverifiedException -> 0x03f6, SSLException -> 0x03cc, NoHttpResponseException -> 0x0322, ConnectionPoolTimeoutException -> 0x03a2, ConnectTimeoutException -> 0x0378, HttpHostConnectException -> 0x02fe, IOException -> 0x02a8, Exception -> 0x0262, blocks: (B:4:0x0006, B:8:0x0032, B:10:0x003a, B:12:0x0040, B:13:0x0044, B:15:0x004a, B:17:0x0058, B:19:0x006c, B:21:0x0081, B:23:0x00c1, B:25:0x00d0, B:27:0x00d6, B:29:0x00e0, B:31:0x00e9, B:33:0x00f5, B:36:0x00ff, B:38:0x0106, B:39:0x0121, B:41:0x0129, B:42:0x0136, B:44:0x015c, B:45:0x0163, B:47:0x0169, B:48:0x016d, B:50:0x0173, B:53:0x017f, B:56:0x01ae, B:62:0x01ca, B:69:0x01e7, B:70:0x0200, B:73:0x0201, B:75:0x0209, B:77:0x020f, B:80:0x021b, B:82:0x021f, B:87:0x022f, B:89:0x0237, B:91:0x0241, B:94:0x0109, B:97:0x0256, B:98:0x0261, B:99:0x0017, B:101:0x001b, B:103:0x001f, B:105:0x0025, B:110:0x002d), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0169 A[Catch: Exception -> 0x0262, NullPointerException -> 0x0284, IOException -> 0x02a8, UnknownHostException -> 0x02d2, HttpHostConnectException -> 0x02fe, NoHttpResponseException -> 0x0322, SocketTimeoutException -> 0x034d, ConnectTimeoutException -> 0x0378, ConnectionPoolTimeoutException -> 0x03a2, SSLException -> 0x03cc, SSLPeerUnverifiedException -> 0x03f6, SSLHandshakeException -> 0x0420, URISyntaxException -> 0x044a, HttpException -> 0x0457, TryCatch #3 {HttpException -> 0x0457, NullPointerException -> 0x0284, SocketTimeoutException -> 0x034d, URISyntaxException -> 0x044a, UnknownHostException -> 0x02d2, SSLHandshakeException -> 0x0420, SSLPeerUnverifiedException -> 0x03f6, SSLException -> 0x03cc, NoHttpResponseException -> 0x0322, ConnectionPoolTimeoutException -> 0x03a2, ConnectTimeoutException -> 0x0378, HttpHostConnectException -> 0x02fe, IOException -> 0x02a8, Exception -> 0x0262, blocks: (B:4:0x0006, B:8:0x0032, B:10:0x003a, B:12:0x0040, B:13:0x0044, B:15:0x004a, B:17:0x0058, B:19:0x006c, B:21:0x0081, B:23:0x00c1, B:25:0x00d0, B:27:0x00d6, B:29:0x00e0, B:31:0x00e9, B:33:0x00f5, B:36:0x00ff, B:38:0x0106, B:39:0x0121, B:41:0x0129, B:42:0x0136, B:44:0x015c, B:45:0x0163, B:47:0x0169, B:48:0x016d, B:50:0x0173, B:53:0x017f, B:56:0x01ae, B:62:0x01ca, B:69:0x01e7, B:70:0x0200, B:73:0x0201, B:75:0x0209, B:77:0x020f, B:80:0x021b, B:82:0x021f, B:87:0x022f, B:89:0x0237, B:91:0x0241, B:94:0x0109, B:97:0x0256, B:98:0x0261, B:99:0x0017, B:101:0x001b, B:103:0x001f, B:105:0x0025, B:110:0x002d), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0109 A[Catch: Exception -> 0x0262, NullPointerException -> 0x0284, IOException -> 0x02a8, UnknownHostException -> 0x02d2, HttpHostConnectException -> 0x02fe, NoHttpResponseException -> 0x0322, SocketTimeoutException -> 0x034d, ConnectTimeoutException -> 0x0378, ConnectionPoolTimeoutException -> 0x03a2, SSLException -> 0x03cc, SSLPeerUnverifiedException -> 0x03f6, SSLHandshakeException -> 0x0420, URISyntaxException -> 0x044a, HttpException -> 0x0457, TryCatch #3 {HttpException -> 0x0457, NullPointerException -> 0x0284, SocketTimeoutException -> 0x034d, URISyntaxException -> 0x044a, UnknownHostException -> 0x02d2, SSLHandshakeException -> 0x0420, SSLPeerUnverifiedException -> 0x03f6, SSLException -> 0x03cc, NoHttpResponseException -> 0x0322, ConnectionPoolTimeoutException -> 0x03a2, ConnectTimeoutException -> 0x0378, HttpHostConnectException -> 0x02fe, IOException -> 0x02a8, Exception -> 0x0262, blocks: (B:4:0x0006, B:8:0x0032, B:10:0x003a, B:12:0x0040, B:13:0x0044, B:15:0x004a, B:17:0x0058, B:19:0x006c, B:21:0x0081, B:23:0x00c1, B:25:0x00d0, B:27:0x00d6, B:29:0x00e0, B:31:0x00e9, B:33:0x00f5, B:36:0x00ff, B:38:0x0106, B:39:0x0121, B:41:0x0129, B:42:0x0136, B:44:0x015c, B:45:0x0163, B:47:0x0169, B:48:0x016d, B:50:0x0173, B:53:0x017f, B:56:0x01ae, B:62:0x01ca, B:69:0x01e7, B:70:0x0200, B:73:0x0201, B:75:0x0209, B:77:0x020f, B:80:0x021b, B:82:0x021f, B:87:0x022f, B:89:0x0237, B:91:0x0241, B:94:0x0109, B:97:0x0256, B:98:0x0261, B:99:0x0017, B:101:0x001b, B:103:0x001f, B:105:0x0025, B:110:0x002d), top: B:3:0x0006 }] */
    @Override // java.util.concurrent.Callable
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alipay.android.phone.mrpc.core.u call() {
        /*
            Method dump skipped, instructions count: 1140
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.call():com.alipay.android.phone.mrpc.core.u");
    }

    private void e() {
        HttpUriRequest httpUriRequest = this.f;
        if (httpUriRequest != null) {
            httpUriRequest.abort();
        }
    }

    private String f() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        String b = this.c.b("operationType");
        this.q = b;
        return b;
    }

    private int g() {
        URL h = h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    private URL h() {
        URL url = this.l;
        if (url != null) {
            return url;
        }
        URL url2 = new URL(this.c.a());
        this.l = url2;
        return url2;
    }

    private CookieManager i() {
        CookieManager cookieManager = this.i;
        if (cookieManager != null) {
            return cookieManager;
        }
        CookieManager cookieManager2 = CookieManager.getInstance();
        this.i = cookieManager2;
        return cookieManager2;
    }

    public final o a() {
        return this.c;
    }
}
