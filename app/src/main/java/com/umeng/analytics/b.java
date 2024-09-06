package com.umeng.analytics;

import com.netease.nimlib.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import u.aly.bv;
import u.aly.df;

/* compiled from: DataHelper.java */
/* loaded from: classes.dex */
public class b {
    private static final byte[] a = {10, 1, 11, 5, 4, df.m, 7, 9, 23, 3, 1, 6, 8, 12, df.k, 91};

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i2), 16).intValue();
            i = i2;
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM), new IvParameterSpec(a));
        return cipher.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM), new IvParameterSpec(a));
        return cipher.doFinal(bArr);
    }

    public static int a(int i, String str) {
        if (new Random().nextFloat() < 0.001d) {
            if (str == null) {
                bv.e("null signature..");
            }
            int i2 = 0;
            try {
                i2 = Integer.parseInt(str.substring(9, 11), 16);
            } catch (Exception unused) {
            }
            return (i2 | 128) * 1000;
        }
        int nextInt = new Random().nextInt(i);
        if (nextInt > 255000 || nextInt < 128000) {
            return nextInt;
        }
        return 127000;
    }
}
