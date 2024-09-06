package u.aly;

import android.content.Context;
import android.telephony.TelephonyManager;

/* compiled from: ImeiTracker.java */
/* loaded from: classes.dex */
public class f extends a {
    private static final String a = "imei";
    private Context b;

    public f(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.a
    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        try {
            if (bt.a(this.b, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
