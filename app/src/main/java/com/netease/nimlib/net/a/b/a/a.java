package com.netease.nimlib.net.a.b.a;

import android.text.TextUtils;

/* compiled from: MainLinkLbsUI.java */
/* loaded from: classes.dex */
public class a {
    private static a a = new a();

    public static a a() {
        return a;
    }

    public String[] b() {
        String[] strArr = null;
        try {
            String a2 = com.netease.nimlib.ipc.cp.a.a.a(com.netease.nimlib.c.e(), "NIM").a("KEY_GET_ALL_LINKS", (String) null);
            if (!TextUtils.isEmpty(a2)) {
                strArr = a2.split(";");
                com.netease.nimlib.log.b.c("IPC-CP getAllLinksFromLBS data=" + a2);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.a("getAllNosAccessFromLBS through IPC-CP error", th);
            th.printStackTrace();
        }
        return strArr == null ? com.netease.nimlib.push.net.lbs.c.a().i() : strArr;
    }

    public void c() {
        try {
            com.netease.nimlib.ipc.cp.a.a.a(com.netease.nimlib.c.e(), "NIM").c("KEY_CHANGE_NOS_DL", null);
            com.netease.nimlib.log.b.c("IPC-CP changeLBSNosAccess done");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.a("invoke changeLBSNosAccess through IPC-CP error", th);
            th.printStackTrace();
        }
    }
}
