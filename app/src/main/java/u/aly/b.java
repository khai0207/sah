package u.aly;

import android.content.Context;
import android.provider.Settings;

/* compiled from: AndroidIdTracker.java */
/* loaded from: classes.dex */
public class b extends a {
    private static final String a = "android_id";
    private Context b;

    public b(Context context) {
        super(a);
        this.b = context;
    }

    @Override // u.aly.a
    public String f() {
        try {
            return Settings.Secure.getString(this.b.getContentResolver(), a);
        } catch (Exception unused) {
            return null;
        }
    }
}
