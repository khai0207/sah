package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: ViewPageTracker.java */
/* loaded from: classes.dex */
public class ae {
    private static final String a = "activities";
    private final Map<String, Long> b = new HashMap();
    private final ArrayList<ac> c = new ArrayList<>();

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.b) {
            this.b.put(str, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public void b(String str) {
        Long remove;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.b) {
            remove = this.b.remove(str);
        }
        if (remove == null) {
            bv.e("please call 'onPageStart(%s)' before onPageEnd", str);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - remove.longValue();
        synchronized (this.c) {
            this.c.add(new ac(str, currentTimeMillis));
        }
    }

    public void a() {
        String str;
        synchronized (this.b) {
            str = null;
            long j = 0;
            for (Map.Entry<String, Long> entry : this.b.entrySet()) {
                if (entry.getValue().longValue() > j) {
                    long longValue = entry.getValue().longValue();
                    str = entry.getKey();
                    j = longValue;
                }
            }
        }
        if (str != null) {
            b(str);
        }
    }

    public void a(Context context) {
        SharedPreferences a2 = y.a(context);
        SharedPreferences.Editor edit = a2.edit();
        if (this.c.size() > 0) {
            String string = a2.getString(a, "");
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(string)) {
                sb.append(string);
                sb.append(";");
            }
            synchronized (this.c) {
                Iterator<ac> it = this.c.iterator();
                while (it.hasNext()) {
                    ac next = it.next();
                    sb.append(String.format("[\"%s\",%d]", next.a, Long.valueOf(next.b)));
                    sb.append(";");
                }
                this.c.clear();
            }
            sb.deleteCharAt(sb.length() - 1);
            edit.remove(a);
            edit.putString(a, sb.toString());
        }
        edit.commit();
    }

    public static List<bi> a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(a, "");
        if ("".equals(string)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : string.split(";")) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(new aj(str));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }
}
