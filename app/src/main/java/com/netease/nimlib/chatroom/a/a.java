package com.netease.nimlib.chatroom.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.netease.nimlib.c;
import com.netease.nimlib.chatroom.e;
import com.netease.nimlib.n.b.g;
import com.netease.nimlib.net.a.d.a;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomCdnInfo;
import com.netease.nimlib.session.IMMessageImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CdnHandler.java */
/* loaded from: classes.dex */
public abstract class a {
    private String a;
    private long b;
    private ChatRoomCdnInfo c;
    private long d;
    private ScheduledExecutorService e;
    private HandlerThread i;
    private Handler j;
    private Runnable k;
    private long f = 0;
    private int g = 0;
    private final Object l = new Object();
    private final List<IMMessageImpl> h = new ArrayList(1024);

    abstract void a(String str, boolean z, int i);

    abstract void a(boolean z);

    abstract void e();

    a(String str, ChatRoomCdnInfo chatRoomCdnInfo) {
        this.a = str;
        HandlerThread handlerThread = new HandlerThread("CdnHandler" + str);
        this.i = handlerThread;
        handlerThread.start();
        this.j = new Handler(this.i.getLooper());
        a(chatRoomCdnInfo);
    }

    void a(ChatRoomCdnInfo chatRoomCdnInfo) {
        ChatRoomCdnInfo chatRoomCdnInfo2 = this.c;
        this.c = chatRoomCdnInfo == null ? chatRoomCdnInfo2 : chatRoomCdnInfo;
        com.netease.nimlib.log.b.d("CdnHandler", "update cdn info, interval=" + this.c.getPollingInterval());
        b();
        if (this.c.isEnable()) {
            if (chatRoomCdnInfo != null && chatRoomCdnInfo.getTimestamp() > 0) {
                a(this.c.getTimestamp());
            } else {
                this.c.setTimestamp(chatRoomCdnInfo2.getTimestamp());
            }
            int timeOut = this.c.getTimeOut();
            if (timeOut <= 0) {
                timeOut = (int) (((this.c.getPollingInterval() + 1000) / 2000) * 1000);
            }
            this.c.setTimeOut(timeOut);
            g();
        }
    }

    public void a(long j) {
        this.d = SystemClock.elapsedRealtime() - j;
    }

    private boolean f() {
        return a((Long) null);
    }

    private boolean a(Long l) {
        JSONObject jSONObject;
        long j;
        String b = b(l);
        com.netease.nimlib.log.b.d("CdnHandler", "pullMsg, url=" + b + ", urlTime=" + l);
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        a.C0047a<String> a = a(b);
        if (a == null) {
            return false;
        }
        if (a.a != 200) {
            com.netease.nimlib.log.b.d("CdnHandler", "failed to pull msg, obj=" + a.c + ", code=" + a.a + ", e=" + a.b);
            if (a(a)) {
                this.f = i();
            }
            return false;
        }
        JSONArray jSONArray = null;
        try {
            jSONObject = new JSONObject(a.c);
            try {
                long optInt = jSONObject.optInt("c") * 1000;
                if (optInt > 0) {
                    j = optInt / 300;
                } else if (this.b > 0) {
                    j = this.b;
                } else {
                    Double.isNaN(this.c.getPollingInterval());
                    j = ((int) (r5 * 1.2d)) / 300;
                }
                this.b = j;
                jSONArray = a(jSONObject);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            jSONObject = null;
        }
        if (jSONArray == null) {
            return true;
        }
        b(a(jSONArray));
        if (jSONObject != null && jSONObject.has("pis")) {
            a(jSONObject.optInt("ptm"), jSONObject.optLong("pis") * 1000);
        }
        return true;
    }

    private void a(int i, long j) {
        com.netease.nimlib.log.b.d("CdnHandler", String.format("checkAndUpdatePollingProcess, newPtm: %s, newPis: %s", Integer.valueOf(i), Long.valueOf(j)));
        com.netease.nimlib.c.b.a.b(c.e()).post(new Runnable() { // from class: com.netease.nimlib.chatroom.a.-$$Lambda$a$g70XpAIHW6RyQnM0HUbPAhI1yeQ
            private final /* synthetic */ int f$1;
            private final /* synthetic */ long f$2;

            public /* synthetic */ $$Lambda$a$g70XpAIHW6RyQnM0HUbPAhI1yeQ(int i2, long j2) {
                r2 = i2;
                r3 = j2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.b(r2, r3);
            }
        });
    }

    public /* synthetic */ void b(int i, long j) {
        if (i > 0) {
            this.c.setTimeOut(i);
        }
        if (j <= 0 || j == this.c.getPollingInterval()) {
            return;
        }
        ChatRoomCdnInfo deepClone = this.c.deepClone();
        deepClone.setPollingInterval(j);
        a(deepClone);
    }

    private a.C0047a<String> a(String str) {
        String host;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        a.C0047a<String> a = com.netease.nimlib.net.a.d.a.a(str, (Map<String, String>) null, Integer.valueOf(this.c.getTimeOut()), (Object) g.CDN);
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        try {
            host = new URL(str).getHost();
        } catch (MalformedURLException unused) {
        }
        if (TextUtils.isEmpty(host)) {
            return a;
        }
        a(host, a.a == 200, (int) elapsedRealtime2);
        return a;
    }

    private boolean a(a.C0047a<String> c0047a) {
        if (c0047a == null || c0047a.a != 404) {
            return false;
        }
        String str = c0047a.c;
        if (!TextUtils.isEmpty(str)) {
            try {
                long optLong = new JSONObject(str).optLong(com.alipay.sdk.m.p.a.k);
                if (optLong > 0) {
                    a(optLong);
                    return true;
                }
            } catch (JSONException unused) {
            }
        }
        long a = y.a(c0047a.d, 0L);
        if (a <= 0) {
            return false;
        }
        a(a);
        return true;
    }

    private JSONArray a(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.optBoolean("e")) {
            return new JSONArray(jSONObject.getString("data"));
        }
        return new JSONArray(new String(e.a(Base64.decode(jSONObject.getString("data"), 0), Base64.decode(this.c.getDecryptKey(), 0))));
    }

