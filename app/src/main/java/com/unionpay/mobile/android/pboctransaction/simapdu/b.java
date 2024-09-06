package com.unionpay.mobile.android.pboctransaction.simapdu;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.unionpay.mobile.android.pboctransaction.AppIdentification;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.simapdu.a;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.data.AppStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;

/* loaded from: classes.dex */
public final class b implements com.unionpay.mobile.android.pboctransaction.c {
    private static b d = new b();
    private com.unionpay.mobile.android.pboctransaction.b c;
    private SEService a = null;
    private Channel[] b = new Channel[3];
    private Handler.Callback e = new c(this);
    private Handler f = new Handler(this.e);

    private b() {
    }

    private synchronized String a(String str, int i) throws a.C0070a {
        String str2 = null;
        if (str == null) {
            return null;
        }
        j.a("plugin-sim", "====>" + str);
        String upperCase = str.toUpperCase(Locale.CHINA);
        if (i > this.b.length) {
            i = 0;
        }
        if (upperCase.startsWith("00A40400") || upperCase.startsWith("01A40400") || upperCase.startsWith("02A40400")) {
            a(i);
            String b = b(e.a(upperCase.substring(10, (((Integer.parseInt(upperCase.substring(8, 9), 16) * 16) + Integer.parseInt(upperCase.substring(9, 10), 16)) * 2) + 10)), i);
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            j.c("plugin-sim", " writeApdu openchannel exception!!!");
            throw new a.C0070a();
        }
        try {
            try {
                byte[] a = e.a(upperCase);
                if (a != null) {
                    str2 = e.a(this.b[i].transmit(a));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            j.a("plugin-sim", "<====" + str2);
            return str2;
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new a.C0070a();
        }
    }

    private void a(int i) {
        j.a("plugin-sim", "closeChannel(int) +++");
        Channel[] channelArr = this.b;
        if (channelArr[i] != null && i <= channelArr.length) {
            try {
                channelArr[i].close();
            } catch (Exception e) {
                e.printStackTrace();
                j.a("plugin-sim", " mChannel[channel].close() exception!!!");
            }
            this.b[i] = null;
        }
        j.a("plugin-sim", "closeChannel(int) ---");
    }

    static /* synthetic */ com.unionpay.mobile.android.pboctransaction.b b(b bVar) {
        bVar.c = null;
        return null;
    }

    private String b(byte[] bArr, int i) {
        Channel openLogicalChannel;
        try {
            Reader[] readers = this.a.getReaders();
            if (readers.length <= 0 || (openLogicalChannel = readers[0].openSession().openLogicalChannel(bArr)) == null) {
                return "";
            }
            this.b[i] = openLogicalChannel;
            return e.a(openLogicalChannel.getSelectResponse());
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static synchronized b e() {
        b bVar;
        synchronized (b.class) {
            bVar = d;
        }
        return bVar;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final String a(String str) {
        return "";
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        ArrayList arrayList;
        String a;
        j.c("plugin-sim", " SIMEngine.readList() +++");
        ArrayList<com.unionpay.mobile.android.model.c> arrayList2 = null;
        try {
            arrayList = new ArrayList(1);
            a = dVar.a("A0000003330101");
            j.c("plugin-sim", "full AID:" + a);
        } catch (Throwable th) {
            th = th;
        }
        if (a != null && a.length() >= 16) {
            arrayList.add(new AppIdentification(a, null));
            if (arrayList.size() > 0) {
                ArrayList<com.unionpay.mobile.android.model.c> arrayList3 = new ArrayList<>();
                try {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        AppIdentification appIdentification = (AppIdentification) it.next();
                        if (!AppStatus.APPLY.equalsIgnoreCase(appIdentification.b())) {
                            String c = e.c(dVar.a(appIdentification));
                            j.a("nfcphone", " cardNumber=" + c);
                            if (c != null && c.length() > 0) {
                                arrayList3.add(new com.unionpay.mobile.android.model.a(16, appIdentification.a(), "", c, 1));
                                j.a("nfcphone", " valid Number= " + c);
                            }
                        }
                    }
                    arrayList2 = arrayList3;
                } catch (Throwable th2) {
                    th = th2;
                    arrayList2 = arrayList3;
                    Log.e("plugin-sim", " SimEngine Exception = " + th.getMessage());
                    j.c("plugin-sim", " SIMEngine.readList() ---");
                    return arrayList2;
                }
            } else {
                Log.e("plugin-sim", " SIMEngine --- there has no PBOC aids!!!");
            }
            j.c("plugin-sim", " SIMEngine.readList() ---");
            return arrayList2;
        }
        return null;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a() {
        j.c("plugin-sim", "SIMEngine.destroy() +++ ");
        j.c("plugin-sim", " mSEService = " + this.a);
        d();
        SEService sEService = this.a;
        if (sEService != null && sEService.isConnected()) {
            j.a("TAG", " mSEService.isConnected() = " + this.a.isConnected());
            j.c("plugin-sim", " mSEService.shutdown() ");
            this.a.shutdown();
        }
        j.c("plugin-sim", "SIMEngine.destroy() --- ");
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a(com.unionpay.mobile.android.pboctransaction.b bVar, Context context) {
        this.c = bVar;
        try {
            new k();
            if (k.a() == null || !k.a().isConnected()) {
                this.f.sendEmptyMessage(2);
            } else {
                this.a = k.a();
                this.f.sendEmptyMessage(1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            Log.e("plugin-sim", " service ERROR!!!");
            this.f.sendEmptyMessage(2);
        }
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final byte[] a(byte[] bArr, int i) {
        byte[] bArr2;
        j.c("plugin-sim", " SIMEngine.sendApdu() +++");
        try {
            bArr2 = e.a(a(e.a(bArr), i));
        } catch (a.C0070a e) {
            e.printStackTrace();
            j.c("plugin-sim", " " + e.getMessage());
            bArr2 = null;
        }
        j.c("plugin-sim", " SIMEngine.sendApdu() ---");
        return bArr2;
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
        j.a("plugin-sim", "closeChannels() +++");
        for (int i = 0; i < this.b.length; i++) {
            a(i);
        }
        j.a("plugin-sim", "closeChannels() ---");
    }
}
