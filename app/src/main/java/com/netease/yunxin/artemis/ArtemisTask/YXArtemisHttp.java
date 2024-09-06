package com.netease.yunxin.artemis.ArtemisTask;

import android.content.Context;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Artemis.d;
import com.netease.yunxin.artemis.Network.a;
import com.netease.yunxin.artemis.Network.c;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemisHttp extends b {
    private static final String TAG = "YXArtemisHttp";
    a handlePullTask;
    private HashMap<String, String> headers;
    private HashMap<String, String> mParams;
    private String method;
    private long startMill;
    private String url;

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void aggregateResult() {
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    public YXArtemisHttp(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, Context context, String str7, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        super(str, str2, str3, i, jSONObject, str4, str5, i2, str6, str7, yXArtemisRunTaskCallback);
        this.handlePullTask = new a() { // from class: com.netease.yunxin.artemis.ArtemisTask.YXArtemisHttp.1
            @Override // com.netease.yunxin.artemis.Network.a
            public /* synthetic */ void a(HttpURLConnection httpURLConnection) {
                a.CC.$default$a(this, httpURLConnection);
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public /* synthetic */ byte[] a(InputStream inputStream, long j) {
                return a.CC.$default$a(this, inputStream, j);
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public void onException(Throwable th) {
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public void onFinish() {
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public void onProgressChanged(long j, long j2) {
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public void onStart(HttpURLConnection httpURLConnection) {
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public void onSuccess(int i3, URL url, Map<String, List<String>> map, byte[] bArr) {
                long currentTimeMillis = System.currentTimeMillis();
                String str8 = (bArr == null || bArr.length <= 0) ? "" : new String(bArr, 0, Math.min(512, bArr.length));
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject2.put("httpcode", i3);
                    jSONObject2.put("body", str8);
                    jSONObject2.put("cost_time", currentTimeMillis - YXArtemisHttp.this.startMill);
                    jSONObject3.put(Constants.URL_ENCODING, url.toString());
                    jSONObject3.put("httpcode", i3);
                } catch (Throwable unused) {
                }
                d.a().a(YXArtemisHttp.this, jSONObject2.toString(), jSONObject3.toString(), YXArtemisHttp.this.mReportAddr);
            }

            @Override // com.netease.yunxin.artemis.Network.a
            public void onFailure(int i3, Map<String, List<String>> map, byte[] bArr) {
                long currentTimeMillis = System.currentTimeMillis();
                String str8 = (bArr == null || bArr.length <= 0) ? "" : new String(bArr, 0, Math.min(512, bArr.length));
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject2.put("httpcode", i3);
                    jSONObject2.put("body", str8);
                    jSONObject2.put("cost_time", currentTimeMillis - YXArtemisHttp.this.startMill);
                    jSONObject3.put(Constants.URL_ENCODING, YXArtemisHttp.this.url);
                    jSONObject3.put("httpcode", i3);
                } catch (Throwable unused) {
                }
                d.a().a(YXArtemisHttp.this, jSONObject2.toString(), jSONObject3.toString(), YXArtemisHttp.this.mReportAddr);
            }
        };
        try {
            this.method = jSONObject.getString("method");
            String string = jSONObject.getString(Constants.URL_ENCODING);
            this.url = string;
            if (!string.startsWith(com.alipay.sdk.m.h.a.q)) {
                this.url = "http://".concat(this.url);
            }
            this.mParams = jsonObj2hashmap(jSONObject.getJSONObject("params"));
            this.headers = jsonObj2hashmap(jSONObject.getJSONObject("headers"));
        } catch (Throwable unused) {
        }
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        c cVar = new c(this.mParams);
        this.startMill = System.currentTimeMillis();
        if (this.method.equalsIgnoreCase("get")) {
            com.netease.yunxin.artemis.Network.b.a().a(this.url, cVar, this.handlePullTask, this.headers);
        } else if (this.method.equalsIgnoreCase("post")) {
            com.netease.yunxin.artemis.Network.b.a().b(this.url, cVar, this.handlePullTask, this.headers);
        }
    }

    private HashMap<String, String> jsonObj2hashmap(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String valueOf = String.valueOf(keys.next());
                hashMap.put(valueOf, jSONObject.get(valueOf).toString());
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }
}
