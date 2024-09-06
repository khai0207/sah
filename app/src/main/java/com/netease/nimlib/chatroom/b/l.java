package com.netease.nimlib.chatroom.b;

import com.netease.nimlib.sdk.util.Entry;
import java.util.ArrayList;

/* compiled from: RoomQueueResponseHandler.java */
/* loaded from: classes.dex */
public class l extends e {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
            return;
        }
        switch (aVar.q()) {
            case 20:
                d(aVar);
                return;
            case 21:
                a((com.netease.nimlib.chatroom.d.p) aVar);
                return;
            case 22:
                a((com.netease.nimlib.chatroom.d.o) aVar);
                return;
            case 23:
            case 25:
            default:
                return;
            case 24:
                a((com.netease.nimlib.chatroom.d.e) aVar);
                return;
            case 26:
                a((com.netease.nimlib.chatroom.d.a) aVar);
                return;
        }
    }

    private void a(com.netease.nimlib.chatroom.d.a aVar) {
        a(aVar, aVar.a() == null ? null : new ArrayList(aVar.a()));
    }

    private void d(com.netease.nimlib.biz.e.a aVar) {
        a(aVar, null);
    }

    private void a(com.netease.nimlib.chatroom.d.p pVar) {
        a(pVar, new Entry(pVar.a(), pVar.b()));
    }

    private void a(com.netease.nimlib.chatroom.d.o oVar) {
        a(oVar, oVar.a());
    }

    private void a(com.netease.nimlib.chatroom.d.e eVar) {
        a(eVar, null);
    }
}
