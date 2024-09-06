package com.netease.nimlib.net.a.b.a;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.util.Mimetypes;
import com.netease.nimlib.n.b.g;
import com.netease.nimlib.n.b.j;
import com.netease.nimlib.n.i;
import com.netease.nimlib.o.m;
import com.netease.nimlib.o.w;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NosUploader.java */
/* loaded from: classes.dex */
public class e {
    private static final String n = com.netease.nimlib.net.a.b.e.b.a(e.class);
    protected volatile HttpURLConnection a;
    protected volatile HttpURLConnection b;
    protected volatile boolean c = false;
    protected volatile boolean d = false;
    protected Context e;
    protected String f;
    protected String g;
    protected String h;
    protected File i;
    protected Object j;
    protected String k;
    protected com.netease.nimlib.net.a.b.c.e l;
    protected long m;
    private com.netease.nimlib.net.a.b.c.b o;

    public e(Context context, String str, String str2, String str3, File file, Object obj, String str4, com.netease.nimlib.net.a.b.c.e eVar) {
        this.e = context;
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.i = file;
        this.j = obj;
        this.k = str4;
        this.l = eVar;
    }

    public void a(com.netease.nimlib.net.a.b.c.b bVar) {
        this.o = bVar;
    }

    public void a() {
        com.netease.nimlib.log.b.c(n, "uploading is canceling");
        this.c = true;
        c();
        if (this.d) {
            return;
        }
        a((com.netease.nimlib.net.a.b.c.a) null);
        this.d = true;
    }

