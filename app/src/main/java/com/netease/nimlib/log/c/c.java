package com.netease.nimlib.log.c;

import java.io.File;

/* compiled from: MLogImpl.java */
/* loaded from: classes.dex */
public class c extends d {
    private com.netease.nimlib.log.a.a d;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.netease.nimlib.log.c.d, com.netease.nimlib.log.c.a
    /* renamed from: a */
    public void b(boolean z) {
        String str;
        super.b(z);
        String str2 = this.c;
        String substring = this.c.substring(0, this.c.lastIndexOf(File.separator));
        String d = com.netease.nimlib.log.c.a.a.d(this.c);
        if (com.netease.nimlib.log.c.a.a.b(d)) {
            str = substring + File.separator + com.netease.nimlib.log.c.a.a.e(d) + "_mapped." + com.netease.nimlib.log.c.a.a.c(d);
        } else {
            str = substring + File.separator + d + "_mapped";
        }
        if (this.d == null) {
            this.d = new com.netease.nimlib.log.a.a();
        }
        this.d.a(str, str2);
    }

    @Override // com.netease.nimlib.log.c.d, com.netease.nimlib.log.c.a
    void a(String str) {
        com.netease.nimlib.log.a.a aVar = this.d;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    @Override // com.netease.nimlib.log.c.d, com.netease.nimlib.log.c.a
    void c() {
        com.netease.nimlib.log.a.a aVar = this.d;
        if (aVar != null) {
            aVar.b();
        }
    }
}
