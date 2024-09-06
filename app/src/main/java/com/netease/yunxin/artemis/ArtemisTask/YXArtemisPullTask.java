package com.netease.yunxin.artemis.ArtemisTask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.MessageQueue;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback;
import com.netease.yunxin.artemis.Artemis.a;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Artemis.c;
import com.netease.yunxin.artemis.Network.a;
import com.netease.yunxin.artemis.a.d;
import com.netease.yunxin.artemis.a.f;
import com.netease.yunxin.artemis.a.g;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemisPullTask extends b {
    private static final String TAG = "YXArtemisPullTask";
    private static YXArtemisPullTask instance;
    public static final MessageQueue.IdleHandler mIdlePullTask = new MessageQueue.IdleHandler() { // from class: com.netease.yunxin.artemis.ArtemisTask.YXArtemisPullTask.2
        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            if (!(c.a().d != null ? a.a((Runnable) YXArtemisPullTask.getInstance()) : false)) {
                c.a().a(YXArtemisPullTask.getInstance());
            }
            return false;
        }
    };
    private Random random = new Random();
    com.netease.yunxin.artemis.Network.a handlePullTask = new com.netease.yunxin.artemis.Network.a() { // from class: com.netease.yunxin.artemis.ArtemisTask.YXArtemisPullTask.1
        @Override // com.netease.yunxin.artemis.Network.a
        public /* synthetic */ void a(HttpURLConnection httpURLConnection) {
            a.CC.$default$a(this, httpURLConnection);
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public /* synthetic */ byte[] a(InputStream inputStream, long j) {
            return a.CC.$default$a(this, inputStream, j);
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
        public void onSuccess(int i, URL url, Map<String, List<String>> map, byte[] bArr) {
            String a;
            f.a("artemis pull task onSuccess");
            c a2 = c.a();
            try {
                a = d.a("4r7yr457rfn57fntyfh8756ty675t43yfh64", new JSONObject(new String(bArr)).optString("data"));
            } catch (Throwable unused) {
                c.b();
            }
            if (a != null && !a.isEmpty()) {
                JSONObject jSONObject = new JSONObject(a);
                String optString = jSONObject.optString("client_ipv4");
                String optString2 = jSONObject.optString("client_ipv6");
                String optString3 = jSONObject.optString("requestId");
                long max = Math.max(System.currentTimeMillis() + 1800000, Math.min(System.currentTimeMillis() + 2592000000L, Long.parseLong(jSONObject.optString("next_fetch_time"))));
                g.a().a(String.valueOf(max));
                YXArtemisPullTask.getInstance().setNextFetchTime(new Date(max));
                JSONArray jSONArray = jSONObject.getJSONArray("task");
                int i2 = 0;
                while (i2 < jSONArray.length()) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    String optString4 = jSONObject2.optString("id");
                    int i3 = jSONObject2.getInt("task_type");
                    JSONObject jSONObject3 = new JSONObject(jSONObject2.optString("params"));
                    String optString5 = jSONObject2.optString("task_start");
                    String optString6 = jSONObject2.optString("task_end");
                    int i4 = jSONObject2.getInt("timeout");
                    String optString7 = jSONObject2.optString("report_addr");
                    Date date = new Date();
                    JSONArray jSONArray2 = jSONArray;
                    String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    String concat = format.concat(" ").concat(optString5);
                    String concat2 = format.concat(" ").concat(optString6);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date parse = simpleDateFormat.parse(concat);
                    Date parse2 = simpleDateFormat.parse(concat2);
                    if (parse2 != null && parse != null && (date.compareTo(parse) < 0 || parse2.compareTo(date) < 0)) {
                        new StringBuilder("不在时间窗口内,任务数：").append(a2.a.size());
                        a2.a(YXArtemisPullTask.mIdlePullTask);
                        break;
                    }
                    Class<?> cls = Class.forName(c.b.get(Integer.valueOf(i3)));
                    if (cls != null) {
                        a2.a.add((b) cls.getConstructor(String.class, String.class, String.class, Integer.TYPE, JSONObject.class, String.class, String.class, Integer.TYPE, String.class, Context.class, String.class, YXArtemisRunTaskCallback.class).newInstance(optString, optString2, optString4, Integer.valueOf(i3), jSONObject3, concat, concat2, Integer.valueOf(i4), optString7, a2.c, optString3, null));
                    }
                    i2++;
                    jSONArray = jSONArray2;
                }
                new StringBuilder("任务解析完毕,任务数：").append(a2.a.size());
                a2.a(a2.f);
                c.a().a(YXArtemisPullTask.mIdlePullTask);
            }
            c.b();
            c.a().a(YXArtemisPullTask.mIdlePullTask);
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public void onFailure(int i, Map<String, List<String>> map, byte[] bArr) {
            f.a("artemis pull task onFailure, statusCode:".concat(String.valueOf(i)));
            try {
                Thread.sleep(600000L);
            } catch (InterruptedException unused) {
            }
            c.a().a(YXArtemisPullTask.mIdlePullTask);
        }

        @Override // com.netease.yunxin.artemis.Network.a
        public void onException(Throwable th) {
            f.a("artemis pull task onException, throwable:" + th.getMessage());
        }
    };

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void aggregateResult() {
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    private YXArtemisPullTask() {
        g a = g.a();
        new HashMap();
        String string = a.a.getSharedPreferences("probe_rec", 0).getString("next_fetch_time", Constants.NULL_VERSION_ID);
        if (com.netease.yunxin.artemis.a.c.g) {
            setDate(new Date());
        } else if (string.equals(Constants.NULL_VERSION_ID)) {
            setDate(new Date());
        } else {
            setDate(new Date(Long.parseLong(string)));
        }
    }

    public static YXArtemisPullTask getInstance() {
        if (instance == null) {
            instance = new YXArtemisPullTask();
        }
        return instance;
    }

    public void setNextFetchTime(Date date) {
        instance.setDate(date);
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) com.netease.yunxin.artemis.Network.b.a().a.getSystemService("connectivity");
        if (!(connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED)) {
            try {
                Thread.sleep(20000L);
                c.a().a(mIdlePullTask);
                return;
            } catch (InterruptedException unused) {
                return;
            }
        }
        new StringBuilder("PullTask delay:").append(getDelay());
        com.netease.yunxin.artemis.Network.c cVar = new com.netease.yunxin.artemis.Network.c();
        cVar.a(com.alipay.sdk.m.h.b.h, com.netease.yunxin.artemis.a.c.a == null ? "" : com.netease.yunxin.artemis.a.c.a);
        cVar.a("device_id", com.netease.yunxin.artemis.a.c.e == null ? "" : com.netease.yunxin.artemis.a.c.e);
        cVar.a("eid", com.netease.yunxin.artemis.a.c.f);
        cVar.a("network_type", com.netease.yunxin.artemis.a.c.b == null ? "" : com.netease.yunxin.artemis.a.c.b);
        cVar.a("os_version", "Android" + Build.VERSION.SDK_INT);
        cVar.a("platform", "Android");
        cVar.a("sdk_type", com.netease.yunxin.artemis.a.c.d == null ? "" : com.netease.yunxin.artemis.a.c.d);
        cVar.a("sdk_ver", com.netease.yunxin.artemis.a.c.c != null ? com.netease.yunxin.artemis.a.c.c : "");
        HashMap<String, String> hashMap = new HashMap<>();
        String valueOf = String.valueOf(this.random.nextInt(1000));
        String valueOf2 = String.valueOf(System.currentTimeMillis() / 1000);
        String a = com.netease.yunxin.artemis.a.b.a("2ebae1de6a438", valueOf, valueOf2);
        hashMap.put("AuthKey", "b167f75a566c403d8e9ac33d311a6b7c");
        hashMap.put("Nonce", valueOf);
        hashMap.put("CurTime", valueOf2);
        hashMap.put("CheckSum", a);
        com.netease.yunxin.artemis.Network.b.a().a("https://change-api.netease.im/change-api/sdk/task/action/batch", cVar, this.handlePullTask, hashMap);
    }
}
