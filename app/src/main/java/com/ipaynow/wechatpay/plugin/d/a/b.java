package com.ipaynow.wechatpay.plugin.d.a;

import android.util.Base64;
import com.alipay.sdk.m.j.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public final class b {
    private static String C = "RSA/ECB/PKCS1Padding";
    private static int D = 2048;
    private static int E = 11;
    private static int F;
    private static int G;

    static {
        int i = 2048 / 8;
        F = i;
        G = i - 11;
    }

    public static String a(String str, byte[] bArr) {
        int length = bArr.length;
        int i = G;
        int i2 = length / i;
        if (bArr.length % i != 0) {
            i2++;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2 * F);
        try {
            try {
                Cipher cipher = Cipher.getInstance(C);
                cipher.init(1, KeyFactory.getInstance(d.a).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0))));
                for (int i3 = 0; i3 < bArr.length; i3 += G) {
                    int length2 = bArr.length - i3;
                    if (length2 > G) {
                        length2 = G;
                    }
                    byteArrayOutputStream.write(cipher.doFinal(bArr, i3, length2));
                }
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                byteArrayOutputStream.close();
                return null;
            } catch (IOException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }
}