    public com.netease.nimlib.net.a.b.c.a b() {
        this.d = true;
        if (this.c) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(this.l.a())) {
                this.l.a(m.b(this.i.getPath()));
            }
            String str = "";
            if (this.k != null && !this.k.equals("")) {
                com.netease.nimlib.net.a.b.c.c a = a(this.e, this.g, this.h, this.k, this.f);
                if (a.a() != 404 && a.a() != 400) {
                    if (a.a() == 200) {
                        this.m = a.b().getInt("offset");
                        com.netease.nimlib.log.b.N("NosUploader query break offset success = " + this.m);
                    } else {
                        return a(new com.netease.nimlib.net.a.b.c.a(this.j, this.k, a.a(), com.netease.nimlib.net.a.b.e.b.a(a, "requestID"), com.netease.nimlib.net.a.b.e.b.a(a, "callbackRetMsg"), a.b().toString(), null));
                    }
                }
                this.k = null;
            }
            if ((this.m >= this.i.length() && this.i.length() != 0) || this.m < 0) {
                com.netease.nimlib.net.a.b.c.a aVar = new com.netease.nimlib.net.a.b.c.a(this.j, this.k, 699, "", "", null, new com.netease.nimlib.net.a.b.b.a("offset is invalid in server side, with offset: " + this.m + ", file length: " + this.i.length()));
                a(aVar);
                return aVar;
            }
            com.netease.nimlib.net.a.b.c.c a2 = a(this.e, this.i, this.m, com.netease.nimlib.net.a.b.a.c().c(), this.g, this.h, this.f, this.k);
            if (a2 == null) {
                a2 = new com.netease.nimlib.net.a.b.c.c(500, new JSONObject(), null);
            }
            if (a2.b() != null) {
                str = a2.b().toString();
            }
            return a(new com.netease.nimlib.net.a.b.c.a(this.j, this.k, a2.a(), com.netease.nimlib.net.a.b.e.b.a(a2, "requestID"), com.netease.nimlib.net.a.b.e.b.a(a2, "callbackRetMsg"), str, null));
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(n, "offset result exception", e);
            com.netease.nimlib.net.a.b.c.a aVar2 = new com.netease.nimlib.net.a.b.c.a(this.j, this.k, 799, "", "", null, e);
            a(aVar2);
            return aVar2;
        }
    }

    private com.netease.nimlib.net.a.b.c.a a(com.netease.nimlib.net.a.b.c.a aVar) {
        if (this.c || aVar == null) {
            com.netease.nimlib.net.a.b.c.a aVar2 = new com.netease.nimlib.net.a.b.c.a(this.j, this.k, 600, "", "", "uploading is cancelled", null);
            this.o.c(aVar2);
            return aVar2;
        }
        if (aVar.b() == 200 && aVar.d() == null) {
            this.o.a(aVar);
            return aVar;
        }
        this.o.b(aVar);
        int b = aVar.b();
        if (b == 799 || b == 899 || b == 500) {
            c.a().d();
        }
        com.netease.nimlib.log.b.e(n, "upload error with code: " + aVar.b());
        return aVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x014a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0116, code lost:
    
        r12 = r11.b().getInt("offset");
        com.netease.nimlib.n.o.a().a(r1.l.a(), r12);
        r0 = r11.b().getString("context");
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x013e, code lost:
    
        if (r0.equals(r1.k) != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x014d, code lost:
    
        r1.k = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0153, code lost:
    
        r15 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0156, code lost:
    
        r1.o.a(r1.j, r12, r9);
        r0 = r19 + 1;
        com.netease.nimlib.log.b.c(com.netease.nimlib.net.a.b.a.e.n, "http post success, offset: " + r12 + ", len: " + r9 + ", this is " + r0 + " block uploaded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0188, code lost:
    
        if (r12 != 0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x018c, code lost:
    
        if (r9 != 0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x018e, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x018f, code lost:
    
        if (r23 != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0191, code lost:
    
        r19 = r0;
        r5 = java.lang.Math.min(r22 * 2, 1048576);
        r2 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0273, code lost:
    
        r1 = r32;
        r4 = r8;
        r7 = r15;
        r12 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01a3, code lost:
    
        r19 = r0;
        r2 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0271, code lost:
    
        r5 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x028f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0140, code lost:
    
        r1.o.a(r1.j, r1.k, r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02a9 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.netease.nimlib.net.a.b.c.c a(android.content.Context r33, java.io.File r34, long r35, int r37, java.lang.String r38, java.lang.String r39, java.lang.String r40, java.lang.String r41) {
        /*
            Method dump skipped, instructions count: 694
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.a.b.a.e.a(android.content.Context, java.io.File, long, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String):com.netease.nimlib.net.a.b.c.c");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008f A[Catch: Exception -> 0x00f8, all -> 0x0153, TRY_ENTER, TryCatch #0 {all -> 0x0153, blocks: (B:32:0x008f, B:36:0x00bf, B:37:0x00cf, B:41:0x00cc, B:43:0x00df, B:46:0x010e), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00df A[Catch: Exception -> 0x00f8, all -> 0x0153, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0153, blocks: (B:32:0x008f, B:36:0x00bf, B:37:0x00cf, B:41:0x00cc, B:43:0x00df, B:46:0x010e), top: B:2:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.netease.nimlib.net.a.b.c.c a(java.lang.String r13, java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            Method dump skipped, instructions count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.a.b.a.e.a(java.lang.String, java.util.Map):com.netease.nimlib.net.a.b.c.c");
    }

    private com.netease.nimlib.net.a.b.c.c a(Context context, String str, String str2, String str3, String str4) {
        com.netease.nimlib.net.a.b.c.c cVar;
        String[] c = c.a().c();
        if (c == null || c.length == 0) {
            com.netease.nimlib.log.b.f(n, "nos uploader getBreakOffset get nos upload ip null!");
            return new com.netease.nimlib.net.a.b.c.c(10000, null, null);
        }
        com.netease.nimlib.log.b.c(n, "upload servers: " + Arrays.toString(c));
        HashMap hashMap = new HashMap();
        hashMap.put("x-nos-token", str4);
        try {
            cVar = null;
            for (String str5 : c) {
                try {
                    String a = com.netease.nimlib.net.a.b.d.b.a(str5, str, str2, str3);
                    com.netease.nimlib.log.b.c(n, "break query upload server url: " + a);
                    cVar = a(a, context, hashMap);
                    if (this.c || cVar.a() == 200 || cVar.a() == 404) {
                        return cVar;
                    }
                } catch (Exception e) {
                    e = e;
                    com.netease.nimlib.log.b.e(n, "get break offset exception", e);
                    return cVar == null ? new com.netease.nimlib.net.a.b.c.c(500, new JSONObject(), null) : cVar;
                }
            }
            return cVar;
        } catch (Exception e2) {
            e = e2;
            cVar = null;
        }
    }

    private com.netease.nimlib.net.a.b.c.c a(String str, Context context, Map<String, String> map) throws JSONException {
        int e = com.netease.nimlib.net.a.b.a.c().e();
        int i = 0;
        com.netease.nimlib.net.a.b.c.c cVar = null;
        while (true) {
            int i2 = i + 1;
            if (i >= e || this.c) {
                break;
            }
            com.netease.nimlib.log.b.c(n, "query offset with url: " + str + ", retry times: " + i2);
            cVar = a(str, map);
            if (cVar.a() == 200) {
                JSONObject b = cVar.b();
                com.netease.nimlib.log.b.c(n, "get break offset result:" + b.toString());
                return cVar;
            }
            if (cVar.a() == 404) {
                com.netease.nimlib.log.b.c(n, "upload file is expired in server side.");
                return cVar;
            }
            i = i2;
        }
        return cVar;
    }

    private com.netease.nimlib.net.a.b.c.c a(String str, byte[] bArr) {
        int d = com.netease.nimlib.net.a.b.a.c().d();
        com.netease.nimlib.log.b.c(n, "user set the retry times is : " + d);
        int i = 0;
        int i2 = -1;
        com.netease.nimlib.net.a.b.c.c cVar = null;
        while (true) {
            int i3 = i + 1;
            if (i >= d) {
                break;
            }
            try {
                if (this.c) {
                    break;
                }
                com.netease.nimlib.log.b.c(n, "put block to server side with url: " + str + ", length: " + bArr.length + ", retryTime: " + i3);
                i.a().b(this.l.a());
                cVar = b(str, bArr);
                if (this.c) {
                    return cVar;
                }
                int a = cVar.a();
                if (a != 200) {
                    i.a().a(this.l.a(), str, a);
                }
                if (a == 200) {
                    com.netease.nimlib.log.b.c(n, "http post result is back, result:" + cVar.toString() + ", retryTime: " + i3);
                    JSONObject b = cVar.b();
                    if (b != null && b.has("context") && b.has("offset")) {
                        int i4 = cVar.b().getInt("offset");
                        com.netease.nimlib.log.b.c(n, "http post result success with context: " + this.e + ", offset: " + i4);
                        i2 = i4;
                    }
                } else {
                    if (a == 403 || a == 500 || a == 520) {
                        break;
                    }
                    if (a == 799) {
                        i2 = -4;
                    } else if (a == 899) {
                        i2 = -5;
                    } else if (a == 1099) {
                        return cVar;
                    }
                }
                if (i2 > 0) {
                    com.netease.nimlib.log.b.c(n, "retryPutFile with success result: " + i2);
                    return cVar;
                }
                i = i3;
            } catch (Exception e) {
                com.netease.nimlib.log.b.e(n, "put file exception", e);
            }
        }
        return cVar;
    }

    private com.netease.nimlib.net.a.b.c.c b(String str, byte[] bArr) {
        String str2;
        InputStream inputStream;
        String str3;
        int i;
        int i2;
        com.netease.nimlib.net.a.b.c.c cVar;
        String str4;
        String str5;
        com.netease.nimlib.log.b.c(n, "http post task is executing");
        String b = w.b();
        com.netease.nimlib.n.e.a(b, g.NOS);
        try {
            try {
                try {
                    this.a = com.netease.nimlib.net.a.c.b.a(str, "POST");
                    if (com.netease.nimlib.net.a.c.b.a(str) && com.netease.nimlib.net.a.c.b.a(this.a)) {
                        com.netease.nimlib.net.a.c.b.a(this.a, com.netease.nimlib.d.g.j());
                    }
                    com.netease.nimlib.net.a.c.b.a(this.a, "NIM-Android-NOS-Upload-V9.17.0", com.netease.nimlib.net.a.b.a.c().a(), com.netease.nimlib.net.a.b.a.c().b(), com.netease.nimlib.d.g.j());
                    com.netease.nimlib.net.a.c.b.a(this.a, bArr.length);
                    if (com.netease.nimlib.net.a.c.b.a(this.a) && !TextUtils.isEmpty(com.netease.nimlib.d.g.j())) {
                        com.netease.nimlib.net.a.c.b.a(this.a, HttpHeader.HOST, com.netease.nimlib.d.g.j());
                    }
                    com.netease.nimlib.net.a.c.b.a(this.a, "x-nos-token", this.f);
                    if (this.l != null) {
                        if (!TextUtils.isEmpty(this.l.b())) {
                            com.netease.nimlib.net.a.c.b.a(this.a, "Content-Type", this.l.b());
                        } else {
                            com.netease.nimlib.net.a.c.b.a(this.a, "Content-Type", Mimetypes.MIMETYPE_OCTET_STREAM);
                        }
                        if (!TextUtils.isEmpty(this.l.a())) {
                            com.netease.nimlib.net.a.c.b.a(this.a, Headers.CONTENT_MD5, this.l.a());
                        }
                        if (this.l.c() != null && this.l.c().size() > 0) {
                            Map<String, String> c = this.l.c();
                            for (String str6 : c.keySet()) {
                                com.netease.nimlib.net.a.c.b.a(this.a, "x-nos-meta-" + str6, c.get(str6));
                            }
                        }
                    }
                    try {
                        Map<String, List<String>> requestProperties = this.a.getRequestProperties();
                        if (requestProperties != null) {
                            Set<String> keySet = requestProperties.keySet();
                            JSONObject jSONObject = new JSONObject();
                            for (String str7 : keySet) {
                                String requestProperty = this.a.getRequestProperty(str7);
                                if (str7 == null) {
                                    str7 = "";
                                }
                                jSONObject.put(str7, requestProperty);
                            }
                            str5 = jSONObject.toString();
                        } else {
                            str5 = null;
                        }
                        str4 = str5;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        str4 = null;
                    }
                } catch (SSLPeerUnverifiedException e) {
                    e = e;
                    str3 = null;
                    inputStream = null;
                } catch (Exception e2) {
                    e = e2;
                    str2 = null;
                    inputStream = null;
                }
                try {
                    com.netease.nimlib.net.a.c.b.a(this.a, bArr);
                    int responseCode = this.a.getResponseCode();
                    try {
                        inputStream = this.a.getInputStream();
                    } catch (SSLPeerUnverifiedException e3) {
                        e = e3;
                        i2 = responseCode;
                        inputStream = null;
                    } catch (Exception e4) {
                        e = e4;
                        inputStream = null;
                    }
                    try {
                        if (inputStream != null) {
                            String a = com.netease.nimlib.net.a.c.b.a(inputStream);
                            if (responseCode == 200) {
                                com.netease.nimlib.log.b.c(n, "http post response is correct, response: " + a);
                            } else {
                                com.netease.nimlib.log.b.c(n, "http post response is failed, status code: " + responseCode);
                            }
                            cVar = new com.netease.nimlib.net.a.b.c.c(responseCode, new JSONObject(a), null);
                            if (!(responseCode == 200)) {
                                com.netease.nimlib.n.e.a(b, j.kPost, str, responseCode, str4, null, "NosUploader#post failed");
                            } else {
                                com.netease.nimlib.n.e.a(b);
                            }
                        } else {
                            cVar = new com.netease.nimlib.net.a.b.c.c(899, null, null);
                            com.netease.nimlib.n.e.a(b, j.kPost, str, responseCode, str4, null, "NosUploader#post http no response");
                        }
                    } catch (SSLPeerUnverifiedException e5) {
                        e = e5;
                        i2 = responseCode;
                        e = e;
                        str3 = str4;
                        com.netease.nimlib.n.e.a(b, j.kPost, str, i2, str3, null, "NosUploader#post exception = " + e);
                        com.netease.nimlib.log.b.f(n, "http post exception, e=SSL_PEER_UNVERIFIED_EXCEPTION," + e.getMessage());
                        cVar = new com.netease.nimlib.net.a.b.c.c(1099, new JSONObject(), e);
                        com.netease.nimlib.net.a.c.b.b(inputStream);
                        this.a.disconnect();
                        this.a = null;
                        return cVar;
                    } catch (Exception e6) {
                        e = e6;
                        str2 = str4;
                        i = responseCode;
                        e = e;
                        com.netease.nimlib.n.e.a(b, j.kPost, str, i, str2, null, "NosUploader#post exception = " + e);
                        com.netease.nimlib.log.b.e(n, "http post exception, status code=" + i, e);
                        cVar = new com.netease.nimlib.net.a.b.c.c(i, new JSONObject(), e);
                        com.netease.nimlib.net.a.c.b.b(inputStream);
                        this.a.disconnect();
                        this.a = null;
                        return cVar;
                    }
                } catch (SSLPeerUnverifiedException e7) {
                    e = e7;
                    inputStream = null;
                    str3 = str4;
                    i2 = 799;
                    com.netease.nimlib.n.e.a(b, j.kPost, str, i2, str3, null, "NosUploader#post exception = " + e);
                    com.netease.nimlib.log.b.f(n, "http post exception, e=SSL_PEER_UNVERIFIED_EXCEPTION," + e.getMessage());
                    cVar = new com.netease.nimlib.net.a.b.c.c(1099, new JSONObject(), e);
                    com.netease.nimlib.net.a.c.b.b(inputStream);
                    this.a.disconnect();
                    this.a = null;
                    return cVar;
                } catch (Exception e8) {
                    e = e8;
                    inputStream = null;
                    str2 = str4;
                    i = 799;
                    com.netease.nimlib.n.e.a(b, j.kPost, str, i, str2, null, "NosUploader#post exception = " + e);
                    com.netease.nimlib.log.b.e(n, "http post exception, status code=" + i, e);
                    cVar = new com.netease.nimlib.net.a.b.c.c(i, new JSONObject(), e);
                    com.netease.nimlib.net.a.c.b.b(inputStream);
                    this.a.disconnect();
                    this.a = null;
                    return cVar;
                }
                com.netease.nimlib.net.a.c.b.b(inputStream);
                this.a.disconnect();
                this.a = null;
                return cVar;
            } catch (Throwable th2) {
                th = th2;
                com.netease.nimlib.net.a.c.b.b((InputStream) null);
                this.a.disconnect();
                this.a = null;
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            com.netease.nimlib.net.a.c.b.b((InputStream) null);
            this.a.disconnect();
            this.a = null;
            throw th;
        }
    }

    private void c() {
        if (this.b != null) {
            this.b.disconnect();
        }
        if (this.a != null) {
            this.a.disconnect();
        }
    }
}
