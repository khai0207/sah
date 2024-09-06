package com.netease.nimlib.push.net.a;

import com.netease.nimlib.o.e;
import com.netease.nimlib.o.j;

/* compiled from: BufferCache.java */
/* loaded from: classes.dex */
public class b extends e<a> {
    b() {
        super(2);
    }

    @Override // com.netease.nimlib.o.e
    public void a(a aVar) {
        super.a((b) aVar);
        if (c.c()) {
            com.netease.nimlib.log.b.E("==== nio read=" + j.a(aVar.a()));
        }
    }

    byte[] a(byte[] bArr, int i) {
        int i2;
        if (c.c()) {
            com.netease.nimlib.log.b.E("==== find key20=" + j.a(bArr) + ", key length=" + i);
        }
        if (bArr == null || bArr.length <= 0) {
            com.netease.nimlib.log.b.D("key20 is null!");
            return null;
        }
        int i3 = 0;
        for (a aVar : d()) {
            i3 += aVar.a().length;
            if (c.c()) {
                com.netease.nimlib.log.b.D("==== b=" + j.a(aVar.a()));
            }
        }
        if (i3 < 0) {
            com.netease.nimlib.log.b.D("buffer size 0!");
            return null;
        }
        byte[] bArr2 = new byte[i3];
        int i4 = 0;
        for (a aVar2 : d()) {
            System.arraycopy(aVar2.a(), 0, bArr2, i4, aVar2.b());
            i4 += aVar2.b();
        }
        int length = i < bArr.length ? i : bArr.length;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i5 >= i3) {
                i2 = -1;
                break;
            }
            if (bArr2[i5] == bArr[i6]) {
                i6++;
            }
            if (i6 == length) {
                i2 = i5 + 1;
                break;
            }
            i5++;
        }
        if (i2 == -1) {
            com.netease.nimlib.log.b.D("key20 not found!");
            return null;
        }
        int i7 = (i2 - length) + i;
        int i8 = i3 - i7;
        if (i8 <= 0) {
            com.netease.nimlib.log.b.D("buffer reach the end!");
            return null;
        }
        byte[] bArr3 = new byte[i8];
        System.arraycopy(bArr2, i7, bArr3, 0, i8);
        return bArr3;
    }
}
