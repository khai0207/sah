package com.netease.nimlib.j.b;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.session.j;
import java.util.HashMap;

/* compiled from: MsgExportRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 19;
    }

    public a(String str, String str2, HashMap<String, Object> hashMap, String str3) {
        c cVar = new c();
        this.a = cVar;
        cVar.a(1, str2);
        if (!TextUtils.isEmpty(str)) {
            this.a.a(2, str);
        }
        String a = j.a(hashMap);
        if (!TextUtils.isEmpty(a)) {
            this.a.a(4, a);
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        this.a.a(3, str3);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
