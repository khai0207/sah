package com.netease.nimlib.push.net.lbs;

import android.os.Handler;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.netease.nim.highavailable.HighAvailableLBSCallback;
import com.netease.nim.highavailable.HighAvailableLBSLinkAddressCb;
import com.netease.nim.highavailable.HighAvailableLBSService;
import com.netease.nim.highavailable.HighAvailableNetworkCommunicator;
import com.netease.nim.highavailable.HighAvailableObject;
import com.netease.nim.highavailable.NativeHighAvailableGetLbsResponseCallback;
import com.netease.nim.highavailable.enums.HAvailableAuthState;
import com.netease.nimlib.d.g;
import com.netease.nimlib.h;
import com.netease.nimlib.n.j;
import com.netease.nimlib.n.l;
import com.netease.nimlib.n.q;
import com.netease.nimlib.o.k;
import com.netease.nimlib.o.p;
import com.netease.nimlib.push.f;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.util.NIMUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MainLinkLbsPush.java */
/* loaded from: classes.dex */
public class c {
    private static final c h = new c();
    private static Semaphore j = new Semaphore(1);
    private d a;
    private HighAvailableObject b;
    private HighAvailableLBSService c;
    private d d;
    private String e;
    private volatile HighAvailableLBSService.HighAvailableLinkAddress f;
    private Handler g = com.netease.nimlib.c.b.a.c().a("lbs_handler");
    private List<NativeHighAvailableGetLbsResponseCallback> i = new ArrayList();

    public static c a() {
        return h;
    }

