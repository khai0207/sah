package com.talkingdata.sdk;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

/* compiled from: td */
/* loaded from: classes.dex */
final class ch extends PhoneStateListener {
    static final long a = 180000;
    int d;
    long b = 0;
    long c = 0;
    int e = 0;

    ch() {
    }

    @Override // android.telephony.PhoneStateListener
    public void onCellLocationChanged(CellLocation cellLocation) {
        try {
            if (cellLocation.getClass().equals(GsmCellLocation.class)) {
                this.d = ((GsmCellLocation) cellLocation).getLac();
                a();
            } else if (cellLocation.getClass().equals(CdmaCellLocation.class)) {
                this.d = ((CdmaCellLocation) cellLocation).getNetworkId();
                a();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.b = currentTimeMillis;
            if (this.d == this.e || this.d <= 1 || currentTimeMillis - this.c <= a) {
                return;
            }
            cn cnVar = new cn();
            cnVar.b = "env";
            cnVar.c = "cellUpdate";
            cnVar.a = a.e;
            bk.a().post(cnVar);
            this.c = this.b;
            this.e = this.d;
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }
}
