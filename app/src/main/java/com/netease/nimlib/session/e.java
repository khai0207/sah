package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: MessageReceiptCache.java */
/* loaded from: classes.dex */
public class e {
    private Map<String, Long> a = new ConcurrentHashMap();
    private Map<String, f> b = new ConcurrentHashMap();

    /* compiled from: MessageReceiptCache.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final e a = new e();
    }

    public void a() {
        this.b.clear();
        List<f> queryAllMessageReceipt = MsgDBHelper.queryAllMessageReceipt();
        a(queryAllMessageReceipt);
        this.a.clear();
        List<MessageReceipt> queryAllSendReceiptRecord = MsgDBHelper.queryAllSendReceiptRecord();
        b(queryAllSendReceiptRecord);
        com.netease.nimlib.log.b.c("MessageReceiptCache", "MessageReceiptCache init, received cache size=" + queryAllMessageReceipt.size() + " sent cache size=" + queryAllSendReceiptRecord.size());
    }

    public void a(List<f> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (f fVar : list) {
            this.b.put(fVar.a, fVar);
        }
    }

    public long a(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str).b;
        }
        return 0L;
    }

    public boolean a(MessageReceipt messageReceipt) {
        return !this.a.containsKey(messageReceipt.getSessionId()) || messageReceipt.getTime() > this.a.get(messageReceipt.getSessionId()).longValue();
    }

    public void b(MessageReceipt messageReceipt) {
        if (a(messageReceipt)) {
            this.a.put(messageReceipt.getSessionId(), Long.valueOf(messageReceipt.getTime()));
        }
    }

    private void b(List<MessageReceipt> list) {
        Iterator<MessageReceipt> it = list.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    public static e b() {
        return a.a;
    }
}
