package com.netease.nimlib.biz.d.i;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.session.v;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: AckSessionBatchRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private List<com.netease.nimlib.push.packet.b.c> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 25;
    }

    public a(List<Pair<String, SessionTypeEnum>> list) {
        this.a = com.netease.nimlib.o.f.c(list, new f.a() { // from class: com.netease.nimlib.biz.d.i.-$$Lambda$a$_bky39l-qDmZoB2vQqEgXivGaqI
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                com.netease.nimlib.push.packet.b.c a;
                a = a.this.a((Pair) obj);
                return a;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ com.netease.nimlib.push.packet.b.c a(Pair pair) {
        return a(pair, v.a((String) pair.first, (SessionTypeEnum) pair.second));
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        if (com.netease.nimlib.o.f.c((Collection) this.a)) {
            return bVar;
        }
        bVar.b(this.a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = this.a.iterator();
        while (it.hasNext()) {
            bVar.a(it.next());
        }
        return bVar;
    }

    public List<com.netease.nimlib.push.packet.b.c> d() {
        return this.a;
    }

    private com.netease.nimlib.push.packet.b.c a(Pair<String, SessionTypeEnum> pair, long j) {
        SessionTypeEnum sessionTypeEnum;
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        if (pair != null) {
            String str = (String) pair.first;
            if (!TextUtils.isEmpty(str) && (sessionTypeEnum = (SessionTypeEnum) pair.second) != null) {
                cVar.a(1, SessionTypeEnum.Team == sessionTypeEnum ? 1 : 0);
                cVar.a(2, str);
                cVar.a(3, j);
            }
        }
        return cVar;
    }
}
