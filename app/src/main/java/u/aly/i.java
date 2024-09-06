package u.aly;

import android.os.Build;

/* compiled from: SerialTracker.java */
/* loaded from: classes.dex */
public class i extends a {
    private static final String a = "serial";

    public i() {
        super(a);
    }

    @Override // u.aly.a
    public String f() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}
