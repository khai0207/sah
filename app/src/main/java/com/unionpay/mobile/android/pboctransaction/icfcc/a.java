package com.unionpay.mobile.android.pboctransaction.icfcc;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.unionpay.mobile.android.pboctransaction.AppIdentification;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class a implements com.unionpay.mobile.android.pboctransaction.c {
    private cn.gov.pbc.tsm.client.mobile.android.bank.service.a c;
    private com.unionpay.mobile.android.pboctransaction.b d;
    private Context e;
    private String a = null;
    private String b = "A0000003334355502D4D4F42494C45";
    private ServiceConnection f = new b(this);

    private byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            String a = e.a(bArr);
            if (b(a)) {
                if (a.contains(this.a)) {
                    j.c("icfcc", "pbocAID = " + this.a);
                    bArr2 = this.c.a(e.a(this.a), "00");
                } else if (a.contains(this.b)) {
                    j.c("icfcc", "upcardAID = " + this.b);
                    bArr2 = this.c.a(e.a(this.b), "01");
                }
            }
        } catch (Exception unused) {
        }
        j.c("icfcc", " openSEChannel result=" + e.a(bArr2));
        return bArr2;
    }

    private static boolean b(String str) {
        return str.startsWith("00A40400") || str.startsWith("01A40400") || str.startsWith("02A40400");
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final String a(String str) {
        return "";
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        if (this.c == null) {
            return null;
        }
        ArrayList<com.unionpay.mobile.android.model.c> arrayList = new ArrayList<>();
        try {
            try {
                String a = c.a(e.a(this.c.a(e.a("325041592e5359532e4444463031"), "00")), "4F");
                j.c("icfcc", "aid =" + a);
                if (a != null) {
                    this.a = a;
                    AppIdentification appIdentification = new AppIdentification(a, "");
                    String c = e.c(dVar.a(appIdentification));
                    if (c != null && c.length() > 0) {
                        j.c("icfcc", "  " + c);
                        arrayList.add(new com.unionpay.mobile.android.model.a(8, appIdentification.a(), "", c, 1));
                    }
                }
                try {
                    this.c.b("00");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return arrayList;
            } catch (Exception unused) {
                this.c.b("00");
                return null;
            } catch (Throwable th) {
                try {
                    this.c.b("00");
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a() {
        d();
        cn.gov.pbc.tsm.client.mobile.android.bank.service.a aVar = this.c;
        if (aVar != null) {
            try {
                aVar.a();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception unused) {
            }
        }
        if (this.e != null) {
            new Intent("com.unionpay.mobile.tsm.PBOCService");
            this.e.unbindService(this.f);
        }
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a(com.unionpay.mobile.android.pboctransaction.b bVar, Context context) {
        this.d = bVar;
        this.e = context;
        Intent intent = new Intent("cn.gov.pbc.tsm.client.mobile.android.bank.service");
        intent.setPackage("cn.gov.pbc.tsm.client.mobile.andorid");
        context.startService(intent);
        if (context.bindService(intent, this.f, 1) || bVar == null) {
            return;
        }
        j.a("icfcc", "startTSMService.initFailed()");
        bVar.b();
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final byte[] a(byte[] bArr, int i) {
        String a = e.a(bArr);
        j.c("icfcc", "====>" + a);
        byte[] bArr2 = null;
        if (this.c == null) {
            return null;
        }
        if (b(a)) {
            return a(bArr);
        }
        try {
            bArr2 = this.c.b(bArr);
        } catch (RemoteException | Exception unused) {
        }
        j.c("icfcc", "<====" + e.a(bArr2));
        return bArr2;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void b() {
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void c() {
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void d() {
        cn.gov.pbc.tsm.client.mobile.android.bank.service.a aVar = this.c;
        if (aVar != null) {
            try {
                aVar.b("00");
                this.c.b("01");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception unused) {
            }
        }
    }
}
