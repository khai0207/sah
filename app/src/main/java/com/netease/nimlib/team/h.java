package com.netease.nimlib.team;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: TeamMsgAckCache.java */
/* loaded from: classes.dex */
public class h {
    private Set<String> a = Collections.synchronizedSet(new HashSet());
    private Set<String> b = Collections.synchronizedSet(new HashSet());
    private Map<String, Pair<Integer, Integer>> c = new ConcurrentHashMap();
    private Map<String, List<com.netease.nimlib.i.k>> d = new ConcurrentHashMap();

    /* compiled from: TeamMsgAckCache.java */
    /* loaded from: classes.dex */
    private static class a {
        static final h a = new h();
    }

    public void a() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public List<IMMessage> a(List<IMMessage> list) {
        if (list == null) {
            return null;
        }
        Iterator<IMMessage> it = list.iterator();
        while (it.hasNext()) {
            IMMessage next = it.next();
            if (!next.needMsgAck()) {
                it.remove();
            } else if (next.hasSendAck()) {
                it.remove();
            } else if (this.a.contains(next.getUuid())) {
                it.remove();
            } else {
                this.a.add(next.getUuid());
            }
        }
        return list;
    }

    public void b(List<IMMessage> list) {
        if (list == null) {
            return;
        }
        Iterator<IMMessage> it = list.iterator();
        while (it.hasNext()) {
            this.a.remove(it.next().getUuid());
        }
    }

    public List<IMMessage> c(List<IMMessage> list) {
        if (list == null) {
            return null;
        }
        Iterator<IMMessage> it = list.iterator();
        while (it.hasNext()) {
            IMMessage next = it.next();
            if (!next.needMsgAck()) {
                it.remove();
            } else if (this.b.contains(next.getUuid())) {
                it.remove();
            } else {
                this.b.add(next.getUuid());
            }
        }
        return list;
    }

    public void d(List<IMMessage> list) {
        if (list == null) {
            return;
        }
        Iterator<IMMessage> it = list.iterator();
        while (it.hasNext()) {
            this.b.remove(it.next().getUuid());
        }
    }

    public Pair<Integer, Integer> a(String str) {
        return this.c.get(str);
    }

    public int b(String str) {
        if (str == null || !this.c.containsKey(str)) {
            return -1;
        }
        return ((Integer) this.c.get(str).first).intValue();
    }

    public int c(String str) {
        if (this.c.containsKey(str)) {
            return ((Integer) this.c.get(str).second).intValue();
        }
        return -1;
    }

    public void e(List<TeamMessageReceipt> list) {
        if (list == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (TeamMessageReceipt teamMessageReceipt : list) {
            MsgDBHelper.updateTeamMsgAckCount(teamMessageReceipt.getMsgId(), teamMessageReceipt.getAckCount(), teamMessageReceipt.getUnAckCount());
            Pair<Integer, Integer> pair = this.c.get(teamMessageReceipt.getMsgId());
            if (pair == null || (teamMessageReceipt.getAckCount() > ((Integer) pair.first).intValue() && teamMessageReceipt.getUnAckCount() < ((Integer) pair.second).intValue())) {
                this.c.put(teamMessageReceipt.getMsgId(), new Pair<>(Integer.valueOf(teamMessageReceipt.getAckCount()), Integer.valueOf(teamMessageReceipt.getUnAckCount())));
            }
            if (!this.b.contains(teamMessageReceipt.getMsgId())) {
                this.b.add(teamMessageReceipt.getMsgId());
            }
            sb.append(teamMessageReceipt.getMsgId());
            sb.append(" ");
        }
        com.netease.nimlib.log.b.N("on team msg ack notify, receipts size=" + list.size() + ", msg id list=" + sb.toString());
    }

    public void b() {
        com.netease.nimlib.log.b.N("remove all has refreshed message ids");
        this.b.clear();
    }

    public void a(String str, com.netease.nimlib.i.k kVar) {
        if (TextUtils.isEmpty(str) || kVar == null) {
            return;
        }
        List<com.netease.nimlib.i.k> list = this.d.get(str);
        if (list != null) {
            list.add(kVar);
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(kVar);
        this.d.put(str, copyOnWriteArrayList);
    }

    public List<com.netease.nimlib.i.k> d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.d.remove(str);
    }

    public static h c() {
        return a.a;
    }
}
