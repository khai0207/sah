package com.netease.nimlib.biz.c.i;

import android.text.TextUtils;
import com.netease.nimlib.sdk.msg.model.BroadcastMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* compiled from: BroadcastNotifyHandler.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof com.netease.nimlib.biz.e.j.c) {
            BroadcastMessage a = a(((com.netease.nimlib.biz.e.j.c) aVar).a());
            if (a != null) {
                long id = a.getId();
                com.netease.nimlib.i.b.a(a);
                a(id);
                a(aVar, a);
                return;
            }
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.f.a) {
            List<com.netease.nimlib.push.packet.b.c> a2 = ((com.netease.nimlib.biz.e.f.a) aVar).a();
            long j = com.netease.nimlib.biz.l.j();
            if (a2 == null || a2.isEmpty()) {
                return;
            }
            HashSet<Long> a3 = a();
            ArrayList arrayList = new ArrayList();
            Iterator<com.netease.nimlib.push.packet.b.c> it = a2.iterator();
            while (it.hasNext()) {
                BroadcastMessage a4 = a(it.next());
                if (a4 != null) {
                    if (a4.getId() > j) {
                        j = a4.getId();
                    }
                    if (!a3.contains(Long.valueOf(a4.getId()))) {
                        com.netease.nimlib.i.b.a(a4);
                        arrayList.add(Long.valueOf(a4.getId()));
                    } else {
                        a3.remove(Long.valueOf(a4.getId()));
                    }
                }
            }
            com.netease.nimlib.biz.l.d(j);
            a(a3, j);
            a(arrayList);
        }
    }

    private void a(long j) {
        JSONArray jSONArray;
        String k = com.netease.nimlib.biz.l.k();
        if (TextUtils.isEmpty(k)) {
            jSONArray = null;
        } else {
            jSONArray = com.netease.nimlib.o.k.b(k);
            if (jSONArray != null) {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= jSONArray.length()) {
                        break;
                    }
                    if (j == com.netease.nimlib.o.k.c(jSONArray, i)) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    jSONArray.put(j);
                }
            }
        }
        if (jSONArray == null) {
            jSONArray = new JSONArray();
            jSONArray.put(j);
        }
        com.netease.nimlib.biz.l.c(jSONArray.toString());
    }

    private HashSet<Long> a() {
        JSONArray b;
        HashSet<Long> hashSet = new HashSet<>();
        String k = com.netease.nimlib.biz.l.k();
        if (!TextUtils.isEmpty(k) && (b = com.netease.nimlib.o.k.b(k)) != null) {
            for (int i = 0; i < b.length(); i++) {
                hashSet.add(Long.valueOf(com.netease.nimlib.o.k.c(b, i)));
            }
        }
        return hashSet;
    }

    private void a(HashSet<Long> hashSet, long j) {
        if (hashSet != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<Long> it = hashSet.iterator();
            while (it.hasNext()) {
                long longValue = it.next().longValue();
                if (longValue > j) {
                    jSONArray.put(longValue);
                }
            }
            com.netease.nimlib.biz.l.c(jSONArray.toString());
        }
    }

    private void a(List<Long> list) {
        com.netease.nimlib.biz.d.e.a aVar = new com.netease.nimlib.biz.d.e.a();
        aVar.a((byte) 7);
        aVar.b((byte) 17);
        aVar.a(list);
        com.netease.nimlib.biz.i.a().a(aVar, com.netease.nimlib.biz.g.a.d);
    }

    private void a(com.netease.nimlib.biz.e.a aVar, BroadcastMessage broadcastMessage) {
        com.netease.nimlib.biz.d.e.b bVar = new com.netease.nimlib.biz.d.e.b();
        bVar.a(aVar.j());
        bVar.a(broadcastMessage.getId());
        com.netease.nimlib.biz.i.a().a(bVar, com.netease.nimlib.biz.g.a.d);
    }

    private BroadcastMessage a(com.netease.nimlib.push.packet.b.c cVar) {
        if (cVar == null) {
            return null;
        }
        BroadcastMessage broadcastMessage = new BroadcastMessage();
        broadcastMessage.setId(Long.parseLong(cVar.c(1)));
        broadcastMessage.setFromAccount(cVar.c(2));
        broadcastMessage.setTime(Long.parseLong(cVar.c(4)));
        broadcastMessage.setContent(cVar.c(5));
        return broadcastMessage;
    }
}
