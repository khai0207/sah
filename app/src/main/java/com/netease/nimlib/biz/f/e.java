package com.netease.nimlib.biz.f;

import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.biz.d.d.s;
import com.netease.nimlib.m.d;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.misc.DirCacheFileType;
import com.netease.nimlib.sdk.misc.MiscService;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MiscServiceRemote.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.i.j implements MiscService {
    private long a = 0;

    @Override // com.netease.nimlib.sdk.misc.MiscService
    public InvocationFuture<String> zipLogs() {
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.biz.f.e.1
            @Override // java.lang.Runnable
            public void run() {
                b.b(com.netease.nimlib.log.a.a(false)).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.misc.MiscService
    public InvocationFuture<Long> getSizeOfDirCache(final List<DirCacheFileType> list, final long j, final long j2) {
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.biz.f.e.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    b.b(Long.valueOf(com.netease.nimlib.o.h.a(e.this.a((List<DirCacheFileType>) list), j, j2))).b();
                } catch (Throwable th) {
                    b.a(th).b();
                }
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.misc.MiscService
    public InvocationFuture<Void> clearDirCache(final List<DirCacheFileType> list, final long j, final long j2) {
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.biz.f.e.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.netease.nimlib.o.h.b(e.this.a((List<DirCacheFileType>) list), j, j2);
                    b.b((Object) null).b();
                } catch (Throwable th) {
                    b.a(th).b();
                }
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> a(List<DirCacheFileType> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<DirCacheFileType> it = list.iterator();
        while (it.hasNext()) {
            String str = null;
            switch (AnonymousClass6.a[it.next().ordinal()]) {
                case 1:
                    str = com.netease.nimlib.o.b.c.b(com.netease.nimlib.o.b.b.TYPE_IMAGE);
                    break;
                case 2:
                    str = com.netease.nimlib.o.b.c.b(com.netease.nimlib.o.b.b.TYPE_LOG);
                    break;
                case 3:
                    str = com.netease.nimlib.o.b.c.b(com.netease.nimlib.o.b.b.TYPE_VIDEO);
                    break;
                case 4:
                    str = com.netease.nimlib.o.b.c.b(com.netease.nimlib.o.b.b.TYPE_AUDIO);
                    break;
                case 5:
                    str = com.netease.nimlib.o.b.c.b(com.netease.nimlib.o.b.b.TYPE_FILE);
                    break;
                case 6:
                    str = com.netease.nimlib.o.b.c.b(com.netease.nimlib.o.b.b.TYPE_THUMB_IMAGE);
                    break;
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* compiled from: MiscServiceRemote.java */
    /* renamed from: com.netease.nimlib.biz.f.e$6, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[DirCacheFileType.values().length];
            a = iArr;
            try {
                iArr[DirCacheFileType.IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[DirCacheFileType.LOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[DirCacheFileType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[DirCacheFileType.AUDIO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[DirCacheFileType.OTHER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[DirCacheFileType.THUMB.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.netease.nimlib.sdk.misc.MiscService
    public InvocationFuture<Long> getServerTime() {
        long currentTimeMillis = System.currentTimeMillis();
        final com.netease.nimlib.i.k b = b();
        long j = currentTimeMillis - this.a;
        long c = com.netease.nimlib.biz.i.a().b().c();
        if (c <= 0 || j > Math.max(com.netease.nimlib.c.i().fetchServerTimeInterval, 1000L)) {
            com.netease.nimlib.biz.i.a().b().a(new d.a() { // from class: com.netease.nimlib.biz.f.e.4
                @Override // com.netease.nimlib.m.d.a
                public void a(long j2) {
                    b.b(Long.valueOf(j2)).b();
                }

                @Override // com.netease.nimlib.m.d.a
                public void a(int i, String str) {
                    b.a(i).b();
                }
            }, true);
            return null;
        }
        b.b(Long.valueOf(c)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.misc.MiscService
    public InvocationFuture<String> getSdkLogUpload(final boolean z, final String str, final String str2) {
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.biz.f.-$$Lambda$e$8EvF2vuhzCS_yUf3X9e2OT_nYPo
            @Override // java.lang.Runnable
            public final void run() {
                e.this.a(z, b, str, str2);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, final com.netease.nimlib.i.k kVar, final String str, final String str2) {
        final String a = com.netease.nimlib.log.a.a(z);
        if (a == null) {
            kVar.a(6);
        } else {
            com.netease.nimlib.net.a.b.a.a().a(str, a, "", null, a, NimNosSceneKeyConstant.NIM_SYSTEM_NOS_SCENE, false, new com.netease.nimlib.net.a.b.c() { // from class: com.netease.nimlib.biz.f.e.5
                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj, long j, long j2) {
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj, String str3) {
                    s sVar = new s(str3, true, str2);
                    sVar.a(kVar);
                    e.this.a(str, sVar);
                    new File(a).delete();
                    kVar.b(str3).b();
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj, int i, String str3) {
                    kVar.a(i).b();
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj) {
                    kVar.a(417);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, s sVar) {
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(sVar));
            return;
        }
        IChatRoomInteract iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class);
        if (iChatRoomInteract == null) {
            return;
        }
        iChatRoomInteract.sendRequest(str, sVar);
    }
}
