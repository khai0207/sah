package com.netease.nimlib.session;

import java.util.HashSet;
import java.util.Set;

/* compiled from: SystemMsgCache.java */
/* loaded from: classes.dex */
public class aa {
    private final Set<Long> a;

    /* compiled from: SystemMsgCache.java */
    /* loaded from: classes.dex */
    private static class a {
        public static final aa a = new aa();
    }

    public boolean a(Long l) {
        return this.a.add(l);
    }

    public static aa a() {
        return a.a;
    }

    private aa() {
        this.a = new HashSet();
    }
}
