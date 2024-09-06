package com.netease.nimlib.session;

import com.netease.nimlib.user.UserInfoDBHelper;

/* compiled from: SelfUserInfoCache.java */
/* loaded from: classes.dex */
public class t {
    private long a;

    /* compiled from: SelfUserInfoCache.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final t a = new t();
    }

    public void a() {
        if (com.netease.nimlib.c.x()) {
            this.a = UserInfoDBHelper.getUpdateTimeTag(com.netease.nimlib.c.n());
            com.netease.nimlib.log.b.N("SelfUserInfoCache init userInfoTimeTag = " + this.a);
        }
    }

    public void a(long j) {
        this.a = j;
    }

    public long b() {
        return this.a;
    }

    public static t c() {
        return a.a;
    }
}
