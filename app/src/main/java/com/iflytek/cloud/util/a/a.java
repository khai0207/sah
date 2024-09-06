package com.iflytek.cloud.util.a;

import android.content.Context;
import com.android.pc.util.ThreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public class a {
    protected Context a;
    private com.iflytek.cloud.util.a.c.a b;
    private String[] c = null;
    private String[] d = null;
    private HashMap<String, String> e = new HashMap<>();
    private HashMap<String, String> f = new HashMap<>();
    private List<com.iflytek.cloud.util.a.a.a> g = new ArrayList();
    private HashMap<String, String> h = new HashMap<>();
    private HashMap<String, String> i = new HashMap<>();

    public a(Context context, com.iflytek.cloud.util.a.c.a aVar) {
        this.b = aVar;
        this.a = context;
    }

    private void b() {
        if (this.f.size() > 0) {
            this.f = null;
            this.f = new HashMap<>();
        }
        if (this.i.size() > 0) {
            this.i = null;
            this.i = new HashMap<>();
        }
        if (this.e.size() > 0) {
            this.e = null;
            this.e = new HashMap<>();
        }
        String[] strArr = this.c;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        this.c = null;
    }

    public void a(int i) {
        HashMap<String, String> hashMap = this.h;
        if (hashMap != null && hashMap.size() > 0) {
            this.h.clear();
        }
        com.iflytek.cloud.util.a.c.a aVar = this.b;
        if (aVar != null) {
            this.h = aVar.a(i);
        }
    }

    public String[] a() {
        b();
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> d = this.b.d();
        List<com.iflytek.cloud.util.a.a.a> e = this.b.e();
        for (String str : d.keySet()) {
            String str2 = d.get(str);
            this.e.put(str + "p", str2);
            arrayList.add(str2);
            if (str2.contains("\u0000")) {
                this.i.put(str2.replace("\u0000", " "), str2);
            }
        }
        for (com.iflytek.cloud.util.a.a.a aVar : e) {
            String a = aVar.a();
            String c = aVar.c();
            String b = aVar.b();
            this.f.put(a + ThreeMap.type_string, c);
            this.e.put(a + ThreeMap.type_string, b);
            arrayList.add(b);
            if (b.contains("\u0000")) {
                this.i.put(b.replace("\u0000", " "), b);
            }
            this.g.add(aVar);
        }
        HashSet hashSet = new HashSet(arrayList);
        String[] strArr = (String[]) hashSet.toArray(new String[hashSet.size()]);
        this.c = strArr;
        return strArr;
    }
}
