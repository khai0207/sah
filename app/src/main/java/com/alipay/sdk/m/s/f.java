package com.alipay.sdk.m.s;

import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes.dex */
public class f {
    public Stack<e> a = new Stack<>();

    public void a(e eVar) {
        this.a.push(eVar);
    }

    public boolean b() {
        return this.a.isEmpty();
    }

    public e c() {
        return this.a.pop();
    }

    public void a() {
        if (b()) {
            return;
        }
        Iterator<e> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.a.clear();
    }
}