    private c() {
        String[] f = f(o());
        String[] a = a(f(n()));
        this.a = new d("IM_LINK", f, a, 1);
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "load cached LBS link address, links count=" + f.length + ", def links count=" + a.length);
        String[] q = q();
        this.d = new d("NOS_DL", q, null, 5);
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "load cached nosdl address, links count=" + q.length);
    }

    public synchronized String b() {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "getNosdlAddress (sync)");
        return this.d.b();
    }

    public void a(com.netease.nimlib.c.a<b> aVar) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "getLinkAddress (sync)");
        j.a().b();
        b(new $$Lambda$c$m2RiDEIaAEq7ogvTwTzjNYaKnk0(this, aVar));
    }

    public /* synthetic */ void a(com.netease.nimlib.c.a aVar, Boolean bool) {
        a(bool, (com.netease.nimlib.c.a<b>) aVar);
    }

    private void a(Boolean bool, com.netease.nimlib.c.a<b> aVar) {
        if (bool == null || !bool.booleanValue()) {
            if (this.f != null) {
                if (aVar != null) {
                    aVar.onCallback(new b(this.f.getSn(), this.f.getIp(), this.f.getPort()));
                    return;
                }
                return;
            }
            IPVersion e = a.e();
            String g = e == IPVersion.IPV6 ? g.g() : g.e();
            if (aVar != null) {
                b bVar = new b(g);
                this.f = new HighAvailableLBSService.HighAvailableLinkAddress(bVar.a, bVar.b, bVar.c, (e == IPVersion.IPV6 ? HighAvailableLBSService.AddressFamily.kIPV6 : HighAvailableLBSService.AddressFamily.kIPV4).ordinal());
                aVar.onCallback(bVar);
                return;
            }
            return;
        }
        a(com.netease.nimlib.c.g());
        a(false);
        IPVersion d = a.d();
        IPVersion e2 = a.e();
        this.c.getLinkAddress(e2.toAddressFamily(), new HighAvailableLBSLinkAddressCb() { // from class: com.netease.nimlib.push.net.lbs.c.1
            final /* synthetic */ IPVersion a;
            final /* synthetic */ IPVersion b;
            final /* synthetic */ com.netease.nimlib.c.a c;

            AnonymousClass1(IPVersion d2, IPVersion e22, com.netease.nimlib.c.a aVar2) {
                r2 = d2;
                r3 = e22;
                r4 = aVar2;
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSLinkAddressCb
            public void onGetLinkAddressCallBack(HighAvailableLBSService.HighAvailableLinkAddress highAvailableLinkAddress) {
                com.netease.nimlib.log.b.d("MainLinkLbsPush", "get link address: " + highAvailableLinkAddress);
                c.this.f = highAvailableLinkAddress;
                if (r2 == IPVersion.IPV6_FIRST && r3 == IPVersion.IPV6 && !com.netease.nimlib.net.a.c.b.a(highAvailableLinkAddress.getIp())) {
                    highAvailableLinkAddress.setIp(com.netease.nimlib.net.a.c.b.b(highAvailableLinkAddress.getIp()));
                }
                com.netease.nimlib.c.a aVar2 = r4;
                if (aVar2 != null) {
                    aVar2.onCallback(new b(c.this.f.getSn(), c.this.f.getIp(), c.this.f.getPort()));
                }
            }
        });
    }

    /* compiled from: MainLinkLbsPush.java */
    /* renamed from: com.netease.nimlib.push.net.lbs.c$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements HighAvailableLBSLinkAddressCb {
        final /* synthetic */ IPVersion a;
        final /* synthetic */ IPVersion b;
        final /* synthetic */ com.netease.nimlib.c.a c;

        AnonymousClass1(IPVersion d2, IPVersion e22, com.netease.nimlib.c.a aVar2) {
            r2 = d2;
            r3 = e22;
            r4 = aVar2;
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSLinkAddressCb
        public void onGetLinkAddressCallBack(HighAvailableLBSService.HighAvailableLinkAddress highAvailableLinkAddress) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "get link address: " + highAvailableLinkAddress);
            c.this.f = highAvailableLinkAddress;
            if (r2 == IPVersion.IPV6_FIRST && r3 == IPVersion.IPV6 && !com.netease.nimlib.net.a.c.b.a(highAvailableLinkAddress.getIp())) {
                highAvailableLinkAddress.setIp(com.netease.nimlib.net.a.c.b.b(highAvailableLinkAddress.getIp()));
            }
            com.netease.nimlib.c.a aVar2 = r4;
            if (aVar2 != null) {
                aVar2.onCallback(new b(c.this.f.getSn(), c.this.f.getIp(), c.this.f.getPort()));
            }
        }
    }

    public HighAvailableLBSService.HighAvailableLinkAddress c() {
        return this.f;
    }

    public String d() {
        return this.e;
    }

    public synchronized void e() {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "changeNosDL (sync)");
        if (f.l().f()) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "change nosdl, current ServerData=" + this.d + ", move to next");
            if (!this.d.a()) {
                com.netease.nimlib.log.b.d("MainLinkLbsPush", "nosdl has used up!!!");
                f();
            }
        } else {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "cancel change nosdl, as APP is on background");
        }
    }

    public synchronized void f() {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "reset all, should fetch LBS... (sync)");
        g();
    }

    public void g() {
        b(new $$Lambda$c$fI7zsazJxz7exsgMhNnfARB_5rw(this));
    }

    public /* synthetic */ void b(Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        a(false);
    }

    public synchronized void a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("fetch LBS ");
        sb.append(z ? "right now" : "in background (sync)");
        com.netease.nimlib.log.b.d("MainLinkLbsPush", sb.toString());
        if (m()) {
            if (z) {
                b(this.c.getLBSResponse(false));
            } else {
                this.g.post(new Runnable() { // from class: com.netease.nimlib.push.net.lbs.-$$Lambda$c$fyycvEho47fY5oCVHUiYn4OM978
                    public /* synthetic */ $$Lambda$c$fyycvEho47fY5oCVHUiYn4OM978() {
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        c.this.r();
                    }
                });
            }
        }
    }

    public /* synthetic */ void r() {
        b(this.c.getLBSResponse(false));
    }

    public void h() {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "update LBS");
        b(new $$Lambda$c$rtClQoTPgF1ivIH7QcjMNsI7N6U(this));
    }

    public /* synthetic */ void a(Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        this.c.update(a.a().toAddressFamily());
    }

    public void a(NativeHighAvailableGetLbsResponseCallback nativeHighAvailableGetLbsResponseCallback) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "reg update response");
        b(new $$Lambda$c$6GIQVfk9SRxoH3WGTdNtoaUqnko(this, nativeHighAvailableGetLbsResponseCallback));
    }

    public /* synthetic */ void b(NativeHighAvailableGetLbsResponseCallback nativeHighAvailableGetLbsResponseCallback, Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        this.i.add(nativeHighAvailableGetLbsResponseCallback);
    }

    public void b(NativeHighAvailableGetLbsResponseCallback nativeHighAvailableGetLbsResponseCallback) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "unreg update response");
        b(new $$Lambda$c$1_R4XwUHt3LgMr5NLFQ8jwr7TiE(this, nativeHighAvailableGetLbsResponseCallback));
    }

    public /* synthetic */ void a(NativeHighAvailableGetLbsResponseCallback nativeHighAvailableGetLbsResponseCallback, Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        this.i.remove(nativeHighAvailableGetLbsResponseCallback);
    }

    private void b(com.netease.nimlib.c.a<Boolean> aVar) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "start init");
        if (com.netease.nimlib.h.a.a().c() && this.c != null) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "already init");
            if (aVar != null) {
                aVar.onCallback(true);
                return;
            }
            return;
        }
        if (!HighAvailableObject.isLoadLibSuccess()) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "init load lib failed");
            if (aVar != null) {
                aVar.onCallback(false);
                return;
            }
            return;
        }
        this.g.post(new Runnable() { // from class: com.netease.nimlib.push.net.lbs.c.2
            final /* synthetic */ com.netease.nimlib.c.a a;

            AnonymousClass2(com.netease.nimlib.c.a aVar2) {
                r2 = aVar2;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.c((com.netease.nimlib.c.a<Boolean>) r2);
            }
        });
    }

    /* compiled from: MainLinkLbsPush.java */
    /* renamed from: com.netease.nimlib.push.net.lbs.c$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ com.netease.nimlib.c.a a;

        AnonymousClass2(com.netease.nimlib.c.a aVar2) {
            r2 = aVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.c((com.netease.nimlib.c.a<Boolean>) r2);
        }
    }

    public void c(com.netease.nimlib.c.a<Boolean> aVar) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "start initPri");
        try {
            j.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (com.netease.nimlib.h.a.a().c() && this.c != null) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "initPri already init");
            j.release();
            if (aVar != null) {
                aVar.onCallback(true);
                return;
            }
            return;
        }
        if (!HighAvailableObject.isLoadLibSuccess()) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "initPri load lib failed");
            j.release();
            if (aVar != null) {
                aVar.onCallback(false);
                return;
            }
            return;
        }
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "start initLBSService");
        com.netease.nimlib.h.a.a().a(new HighAvailableLBSCallback() { // from class: com.netease.nimlib.push.net.lbs.c.3
            final /* synthetic */ com.netease.nimlib.c.a a;

            AnonymousClass3(com.netease.nimlib.c.a aVar2) {
                r2 = aVar2;
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onInitCallback(boolean z) {
                com.netease.nimlib.log.b.d("MainLinkLbsPush", "onInitCallback: result = " + z);
                c.this.b = com.netease.nimlib.h.a.a().b();
                c.this.c = com.netease.nimlib.h.a.a().d();
                c.j.release();
                com.netease.nimlib.c.a aVar2 = r2;
                if (aVar2 != null) {
                    aVar2.onCallback(Boolean.valueOf(z));
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public String getAccid() {
                return TextUtils.isEmpty(com.netease.nimlib.c.n()) ? "" : com.netease.nimlib.c.n();
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onUpdate(int i, String str) {
                if (c.this.i != null) {
                    Iterator it = c.this.i.iterator();
                    while (it.hasNext()) {
                        ((NativeHighAvailableGetLbsResponseCallback) it.next()).onGetLbsResponse(i, str);
                    }
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onRequestError(int i, String str, String str2, String str3, long j2, long j3) {
                com.netease.nimlib.log.b.G("reportError: code = " + i + ", url = " + str + ", head = " + str2 + ", body = " + str3);
                l.a().a(j.a().a(i, str, str3));
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public HAvailableAuthState getAuthState() {
                StatusCode e2 = h.e();
                if (e2 == StatusCode.LOGINED || e2 == StatusCode.SYNCING) {
                    return HAvailableAuthState.kAuthState_Logged;
                }
                if (e2 == StatusCode.CONNECTING || e2 == StatusCode.LOGINING || e2 == StatusCode.NEED_CHANGE_LBS || e2 == StatusCode.NEED_RECONNECT) {
                    return HAvailableAuthState.kAuthState_Logging;
                }
                return HAvailableAuthState.kAuthState_UnLogin;
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onSingleRequestTrackerReport(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    if (h.h()) {
                        q.a().b(new JSONObject(str));
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        JSONObject jSONObject2 = new JSONObject(str);
                        jSONObject.put("eventKey", "nim_sdk_lbs_records");
                        jSONObject.put(NotificationCompat.CATEGORY_EVENT, jSONObject2);
                        com.netease.nimlib.ipc.e.b(jSONObject.toString());
                    }
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.e("MainLinkLbsPush", "onSingleRequestTrackerReport error", th);
                }
            }
        });
    }

    /* compiled from: MainLinkLbsPush.java */
    /* renamed from: com.netease.nimlib.push.net.lbs.c$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements HighAvailableLBSCallback {
        final /* synthetic */ com.netease.nimlib.c.a a;

        AnonymousClass3(com.netease.nimlib.c.a aVar2) {
            r2 = aVar2;
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
        public void onInitCallback(boolean z) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "onInitCallback: result = " + z);
            c.this.b = com.netease.nimlib.h.a.a().b();
            c.this.c = com.netease.nimlib.h.a.a().d();
            c.j.release();
            com.netease.nimlib.c.a aVar2 = r2;
            if (aVar2 != null) {
                aVar2.onCallback(Boolean.valueOf(z));
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
        public String getAccid() {
            return TextUtils.isEmpty(com.netease.nimlib.c.n()) ? "" : com.netease.nimlib.c.n();
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
        public void onUpdate(int i, String str) {
            if (c.this.i != null) {
                Iterator it = c.this.i.iterator();
                while (it.hasNext()) {
                    ((NativeHighAvailableGetLbsResponseCallback) it.next()).onGetLbsResponse(i, str);
                }
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
        public void onRequestError(int i, String str, String str2, String str3, long j2, long j3) {
            com.netease.nimlib.log.b.G("reportError: code = " + i + ", url = " + str + ", head = " + str2 + ", body = " + str3);
            l.a().a(j.a().a(i, str, str3));
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
        public HAvailableAuthState getAuthState() {
            StatusCode e2 = h.e();
            if (e2 == StatusCode.LOGINED || e2 == StatusCode.SYNCING) {
                return HAvailableAuthState.kAuthState_Logged;
            }
            if (e2 == StatusCode.CONNECTING || e2 == StatusCode.LOGINING || e2 == StatusCode.NEED_CHANGE_LBS || e2 == StatusCode.NEED_RECONNECT) {
                return HAvailableAuthState.kAuthState_Logging;
            }
            return HAvailableAuthState.kAuthState_UnLogin;
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
        public void onSingleRequestTrackerReport(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                if (h.h()) {
                    q.a().b(new JSONObject(str));
                } else {
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject(str);
                    jSONObject.put("eventKey", "nim_sdk_lbs_records");
                    jSONObject.put(NotificationCompat.CATEGORY_EVENT, jSONObject2);
                    com.netease.nimlib.ipc.e.b(jSONObject.toString());
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("MainLinkLbsPush", "onSingleRequestTrackerReport error", th);
            }
        }
    }

    private synchronized boolean m() {
        if (NIMUtil.isMainProcess(com.netease.nimlib.c.e())) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "isInit, wrong process");
            return false;
        }
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "isInit lbsService = " + this.c);
        return this.c != null;
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject("common");
                if (jSONObject == null) {
                    return;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("nosdls");
                String str2 = "";
                e(optJSONArray != null ? optJSONArray.toString() : "");
                this.d.a(a(optJSONArray));
                JSONArray optJSONArray2 = jSONObject.optJSONArray("link");
                d(optJSONArray2 != null ? optJSONArray2.toString() : "");
                this.a.a(a(optJSONArray2));
                JSONArray optJSONArray3 = jSONObject.optJSONArray("link.default");
                c(optJSONArray3 != null ? optJSONArray3.toString() : "");
                this.a.b(a(a(optJSONArray3)));
                String[] a = a(jSONObject.optJSONArray("turns"));
                if (a != null && a.length > 0) {
                    for (int i = 0; i < a.length; i++) {
                        str2 = str2 + a[i];
                        if (i != a.length - 1) {
                            str2 = str2 + ";";
                        }
                    }
                    this.e = str2;
                    e.a().a(str2);
                }
                com.netease.nimlib.d.c.e().a(jSONObject.optJSONObject("c.aos"));
                com.netease.nimlib.log.b.d("MainLinkLbsPush", "update server address from LBS, links count=" + this.a.d() + ", def links count=" + this.a.c() + ", nosdl count=" + this.d.d());
                StringBuilder sb = new StringBuilder();
                sb.append("parse LBS json, origin content:");
                sb.append(str);
                com.netease.nimlib.log.b.d("MainLinkLbsPush", sb.toString());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.f("MainLinkLbsPush", "parse LBS json error " + e.getMessage() + " origin content:" + str);
                return;
            }
        }
        com.netease.nimlib.log.b.f("MainLinkLbsPush", "get server address from LBS failed, get null");
    }

    private String n() {
        return com.netease.nimlib.push.e.e();
    }

    private void c(String str) {
        com.netease.nimlib.push.e.c(str);
    }

    private String[] a(String[] strArr) {
        return (strArr == null || strArr.length == 0) ? new String[]{g.e()} : strArr;
    }

    private String o() {
        return com.netease.nimlib.push.e.d();
    }

    private void d(String str) {
        com.netease.nimlib.push.e.b(str);
    }

    public String[] i() {
        String[] f = f(o());
        String[] a = a(f(n()));
        String[] strArr = new String[f.length + a.length];
        for (int i = 0; i < f.length; i++) {
            strArr[i] = f[i];
        }
        for (int i2 = 0; i2 < a.length; i2++) {
            strArr[f.length + i2] = a[i2];
        }
        return strArr;
    }

    private String p() {
        return com.netease.nimlib.push.e.c();
    }

    private void e(String str) {
        com.netease.nimlib.push.e.a(str);
    }

    private String[] q() {
        return f(p());
    }

    private String[] f(String str) {
        JSONArray b;
        if (TextUtils.isEmpty(str) || (b = k.b(str)) == null) {
            return new String[0];
        }
        String[] strArr = new String[b.length()];
        for (int i = 0; i < b.length(); i++) {
            strArr[i] = k.b(b, i);
        }
        return strArr;
    }

    private String[] a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    public synchronized void a(b bVar) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", String.format("onConnected (sync) %s", bVar));
        if (bVar == null) {
            return;
        }
        b(new $$Lambda$c$Izfd8lUCxn9ut1epkQ3DoJUrC3Y(this, bVar));
    }

    public /* synthetic */ void a(b bVar, Boolean bool) {
        if (bool == null || !bool.booleanValue() || bVar.a()) {
            return;
        }
        this.c.notifyAddressSucceed(bVar.a);
    }

    public synchronized void j() {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "onNetworkAvailable (sync)");
        if (!m()) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "onNetworkAvailable but not init");
            return;
        }
        HighAvailableNetworkCommunicator.ConnectionType connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_UNKNOWN;
        if (p.f(com.netease.nimlib.c.e())) {
            connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_WIFI;
        } else if (p.e(com.netease.nimlib.c.e())) {
            connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_XG;
        }
        this.b.getHighAvailableNetworkCommunicator().notifyConnectionTypeChanged(connectionType);
    }

    public synchronized void k() {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "onNetworkUnavailable (sync)");
        if (!m()) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "onNetworkUnavailable but not init");
        } else {
            this.b.getHighAvailableNetworkCommunicator().notifyConnectionTypeChanged(HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_NONE);
        }
    }

    public synchronized void a(String str) {
        com.netease.nimlib.log.b.d("MainLinkLbsPush", "updateAppKey (sync): " + str);
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "updateAppKey but appKey is empty");
        } else if (!m()) {
            com.netease.nimlib.log.b.d("MainLinkLbsPush", "updateAppKey but not init");
        } else {
            this.b.updateAppKey(str);
        }
    }
}
