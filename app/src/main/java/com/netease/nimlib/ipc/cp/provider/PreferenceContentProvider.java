package com.netease.nimlib.ipc.cp.provider;

import android.content.Context;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.c;
import com.netease.nimlib.ipc.a.f;
import com.netease.nimlib.ipc.cp.b.a;
import com.netease.nimlib.ipc.cp.b.b;
import com.netease.nimlib.sdk.ReconnectStrategy;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PreferenceContentProvider extends AbsContentProvider {
    protected void a(String str, String str2, String str3) {
    }

    @Override // com.netease.nimlib.ipc.cp.provider.AbsContentProvider
    public b a(Context context, final String str) {
        if ("PARAMS".equals(str)) {
            return new a() { // from class: com.netease.nimlib.ipc.cp.provider.PreferenceContentProvider.1
                final SDKOptions a = c.i();

                @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
                public boolean a(String str2) {
                    return true;
                }

                /* JADX WARN: Code restructure failed: missing block: B:13:0x0040, code lost:
                
                    if (r0 != 2) goto L56;
                 */
                @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.String a(java.lang.String r11, java.lang.String r12) {
                    /*
                        Method dump skipped, instructions count: 306
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.ipc.cp.provider.PreferenceContentProvider.AnonymousClass1.a(java.lang.String, java.lang.String):java.lang.String");
                }

                @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
                public int a(String str2, int i) {
                    char c;
                    int hashCode = str2.hashCode();
                    if (hashCode == -1731287366) {
                        if (str2.equals("KEY_AB_REAL_REACHABILITY")) {
                            c = 1;
                        }
                        c = 65535;
                    } else if (hashCode != 214708503) {
                        if (hashCode == 1866165627 && str2.equals("KEY_RECONNECT_STRATEGY")) {
                            c = 0;
                        }
                        c = 65535;
                    } else {
                        if (str2.equals("AB_LINK_KEEP_EXCEPTION_REPORT")) {
                            c = 2;
                        }
                        c = 65535;
                    }
                    if (c != 0) {
                        return c != 1 ? c != 2 ? i : com.netease.nimlib.abtest.c.a().c() ? 1 : 0 : com.netease.nimlib.abtest.c.a().b() ? 1 : 0;
                    }
                    ReconnectStrategy reconnectStrategy = this.a.reconnectStrategy;
                    if (reconnectStrategy == null) {
                        return -1;
                    }
                    return Math.max(reconnectStrategy.reconnectDelay(), 0);
                }
            };
        }
        return new com.netease.nimlib.ipc.cp.b.c(context, str) { // from class: com.netease.nimlib.ipc.cp.provider.PreferenceContentProvider.2
            @Override // com.netease.nimlib.ipc.cp.b.c, com.netease.nimlib.ipc.cp.b.b
            public String a(String str2, String str3) {
                if ("KEY_LOGIN_INFO".equals(str2)) {
                    LoginInfo m = c.m();
                    if (m == null) {
                        return str3;
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("account", m.getAccount());
                        jSONObject.put("token", m.getToken());
                        jSONObject.put("authType", m.getAuthType());
                        jSONObject.put("loginExt", m.getLoginExt());
                        jSONObject.put("appKey", m.getAppKey());
                        jSONObject.put("customClientType", m.getCustomClientType());
                        jSONObject.put("isManualLogging", i.a().g());
                        return jSONObject.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return str3;
                    }
                }
                if ("k_sync_time_tag".equals(str2)) {
                    f fVar = new f();
                    com.netease.nimlib.log.b.I("syncData before load Data");
                    long currentTimeMillis = System.currentTimeMillis();
                    fVar.a();
                    com.netease.nimlib.log.b.I("syncData after load Data,cost time = " + (System.currentTimeMillis() - currentTimeMillis));
                    String b = fVar.b();
                    com.netease.nimlib.log.b.I("syncData after toJson Data,cost time = " + (System.currentTimeMillis() - currentTimeMillis));
                    return b;
                }
                return super.a(str2, str3);
            }

            @Override // com.netease.nimlib.ipc.cp.b.b
            public void b(String str2, String str3) {
                PreferenceContentProvider.this.a(str, str2, str3);
            }
        };
    }
}
