package com.netease.nimlib.apm.a;

import com.netease.nimlib.apm.event.c.b;
import java.util.Map;

/* compiled from: EventReportConfig.java */
/* loaded from: classes.dex */
public class a {
    private Map<String, String> a = null;
    private Map<String, Object> b = null;
    private b c = null;

    public String a() {
        b bVar = this.c;
        if (bVar == null) {
            return null;
        }
        return bVar.a();
    }

    public Map<String, String> b() {
        return this.a;
    }

    public void a(Map<String, String> map) {
        this.a = map;
    }

    public Map<String, Object> c() {
        return this.b;
    }

    public void b(Map<String, Object> map) {
        this.b = map;
    }

    public b d() {
        return this.c;
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
