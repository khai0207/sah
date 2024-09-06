package com.netease.nimlib.biz.d.d;

import android.text.TextUtils;

/* compiled from: UploadIMDetectRequest.java */
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a = new com.netease.nimlib.push.packet.b.c();

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 32;
    }

    public r(com.netease.nimlib.k.b.a aVar) {
        com.netease.nimlib.k.a.a a = aVar.a();
        if (a != null) {
            this.a.a(1, a.a());
        }
        String b = aVar.b();
        if (!TextUtils.isEmpty(b)) {
            this.a.a(2, b);
        }
        String c = aVar.c();
        if (!TextUtils.isEmpty(c)) {
            this.a.a(3, c);
        }
        Long d = aVar.d();
        if (d != null) {
            this.a.a(100, d.longValue());
        }
        Long e = aVar.e();
        if (e != null) {
            this.a.a(101, e.longValue());
        }
        Long f = aVar.f();
        if (f != null) {
            this.a.a(102, f.longValue());
        }
        this.a.a(103, System.currentTimeMillis());
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ UploadIMDetectRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "property", this.a);
        com.netease.nimlib.log.b.J("************ UploadIMDetectRequest end ****************");
        return bVar;
    }
}
