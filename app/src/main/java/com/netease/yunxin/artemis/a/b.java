package com.netease.yunxin.artemis.a;

import java.security.MessageDigest;
import u.aly.df;

/* compiled from: CheckSumBuilder.java */
/* loaded from: classes.dex */
public final class b {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(String str, String str2, String str3) {
        String str4 = str + str2 + str3;
        if (str4 == null) {
            return null;
        }
        return a("sha1", str4.getBytes());
    }

    public static String a(byte[] bArr) {
        return a("md5", bArr);
    }

    private static String a(String str, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            int length = digest.length;
            StringBuilder sb = new StringBuilder(length * 2);
            for (int i = 0; i < length; i++) {
                sb.append(a[(digest[i] >> 4) & 15]);
                sb.append(a[digest[i] & df.m]);
            }
            return sb.toString();
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
