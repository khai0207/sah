package com.netease.nimlib.abtest.a;

import android.text.TextUtils;
import com.netease.nimlib.o.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ABTestExperiment.java */
/* loaded from: classes.dex */
public class b {
    private long a;
    private String b;
    private String c;
    private String d;
    private List<c> e;

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public List<c> d() {
        return this.e;
    }

    public void a(List<c> list) {
        this.e = list;
    }

    public String e() {
        if (this.e == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<c> it = this.e.iterator();
        while (it.hasNext()) {
            jSONArray.put(c.a(it.next()));
        }
        return jSONArray.toString();
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e = null;
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            this.e = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                this.e.add(c.a(jSONArray.optJSONObject(i)));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String a(b bVar) {
        JSONObject b = b(bVar);
        if (b == null) {
            return null;
        }
        return b.toString();
    }

    public static JSONObject b(b bVar) {
        if (bVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (bVar.b != null) {
                jSONObject.put("experimentKey", bVar.b);
            }
            if (bVar.c != null) {
                jSONObject.put("schemeKey", bVar.c);
            }
            if (bVar.d != null) {
                jSONObject.put("extend", bVar.d);
            }
            if (bVar.e != null) {
                JSONArray jSONArray = new JSONArray();
                Iterator<c> it = bVar.e.iterator();
                while (it.hasNext()) {
                    jSONArray.put(c.a(it.next()));
                }
                jSONObject.put("variates", jSONArray);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static b a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            b bVar = new b();
            if (jSONObject.has("experimentKey")) {
                bVar.a(jSONObject.optString("experimentKey"));
            }
            if (jSONObject.has("schemeKey")) {
                bVar.b(jSONObject.optString("schemeKey"));
            }
            if (jSONObject.has("extend")) {
                bVar.c(jSONObject.optString("extend"));
            }
            if (jSONObject.has("variates")) {
                JSONArray jSONArray = jSONObject.getJSONArray("variates");
                ArrayList arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(c.a(jSONArray.optJSONObject(i)));
                }
                bVar.a(arrayList);
            }
            bVar.a(y.a());
            return bVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return a(this);
    }
}
