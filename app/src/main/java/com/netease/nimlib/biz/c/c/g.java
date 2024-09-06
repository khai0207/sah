package com.netease.nimlib.biz.c.c;

import android.text.TextUtils;
import com.netease.nimlib.biz.e.d.n;
import com.netease.nimlib.biz.k;
import com.netease.nimlib.o.m;
import java.io.File;

/* compiled from: SyncLocalAntiSpamHandler.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        com.netease.nimlib.b.a a;
        if (aVar.q() != 17 || (a = a(((n) aVar).a())) == null) {
            return;
        }
        com.netease.nimlib.b.a a2 = k.a();
        if (a(a, a2)) {
            b(a, a2);
        } else {
            a(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final com.netease.nimlib.b.a aVar) {
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.biz.c.c.g.1
            @Override // java.lang.Runnable
            public void run() {
                com.netease.nimlib.b.c.a(new File(g.this.b(aVar)));
            }
        });
    }

    private boolean a(com.netease.nimlib.b.a aVar, com.netease.nimlib.b.a aVar2) {
        if (aVar == null) {
            return false;
        }
        if (aVar2 == null) {
            return true;
        }
        if (aVar2.a() < aVar.a() || !TextUtils.equals(aVar2.b(), aVar.b())) {
            com.netease.nimlib.log.b.N("find newer local anti spam version, need download");
            return true;
        }
        String b = b(aVar2);
        if (new File(b).exists() && m.b(b).equals(aVar2.b())) {
            return false;
        }
        com.netease.nimlib.log.b.N("local anti spam thesaurus miss, start download");
        return true;
    }

    private com.netease.nimlib.b.a a(com.netease.nimlib.push.packet.b.c cVar) {
        if (cVar == null) {
            return null;
        }
        com.netease.nimlib.b.a aVar = new com.netease.nimlib.b.a();
        aVar.a(cVar.d(1));
        aVar.a(cVar.c(2));
        aVar.b(cVar.c(3));
        return aVar;
    }

    private void b(final com.netease.nimlib.b.a aVar, final com.netease.nimlib.b.a aVar2) {
        final String b = b(aVar);
        com.netease.nimlib.net.a.a.f fVar = new com.netease.nimlib.net.a.a.f() { // from class: com.netease.nimlib.biz.c.c.g.2
            @Override // com.netease.nimlib.net.a.a.f
            public void onCancel(com.netease.nimlib.net.a.a.e eVar) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onExpire(com.netease.nimlib.net.a.a.e eVar, String str) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onFail(com.netease.nimlib.net.a.a.e eVar, String str) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onGetLength(com.netease.nimlib.net.a.a.e eVar, long j) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onProgress(com.netease.nimlib.net.a.a.e eVar, long j) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onStart(com.netease.nimlib.net.a.a.e eVar) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onOK(com.netease.nimlib.net.a.a.e eVar) {
                String b2 = m.b(b);
                if (b2 == null || !b2.equals(aVar.b())) {
                    File file = new File(b);
                    if (file.exists()) {
                        file.delete();
                        return;
                    }
                    return;
                }
                File file2 = new File(g.this.b(aVar2));
                if (file2.exists()) {
                    file2.delete();
                }
                com.netease.nimlib.log.b.N("download local anti spam thesaurus success");
                k.a(aVar);
                g.this.a(aVar);
            }
        };
        String c = aVar == null ? null : aVar.c();
        if (com.netease.nimlib.biz.b.e.d().a()) {
            com.netease.nimlib.biz.b.e.d().a(c, b, fVar);
        } else {
            com.netease.nimlib.net.a.a.g.a().a(new com.netease.nimlib.net.a.a.e(c, b, fVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(com.netease.nimlib.b.a aVar) {
        if (aVar == null) {
            return null;
        }
        String str = "antispam_" + aVar.a();
        String str2 = com.netease.nimlib.a.a;
        File filesDir = str2 == null ? com.netease.nimlib.c.e().getFilesDir() : new File(str2);
        if (filesDir == null) {
            return com.netease.nimlib.o.b.a.a().a(str, com.netease.nimlib.o.b.b.TYPE_FILE);
        }
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        String str3 = filesDir.getPath() + "/thesaurus/" + str;
        com.netease.nimlib.log.c.a.a.a(str3);
        return str3;
    }
}
