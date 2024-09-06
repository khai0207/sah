package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.MsgPinOption;

/* compiled from: MsgPinOptionImpl.java */
/* loaded from: classes.dex */
public class n implements MsgPinOption {
    private final String a;
    private final String b;
    private final long c;
    private final long d;

    public n(com.netease.nimlib.push.packet.b.c cVar) {
        this(cVar.c(1), cVar.c(2), cVar.e(3), cVar.e(4));
    }

    public n(String str, String str2, long j, long j2) {
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = j2;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinOption
    public String getAccount() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinOption
    public String getExt() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinOption
    public long getCreateTime() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinOption
    public long getUpdateTime() {
        return this.d;
    }
}
