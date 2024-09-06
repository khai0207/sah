package com.netease.nimlib.j.c;

import com.netease.nimlib.biz.e.b;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.push.packet.c.d;
import com.netease.nimlib.push.packet.c.f;
import com.netease.nimlib.sdk.migration.model.IHistoryRecord;
import java.util.ArrayList;

/* compiled from: MsgImportResponse.java */
@b(a = 6, b = {"20"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private ArrayList<IHistoryRecord> c = new ArrayList<>();

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        int g = fVar.g();
        for (int i = 0; i < g; i++) {
            c a = d.a(fVar);
            this.c.add(new com.netease.nimlib.j.a.a(a.c(1), a.c(2), a.c(3), a.c(4), a.d(5), a.d(6)));
        }
        return null;
    }

    public ArrayList<IHistoryRecord> a() {
        return this.c;
    }
}
