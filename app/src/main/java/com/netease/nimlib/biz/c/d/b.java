package com.netease.nimlib.biz.c.d;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.f.h;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;

/* compiled from: SyncStickTopSessionResponseHandler.java */
/* loaded from: classes.dex */
public class b extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof h) {
            a((h) aVar);
        }
    }

    private void a(h hVar) {
        l.x(hVar.a());
        if (hVar.b()) {
            ArrayList<StickTopSessionInfo> c = hVar.c();
            MsgDBHelper.clearStickTopSession();
            MsgDBHelper.saveStickTopSession(c);
            com.netease.nimlib.i.b.i(c);
        }
    }
}
