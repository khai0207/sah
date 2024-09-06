package com.unionpay.a;

import com.unionpay.utils.g;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class d {
    private String b;
    private int a = 1;
    private HashMap c = null;
    private byte[] d = null;

    public d(String str) {
        this.b = str;
    }

    public final int a() {
        return this.a;
    }

    public final void a(String str) {
        g.b("uppay", "encrypt postData: " + str);
        if (str != null) {
            this.d = str.getBytes();
        }
    }

    public final String b() {
        return this.b;
    }

    public final HashMap c() {
        return this.c;
    }

    public final byte[] d() {
        return this.d;
    }
}
