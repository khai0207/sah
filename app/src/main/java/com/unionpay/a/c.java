package com.unionpay.a;

import android.content.Context;
import com.unionpay.utils.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;

/* loaded from: classes.dex */
public final class c {
    private HttpClient a;
    private HttpResponse b = null;
    private HttpEntity c = null;
    private byte[] d = null;
    private InputStream e = null;
    private d f;

    public c(d dVar, Context context) {
        this.a = null;
        this.f = null;
        this.f = dVar;
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 20);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 60000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpProtocolParams.setUserAgent(basicHttpParams, "uppay");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(com.alipay.sdk.m.h.a.q, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", new a(context), 443));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        this.a = defaultHttpClient;
        defaultHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
    }

    public final int a() {
        String str = "e == null";
        g.a("uppay", "HttpConn.connect() +++");
        d dVar = this.f;
        int i = 1;
        if (dVar == null) {
            g.b("uppay", "params==null!!!");
            return 1;
        }
        HttpUriRequest httpPost = dVar.a() == 1 ? new HttpPost(this.f.b()) : new HttpGet(this.f.b());
        if (this.f.d() != null) {
            ((HttpPost) httpPost).setEntity(new ByteArrayEntity(this.f.d()));
        }
        HashMap c = this.f.c();
        if (c != null) {
            for (String str2 : c.keySet()) {
                httpPost.addHeader(str2, (String) c.get(str2));
            }
        }
        try {
            HttpResponse execute = this.a.execute(httpPost);
            this.b = execute;
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = this.b.getEntity();
                this.c = entity;
                if (entity != null) {
                    ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(2048);
                    byte[] bArr = new byte[2048];
                    this.e = this.c.getContent();
                    while (true) {
                        int read = this.e.read(bArr, 0, 2048);
                        if (read == -1) {
                            break;
                        }
                        if (read > 0) {
                            byteArrayBuffer.append(bArr, 0, read);
                        }
                    }
                    this.d = byteArrayBuffer.toByteArray();
                    i = 0;
                }
            } else if (this.b.getStatusLine().getStatusCode() == 401) {
                i = 8;
            } else {
                g.b("uppay", "http status code:" + this.b.getStatusLine().getStatusCode());
            }
        } catch (SSLHandshakeException e) {
            g.a("uppay", "e0:" + e.getMessage());
            i = 4;
        } catch (IOException e2) {
            if (("e1: " + e2) != null) {
                str = e2.getMessage();
            }
            g.b("uppay", str);
        } catch (IllegalStateException e3) {
            if (("e2: " + e3) != null) {
                str = e3.getMessage();
            }
            g.b("uppay", str);
        } catch (Exception e4) {
            if (("e3: " + e4) != null) {
                str = e4.getMessage();
            }
            g.b("uppay", str);
        }
        g.a("uppay", "HttpConn.connect() ---");
        return i;
    }

    public final String b() {
        byte[] bArr = this.d;
        String str = null;
        if (bArr == null) {
            return null;
        }
        try {
            g.a("uppay", bArr.toString());
            String str2 = new String(this.d, "utf-8");
            try {
                g.a("uppay", "respon:" + str2);
                return str2;
            } catch (UnsupportedEncodingException unused) {
                str = str2;
                g.b("uppay", "convert response to utf-8 error!!!!");
                return str;
            }
        } catch (UnsupportedEncodingException unused2) {
        }
    }
}
