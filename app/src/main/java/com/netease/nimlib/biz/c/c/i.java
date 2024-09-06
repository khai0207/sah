package com.netease.nimlib.biz.c.c;

import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.biz.d.d.s;
import com.netease.nimlib.biz.e.d.q;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import java.io.File;

/* compiled from: UploadLogNotifyHandler.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.biz.c.c.-$$Lambda$i$JRpCbjFVG0bnfaW3c__vvYBP9TM
            private final /* synthetic */ com.netease.nimlib.biz.e.a f$1;

            public /* synthetic */ $$Lambda$i$JRpCbjFVG0bnfaW3c__vvYBP9TM(com.netease.nimlib.biz.e.a aVar2) {
                r2 = aVar2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                i.a(q.this, r2);
            }
        });
    }

    public static /* synthetic */ void a(q qVar, com.netease.nimlib.biz.e.a aVar) {
        com.netease.nimlib.log.b.d("UploadLogNotifyHandler", "upload log , type = " + qVar.a());
        String a = com.netease.nimlib.log.a.a(qVar.a() == 0);
        if (a == null) {
            com.netease.nimlib.log.b.f("UploadLogNotifyHandler", "upload log zip file is null ");
        } else {
            a(aVar.j().o(), a, NimNosSceneKeyConstant.NIM_SYSTEM_NOS_SCENE);
        }
    }

    /* compiled from: UploadLogNotifyHandler.java */
    /* renamed from: com.netease.nimlib.biz.c.c.i$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements com.netease.nimlib.net.a.b.c {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        @Override // com.netease.nimlib.net.a.b.c
        public void a(Object obj) {
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(Object obj, long j, long j2) {
        }

        AnonymousClass1(String str, String str2) {
            r1 = str;
            r2 = str2;
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(Object obj, String str) {
            i.b(r1, str);
            new File(r2).delete();
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(Object obj, int i, String str) {
            com.netease.nimlib.log.b.d("UploadLogNotifyHandler", "upload log file error, code = " + i);
        }
    }

    private static void a(String str, String str2, String str3) {
        com.netease.nimlib.net.a.b.a.a().a(str, str2, null, str2, str3, false, new com.netease.nimlib.net.a.b.c() { // from class: com.netease.nimlib.biz.c.c.i.1
            final /* synthetic */ String a;
            final /* synthetic */ String b;

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj) {
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj, long j, long j2) {
            }

            AnonymousClass1(String str4, String str22) {
                r1 = str4;
                r2 = str22;
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj, String str4) {
                i.b(r1, str4);
                new File(r2).delete();
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj, int i, String str4) {
                com.netease.nimlib.log.b.d("UploadLogNotifyHandler", "upload log file error, code = " + i);
            }
        });
    }

    public static void b(String str, String str2) {
        s sVar = new s(str2);
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.biz.i.a().a(sVar, com.netease.nimlib.biz.g.a.d);
            return;
        }
        IChatRoomInteract iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class);
        if (iChatRoomInteract == null) {
            return;
        }
        iChatRoomInteract.sendRequest(str, sVar);
    }
}
