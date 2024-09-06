package com.netease.nimlib.biz.f;

import android.net.Uri;
import android.text.TextUtils;
import android.util.LruCache;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.net.a.b.a;
import com.netease.nimlib.o.w;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.nos.NosService;
import com.netease.nimlib.sdk.nos.constant.NosTransferStatus;
import com.netease.nimlib.sdk.nos.model.NosThumbParam;
import com.netease.nimlib.sdk.nos.model.NosTransferInfo;
import java.io.File;

/* compiled from: NosServiceRemote.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.i.j implements NosService {
    private LruCache<String, String> a = new LruCache<>(100);

    @Override // com.netease.nimlib.sdk.nos.NosService
    public AbortableFuture upload(File file, String str) {
        return uploadEnableForce(file, str, NimNosSceneKeyConstant.NIM_DEFAULT_PROFILE, false);
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public AbortableFuture uploadAtScene(File file, String str, String str2) {
        return uploadEnableForce(file, str, str2, false);
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public AbortableFuture<String> uploadEnableForce(File file, String str, String str2, boolean z) {
        NosTransferInfo nosTransferInfo = new NosTransferInfo();
        nosTransferInfo.setPath(file.getPath());
        nosTransferInfo.setSize(file.length());
        nosTransferInfo.setTransferType(NosTransferInfo.TransferType.UPLOAD);
        if (TextUtils.isEmpty(str)) {
            nosTransferInfo.setExtension(w.c(file.getName()));
        } else {
            nosTransferInfo.setExtension(str);
        }
        if (!w.f(str)) {
            str = "";
        }
        final a.c a = a(nosTransferInfo, str, b(), str2, z);
        return new com.netease.nimlib.i.h<Runnable>(a) { // from class: com.netease.nimlib.biz.f.h.1
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                com.netease.nimlib.net.a.b.a.a().a(a);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public AbortableFuture<Void> download(String str, NosThumbParam nosThumbParam, String str2) {
        NosTransferInfo nosTransferInfo = new NosTransferInfo();
        nosTransferInfo.setUrl(str);
        nosTransferInfo.setPath(str2);
        nosTransferInfo.setTransferType(NosTransferInfo.TransferType.DOWNLOAD);
        com.netease.nimlib.net.a.a.e a = a(nosTransferInfo, nosThumbParam, b());
        if (a == null) {
            return null;
        }
        return new com.netease.nimlib.i.h<com.netease.nimlib.net.a.a.e>(a) { // from class: com.netease.nimlib.biz.f.h.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                if (com.netease.nimlib.biz.b.e.d().a()) {
                    com.netease.nimlib.biz.b.e.d().a((com.netease.nimlib.net.a.a.e) this.c);
                    return false;
                }
                com.netease.nimlib.net.a.a.g.a().b((com.netease.nimlib.net.a.a.e) this.c);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public AbortableFuture<Void> downloadFileSecure(final String str, String str2) {
        com.netease.nimlib.net.a.a.e eVar;
        final com.netease.nimlib.i.k b = b();
        if (TextUtils.isEmpty(str)) {
            a(b, 414);
            return null;
        }
        final NosTransferInfo nosTransferInfo = new NosTransferInfo();
        nosTransferInfo.setUrl(str);
        nosTransferInfo.setPath(str2);
        nosTransferInfo.setTransferType(NosTransferInfo.TransferType.DOWNLOAD);
        com.netease.nimlib.net.a.a.f fVar = new com.netease.nimlib.net.a.a.f() { // from class: com.netease.nimlib.biz.f.h.3
            private long d;

            @Override // com.netease.nimlib.net.a.a.f
            public void onStart(com.netease.nimlib.net.a.a.e eVar2) {
                nosTransferInfo.setStatus(NosTransferStatus.transferring);
                com.netease.nimlib.i.b.a(nosTransferInfo);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onOK(com.netease.nimlib.net.a.a.e eVar2) {
                nosTransferInfo.setStatus(NosTransferStatus.transferred);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                h.b(eVar2);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onFail(com.netease.nimlib.net.a.a.e eVar2, String str3) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                h.b(eVar2);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onCancel(com.netease.nimlib.net.a.a.e eVar2) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onProgress(com.netease.nimlib.net.a.a.e eVar2, long j) {
                com.netease.nimlib.i.b.b(str, j, this.d);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onGetLength(com.netease.nimlib.net.a.a.e eVar2, long j) {
                this.d = j;
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onExpire(com.netease.nimlib.net.a.a.e eVar2, String str3) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                h.b(eVar2);
            }
        };
        if (com.netease.nimlib.biz.b.e.d().a()) {
            eVar = com.netease.nimlib.biz.b.e.d().a(str, str2, fVar);
        } else {
            final com.netease.nimlib.net.a.a.e eVar2 = new com.netease.nimlib.net.a.a.e(str, str2, fVar);
            if (com.netease.nimlib.session.j.e(str)) {
                com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.i(str)) { // from class: com.netease.nimlib.biz.f.h.4
                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar) {
                        super.a(aVar);
                        if (!aVar.n() || !(aVar instanceof com.netease.nimlib.biz.e.d.f)) {
                            h.this.a(b, 4);
                            return;
                        }
                        String a = ((com.netease.nimlib.biz.e.d.f) aVar).a();
                        String str3 = str;
                        if (!TextUtils.isEmpty(a)) {
                            if (str.contains("?")) {
                                str3 = str3 + "&token=" + a;
                            } else {
                                str3 = str3 + "?token=" + a;
                            }
                        }
                        eVar2.a(str3);
                        com.netease.nimlib.net.a.a.g.a().a(eVar2);
                    }
                });
            } else {
                com.netease.nimlib.net.a.a.g.a().a(eVar2);
            }
            eVar = eVar2;
        }
        return new com.netease.nimlib.i.h<com.netease.nimlib.net.a.a.e>(eVar) { // from class: com.netease.nimlib.biz.f.h.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                if (com.netease.nimlib.biz.b.e.d().a()) {
                    com.netease.nimlib.biz.b.e.d().a((com.netease.nimlib.net.a.a.e) this.c);
                    return false;
                }
                com.netease.nimlib.net.a.a.g.a().b((com.netease.nimlib.net.a.a.e) this.c);
                return false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(com.netease.nimlib.net.a.a.e eVar) {
        String f = eVar.f();
        if (TextUtils.isEmpty(f)) {
            return;
        }
        String str = null;
        try {
            str = Uri.parse(f).getQueryParameter("token");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.d.d.a(str));
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public String convertDownloadUrlToCDNUrl(String str) {
        return com.netease.nimlib.net.a.c.d.a(str);
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public InvocationFuture<String> getOriginUrlFromShortUrl(String str) {
        getOriginUrlFromShortUrl(null, str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.nos.NosService
    public InvocationFuture<String> getOriginUrlFromShortUrl(String str, final String str2) {
        com.netease.nimlib.i.k b = b();
        if (TextUtils.isEmpty(str2)) {
            b.b(str2).b();
            return null;
        }
        String str3 = this.a.get(str2);
        if (!TextUtils.isEmpty(str3)) {
            b.b(str3).b();
            return null;
        }
        if (TextUtils.isEmpty(str2) || !com.netease.nimlib.net.a.a.g.e(str2)) {
            b.b(str2).b();
            this.a.put(str2, str2);
            return null;
        }
        com.netease.nimlib.biz.d.d.k kVar = new com.netease.nimlib.biz.d.d.k(str2);
        kVar.a(b);
        IChatRoomInteract iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class);
        com.netease.nimlib.biz.g.b bVar = new com.netease.nimlib.biz.g.b(kVar) { // from class: com.netease.nimlib.biz.f.h.6
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                com.netease.nimlib.biz.e.d.h hVar;
                super.a(aVar);
                if (!(aVar instanceof com.netease.nimlib.biz.e.d.h) || (hVar = (com.netease.nimlib.biz.e.d.h) aVar) == null || TextUtils.isEmpty(hVar.a())) {
                    return;
                }
                h.this.a.put(str2, hVar.a());
            }
        };
        if (a(str)) {
            iChatRoomInteract.addSendTask(bVar, str);
        } else {
            com.netease.nimlib.biz.i.a().a(bVar);
        }
        return null;
    }

    private boolean a(String str) {
        IChatRoomInteract iChatRoomInteract;
        if (TextUtils.isEmpty(str) || (iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class)) == null) {
            return false;
        }
        return iChatRoomInteract.independent(str);
    }

    private a.c a(final NosTransferInfo nosTransferInfo, String str, final com.netease.nimlib.i.k kVar, String str2, boolean z) {
        nosTransferInfo.setStatus(NosTransferStatus.transferring);
        com.netease.nimlib.i.b.a(nosTransferInfo);
        return com.netease.nimlib.net.a.b.a.a().a(null, nosTransferInfo.getPath(), str, nosTransferInfo.getMd5(), kVar, str2, z, new com.netease.nimlib.net.a.b.c<com.netease.nimlib.i.k>() { // from class: com.netease.nimlib.biz.f.h.7
            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, long j, long j2) {
                com.netease.nimlib.i.b.b(nosTransferInfo.getPath(), j, j2);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, String str3) {
                nosTransferInfo.setStatus(NosTransferStatus.transferred);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                kVar.b(str3).b();
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, int i, String str3) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                kVar.a(i).b();
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2) {
                a(kVar2, 400, (String) null);
            }
        });
    }

    private com.netease.nimlib.net.a.a.e a(final NosTransferInfo nosTransferInfo, NosThumbParam nosThumbParam, final com.netease.nimlib.i.k kVar) {
        final String url = nosTransferInfo.getUrl();
        String path = nosTransferInfo.getPath();
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(path)) {
            return null;
        }
        if (new File(path).exists()) {
            kVar.b((Object) null).b();
            return null;
        }
        com.netease.nimlib.net.a.a.f fVar = new com.netease.nimlib.net.a.a.f() { // from class: com.netease.nimlib.biz.f.h.8
            private long e;

            @Override // com.netease.nimlib.net.a.a.f
            public void onStart(com.netease.nimlib.net.a.a.e eVar) {
                nosTransferInfo.setStatus(NosTransferStatus.transferring);
                com.netease.nimlib.i.b.a(nosTransferInfo);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onOK(com.netease.nimlib.net.a.a.e eVar) {
                nosTransferInfo.setStatus(NosTransferStatus.transferred);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                kVar.b((Object) null).b();
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onFail(com.netease.nimlib.net.a.a.e eVar, String str) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                h.this.a(kVar, 415);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onCancel(com.netease.nimlib.net.a.a.e eVar) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onProgress(com.netease.nimlib.net.a.a.e eVar, long j) {
                com.netease.nimlib.i.b.b(url, j, this.e);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onGetLength(com.netease.nimlib.net.a.a.e eVar, long j) {
                this.e = j;
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onExpire(com.netease.nimlib.net.a.a.e eVar, String str) {
                nosTransferInfo.setStatus(NosTransferStatus.fail);
                com.netease.nimlib.i.b.a(nosTransferInfo);
                h.this.a(kVar, 4);
            }
        };
        if (com.netease.nimlib.biz.b.e.d().a()) {
            if (nosThumbParam == null) {
                return com.netease.nimlib.biz.b.e.d().a(url, path, fVar);
            }
            return com.netease.nimlib.biz.b.e.d().a(url, path, nosThumbParam.width, nosThumbParam.height, fVar);
        }
        if (nosThumbParam != null) {
            url = com.netease.nimlib.net.a.c.d.a(url, a(nosThumbParam.thumb), nosThumbParam.width, nosThumbParam.height);
        }
        com.netease.nimlib.net.a.a.e eVar = new com.netease.nimlib.net.a.a.e(url, path, fVar);
        com.netease.nimlib.net.a.a.g.a().a(eVar);
        return eVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.netease.nimlib.i.k kVar, int i) {
        if (kVar != null) {
            kVar.a(i).b();
        }
    }

    /* compiled from: NosServiceRemote.java */
    /* renamed from: com.netease.nimlib.biz.f.h$9, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NosThumbParam.ThumbType.values().length];
            a = iArr;
            try {
                iArr[NosThumbParam.ThumbType.Internal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NosThumbParam.ThumbType.External.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NosThumbParam.ThumbType.Crop.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private com.netease.nimlib.net.a.c.e a(NosThumbParam.ThumbType thumbType) {
        int i = AnonymousClass9.a[thumbType.ordinal()];
        if (i == 1) {
            return com.netease.nimlib.net.a.c.e.Internal;
        }
        if (i == 2) {
            return com.netease.nimlib.net.a.c.e.External;
        }
        return com.netease.nimlib.net.a.c.e.Crop;
    }
}
