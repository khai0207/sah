package com.netease.nimlib.biz.d.i;

import android.text.TextUtils;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.session.IMMessageImpl;
import u.aly.df;

/* compiled from: RevokeMessageRequest.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private IMMessageImpl b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.k;
    }

    public o(IMMessageImpl iMMessageImpl, String str, String str2, String str3, boolean z, String str4, String str5) {
        this.b = iMMessageImpl;
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        this.a = cVar;
        cVar.a(0, iMMessageImpl.getTime());
        if (iMMessageImpl.getSessionType() == SessionTypeEnum.P2P) {
            this.a.a(1, 7);
        } else if (iMMessageImpl.getSessionType() == SessionTypeEnum.Team) {
            this.a.a(1, 8);
        }
        this.a.a(2, iMMessageImpl.getSessionId());
        if (!TextUtils.isEmpty(str)) {
            this.a.a(3, str);
        } else {
            this.a.a(3, iMMessageImpl.getFromAccount());
        }
        this.a.a(4, str4 == null ? iMMessageImpl.getContent() : str4);
        if (!TextUtils.isEmpty(str5)) {
            this.a.a(5, str5);
        }
        this.a.a(11, iMMessageImpl.getServerId());
        this.a.a(10, iMMessageImpl.getUuid());
        if (!TextUtils.isEmpty(str2)) {
            this.a.a(8, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            this.a.a(9, str3);
        }
        this.a.a(109, z ? 1 : 0);
        this.a.a(21, iMMessageImpl.getEnv());
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }

    public IMMessageImpl d() {
        return this.b;
    }
}
