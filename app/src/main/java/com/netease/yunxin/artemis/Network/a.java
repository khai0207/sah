package com.netease.yunxin.artemis.Network;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* compiled from: HttpResponseHandler.java */
/* loaded from: classes.dex */
public interface a {
    void a(HttpURLConnection httpURLConnection);

    byte[] a(InputStream inputStream, long j);

    void onException(Throwable th);

    void onFailure(int i, Map<String, List<String>> map, byte[] bArr);

    void onFinish();

    void onProgressChanged(long j, long j2);

    void onStart(HttpURLConnection httpURLConnection);

    void onSuccess(int i, URL url, Map<String, List<String>> map, byte[] bArr);

    /* compiled from: HttpResponseHandler.java */
    /* renamed from: com.netease.yunxin.artemis.Network.a$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static byte[] $default$a(a _this, InputStream inputStream, long j) {
            if (inputStream == null) {
                return new byte[0];
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr, 0, 8192);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                    _this.onProgressChanged(read, j);
                } else {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        }

        public static void $default$a(a _this, HttpURLConnection httpURLConnection) {
            int responseCode = httpURLConnection.getResponseCode();
            long contentLength = httpURLConnection.getContentLength();
            URL url = httpURLConnection.getURL();
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            if (responseCode >= 200 && responseCode < 300) {
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    _this.onSuccess(responseCode, url, headerFields, _this.a(inputStream, contentLength));
                    if (inputStream != null) {
                        inputStream.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable unused) {
                            }
                        }
                        throw th2;
                    }
                }
            }
            InputStream errorStream = httpURLConnection.getErrorStream();
            try {
                _this.onFailure(responseCode, headerFields, _this.a(errorStream, contentLength));
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    if (errorStream != null) {
                        try {
                            errorStream.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th4;
                }
            }
        }
    }
}
