package com.netease.nimlib.push.a.a;

import com.netease.nimlib.biz.f;
import com.netease.nimlib.push.h;
import java.util.List;

/* compiled from: LoginStatusNotifyHandler.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.c.a {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.push.a.c.c cVar = (com.netease.nimlib.push.a.c.c) aVar;
            byte a = cVar.a();
            if (a == 1) {
                h.a(cVar.b());
            } else if (a == 2) {
                h.b(cVar.b());
            } else {
                if (a != 3) {
                    return;
                }
                h.a((List<f>) cVar.b());
            }
        }
    }
}
