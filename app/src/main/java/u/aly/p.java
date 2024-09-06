package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import java.util.HashMap;
import java.util.Map;

/* compiled from: EventTracker.java */
/* loaded from: classes.dex */
public class p {
    private final int a = 128;
    private final int b = 256;
    private n c;
    private Context d;
    private m e;

    public p(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null, can't track event");
        }
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        n nVar = new n(applicationContext);
        this.c = nVar;
        nVar.a(!AnalyticsConfig.ENABLE_MEMORY_BUFFER);
        this.e = m.a(this.d);
    }

    public void a(String str, Map<String, Object> map, long j) {
        try {
            if (a(str) && a(map)) {
                this.e.a(new ag(str, map, j, -1));
            }
        } catch (Exception e) {
            bv.e("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    public void a(String str, String str2, long j, int i) {
        if (a(str) && b(str2)) {
            HashMap hashMap = new HashMap();
            if (str2 == null) {
                str2 = "";
            }
            hashMap.put(str, str2);
            this.e.a(new ag(str, hashMap, j, i));
        }
    }

    public void a(String str, Map<String, Object> map) {
        try {
            if (a(str)) {
                this.e.a(new ai(str, map));
            }
        } catch (Exception e) {
            bv.e("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    public void a(String str, String str2) {
        if (a(str) && b(str2)) {
            this.c.a(ag.b(str, str2, null), ag.a(str, str2, null));
        }
    }

    public void b(String str, String str2) {
        af b;
        if (a(str) && b(str2) && (b = this.c.b(ag.b(str, str2, null))) != null) {
            a(str, str2, (int) (System.currentTimeMillis() - b.a), 0);
        }
    }

    public void a(String str, Map<String, Object> map, String str2) {
        if (a(str) && a(map)) {
            this.c.a(ag.b(str, str2, map), ag.a(str, str2, map));
        }
    }

    public void c(String str, String str2) {
        af b;
        if (a(str) && (b = this.c.b(ag.b(str, str2, null))) != null) {
            a(str, b.d, (int) (System.currentTimeMillis() - b.a));
        }
    }

    private boolean a(String str) {
        int length;
        if (str != null && (length = str.trim().getBytes().length) > 0 && length <= 128) {
            return true;
        }
        bv.e("Event id is empty or too long in tracking Event");
        return false;
    }

    private boolean b(String str) {
        if (str == null || str.trim().getBytes().length <= 256) {
            return true;
        }
        bv.e("Event label or value is empty or too long in tracking Event");
        return false;
    }

    private boolean a(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            bv.e("map is null or empty in onEvent");
            return false;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!a(entry.getKey()) || entry.getValue() == null) {
                return false;
            }
            if ((entry.getValue() instanceof String) && !b(entry.getValue().toString())) {
                return false;
            }
        }
        return true;
    }
}
