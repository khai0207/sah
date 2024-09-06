package com.netease.nimlib.biz.d.f;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.push.packet.c.b;
import com.netease.nimlib.sdk.passthrough.model.PassthroughProxyData;

/* compiled from: HttpProxyRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private PassthroughProxyData a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 22;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public a(PassthroughProxyData passthroughProxyData) {
        this.a = passthroughProxyData;
    }

    @Override // com.netease.nimlib.biz.d.a
    public b a() {
        b bVar = new b();
        c cVar = new c();
        if (!TextUtils.isEmpty(this.a.getZone())) {
            cVar.a(1, this.a.getZone());
        }
        if (!TextUtils.isEmpty(this.a.getPath())) {
            cVar.a(2, this.a.getPath());
        }
        cVar.a(3, this.a.getMethod());
        if (!TextUtils.isEmpty(this.a.getHeader())) {
            cVar.a(4, this.a.getHeader());
        }
        if (!TextUtils.isEmpty(this.a.getBody())) {
            cVar.a(5, this.a.getBody());
        }
        bVar.a(cVar);
        return bVar;
    }
}
