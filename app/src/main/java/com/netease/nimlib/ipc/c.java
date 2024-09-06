package com.netease.nimlib.ipc;

import android.text.TextUtils;
import com.netease.nimlib.m.h;

/* compiled from: NIMDataHandler.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.ipc.cp.b.a {
    @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
    public boolean a(String str) {
        return true;
    }

    @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
    public boolean a(String str, boolean z) {
        return false;
    }

    @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
    public String a(String str, String str2) {
        String c;
        if ("KEY_GET_ALL_LINKS".equals(str)) {
            c = a();
        } else if ("KEY_GET_NOS_DL".equals(str)) {
            c = b();
        } else {
            c = "KEY_GET_TURN_ADDRESS".equals(str) ? c() : null;
        }
        com.netease.nimlib.log.b.c("NIMContentProvider onQueryString key=" + str + ", return data=" + c);
        return c;
    }

    @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
    public void b(String str, String str2) {
        if ("KEY_CHANGE_NOS_DL".equals(str)) {
            d();
        }
        com.netease.nimlib.log.b.c("NIMContentProvider onHandleVoid key=" + str);
    }

    @Override // com.netease.nimlib.ipc.cp.b.a, com.netease.nimlib.ipc.cp.b.b
    public int c(String str, String str2) {
        com.netease.nimlib.log.b.b(String.format("NIMContentProvider onHandleString key %s value %s", str, str2));
        if (!TextUtils.equals("KEY_UPDATE_NTP", str)) {
            return 0;
        }
        h a = h.a(str2);
        com.netease.nimlib.log.b.d("NIMDataHandler", String.format("KEY_UPDATE_NTP value %s originTimestamp %s", str2, a));
        if (a == null) {
            return -1;
        }
        com.netease.nimlib.m.c.d().a(a);
        return 1;
    }

    private String a() {
        String[] i = com.netease.nimlib.push.net.lbs.c.a().i();
        StringBuilder sb = new StringBuilder();
        for (String str : i) {
            sb.append(str);
            sb.append(";");
        }
        return sb.toString();
    }

    private String b() {
        return com.netease.nimlib.push.net.lbs.c.a().b();
    }

    private String c() {
        return com.netease.nimlib.push.net.lbs.c.a().d();
    }

    private void d() {
        com.netease.nimlib.push.net.lbs.c.a().e();
    }
}
