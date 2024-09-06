package com.netease.nimlib.net.a.b.c;

import android.text.TextUtils;
import com.iflytek.speech.TextUnderstanderAidl;
import com.netease.nimlib.o.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NosToken.java */
/* loaded from: classes.dex */
public class d {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private long f;
    private String g;
    private String h;

    public void a(String str) {
        this.e = str;
    }

    public long a() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public String d() {
        return this.a;
    }

    public void d(String str) {
        this.a = str;
    }

    public String e() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public String f() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public static ArrayList<d> g(String str) {
        ArrayList<d> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray b = k.b(str);
                for (int i = 0; i < b.length(); i++) {
                    d a = a(b.getJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public static String a(List<d> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<d> it = list.iterator();
        while (it.hasNext()) {
            JSONObject b = b(it.next());
            if (b != null) {
                jSONArray.put(b);
            }
        }
        return jSONArray.toString();
    }

    public static String a(d dVar) {
        JSONObject b = b(dVar);
        if (b == null) {
            return null;
        }
        return b.toString();
    }

    public static d h(String str) {
        return a(k.a(str));
    }

    private static JSONObject b(d dVar) {
        if (dVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        k.a(jSONObject, "bucket", dVar.c);
        k.a(jSONObject, "token", dVar.b);
        k.a(jSONObject, "obj", dVar.a);
        k.a(jSONObject, "expire", dVar.d);
        k.a(jSONObject, TextUnderstanderAidl.SCENE, dVar.e);
        k.a(jSONObject, "file_expire", dVar.f);
        if (!TextUtils.isEmpty(dVar.g)) {
            k.a(jSONObject, "short_url", dVar.g);
        }
        k.a(jSONObject, com.alipay.sdk.m.h.b.h, dVar.f());
        return jSONObject;
    }

    private static d a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        d dVar = new d();
        dVar.c = k.e(jSONObject, "bucket");
        dVar.b = k.e(jSONObject, "token");
        dVar.a = k.e(jSONObject, "obj");
        dVar.d = k.a(jSONObject, "expire");
        dVar.e = k.e(jSONObject, TextUnderstanderAidl.SCENE);
        dVar.f = k.b(jSONObject, "file_expire");
        dVar.g = k.e(jSONObject, "short_url");
        dVar.h = k.e(jSONObject, com.alipay.sdk.m.h.b.h);
        return dVar;
    }
}
