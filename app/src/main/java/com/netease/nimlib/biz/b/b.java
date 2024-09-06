package com.netease.nimlib.biz.b;

import com.netease.nim.highavailable.FCSUploadCallback;
import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;
import com.netease.nimlib.net.a.a.g;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FCSNextManager.java */
/* loaded from: classes.dex */
public class b extends a {
    private final Map<String, Boolean> a = new HashMap();

    @Override // com.netease.nimlib.biz.b.a
    public void c() {
    }

    @Override // com.netease.nimlib.biz.b.a
    public synchronized boolean b() {
        return a(com.netease.nimlib.c.g());
    }

    private synchronized boolean a(String str) {
        boolean z;
        Boolean bool = this.a.get(str);
        if (bool != null) {
            z = bool.booleanValue();
        }
        return z;
    }

    @Override // com.netease.nimlib.biz.b.a
    public synchronized void a(final com.netease.nimlib.c.a<Boolean> aVar) {
        final String g = com.netease.nimlib.c.g();
        if (a(g)) {
            aVar.onCallback(true);
            return;
        }
        c b = com.netease.nimlib.plugin.c.a().b();
        if (b == null) {
            aVar.onCallback(false);
        } else {
            b.a(new com.netease.nimlib.c.a<Boolean>() { // from class: com.netease.nimlib.biz.b.b.1
                @Override // com.netease.nimlib.c.a
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onCallback(Boolean bool) {
                    if (bool != null) {
                        b.this.a.put(g, bool);
                        aVar.onCallback(bool);
                    } else {
                        aVar.onCallback(false);
                    }
                }
            }, g);
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void a(String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback, f fVar) {
        c b = com.netease.nimlib.plugin.c.a().b();
        if (b == null) {
            com.netease.nimlib.log.b.e("FCSNextManager", String.format("upload without fcsPlugin: filePath %s, mimeType %s, sceneKey %s, timeOut %s, md5 %s, forceUpload %s, callback %s", str, str2, str3, Integer.valueOf(i), str4, Boolean.valueOf(z), fCSUploadCallback));
            fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, 0, "");
            fVar.a(-1L);
            return;
        }
        b.a(str, str2, str3, i, str4, z, fCSUploadCallback, fVar);
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void b(f fVar) {
        c b = com.netease.nimlib.plugin.c.a().b();
        if (b != null) {
            b.a(fVar);
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void a(HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, d dVar) {
        c b = com.netease.nimlib.plugin.c.a().b();
        if (b != null) {
            b.a(hAvailableFCSDownloadType, i, i2, dVar);
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void a(d dVar) {
        g.a().b(dVar);
    }
}
