package com.talkingdata.sdk;

import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: td */
/* loaded from: classes.dex */
class bl extends ThreadLocal {
    final /* synthetic */ bk a;

    bl(bk bkVar) {
        this.a = bkVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ConcurrentLinkedQueue initialValue() {
        return new ConcurrentLinkedQueue();
    }
}
