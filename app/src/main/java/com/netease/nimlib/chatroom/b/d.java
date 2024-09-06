package com.netease.nimlib.chatroom.b;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ChatRoomSyncRobotListHandler.java */
/* loaded from: classes.dex */
public class d extends e {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.q() == 16) {
            com.netease.nimlib.biz.e.m.a aVar2 = (com.netease.nimlib.biz.e.m.a) aVar;
            ArrayList arrayList = new ArrayList();
            if (aVar2.r() == 200) {
                Iterator<com.netease.nimlib.push.packet.b.c> it = aVar2.a().iterator();
                while (it.hasNext()) {
                    com.netease.nimlib.robot.a a = com.netease.nimlib.robot.a.a(it.next());
                    if (!TextUtils.isEmpty(a.getBotId())) {
                        arrayList.add(a);
                    }
                }
            }
            a(aVar2, arrayList);
        }
    }
}
