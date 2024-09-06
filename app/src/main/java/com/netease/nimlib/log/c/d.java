package com.netease.nimlib.log.c;

/* compiled from: NLogImpl.java */
/* loaded from: classes.dex */
public class d extends a {
    @Override // com.netease.nimlib.log.c.a
    void c() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.netease.nimlib.log.c.a
    /* renamed from: a */
    public void b(boolean z) {
        if (z) {
            com.netease.nimlib.log.c.a.a.a(this.c, this.a, this.b);
            a("Log", "shrink log success");
        }
    }

    @Override // com.netease.nimlib.log.c.a
    void a(String str) {
        com.netease.nimlib.log.c.a.a.a(str, this.c);
    }
}
