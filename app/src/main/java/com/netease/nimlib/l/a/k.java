package com.netease.nimlib.l.a;

import android.os.Build;
import android.os.Bundle;
import com.netease.nimlib.l.a.m;
import java.util.Set;

/* compiled from: RemoteInput.java */
/* loaded from: classes.dex */
public final class k extends m.a {
    public static final m.a.InterfaceC0040a a;
    private static final a h;
    private final String b;
    private final CharSequence c;
    private final CharSequence[] d;
    private final boolean e;
    private final Bundle f;
    private final Set<String> g;

    /* compiled from: RemoteInput.java */
    /* loaded from: classes.dex */
    interface a {
    }

    @Override // com.netease.nimlib.l.a.m.a
    public String a() {
        return this.b;
    }

    @Override // com.netease.nimlib.l.a.m.a
    public CharSequence b() {
        return this.c;
    }

    @Override // com.netease.nimlib.l.a.m.a
    public CharSequence[] c() {
        return this.d;
    }

    @Override // com.netease.nimlib.l.a.m.a
    public Set<String> d() {
        return this.g;
    }

    @Override // com.netease.nimlib.l.a.m.a
    public boolean e() {
        return this.e;
    }

    @Override // com.netease.nimlib.l.a.m.a
    public Bundle f() {
        return this.f;
    }

    /* compiled from: RemoteInput.java */
    /* loaded from: classes.dex */
    static class c implements a {
        c() {
        }
    }

    /* compiled from: RemoteInput.java */
    /* loaded from: classes.dex */
    static class d implements a {
        d() {
        }
    }

    /* compiled from: RemoteInput.java */
    /* loaded from: classes.dex */
    static class b implements a {
        b() {
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 20) {
            h = new b();
        } else if (Build.VERSION.SDK_INT >= 16) {
            h = new d();
        } else {
            h = new c();
        }
        a = new m.a.InterfaceC0040a() { // from class: com.netease.nimlib.l.a.k.1
        };
    }
}
