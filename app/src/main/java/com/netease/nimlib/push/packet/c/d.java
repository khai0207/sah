package com.netease.nimlib.push.packet.c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: PackHelper.java */
/* loaded from: classes.dex */
public class d {
    public static void a(b bVar, Collection<?> collection) {
        if (collection == null) {
            bVar.b(0);
            return;
        }
        bVar.b(collection.size());
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            a(bVar, it.next());
        }
    }

    private static void a(b bVar, Object obj) {
        if (obj instanceof Integer) {
            bVar.a(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Short) {
            bVar.a(((Short) obj).shortValue());
            return;
        }
        if (obj instanceof Long) {
            bVar.a(((Long) obj).longValue());
            return;
        }
        if (obj instanceof String) {
            bVar.a((String) obj);
            return;
        }
        if (obj instanceof com.netease.nimlib.push.packet.b.b) {
            bVar.a((com.netease.nimlib.push.packet.b.b) obj);
            return;
        }
        if (obj instanceof Collection) {
            a(bVar, (Collection<?>) obj);
            return;
        }
        if (obj instanceof byte[]) {
            bVar.b((byte[]) obj);
        } else {
            if (obj instanceof Byte) {
                bVar.a(((Byte) obj).byteValue());
                return;
            }
            throw new IllegalArgumentException("unmarshallable type: " + obj.getClass());
        }
    }

    public static com.netease.nimlib.push.packet.b.c a(f fVar) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        fVar.a(cVar);
        return cVar;
    }

    public static List<String> b(f fVar) {
        int g = fVar.g();
        ArrayList arrayList = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            arrayList.add(fVar.e());
        }
        return arrayList;
    }

    public static List<com.netease.nimlib.push.packet.b.c> c(f fVar) {
        int g = fVar.g();
        ArrayList arrayList = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            arrayList.add(a(fVar));
        }
        return arrayList;
    }

    public static int a(byte[] bArr) {
        byte b;
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        do {
            b = bArr[i];
            i2 += (b & Byte.MAX_VALUE) * i3;
            i3 *= 128;
            i++;
        } while ((b & 128) != 0);
        return i2;
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (true) {
            byte b = (byte) (i % 128);
            i /= 128;
            if (i > 0) {
                b = (byte) (b | 128);
            }
            int i3 = i2 + 1;
            bArr[i2] = b;
            if (i <= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, 0, bArr2, 0, i3);
                return bArr2;
            }
            i2 = i3;
        }
    }

    public static int b(int i) {
        int i2 = 0;
        do {
            i /= 128;
            i2++;
        } while (i > 0);
        return i2;
    }

    public static int d(f fVar) {
        byte c;
        int i = 1;
        int i2 = 0;
        do {
            c = fVar.c();
            i2 += (c & Byte.MAX_VALUE) * i;
            i *= 128;
        } while ((c & 128) != 0);
        return i2;
    }
}
