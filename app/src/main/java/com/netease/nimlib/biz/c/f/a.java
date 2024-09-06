package com.netease.nimlib.biz.c.f;

import com.netease.nimlib.log.b;
import java.util.Iterator;

/* compiled from: QChatTokenResponseHandler.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.c.a {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof com.netease.nimlib.biz.e.h.a) {
            com.netease.nimlib.biz.e.h.a aVar2 = (com.netease.nimlib.biz.e.h.a) aVar;
            if (!aVar2.n() || aVar2.a() == null) {
                return;
            }
            Iterator<String> it = aVar2.a().iterator();
            while (it.hasNext()) {
                b.d("QChatTokenResponseHandler", String.format("Link: %s", it.next()));
            }
        }
    }
}
