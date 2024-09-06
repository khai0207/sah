package com.netease.nimlib.biz.c.l;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.robot.RobotDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SyncUpdateRobotListHandler.java */
/* loaded from: classes.dex */
public class b extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.q() == 16) {
            com.netease.nimlib.biz.e.m.a aVar2 = (com.netease.nimlib.biz.e.m.a) aVar;
            ArrayList arrayList = new ArrayList();
            if (aVar2.r() == 200) {
                long i = l.i();
                Iterator<com.netease.nimlib.push.packet.b.c> it = aVar2.a().iterator();
                while (it.hasNext()) {
                    com.netease.nimlib.robot.a a = com.netease.nimlib.robot.a.a(it.next());
                    arrayList.add(a);
                    if (a.b() > i) {
                        i = a.b();
                    }
                }
                if (arrayList.size() > 0) {
                    RobotDBHelper.saveRobotInfo(arrayList);
                }
                l.c(i);
            }
            List<com.netease.nimlib.robot.a> queryAllRobotInfo = RobotDBHelper.queryAllRobotInfo(true);
            a(aVar, queryAllRobotInfo == null ? null : new ArrayList(queryAllRobotInfo));
        }
    }
}
