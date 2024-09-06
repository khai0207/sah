package u.aly;

import android.content.Context;

/* compiled from: IDFATracker.java */
/* loaded from: classes.dex */
public class d extends a {
    private static final String a = "idfa";
    private Context b;

    public d(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.a
    public String f() {
        String a2 = br.a(this.b);
        return a2 == null ? "" : a2;
    }
}
