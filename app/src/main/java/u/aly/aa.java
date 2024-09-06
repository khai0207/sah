package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import java.util.Arrays;
import java.util.List;

/* compiled from: SessionTracker.java */
/* loaded from: classes.dex */
public class aa {
    private static final String a = "session_start_time";
    private static final String b = "session_end_time";
    private static final String c = "session_id";
    private static final String f = "activities";
    private static String g;
    private final String d = "a_start_time";
    private final String e = "a_end_time";

    public ak a(Context context) {
        SharedPreferences a2 = y.a(context);
        String string = a2.getString("session_id", null);
        if (string == null) {
            return null;
        }
        long j = 0;
        long j2 = a2.getLong(a, 0L);
        long j3 = a2.getLong(b, 0L);
        if (j3 != 0) {
            long j4 = j3 - j2;
            if (Math.abs(j4) <= com.umeng.analytics.a.h) {
                j = j4;
            }
        }
        ak akVar = new ak();
        akVar.a(string);
        akVar.a(j2);
        akVar.b(j3);
        akVar.c(j);
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            bg bgVar = new bg(location[0], location[1], System.currentTimeMillis());
            if (akVar.y()) {
                akVar.a(bgVar);
            } else {
                akVar.b(Arrays.asList(bgVar));
            }
        }
        bo a3 = ad.a(context);
        if (a3 != null) {
            akVar.a(a3);
        }
        List<bi> a4 = ae.a(a2);
        if (a4 != null && a4.size() > 0) {
            akVar.a(a4);
        }
        a(a2);
        return akVar;
    }

    private void a(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(a);
        edit.remove(b);
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.putString(f, "");
        edit.commit();
    }

    public String b(Context context) {
        String f2 = bt.f(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        String a2 = bu.a(currentTimeMillis + appkey + f2);
        g = a2;
        return a2;
    }

    public void c(Context context) {
        SharedPreferences a2 = y.a(context);
        if (a2 == null) {
            return;
        }
        if (b(a2)) {
            bv.c("Start new session: " + a(context, a2));
            return;
        }
        String string = a2.getString("session_id", null);
        SharedPreferences.Editor edit = a2.edit();
        edit.putLong("a_start_time", System.currentTimeMillis());
        edit.putLong("a_end_time", 0L);
        edit.commit();
        bv.c("Extend current session: " + string);
    }

    public void d(Context context) {
        SharedPreferences a2 = y.a(context);
        if (a2 == null) {
            return;
        }
        if (a2.getLong("a_start_time", 0L) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            bv.e("onPause called before onResume");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = a2.edit();
        edit.putLong("a_start_time", 0L);
        edit.putLong("a_end_time", currentTimeMillis);
        edit.putLong(b, currentTimeMillis);
        edit.commit();
    }

    private boolean b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0L);
        long j2 = sharedPreferences.getLong("a_end_time", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (j == 0 || currentTimeMillis - j >= AnalyticsConfig.kContinueSessionMillis) {
            return currentTimeMillis - j2 > AnalyticsConfig.kContinueSessionMillis;
        }
        bv.e("onResume called before onPause");
        return false;
    }

    private String a(Context context, SharedPreferences sharedPreferences) {
        m a2 = m.a(context);
        String b2 = b(context);
        ak a3 = a(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("session_id", b2);
        edit.putLong(a, System.currentTimeMillis());
        edit.putLong(b, 0L);
        edit.putLong("a_start_time", System.currentTimeMillis());
        edit.putLong("a_end_time", 0L);
        edit.commit();
        if (a3 != null) {
            a2.a(a3);
        } else {
            a2.a((ak) null);
        }
        return b2;
    }

    public boolean e(Context context) {
        SharedPreferences a2 = y.a(context);
        boolean z = false;
        if (a2 == null || a2.getString("session_id", null) == null) {
            return false;
        }
        long j = a2.getLong("a_start_time", 0L);
        long j2 = a2.getLong("a_end_time", 0L);
        if (j > 0 && j2 == 0) {
            z = true;
            d(context);
        }
        m a3 = m.a(context);
        ak a4 = a(context);
        if (a4 != null) {
            a3.b(a4);
        }
        return z;
    }

    public void f(Context context) {
        SharedPreferences a2 = y.a(context);
        if (a2 == null) {
            return;
        }
        String b2 = b(context);
        SharedPreferences.Editor edit = a2.edit();
        edit.putString("session_id", b2);
        edit.putLong(a, System.currentTimeMillis());
        edit.putLong(b, 0L);
        edit.putLong("a_start_time", System.currentTimeMillis());
        edit.putLong("a_end_time", 0L);
        edit.commit();
        bv.c("Restart session: " + b2);
    }

    public static String g(Context context) {
        if (g == null) {
            g = y.a(context).getString("session_id", null);
        }
        return g;
    }
}
