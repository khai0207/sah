package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.unionpay.mobile.android.pboctransaction.AppIdentification;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.tsm.connect.IInitCallback;
import com.unionpay.mobile.tsm.connect.IRemoteApdu;
import com.unionpay.tsmservice.data.AppStatus;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class a implements com.unionpay.mobile.android.pboctransaction.c {
    com.unionpay.mobile.android.pboctransaction.b a;
    private IRemoteApdu b = null;
    private boolean c = false;
    private Context d = null;
    private final ServiceConnection e = new b(this);
    private final IInitCallback.Stub f = new c(this);

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final String a(String str) {
        return "";
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        ArrayList<AppIdentification> arrayList;
        String c;
        j.a("plugin-tsm", "RemoteApduEngine.readList() +++");
        ArrayList<com.unionpay.mobile.android.model.c> arrayList2 = null;
        try {
            String str = this.c ? "D15600010100016111000000B0004101" : "D15600010100016111000000B0004001";
            j.a("plugin-tsm", "sid=" + str);
            String writeApdu = this.b.writeApdu("00a4040010" + str, 0);
            if (writeApdu != null && writeApdu.equalsIgnoreCase("9000")) {
                writeApdu = this.b.writeApdu("80CA2F0000", 0);
            }
            arrayList = e.b(writeApdu);
        } catch (Exception e) {
            e.printStackTrace();
            j.c("plugin-tsm", e.getMessage());
            arrayList = null;
        }
        if (arrayList != null && arrayList.size() > 0) {
            arrayList2 = new ArrayList<>();
            Iterator<AppIdentification> it = arrayList.iterator();
            while (it.hasNext()) {
                AppIdentification next = it.next();
                if (next.c() && !AppStatus.APPLY.equalsIgnoreCase(next.b()) && (c = e.c(dVar.a(next))) != null && c.length() > 0) {
                    arrayList2.add(new com.unionpay.mobile.android.model.a(4, next.a(), "", c, 1));
                }
            }
        }
        j.a("plugin-tsm", "RemoteApduEngine.readList() ---");
        return arrayList2;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a() {
        d();
        Context context = this.d;
        if (context != null) {
            j.a("plugin-tsm", "unbindTSMService() ++");
            new Intent("com.unionpay.mobile.tsm.PBOCService").setPackage("com.unionpay.mobile.tsm");
            context.unbindService(this.e);
        }
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a(com.unionpay.mobile.android.pboctransaction.b bVar, Context context) {
        this.a = bVar;
        this.d = context;
        Intent intent = new Intent("com.unionpay.mobile.tsm.PBOCService");
        intent.setPackage("com.unionpay.mobile.tsm");
        context.startService(intent);
        if (context.bindService(intent, this.e, 1) || this.a == null) {
            return;
        }
        j.a("plugin-tsm", "startTSMService.initFailed()");
        this.a.b();
    }

    public final void a(boolean z) {
        this.c = z;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final byte[] a(byte[] bArr, int i) {
        String str = null;
        if (bArr == null) {
            return null;
        }
        String a = e.a(bArr);
        j.a("plugin-tsm", "[---->]" + a);
        try {
            str = this.b.writeApdu(a, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        j.a("plugin-tsm", "[<----]" + str);
        return e.a(str);
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void b() {
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void c() {
        d();
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void d() {
        IRemoteApdu iRemoteApdu = this.b;
        if (iRemoteApdu != null) {
            try {
                iRemoteApdu.closeChannel(0);
                this.b.closeChannel(1);
                this.b.closeChannel(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
