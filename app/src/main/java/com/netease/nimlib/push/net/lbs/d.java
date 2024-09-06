package com.netease.nimlib.push.net.lbs;

import java.io.Serializable;

/* compiled from: ServerData.java */
/* loaded from: classes.dex */
public final class d implements Serializable {
    private String a;
    private String[] b;
    private String[] c;
    private int d;
    private int e;
    private int f;
    private boolean g;

    public d(String str, String[] strArr, String[] strArr2, int i) {
        this.a = str;
        this.b = strArr;
        this.c = strArr2;
        this.d = i;
    }

    public void a(String[] strArr) {
        this.b = strArr;
        this.f = 0;
        this.e = 0;
    }

    void b(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        this.c = strArr;
    }

    public boolean a() {
        String[] strArr = this.b;
        boolean z = strArr != null && strArr.length > 0;
        if (this.g) {
            return z;
        }
        if (z) {
            int i = this.e + 1;
            this.e = i;
            if (i >= this.d) {
                this.e = 0;
                int i2 = this.f;
                String[] strArr2 = this.b;
                if (i2 >= strArr2.length - 1) {
                    this.b = null;
                    return false;
                }
                this.f = (i2 + 1) % strArr2.length;
            }
            return true;
        }
        this.b = null;
        return false;
    }

    public String b() {
        String[] strArr = this.b;
        if (strArr != null && strArr.length > 0) {
            this.g = false;
            return strArr[this.f];
        }
        String[] strArr2 = this.c;
        if (strArr2 == null || strArr2.length <= 0) {
            return null;
        }
        this.g = true;
        return strArr2[this.f % strArr2.length];
    }

    int c() {
        String[] strArr = this.c;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    int d() {
        String[] strArr = this.b;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    public String toString() {
        return "ServerData{moveIndex=" + this.f + ", linkCount=" + d() + ", defLinkCount=" + c() + ", useDef=" + this.g + ", retryCount=" + this.e + ", retryLimit=" + this.d + ", key=" + this.a + '}';
    }
}
