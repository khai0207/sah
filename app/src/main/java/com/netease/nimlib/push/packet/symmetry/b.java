package com.netease.nimlib.push.packet.symmetry;

import com.netease.nimlib.push.packet.a.a.c.j;
import java.util.Random;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: SM4.java */
/* loaded from: classes.dex */
public class b implements c {
    private SecretKey a;

    public b(byte[] bArr) {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "SM4");
        this.a = new SecretKey() { // from class: com.netease.nimlib.push.packet.symmetry.b.1
            @Override // java.security.Key
            public String getAlgorithm() {
                return secretKeySpec.getAlgorithm();
            }

            @Override // java.security.Key
            public String getFormat() {
                return secretKeySpec.getFormat();
            }

            @Override // java.security.Key
            public byte[] getEncoded() {
                return secretKeySpec.getEncoded();
            }
        };
    }

    public static byte[] a() {
        byte[] bArr = new byte[16];
        new Random().nextBytes(bArr);
        return bArr;
    }

    @Override // com.netease.nimlib.push.packet.symmetry.c
    public byte[] b(byte[] bArr, int i, int i2) {
        int i3;
        j jVar = new j(this.a.getEncoded());
        com.netease.nimlib.push.packet.a.a.b.b bVar = new com.netease.nimlib.push.packet.a.a.b.b();
        bVar.a(true, (com.netease.nimlib.push.packet.a.a.a) jVar);
        int i4 = ((i2 >> 4) + 1) << 4;
        byte[] bArr2 = new byte[i4];
        int i5 = i;
        while (true) {
            i3 = (i2 + i) - i5;
            if (i3 < 16) {
                break;
            }
            bVar.a(bArr, i5, bArr2, i5 - i);
            i5 += 16;
        }
        int i6 = i5 - i;
        System.arraycopy(bArr, i5, bArr2, i6, i3);
        byte b = (byte) (i4 - i2);
        while (i2 < i4) {
            bArr2[i2] = b;
            i2++;
        }
        bVar.a(bArr2, i6, bArr2, i6);
        return bArr2;
    }

    @Override // com.netease.nimlib.push.packet.symmetry.c
    public byte[] c(byte[] bArr, int i, int i2) {
        if (i2 < 0 || ((i2 >> 4) << 4) != i2) {
            throw new IllegalArgumentException("SM4: 待解密串长度不合法, len=" + i2);
        }
        j jVar = new j(this.a.getEncoded());
        com.netease.nimlib.push.packet.a.a.b.b bVar = new com.netease.nimlib.push.packet.a.a.b.b();
        bVar.a(false, (com.netease.nimlib.push.packet.a.a.a) jVar);
        byte[] bArr2 = new byte[i2];
        for (int i3 = i; (i + i2) - i3 > 0; i3 += 16) {
            bVar.a(bArr, i3, bArr2, i3 - i);
        }
        int i4 = bArr2[i2 - 1];
        if (!a(bArr2)) {
            throw new IllegalArgumentException("SM4: 格式不对, 补位数=" + i4);
        }
        int i5 = i2 - i4;
        byte[] bArr3 = new byte[i5];
        System.arraycopy(bArr2, 0, bArr3, 0, i5);
        return bArr3;
    }

    private boolean a(byte[] bArr) {
        byte b = bArr[bArr.length - 1];
        boolean z = b > 0 && b <= 16 && b <= bArr.length;
        for (int length = bArr.length - 1; length >= bArr.length - b; length--) {
            if (bArr[length] != b) {
                z = false;
            }
        }
        if (!z) {
            b(bArr);
        }
        return z;
    }

    private void b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("SM4格式问题. 解密后内容：");
        for (byte b : bArr) {
            sb.append((int) b);
            sb.append(", ");
        }
        com.netease.nimlib.log.b.O(sb.toString());
    }
}
