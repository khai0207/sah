package com.netease.nimlib.chatroom;

import com.netease.nimlib.biz.e.a;

/* compiled from: SendChatRoomRequestTask.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.g.b {
    protected final String a;

    public o(String str, com.netease.nimlib.biz.d.a aVar) {
        this(str, aVar, com.netease.nimlib.biz.g.a.a);
    }

    public o(String str, com.netease.nimlib.biz.d.a aVar, com.netease.nimlib.biz.g.a aVar2) {
        super(aVar, aVar2);
        this.a = str;
    }

    public String h() {
        return this.a;
    }

    @Override // com.netease.nimlib.biz.g.c
    protected boolean a() {
        this.h.i().b();
        d.e().a(this, this.a);
        return true;
    }

    @Override // com.netease.nimlib.biz.g.c
    public final void a(short s) {
        d.e().a(a.C0029a.a(b().i(), s));
    }
}
