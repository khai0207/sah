package com.netease.nimlib.push.packet.b;

import android.util.SparseArray;
import com.netease.nimlib.push.packet.c.f;

/* compiled from: Property.java */
/* loaded from: classes.dex */
public class c implements b {
    public SparseArray<String> a = new SparseArray<>();
    public SparseArray<byte[]> b = new SparseArray<>(1);

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(com.netease.nimlib.push.packet.c.b bVar) {
        bVar.b(this.a.size());
        for (int i = 0; i < this.a.size(); i++) {
            int keyAt = this.a.keyAt(i);
            bVar.b(keyAt);
            if (this.b.indexOfKey(keyAt) >= 0) {
                bVar.b(this.b.get(keyAt));
            } else {
                bVar.a(this.a.valueAt(i));
            }
        }
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(f fVar) {
        int g = fVar.g();
        for (int i = 0; i < g; i++) {
            a(fVar.g(), fVar.e());
        }
    }

    public int a(int i) {
        return this.a.keyAt(i);
    }

    public String b(int i) {
        return this.a.valueAt(i);
    }

    public String c(int i) {
        return this.a.get(i);
    }

    public void a(int i, String str) {
        if (str != null) {
            this.a.put(i, str);
        }
    }

    public void a(Integer num, byte[] bArr) {
        this.b.put(num.intValue(), bArr);
        this.a.put(num.intValue(), "");
    }

    public int d(int i) {
        String str = this.a.get(i);
        if (str != null && !str.equals("")) {
            try {
                return Integer.parseInt(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public void a(int i, int i2) {
        this.a.put(i, "" + i2);
    }

    public long e(int i) {
        String str = this.a.get(i);
        if (str == null || str.equals("")) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public void a(int i, long j) {
        this.a.put(i, "" + j);
    }

    public void a(int i, double d) {
        this.a.put(i, "" + d);
    }

    public int a() {
        return this.a.size();
    }

    public String toString() {
        return this.a.toString();
    }

    public boolean f(int i) {
        return this.a.indexOfKey(i) >= 0;
    }
}