    private void a(List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        com.netease.nimlib.chatroom.g.a((ArrayList<IMMessageImpl>) new ArrayList(list));
        com.netease.nimlib.chatroom.c.a().u(this.a).b(list);
    }

    private void b(List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.j.removeCallbacks(this.k);
        synchronized (this.h) {
            this.h.clear();
            this.h.addAll(list);
        }
        int size = (int) (((this.h.size() - 1) / this.b) + 1);
        com.netease.nimlib.log.b.d("CdnHandler", "doShardCallback, step=" + size);
        a(0, size, 0L);
    }

    private void a(int i, int i2, long j) {
        this.k = new Runnable() { // from class: com.netease.nimlib.chatroom.a.-$$Lambda$a$fRDGOB_yEokWoCLB3NCdP6wIWu4
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;

            public /* synthetic */ $$Lambda$a$fRDGOB_yEokWoCLB3NCdP6wIWu4(int i3, int i22) {
                r2 = i3;
                r3 = i22;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.a(r2, r3);
            }
        };
        if (this.j != null) {
            synchronized (this.l) {
                if (this.j != null) {
                    this.j.postDelayed(this.k, j);
                }
            }
        }
    }

    public /* synthetic */ void a(int i, int i2) {
        synchronized (this.h) {
            if (i < this.h.size() && i >= 0) {
                int min = Math.min(this.h.size(), i + i2);
                a(this.h.subList(i, min));
                a(min, i2, 300L);
            }
        }
    }

    public void a() {
        HandlerThread handlerThread = this.i;
        if (handlerThread == null) {
            return;
        }
        handlerThread.quit();
        this.i = null;
        if (this.j == null) {
            return;
        }
        synchronized (this.l) {
            this.j.removeCallbacks(this.k);
            this.j = null;
        }
    }

    private void g() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
        this.e = newScheduledThreadPool;
        newScheduledThreadPool.scheduleAtFixedRate(new Runnable() { // from class: com.netease.nimlib.chatroom.a.-$$Lambda$a$GQ17a0f1L7SR4zkvtNB1enY3bD0
            public /* synthetic */ $$Lambda$a$GQ17a0f1L7SR4zkvtNB1enY3bD0() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.j();
            }
        }, 0L, this.c.getPollingInterval(), TimeUnit.MILLISECONDS);
        a(true);
    }

    public /* synthetic */ void j() {
        boolean f = f();
        String[] cdnUrlArray = this.c.getCdnUrlArray();
        int i = 0;
        int min = Math.min(2, cdnUrlArray == null ? 0 : cdnUrlArray.length);
        while (!f && i < min) {
            long h = h();
            long j = this.f;
            if (h > j) {
                break;
            }
            i++;
            f = a(Long.valueOf(j));
        }
        if (f) {
            return;
        }
        e();
    }

    public void b() {
        ScheduledExecutorService scheduledExecutorService = this.e;
        if (scheduledExecutorService == null) {
            return;
        }
        if (!scheduledExecutorService.isShutdown()) {
            try {
                this.e.shutdown();
            } catch (Throwable unused) {
            }
        }
        this.e = null;
        a(false);
    }

    public void c() {
        b();
        a();
    }

    private String b(Long l) {
        String[] cdnUrlArray = this.c.getCdnUrlArray();
        if (cdnUrlArray == null || cdnUrlArray.length == 0) {
            com.netease.nimlib.log.b.e("CdnHandler", "info is null when calculateNextUrlTime");
            return "";
        }
        this.g %= cdnUrlArray.length;
        long h = l == null ? h() : l.longValue();
        if (l == null && h <= this.f) {
            return "";
        }
        this.f = h;
        int i = this.g;
        this.g = i + 1;
        return cdnUrlArray[i].replace("#time", String.valueOf(h));
    }

    private long h() {
        if (this.c == null) {
            com.netease.nimlib.log.b.e("CdnHandler", "info is null when calculateNextUrlTime");
            return 0L;
        }
        long i = i();
        long pollingInterval = this.c.getPollingInterval();
        return (i / pollingInterval) * pollingInterval;
    }

    private long i() {
        return SystemClock.elapsedRealtime() - this.d;
    }

    private List<IMMessageImpl> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList(0);
        }
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject == null) {
                    com.netease.nimlib.log.b.d("CdnHandler", "empty msg from json array, roomId=" + this.a);
                } else {
                    IMMessageImpl a = com.netease.nimlib.chatroom.g.a(jSONObject, true);
                    if (a != null) {
                        com.netease.nimlib.chatroom.c.a().u(this.a).a(a.getUuid());
                        arrayList.add(a);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    ChatRoomCdnInfo d() {
        return this.c.deepClone();
    }
}
