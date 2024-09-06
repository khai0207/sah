package u.aly;

import android.content.Context;

/* compiled from: MacTracker.java */
/* loaded from: classes.dex */
public class h extends a {
    private static final String a = "mac";
    private Context b;

    public h(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.a
    public String f() {
        try {
            return bt.q(this.b);
        } catch (Exception unused) {
            return null;
        }
    }
}
