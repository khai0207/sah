package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Caretaker.java */
/* loaded from: classes.dex */
public class n {
    private SharedPreferences c;
    private final String a = "umeng_event_snapshot";
    private boolean b = false;
    private Map<String, ArrayList<af>> d = new HashMap();

    public n(Context context) {
        this.c = y.a(context, "umeng_event_snapshot");
    }

    public void a(boolean z) {
        this.b = z;
    }

    public int a(String str) {
        if (this.d.containsKey(str)) {
            return this.d.get(str).size();
        }
        return 0;
    }

    public void a(String str, af afVar) {
        if (this.b) {
            d(str);
        }
        if (this.d.containsKey(str)) {
            this.d.get(str).add(afVar);
        } else {
            ArrayList<af> arrayList = new ArrayList<>();
            arrayList.add(afVar);
            this.d.put(str, arrayList);
        }
        if (this.b) {
            c(str);
        }
    }

    public af b(String str) {
        if (this.b) {
            d(str);
        }
        af afVar = null;
        if (this.d.containsKey(str)) {
            ArrayList<af> arrayList = this.d.get(str);
            if (arrayList.size() > 0) {
                afVar = arrayList.remove(arrayList.size() - 1);
            }
        }
        if (this.b) {
            c(str);
        }
        return afVar;
    }

    private void c(String str) {
        String str2;
        if (this.d.containsKey(str)) {
            ArrayList<af> arrayList = this.d.get(str);
            while (arrayList.size() > 4) {
                arrayList.remove(0);
            }
            str2 = v.a(arrayList);
        } else {
            str2 = null;
        }
        this.c.edit().putString(str, str2).commit();
    }

    private boolean d(String str) {
        ArrayList<af> arrayList;
        if (this.d.containsKey(str)) {
            return true;
        }
        String string = this.c.getString(str, null);
        if (string == null || (arrayList = (ArrayList) v.a(string)) == null) {
            return false;
        }
        this.d.put(str, arrayList);
        return true;
    }
}
