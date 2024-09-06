package com.netease.nimlib.session.a;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.session.j;
import com.netease.nimlib.session.y;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: SyncReliableInfo.java */
/* loaded from: classes.dex */
public class e {
    private final long a;
    private final y b;
    private long c;
    private final HashMap<String, f> d = new HashMap<>();

    public static e a(com.netease.nimlib.push.packet.b.c cVar, List<com.netease.nimlib.push.packet.b.c> list) {
        e eVar = new e(cVar.e(1), y.a(cVar.d(2)));
        Iterator<com.netease.nimlib.push.packet.b.c> it = list.iterator();
        while (it.hasNext()) {
            f a = f.a(it.next());
            if (a != null && a.b() != null && a.c() != null) {
                eVar.a(a);
            }
        }
        return eVar;
    }

    private e(long j, y yVar) {
        this.a = j;
        this.b = yVar;
    }

    public y a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public Map<String, f> c() {
        return this.d;
    }

    public f a(String str, SessionTypeEnum sessionTypeEnum) {
        return this.d.get(j.a(sessionTypeEnum, str));
    }

    private void a(f fVar) {
        if (fVar == null) {
            return;
        }
        this.d.put(j.a(fVar.c(), fVar.b()), fVar);
    }

    public String toString() {
        return "RecentReliableInfo{syncRequestRoamingTimestamp=" + this.a + ", type=" + this.b + ", syncResponseTimestamp=" + this.c + ", syncSessionReliableInfos=" + this.d + '}';
    }
}
