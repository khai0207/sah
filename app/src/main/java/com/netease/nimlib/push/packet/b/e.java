package com.netease.nimlib.push.packet.b;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.c.f;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: StrStrMap.java */
/* loaded from: classes.dex */
public class e implements b {
    private Map<String, String> a;
    private boolean b;

    public e() {
        this.a = new HashMap();
        this.b = false;
    }

    public e(boolean z) {
        this.a = new HashMap();
        this.b = false;
        this.b = z;
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(com.netease.nimlib.push.packet.c.b bVar) {
        bVar.b(this.a.size());
        Iterator<String> b = b();
        while (b.hasNext()) {
            String next = b.next();
            String a = a(next);
            if (this.b || (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(a))) {
                bVar.a(next);
                bVar.a(a);
            }
        }
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(f fVar) {
        int g = fVar.g();
        for (int i = 0; i < g; i++) {
            String e = fVar.e();
            String e2 = fVar.e();
            if (this.b || (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(e2))) {
                a(e, e2);
            }
        }
    }

    public Map<String, String> a() {
        return this.a;
    }

    public Iterator<String> b() {
        return this.a.keySet().iterator();
    }

    public String a(String str) {
        return this.a.get(str);
    }

    public void a(String str, String str2) {
        this.a.put(str, str2);
    }
}
