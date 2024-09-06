package com.netease.nimlib.push.packet.asymmetric;

import com.netease.nimlib.o.g;
import java.security.PublicKey;
import javax.crypto.Cipher;

/* compiled from: RSA.java */
/* loaded from: classes.dex */
public class d {
    public static byte[] a(PublicKey publicKey, byte[] bArr, int i, int i2) {
        if (bArr != null && i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
            try {
                String a = g.a("UlNBL0VDQi9QS0NTMVBhZGRpbmc=");
                com.netease.nimlib.log.b.I("RSA#encrypt transformation = " + a);
                Cipher cipher = Cipher.getInstance(a);
                cipher.init(1, publicKey);
                byte[] bArr2 = new byte[(((i2 + (-1)) / 117) * 128) + 128];
                int i3 = 0;
                while (i < i2) {
                    int min = Math.min(i2 - (i3 * 117), 117);
                    byte[] doFinal = cipher.doFinal(bArr, i, min);
                    System.arraycopy(doFinal, 0, bArr2, i3 * 128, doFinal.length);
                    i3++;
                    i += min;
                }
                return bArr2;
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
