package com.netease.nimlib.biz.c.g;

import android.text.TextUtils;
import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.f.f;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.i.b;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.robot.RobotDBHelper;
import com.netease.nimlib.sdk.robot.model.RobotChangedNotify;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SyncRobotListResponseHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        List<c> a = ((f) aVar).a();
        ArrayList arrayList = new ArrayList(a.size());
        ArrayList arrayList2 = new ArrayList(a.size());
        ArrayList arrayList3 = new ArrayList(a.size());
        Iterator<c> it = a.iterator();
        long j = 0;
        while (it.hasNext()) {
            com.netease.nimlib.robot.a a2 = com.netease.nimlib.robot.a.a(it.next());
            if (a2.b() > j) {
                j = a2.b();
            }
            arrayList.add(a2);
            if (!TextUtils.isEmpty(a2.getBotId())) {
                arrayList2.add(a2);
            } else {
                arrayList3.add(a2.getAccount());
            }
        }
        if (arrayList.size() > 0) {
            RobotDBHelper.saveRobotInfo(arrayList);
        }
        l.c(j);
        b.a(new RobotChangedNotify(arrayList2, arrayList3));
    }
}
