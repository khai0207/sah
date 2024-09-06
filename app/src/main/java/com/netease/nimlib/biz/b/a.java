package com.netease.nimlib.biz.b;

import com.netease.nim.highavailable.FCSUploadCallback;
import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;
import com.netease.nim.highavailable.enums.HAvailableFCSUploadPluginTag;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.n.b.h;
import com.netease.nimlib.n.b.n;
import com.netease.nimlib.n.o;

/* compiled from: AbstractFCSManager.java */
/* loaded from: classes.dex */
public abstract class a {
    protected abstract void a(HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, d dVar);

    protected abstract void a(d dVar);

    public abstract void a(com.netease.nimlib.c.a<Boolean> aVar);

    protected abstract void a(String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback, f fVar);

    protected abstract void b(f fVar);

    public abstract boolean b();

    public abstract void c();

    public boolean a() {
        boolean f = l.f();
        boolean D = com.netease.nimlib.c.D();
        boolean z = f && D;
        com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "current mix store enable = " + f + ",enableFcs option = " + D);
        return z;
    }

    public com.netease.nimlib.net.a.b.f.b a(String str, String str2, String str3, String str4, Object obj, boolean z, com.netease.nimlib.net.a.b.c<Object> cVar) {
        f fVar = new f(obj, cVar);
        if (!b()) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_UPLOAD upload  highavailable is not init ");
            a(new $$Lambda$a$UJl63AcqHMoDwxWao5Arp136Xw0(this, str, str2, str3, str4, obj, z, cVar, fVar));
        } else {
            a(str, str2, str3, str4, obj, z, cVar, fVar);
        }
        return fVar;
    }

    public /* synthetic */ void a(String str, String str2, String str3, String str4, Object obj, boolean z, com.netease.nimlib.net.a.b.c cVar, f fVar, Boolean bool) {
        a(str, str2, str3, str4, obj, z, (com.netease.nimlib.net.a.b.c<Object>) cVar, fVar);
    }

    private com.netease.nimlib.net.a.b.f.b a(String str, String str2, String str3, String str4, Object obj, boolean z, com.netease.nimlib.net.a.b.c<Object> cVar, f fVar) {
        com.netease.nimlib.log.b.c("HighAvailableManagerInterface", "FCS_UPLOAD upload filePath = " + str + ",sceneKey = " + str3 + ",forceUpload = " + z + ",mimeType = " + str2);
        a(str, str2, str3, 30, str4, z, a(str4, obj, cVar), fVar);
        StringBuilder sb = new StringBuilder();
        sb.append("FCS_UPLOAD uploadTask =  ");
        sb.append(fVar);
        com.netease.nimlib.log.b.c("HighAvailableManagerInterface", sb.toString());
        return fVar;
    }

    public void a(f fVar) {
        if (!b()) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_UPLOAD cancelUpload  highavailable is not init ");
            a(new $$Lambda$a$I6nISPpKH9GtFZ_MqRq6w6tfcxI(this, fVar));
        } else {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_UPLOAD cancelUpload stopUpload");
            b(fVar);
        }
    }

    public /* synthetic */ void a(f fVar, Boolean bool) {
        com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_UPLOAD cancelUpload stopUpload");
        b(fVar);
    }

    public com.netease.nimlib.net.a.a.e a(String str, String str2, com.netease.nimlib.net.a.a.f fVar) {
        d dVar = new d(str, str2, fVar);
        HAvailableFCSDownloadType hAvailableFCSDownloadType = HAvailableFCSDownloadType.kSource;
        com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD downloadSource");
        if (!b()) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD downloadSource highavailable is not init ");
            a(new $$Lambda$a$0at2o0XcGQf_VAvEnVEyE47SsQ(this, hAvailableFCSDownloadType, dVar));
        } else {
            a(hAvailableFCSDownloadType, 0, 0, dVar);
        }
        return dVar;
    }

    public /* synthetic */ void a(HAvailableFCSDownloadType hAvailableFCSDownloadType, d dVar, Boolean bool) {
        a(hAvailableFCSDownloadType, 0, 0, dVar);
    }

    public com.netease.nimlib.net.a.a.e a(String str, String str2, int i, int i2, com.netease.nimlib.net.a.a.f fVar) {
        d dVar = new d(str, str2, fVar);
        HAvailableFCSDownloadType hAvailableFCSDownloadType = HAvailableFCSDownloadType.kThumbnail;
        com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD downloadThumbnail");
        if (!b()) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD downloadThumbnail highavailable is not init ");
            a(new $$Lambda$a$vF4uT5N8G0IpCgpjaXupgGhWBik(this, hAvailableFCSDownloadType, i, i2, dVar));
        } else {
            a(hAvailableFCSDownloadType, i, i2, dVar);
        }
        return dVar;
    }

    public /* synthetic */ void b(HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, d dVar, Boolean bool) {
        a(hAvailableFCSDownloadType, i, i2, dVar);
    }

    public com.netease.nimlib.net.a.a.e b(String str, String str2, int i, int i2, com.netease.nimlib.net.a.a.f fVar) {
        d dVar = new d(str, str2, fVar);
        HAvailableFCSDownloadType hAvailableFCSDownloadType = HAvailableFCSDownloadType.kVideoCover;
        com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD downloadVideoCover");
        if (!b()) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD downloadVideoCover highavailable is not init ");
            a(new $$Lambda$a$Z4pZLsrPqEiqCPBkYMksHQPGzeQ(this, hAvailableFCSDownloadType, i, i2, dVar));
        } else {
            a(hAvailableFCSDownloadType, i, i2, dVar);
        }
        return dVar;
    }

    public /* synthetic */ void a(HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, d dVar, Boolean bool) {
        a(hAvailableFCSDownloadType, i, i2, dVar);
    }

    public void a(com.netease.nimlib.net.a.a.e eVar) {
        if (!b()) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", "FCS_DOWNLOAD cancelDownload highavailable is not init ");
            a(new $$Lambda$a$cjCZY8_Q7ufkZPYo42S9vKGcFZM(this, eVar));
        } else if (eVar instanceof d) {
            a((d) eVar);
        }
    }

    public /* synthetic */ void a(com.netease.nimlib.net.a.a.e eVar, Boolean bool) {
        if (eVar instanceof d) {
            a((d) eVar);
        }
    }

    /* compiled from: AbstractFCSManager.java */
    /* renamed from: com.netease.nimlib.biz.b.a$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements FCSUploadCallback {
        final /* synthetic */ String a;
        final /* synthetic */ com.netease.nimlib.net.a.b.c b;
        final /* synthetic */ Object c;

        @Override // com.netease.nim.highavailable.FCSUploadCallback
        public void onUploadSpeed(long j) {
        }

        AnonymousClass1(String str, com.netease.nimlib.net.a.b.c cVar, Object obj) {
            r1 = str;
            r2 = cVar;
            r3 = obj;
        }

        @Override // com.netease.nim.highavailable.FCSUploadCallback
        public void onUploadResult(HAvailableFCSErrorCode hAvailableFCSErrorCode, int i, String str) {
            com.netease.nimlib.net.a.b.c cVar;
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", String.format("%s onUploadResult resultCode = %s,httpCode = %s,url = %s", "FCS_UPLOAD", Integer.valueOf(hAvailableFCSErrorCode.getValue()), Integer.valueOf(i), com.netease.nimlib.log.b.a.a(str, com.netease.nimlib.c.i().logDesensitizationConfig)));
            o.a().a(r1, h.a(hAvailableFCSErrorCode).a(), str);
            if (hAvailableFCSErrorCode == HAvailableFCSErrorCode.kOK) {
                com.netease.nimlib.net.a.b.c cVar2 = r2;
                if (cVar2 != null) {
                    cVar2.a(r3, str);
                    return;
                }
                return;
            }
            if (hAvailableFCSErrorCode == HAvailableFCSErrorCode.kError) {
                com.netease.nimlib.net.a.b.c cVar3 = r2;
                if (cVar3 != null) {
                    cVar3.a((com.netease.nimlib.net.a.b.c) r3, i, "");
                    return;
                }
                return;
            }
            if (hAvailableFCSErrorCode != HAvailableFCSErrorCode.kCancel || (cVar = r2) == null) {
                return;
            }
            cVar.a(r3);
        }

        @Override // com.netease.nim.highavailable.FCSUploadCallback
        public void onUploadResume(long j, long j2, HAvailableFCSUploadPluginTag hAvailableFCSUploadPluginTag) {
            com.netease.nimlib.log.b.d("HighAvailableManagerInterface", String.format("%s onUploadResume transferred = %s, fileSize = %s, uploadPluginTag = %s", "HighAvailableManagerInterface", Long.valueOf(j), Long.valueOf(j2), hAvailableFCSUploadPluginTag));
            o.a().a(r1, n.a(hAvailableFCSUploadPluginTag).a(), j2, j);
        }

        @Override // com.netease.nim.highavailable.FCSUploadCallback
        public void onUploadProgress(long j, long j2) {
            o.a().a(r1, j);
            com.netease.nimlib.net.a.b.c cVar = r2;
            if (cVar == null || j2 <= 0) {
                return;
            }
            cVar.a((com.netease.nimlib.net.a.b.c) r3, j, j2);
        }
    }

    protected static FCSUploadCallback a(String str, Object obj, com.netease.nimlib.net.a.b.c<Object> cVar) {
        return new FCSUploadCallback() { // from class: com.netease.nimlib.biz.b.a.1
            final /* synthetic */ String a;
            final /* synthetic */ com.netease.nimlib.net.a.b.c b;
            final /* synthetic */ Object c;

            @Override // com.netease.nim.highavailable.FCSUploadCallback
            public void onUploadSpeed(long j) {
            }

            AnonymousClass1(String str2, com.netease.nimlib.net.a.b.c cVar2, Object obj2) {
                r1 = str2;
                r2 = cVar2;
                r3 = obj2;
            }

            @Override // com.netease.nim.highavailable.FCSUploadCallback
            public void onUploadResult(HAvailableFCSErrorCode hAvailableFCSErrorCode, int i, String str2) {
                com.netease.nimlib.net.a.b.c cVar2;
                com.netease.nimlib.log.b.d("HighAvailableManagerInterface", String.format("%s onUploadResult resultCode = %s,httpCode = %s,url = %s", "FCS_UPLOAD", Integer.valueOf(hAvailableFCSErrorCode.getValue()), Integer.valueOf(i), com.netease.nimlib.log.b.a.a(str2, com.netease.nimlib.c.i().logDesensitizationConfig)));
                o.a().a(r1, h.a(hAvailableFCSErrorCode).a(), str2);
                if (hAvailableFCSErrorCode == HAvailableFCSErrorCode.kOK) {
                    com.netease.nimlib.net.a.b.c cVar22 = r2;
                    if (cVar22 != null) {
                        cVar22.a(r3, str2);
                        return;
                    }
                    return;
                }
                if (hAvailableFCSErrorCode == HAvailableFCSErrorCode.kError) {
                    com.netease.nimlib.net.a.b.c cVar3 = r2;
                    if (cVar3 != null) {
                        cVar3.a((com.netease.nimlib.net.a.b.c) r3, i, "");
                        return;
                    }
                    return;
                }
                if (hAvailableFCSErrorCode != HAvailableFCSErrorCode.kCancel || (cVar2 = r2) == null) {
                    return;
                }
                cVar2.a(r3);
            }

            @Override // com.netease.nim.highavailable.FCSUploadCallback
            public void onUploadResume(long j, long j2, HAvailableFCSUploadPluginTag hAvailableFCSUploadPluginTag) {
                com.netease.nimlib.log.b.d("HighAvailableManagerInterface", String.format("%s onUploadResume transferred = %s, fileSize = %s, uploadPluginTag = %s", "HighAvailableManagerInterface", Long.valueOf(j), Long.valueOf(j2), hAvailableFCSUploadPluginTag));
                o.a().a(r1, n.a(hAvailableFCSUploadPluginTag).a(), j2, j);
            }

            @Override // com.netease.nim.highavailable.FCSUploadCallback
            public void onUploadProgress(long j, long j2) {
                o.a().a(r1, j);
                com.netease.nimlib.net.a.b.c cVar2 = r2;
                if (cVar2 == null || j2 <= 0) {
                    return;
                }
                cVar2.a((com.netease.nimlib.net.a.b.c) r3, j, j2);
            }
        };
    }
}
