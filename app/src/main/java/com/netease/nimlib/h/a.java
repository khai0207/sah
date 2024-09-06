package com.netease.nimlib.h;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.netease.nim.highavailable.FCSChannelResponseCallback;
import com.netease.nim.highavailable.FCSCustomAuthTokenCallback;
import com.netease.nim.highavailable.HighAvailableEnvironmentSettings;
import com.netease.nim.highavailable.HighAvailableExceptionCallback;
import com.netease.nim.highavailable.HighAvailableFCSCallback;
import com.netease.nim.highavailable.HighAvailableFCSService;
import com.netease.nim.highavailable.HighAvailableLBSCallback;
import com.netease.nim.highavailable.HighAvailableLBSService;
import com.netease.nim.highavailable.HighAvailableLogCallback;
import com.netease.nim.highavailable.HighAvailableNetworkCallback;
import com.netease.nim.highavailable.HighAvailableNetworkCommunicator;
import com.netease.nim.highavailable.HighAvailableObject;
import com.netease.nim.highavailable.HighAvailableObjectSettings;
import com.netease.nim.highavailable.HighAvailableVoidCallback;
import com.netease.nim.highavailable.enums.HAvailableAuthState;
import com.netease.nim.highavailable.enums.HAvailableFCSChannelFunID;
import com.netease.nimlib.c;
import com.netease.nimlib.d.g;
import com.netease.nimlib.h;
import com.netease.nimlib.ipc.e;
import com.netease.nimlib.n.r;
import com.netease.nimlib.o.b.b;
import com.netease.nimlib.o.f;
import com.netease.nimlib.o.p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: HighAvailableObjectManager.java */
/* loaded from: classes.dex */
public class a {
    private static final a d = new a();
    private volatile boolean a = false;
    private volatile boolean b = false;
    private volatile boolean c = false;
    private HighAvailableObject e;
    private HighAvailableNetworkCommunicator f;
    private HighAvailableFCSService g;
    private HighAvailableLBSService h;

    public static a a() {
        return d;
    }

