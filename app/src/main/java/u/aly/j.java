package u.aly;

import android.content.Context;

/* compiled from: UMIdTracker.java */
/* loaded from: classes.dex */
public class j extends a {
    private static final String a = "idmd5";
    private Context b;

    public j(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.a
    public String f() {
        return bt.g(this.b);
    }
}
