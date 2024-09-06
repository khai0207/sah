package com.netease.nimlib.fusionstorage.crossplatform;

import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.biz.d.d.f;
import com.netease.nimlib.biz.d.d.h;
import com.netease.nimlib.biz.e.d.d;
import com.netease.nimlib.biz.e.d.e;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Credential;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Policy;
import com.netease.nimlib.fusionstorage.crossplatform.defines.StorageProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StorageUtils.java */
/* loaded from: classes.dex */
public class d {
    public static void a(final WeakReference<StorageManager> weakReference) {
        i.a().a(new com.netease.nimlib.biz.g.b(new h()) { // from class: com.netease.nimlib.fusionstorage.crossplatform.d.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                super.a(aVar);
                com.netease.nimlib.log.b.d("StorageUtils", "upload [SID 6,CID 28] response: " + aVar);
                if ((aVar instanceof e) && aVar.n()) {
                    com.netease.nimlib.push.packet.b.c b = ((e) aVar).b();
                    int d = b.d(1);
                    int d2 = b.d(5);
                    ArrayList arrayList = new ArrayList();
                    String c = b.c(3);
                    if (!TextUtils.isEmpty(c)) {
                        arrayList.add(Policy.fromJson(c, StorageProvider.STORAGE_PROVIDER_NOS.getValue()));
                    }
                    String c2 = b.c(4);
                    if (!TextUtils.isEmpty(c2)) {
                        arrayList.add(Policy.fromJson(c2, StorageProvider.STORAGE_PROVIDER_AWS_S3.getValue()));
                    }
                    StorageManager storageManager = (StorageManager) weakReference.get();
                    if (storageManager != null) {
                        storageManager.a(arrayList, d, d2, com.netease.nimlib.n.f.a.a(true), false);
                    }
                }
            }
        });
    }

    public static void a(final int i, final Integer num, final String str, long j, final WeakReference<StorageManager> weakReference, final WeakReference<StorageListener> weakReference2) {
        StorageListener storageListener = weakReference2.get();
        if (storageListener != null) {
            storageListener.appendMixStoreAuthorizationCache(i, num, str);
        }
        final f fVar = new f(num, 2, j, str);
        i.a().a(new com.netease.nimlib.biz.g.b(fVar) { // from class: com.netease.nimlib.fusionstorage.crossplatform.d.2
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                StorageManager storageManager;
                short c = fVar.c();
                if (aVar.n()) {
                    com.netease.nimlib.biz.e.d.d dVar = (com.netease.nimlib.biz.e.d.d) aVar;
                    ArrayList arrayList = new ArrayList();
                    if (com.netease.nimlib.o.f.d(dVar.b())) {
                        com.netease.nimlib.log.b.d("StorageUtils", "upload [SID 6,CID 29] response: fun_id = " + ((int) c) + ", code = " + ((int) aVar.r()) + ", sn = -1, getProperties.size = " + dVar.b().size());
                        for (com.netease.nimlib.push.packet.b.c cVar : dVar.b()) {
                            arrayList.add(new Credential(a.a(cVar.d(d.a.provider.a())), cVar.c(d.a.accessKeyId.a()), cVar.c(d.a.secretAccessKey.a()), cVar.c(d.a.sessionToken.a()), cVar.c(d.a.Token.a()), cVar.c(d.a.bucket.a()), Uri.decode(cVar.c(d.a.objectName.a())), cVar.e(d.a.fileExpireSec.a()), cVar.e(d.a.expireAt.a()), cVar.c(d.a.tag.a()), cVar.c(d.a.shortUrl.a()), cVar.c(d.a.region.a())));
                        }
                    }
                    com.netease.nimlib.log.b.d("StorageUtils", "upload [SID 6,CID 29] response: fun_id = " + ((int) c) + ", code = " + ((int) aVar.r()) + ", sn = -1, credentials.size = " + arrayList.size());
                    if (com.netease.nimlib.o.f.d(arrayList) && (storageManager = (StorageManager) weakReference.get()) != null) {
                        storageManager.a(a.a(fVar.d()), fVar.e(), (List<Credential>) arrayList, false);
                    }
                } else {
                    com.netease.nimlib.log.b.e("StorageUtils", "upload [SID 6,CID 29] response: fun_id = " + ((int) c) + ", code = " + ((int) aVar.r()) + ", sn = -1, body = null");
                }
                StorageListener storageListener2 = (StorageListener) weakReference2.get();
                if (storageListener2 != null) {
                    storageListener2.markRequestMixStoreAuthorizationComplete(i, num, str);
                }
            }
        });
    }
}
