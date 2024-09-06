package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/* compiled from: MsgTimingFullKeywordSearchResponseHandler.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
        } else if (aVar instanceof com.netease.nimlib.biz.e.j.q) {
            a((com.netease.nimlib.biz.e.j.q) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.j.q qVar) {
        a(qVar, com.netease.nimlib.o.f.b(qVar.a(), true, new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$i$C0VE3WRGN63SNPidOQelvAVXU0c
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                IMMessage a;
                a = i.a((com.netease.nimlib.push.packet.b.c) obj);
                return a;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ IMMessage a(com.netease.nimlib.push.packet.b.c cVar) {
        return com.netease.nimlib.session.g.a(cVar, false, false);
    }
}
