package com.netease.nimlib.biz.c.d;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.f.e;
import com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SyncRoamMsgHasMoreResponseHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof e) {
            a((e) aVar);
        }
    }

    private void a(e eVar) {
        List<com.netease.nimlib.push.packet.b.c> a = eVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.push.packet.b.c next = it.next();
            try {
                arrayList.add(new RoamMsgHasMoreOption(next));
            } catch (Exception e) {
                e.printStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("createRoamMsgHasMoreOption err, msg=");
                sb.append(next == null ? Constants.NULL_VERSION_ID : next.toString());
                com.netease.nimlib.log.b.d("SyncRoamMsgHasMoreResponseHandler", sb.toString());
            }
        }
        MsgDBHelper.saveRoamMsgHasMore(arrayList);
        com.netease.nimlib.i.b.h(arrayList);
    }
}