    private synchronized void a(final HighAvailableVoidCallback highAvailableVoidCallback) {
        if (this.a) {
            highAvailableVoidCallback.onCallBack();
            return;
        }
        HighAvailableObject.setContext(c.e());
        if (!HighAvailableObject.isLoadLibSuccess()) {
            highAvailableVoidCallback.onCallBack();
            return;
        }
        String b = com.netease.nimlib.o.b.c.b(b.TYPE_LOG);
        HighAvailableObject.startHighAvailableEnvironment(new HighAvailableEnvironmentSettings(1, "im_g1", b + "high_available", b + "high_available"), new HighAvailableLogCallback() { // from class: com.netease.nimlib.h.a.1
            @Override // com.netease.nim.highavailable.HighAvailableLogCallback
            public void onLog(String str) {
                com.netease.nimlib.log.b.K(str);
            }
        }, new HighAvailableVoidCallback() { // from class: com.netease.nimlib.h.a.2
            @Override // com.netease.nim.highavailable.HighAvailableVoidCallback
            public void onCallBack() {
                a.this.a = true;
                com.netease.nimlib.log.b.c("HighAvailableObjectManager", "startHighAvailableEnvironment onCallBack,SDKCache.getAppKey() = " + c.g());
                a.this.e = HighAvailableObject.createHAvailableObject(new HighAvailableObjectSettings(c.g(), "9.17.0", 91700, 1, 1));
                a aVar = a.this;
                aVar.f = aVar.e.getHighAvailableNetworkCommunicator();
                a.this.f.setConnectionTypeQuery(new HighAvailableNetworkCallback() { // from class: com.netease.nimlib.h.a.2.1
                    @Override // com.netease.nim.highavailable.HighAvailableNetworkCallback
                    public int queryConnectionType() {
                        HighAvailableNetworkCommunicator.ConnectionType connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_UNKNOWN;
                        if (p.f(c.e())) {
                            connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_WIFI;
                        } else if (p.e(c.e())) {
                            connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_XG;
                        } else if (!p.c(c.e())) {
                            connectionType = HighAvailableNetworkCommunicator.ConnectionType.CONNECTION_NONE;
                        }
                        return connectionType.ordinal();
                    }
                });
                HighAvailableVoidCallback highAvailableVoidCallback2 = highAvailableVoidCallback;
                if (highAvailableVoidCallback2 != null) {
                    highAvailableVoidCallback2.onCallBack();
                }
            }
        }, new HighAvailableExceptionCallback() { // from class: com.netease.nimlib.h.a.3
            @Override // com.netease.nim.highavailable.HighAvailableExceptionCallback
            public void onExceptionDataReport(String str) {
                com.netease.nimlib.log.b.G("onExceptionDataReport json = " + str);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    if (h.h()) {
                        r.a().a(new JSONObject(str));
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        JSONObject jSONObject2 = new JSONObject(str);
                        jSONObject.put("eventKey", "exceptions");
                        jSONObject.put(NotificationCompat.CATEGORY_EVENT, jSONObject2);
                        e.b(jSONObject.toString());
                    }
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.e("HighAvailableObjectManager", "onExceptionDataReport error", th);
                }
            }
        });
    }

    public HighAvailableObject b() {
        return this.e;
    }

    public boolean c() {
        return this.b;
    }

    public void a(final HighAvailableLBSCallback highAvailableLBSCallback) {
        if (!this.a) {
            a(new HighAvailableVoidCallback() { // from class: com.netease.nimlib.h.a.4
                @Override // com.netease.nim.highavailable.HighAvailableVoidCallback
                public void onCallBack() {
                    a.this.b(highAvailableLBSCallback);
                }
            });
        } else {
            b(highAvailableLBSCallback);
        }
    }

    public HighAvailableLBSService d() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final HighAvailableLBSCallback highAvailableLBSCallback) {
        String a = g.a();
        List b = g.b();
        if (f.c((Collection) b)) {
            if (b == null) {
                b = new ArrayList();
            }
            b.add(a);
        }
        List list = b;
        String e = g.e();
        List f = g.f();
        if (f.c((Collection) f)) {
            if (f == null) {
                f = new ArrayList();
            }
            f.add(e);
        }
        List list2 = f;
        String g = g.g();
        HighAvailableLBSService.AddressFamily addressFamily = com.netease.nimlib.push.net.lbs.a.a().toAddressFamily();
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "usePrivate = true");
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "lbsMain = " + a);
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "lbsBackup = " + list);
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "defaultLink = " + e);
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "defaultLinkIpv6 = " + g);
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "linkBackup = " + list2);
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", "addressFamily = " + addressFamily);
        HighAvailableLBSService highAvailableLBSService = this.e.getHighAvailableLBSService();
        this.h = highAvailableLBSService;
        highAvailableLBSService.init(new HighAvailableLBSService.HighAvailableLBSSettings(true, a, list, e, g, list2, addressFamily, HighAvailableLBSService.LinkVersionType.Normal), new HighAvailableLBSCallback() { // from class: com.netease.nimlib.h.a.5
            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onInitCallback(boolean z) {
                a.this.b = true;
                HighAvailableLBSCallback highAvailableLBSCallback2 = highAvailableLBSCallback;
                if (highAvailableLBSCallback2 != null) {
                    highAvailableLBSCallback2.onInitCallback(z);
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public String getAccid() {
                HighAvailableLBSCallback highAvailableLBSCallback2 = highAvailableLBSCallback;
                return highAvailableLBSCallback2 != null ? highAvailableLBSCallback2.getAccid() : "";
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onUpdate(int i, String str) {
                HighAvailableLBSCallback highAvailableLBSCallback2 = highAvailableLBSCallback;
                if (highAvailableLBSCallback2 != null) {
                    highAvailableLBSCallback2.onUpdate(i, str);
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onRequestError(int i, String str, String str2, String str3, long j, long j2) {
                HighAvailableLBSCallback highAvailableLBSCallback2 = highAvailableLBSCallback;
                if (highAvailableLBSCallback2 != null) {
                    highAvailableLBSCallback2.onRequestError(i, str, str2, str3, j, j2);
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public HAvailableAuthState getAuthState() {
                HighAvailableLBSCallback highAvailableLBSCallback2 = highAvailableLBSCallback;
                if (highAvailableLBSCallback2 != null) {
                    return highAvailableLBSCallback2.getAuthState();
                }
                return HAvailableAuthState.kAuthState_UnLogin;
            }

            @Override // com.netease.nim.highavailable.HighAvailableLBSCallback
            public void onSingleRequestTrackerReport(String str) {
                HighAvailableLBSCallback highAvailableLBSCallback2 = highAvailableLBSCallback;
                if (highAvailableLBSCallback2 != null) {
                    highAvailableLBSCallback2.onSingleRequestTrackerReport(str);
                }
            }
        });
    }

    public boolean e() {
        return this.c;
    }

    public void a(final HighAvailableFCSCallback highAvailableFCSCallback) {
        if (!this.a) {
            a(new HighAvailableVoidCallback() { // from class: com.netease.nimlib.h.a.6
                @Override // com.netease.nim.highavailable.HighAvailableVoidCallback
                public void onCallBack() {
                    a.this.b(highAvailableFCSCallback);
                }
            });
        } else {
            b(highAvailableFCSCallback);
        }
    }

    public HighAvailableFCSService f() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final HighAvailableFCSCallback highAvailableFCSCallback) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Long> entry : c.C().getNosTokenScene().entrySet()) {
            arrayList.add(new HighAvailableFCSService.HighAvailableUploadTag(entry.getKey(), entry.getValue().longValue()));
        }
        String g = g();
        com.netease.nimlib.log.b.c("HighAvailableObjectManager", " loadCaFile caFile = " + g);
        String b = com.netease.nimlib.o.b.c.b(b.TYPE_LOG);
        HighAvailableFCSService highAvailableFCSService = this.e.getHighAvailableFCSService();
        this.g = highAvailableFCSService;
        highAvailableFCSService.init(new HighAvailableFCSService.HighAvailableFCSSettings(g, "png", b + "high_available", arrayList, g.h(), null, null, com.netease.nimlib.net.a.b.d.a.a ? HighAvailableFCSService.Scheme.kHTTPS : HighAvailableFCSService.Scheme.kHTTP), new HighAvailableFCSCallback() { // from class: com.netease.nimlib.h.a.7
            @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
            public void onInitCallback(boolean z) {
                a.this.c = true;
                HighAvailableFCSCallback highAvailableFCSCallback2 = highAvailableFCSCallback;
                if (highAvailableFCSCallback2 != null) {
                    highAvailableFCSCallback2.onInitCallback(z);
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
            public void fcsChannelRequest(HAvailableFCSChannelFunID hAvailableFCSChannelFunID, int i, long j, byte[] bArr, FCSChannelResponseCallback fCSChannelResponseCallback) {
                HighAvailableFCSCallback highAvailableFCSCallback2 = highAvailableFCSCallback;
                if (highAvailableFCSCallback2 != null) {
                    highAvailableFCSCallback2.fcsChannelRequest(hAvailableFCSChannelFunID, i, j, bArr, fCSChannelResponseCallback);
                }
            }

            @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
            public void getCustomAuthToken(String str, FCSCustomAuthTokenCallback fCSCustomAuthTokenCallback) {
                HighAvailableFCSCallback highAvailableFCSCallback2 = highAvailableFCSCallback;
                if (highAvailableFCSCallback2 != null) {
                    highAvailableFCSCallback2.getCustomAuthToken(str, fCSCustomAuthTokenCallback);
                }
            }
        });
    }

    private String g() {
        Context e = c.e();
        if (e != null) {
            try {
                File file = new File(e.getFilesDir(), "nim");
                File file2 = new File(file, "cacert");
                if (file2.exists()) {
                    return file2.getPath();
                }
                if (!file.exists()) {
                    file.mkdirs();
                }
                try {
                    InputStream open = e.getAssets().open("nim/cacert");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = open.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                a(bArr, read);
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.flush();
                            String path = file2.getPath();
                            if (open != null) {
                                open.close();
                            }
                            return path;
                        } finally {
                        }
                    } finally {
                    }
                } catch (Exception e2) {
                    com.netease.nimlib.log.b.f("HighAvailableObjectManager", "loadCaFile exception = " + e2);
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private void a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ 101);
        }
    }
}
