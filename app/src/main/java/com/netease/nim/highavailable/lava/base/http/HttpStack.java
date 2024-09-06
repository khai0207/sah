package com.netease.nim.highavailable.lava.base.http;

import android.text.TextUtils;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.netease.nim.highavailable.LogUtils;
import com.netease.nim.highavailable.lava.base.util.ArrayUtils;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.log.b;
import com.talkingdata.sdk.aa;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpStack {
    private static final int CURLE_COULDNT_CONNECT = 7;
    private static final int CURLE_COULDNT_RESOLVE_HOST = 6;
    private static final int CURLE_SSL_CONNECT_ERROR = 35;
    private static final int CURLE_UNSUPPORTED_PROTOCOL = 1;
    private static final int CURLE_URL_MALFORMAT = 3;
    private static final String TAG = "HttpStack";

    private static boolean needRedirect(int i) {
        return i >= 300 && i < 400;
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    private static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    private static HttpURLConnection openConnection(URL url, String str, Map<String, String> map, byte[] bArr, int i) throws IOException {
        HttpURLConnection createConnection = createConnection(url);
        createConnection.setRequestMethod(str);
        createConnection.setConnectTimeout(i);
        createConnection.setReadTimeout(i);
        createConnection.setUseCaches(false);
        createConnection.setDoInput(true);
        configHttps(createConnection);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                createConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            createConnection.setDoOutput(true);
            OutputStream outputStream = createConnection.getOutputStream();
            if (map != null && "gzip".equals(map.get("Content-Encoding"))) {
                outputStream = new GZIPOutputStream(new BufferedOutputStream(outputStream));
            }
            outputStream.write(bArr);
            outputStream.flush();
            closeQuietly(outputStream);
        }
        return createConnection;
    }

    private static HttpURLConnection openConnection(URL url, String str, Map<String, String> map, int i) throws IOException {
        HttpURLConnection createConnection = createConnection(url);
        createConnection.setRequestMethod(str);
        createConnection.setConnectTimeout(i);
        createConnection.setReadTimeout(i);
        createConnection.setUseCaches(false);
        createConnection.setDoInput(true);
        configHttps(createConnection);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                createConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return createConnection;
    }

    public static HttpStackResponse doPost(String str) {
        return doPost(str, null, null, PathInterpolatorCompat.MAX_NUM_POINTS);
    }

    public static HttpStackResponse doPost(String str, int i) {
        return doPost(str, null, null, i);
    }

    public static HttpStackResponse doPost(String str, Map<String, String> map, byte[] bArr, int i, String str2, String str3) {
        return doHttpMethod(str, map, bArr, i, str2, str3, "POST");
    }

    public static HttpStackResponse doGet(String str, Map<String, String> map, int i, String str2, String str3) {
        return doHttpMethod(str, map, null, i, str2, str3, "GET");
    }

    private static HttpStackResponse doHttpMethod(String str, Map<String, String> map, byte[] bArr, int i, String str2, String str3, String str4) {
        HttpStackResponse httpStackResponse;
        URL url;
        int responseCode;
        String str5;
        HttpsURLConnection httpsURLConnection = null;
        if (str == null || str.isEmpty() || str3 == null || str3.isEmpty()) {
            LogUtils.e(TAG, "illegal argument, path:" + str + ", ip:" + str2 + ", host:" + str3);
            return null;
        }
        try {
            url = new URL(str);
        } catch (Throwable th) {
            th = th;
        }
        if (!"https".equals(url.getProtocol())) {
            LogUtils.e(TAG, "doPost failed, " + url.getProtocol() + " is not https");
            return null;
        }
        if (str2 != null && !str2.isEmpty()) {
            URI uri = url.toURI();
            URL url2 = new URI(uri.getScheme(), uri.getUserInfo(), str2, url.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment()).toURL();
            LogUtils.d(TAG, "oldURL = " + url + ", newURL = " + url2);
        }
        final HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) url.openConnection();
        try {
            httpsURLConnection2.setRequestProperty(HttpHeader.HOST, str3);
            httpsURLConnection2.setRequestMethod(str4);
            httpsURLConnection2.setConnectTimeout(i);
            httpsURLConnection2.setReadTimeout(i);
            httpsURLConnection2.setUseCaches(false);
            httpsURLConnection2.setDoInput(true);
            httpsURLConnection2.setInstanceFollowRedirects(false);
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpsURLConnection2.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpsURLConnection2.setSSLSocketFactory(new TlsSniSocketFactory(httpsURLConnection2));
            httpsURLConnection2.setHostnameVerifier(new HostnameVerifier() { // from class: com.netease.nim.highavailable.lava.base.http.HttpStack.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str6, SSLSession sSLSession) {
                    String requestProperty = httpsURLConnection2.getRequestProperty(HttpHeader.HOST);
                    if (requestProperty == null) {
                        requestProperty = httpsURLConnection2.getURL().getHost();
                    }
                    boolean verify = HttpsURLConnection.getDefaultHostnameVerifier().verify(requestProperty, sSLSession);
                    if (!verify) {
                        try {
                            LogUtils.w(HttpStack.TAG, String.format("host name verify failed %s %s", requestProperty, str6));
                            LogUtils.w(HttpStack.TAG, String.format("getApplicationBufferSize: %s", Integer.valueOf(sSLSession.getApplicationBufferSize())));
                            LogUtils.w(HttpStack.TAG, String.format("getCipherSuite: %s", sSLSession.getCipherSuite()));
                            if (sSLSession.getLocalCertificates() != null) {
                                LogUtils.w(HttpStack.TAG, String.format("getLocalCertificates: %s", Integer.valueOf(sSLSession.getLocalCertificates().length)));
                                for (Certificate certificate : sSLSession.getLocalCertificates()) {
                                    LogUtils.w(HttpStack.TAG, String.format("getLocalCertificates: %s", certificate));
                                }
                            }
                            LogUtils.w(HttpStack.TAG, String.format("getLocalPrincipal: %s", sSLSession.getLocalPrincipal()));
                            try {
                                if (sSLSession.getPeerCertificateChain() != null) {
                                    LogUtils.w(HttpStack.TAG, String.format("getPeerCertificateChain: %s", Integer.valueOf(sSLSession.getPeerCertificateChain().length)));
                                    for (X509Certificate x509Certificate : sSLSession.getPeerCertificateChain()) {
                                        LogUtils.w(HttpStack.TAG, String.format("getPeerCertificateChain: %s", x509Certificate));
                                    }
                                }
                            } catch (Throwable th2) {
                                b.e(HttpStack.TAG, "host name verify failed getPeerCertificateChain", th2);
                            }
                            try {
                                if (sSLSession.getPeerCertificates() != null) {
                                    LogUtils.w(HttpStack.TAG, String.format("getPeerCertificates: %s", Integer.valueOf(sSLSession.getPeerCertificates().length)));
                                    for (Certificate certificate2 : sSLSession.getPeerCertificates()) {
                                        LogUtils.w(HttpStack.TAG, String.format("getPeerCertificates: %s", certificate2));
                                    }
                                }
                            } catch (Throwable th3) {
                                b.e(HttpStack.TAG, "host name verify failed getPeerCertificates", th3);
                            }
                            LogUtils.w(HttpStack.TAG, String.format("getPeerHost: %s", sSLSession.getPeerHost()));
                            LogUtils.w(HttpStack.TAG, String.format("getPeerPort: %s", Integer.valueOf(sSLSession.getPeerPort())));
                            try {
                                LogUtils.w(HttpStack.TAG, String.format("getPeerPrincipal: %s", sSLSession.getPeerPrincipal()));
                            } catch (Throwable th4) {
                                b.e(HttpStack.TAG, "host name verify failed getPeerPrincipal", th4);
                            }
                            LogUtils.w(HttpStack.TAG, String.format("getProtocol: %s", sSLSession.getProtocol()));
                            LogUtils.w(HttpStack.TAG, String.format("getSessionContext: %s", sSLSession.getSessionContext()));
                            if (sSLSession.getValueNames() != null) {
                                LogUtils.w(HttpStack.TAG, String.format("getValueNames: %s", Integer.valueOf(sSLSession.getValueNames().length)));
                                for (String str7 : sSLSession.getValueNames()) {
                                    LogUtils.w(HttpStack.TAG, String.format("getValueNames: %s", str7));
                                }
                            }
                        } catch (Throwable th5) {
                            b.e(HttpStack.TAG, "host name verify failed", th5);
                        }
                    }
                    return verify;
                }
            });
            if (!ArrayUtils.isEmpty(bArr)) {
                httpsURLConnection2.setDoOutput(true);
                OutputStream outputStream = httpsURLConnection2.getOutputStream();
                if (map != null && "gzip".equals(map.get("Content-Encoding"))) {
                    outputStream = new GZIPOutputStream(new BufferedOutputStream(outputStream));
                }
                outputStream.write(bArr);
                outputStream.flush();
                closeQuietly(outputStream);
            }
            responseCode = httpsURLConnection2.getResponseCode();
        } catch (Throwable th2) {
            th = th2;
            httpsURLConnection = httpsURLConnection2;
            try {
                LogUtils.e(TAG, "doHttpMethod exception," + th);
                httpStackResponse = getHttpStackResponse(headersToString(map), th);
                LogUtils.d(TAG, "doHttpMethod: response:" + httpStackResponse);
                return httpStackResponse;
            } finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            }
        }
        if (!needRedirect(responseCode)) {
            long lastModified = httpsURLConnection2.getLastModified();
            String readFully = readFully(httpsURLConnection2.getInputStream());
            httpStackResponse = new HttpStackResponse();
            httpStackResponse.code = responseCode;
            httpStackResponse.result = readFully;
            httpStackResponse.lastModified = lastModified;
            httpStackResponse.headers = headersToString(httpsURLConnection2);
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
            LogUtils.d(TAG, "doHttpMethod: response:" + httpStackResponse);
            return httpStackResponse;
        }
        String headerField = httpsURLConnection2.getHeaderField(HttpHeader.LOCATION);
        if (headerField == null) {
            headerField = httpsURLConnection2.getHeaderField("location");
        }
        if (headerField.startsWith("http://") || headerField.startsWith("https://")) {
            str5 = headerField;
        } else {
            URL url3 = new URL(str);
            str5 = url3.getProtocol() + aa.a + url3.getHost() + headerField;
        }
        HttpStackResponse doPost = doPost(str5, map, bArr, i, null, str3);
        if (httpsURLConnection2 != null) {
            httpsURLConnection2.disconnect();
        }
        return doPost;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
    
        if (r0 == null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003f, code lost:
    
        com.netease.nim.highavailable.LogUtils.d(com.netease.nim.highavailable.lava.base.http.HttpStack.TAG, "doPost: response:" + r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0055, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.netease.nim.highavailable.lava.base.http.HttpStackResponse doPost(java.lang.String r3, java.util.Map<java.lang.String, java.lang.String> r4, byte[] r5, int r6) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L33
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "POST"
            java.net.HttpURLConnection r0 = openConnection(r1, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L33
            int r3 = r0.getResponseCode()     // Catch: java.lang.Throwable -> L33
            long r5 = r0.getLastModified()     // Catch: java.lang.Throwable -> L33
            java.io.InputStream r1 = r0.getInputStream()     // Catch: java.lang.Throwable -> L33
            java.lang.String r1 = readFully(r1)     // Catch: java.lang.Throwable -> L33
            com.netease.nim.highavailable.lava.base.http.HttpStackResponse r2 = new com.netease.nim.highavailable.lava.base.http.HttpStackResponse     // Catch: java.lang.Throwable -> L33
            r2.<init>()     // Catch: java.lang.Throwable -> L33
            r2.code = r3     // Catch: java.lang.Throwable -> L33
            r2.result = r1     // Catch: java.lang.Throwable -> L33
            r2.lastModified = r5     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = headersToString(r0)     // Catch: java.lang.Throwable -> L33
            r2.headers = r3     // Catch: java.lang.Throwable -> L33
            if (r0 == 0) goto L3f
        L2f:
            r0.disconnect()
            goto L3f
        L33:
            r3 = move-exception
            java.lang.String r4 = headersToString(r4)     // Catch: java.lang.Throwable -> L56
            com.netease.nim.highavailable.lava.base.http.HttpStackResponse r2 = getHttpStackResponse(r4, r3)     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L3f
            goto L2f
        L3f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "doPost: response:"
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "HttpStack"
            com.netease.nim.highavailable.LogUtils.d(r4, r3)
            return r2
        L56:
            r3 = move-exception
            if (r0 == 0) goto L5c
            r0.disconnect()
        L5c:
            goto L5e
        L5d:
            throw r3
        L5e:
            goto L5d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nim.highavailable.lava.base.http.HttpStack.doPost(java.lang.String, java.util.Map, byte[], int):com.netease.nim.highavailable.lava.base.http.HttpStackResponse");
    }

    private static HttpStackResponse getHttpStackResponse(String str, Throwable th) {
        int i;
        String message = th.getMessage();
        if (th instanceof SocketTimeoutException) {
            i = 408;
        } else if (th instanceof FileNotFoundException) {
            i = 404;
        } else if (th instanceof ConnectException) {
            i = 7;
        } else if (th instanceof MalformedURLException) {
            i = 3;
        } else if (th instanceof UnknownHostException) {
            i = 6;
        } else if (th instanceof SSLException) {
            i = 35;
        } else {
            i = (message == null || !message.contains("Cleartext HTTP traffic")) ? -1 : 1;
        }
        HttpStackResponse httpStackResponse = new HttpStackResponse();
        httpStackResponse.code = i;
        httpStackResponse.result = th.toString();
        httpStackResponse.headers = str;
        if (httpStackResponse.result.isEmpty()) {
            httpStackResponse.result = message;
        }
        return httpStackResponse;
    }

    public static HttpStackResponse doPostHeaders(String str, HttpHeaderPair[] httpHeaderPairArr, byte[] bArr, int i) {
        HashMap hashMap = new HashMap();
        for (HttpHeaderPair httpHeaderPair : httpHeaderPairArr) {
            hashMap.put(httpHeaderPair.getKey(), httpHeaderPair.getValue());
        }
        return doPost(str, hashMap, bArr, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.netease.nim.highavailable.lava.base.http.HttpStackResponse multipartPost(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.util.Map<java.lang.String, java.lang.String> r10, com.netease.nim.highavailable.lava.base.http.MultipartWriter r11) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nim.highavailable.lava.base.http.HttpStack.multipartPost(java.lang.String, java.lang.String, java.lang.String, java.util.Map, com.netease.nim.highavailable.lava.base.http.MultipartWriter):com.netease.nim.highavailable.lava.base.http.HttpStackResponse");
    }

    private static String readFully(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (inputStream == null) {
            return "";
        }
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
        } catch (Throwable th) {
            th = th;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read >= 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    closeQuietly(bufferedInputStream);
                    return byteArrayOutputStream2;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            closeQuietly(bufferedInputStream2);
            throw th;
        }
    }

    private static void configHttps(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null || !(httpURLConnection instanceof HttpsURLConnection)) {
            return;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, null, null);
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.netease.nim.highavailable.lava.base.http.HttpStack.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    boolean z = !TextUtils.isEmpty(str);
                    if (!z) {
                        LogUtils.e(HttpStack.TAG, "verify: " + str);
                    }
                    return z;
                }
            });
        } catch (Exception e) {
            LogUtils.e(TAG, "configHttps exception," + e);
        }
    }

    private static String headersToString(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key == null) {
                    key = "httpversion";
                }
                jSONObject.put(key, value);
            }
        } catch (Throwable th) {
            LogUtils.e(TAG, "headersToString1 exception," + th);
        }
        return jSONObject.toString();
    }

    private static String headersToString(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : httpURLConnection.getHeaderFields().keySet()) {
                String headerField = httpURLConnection.getHeaderField(str);
                if (str == null) {
                    str = "httpversion";
                }
                jSONObject.put(str, headerField);
            }
        } catch (Throwable th) {
            LogUtils.e(TAG, "headersToString2 exception," + th);
        }
        return jSONObject.toString();
    }
}
