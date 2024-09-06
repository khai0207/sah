package com.iflytek.cloud.a.g;

import java.security.MessageDigest;

/* loaded from: classes.dex */
public class d {
    public static synchronized String a(String str) {
        String stringBuffer;
        synchronized (d.class) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                char[] charArray = str.toCharArray();
                byte[] bArr = new byte[charArray.length];
                for (int i = 0; i < charArray.length; i++) {
                    bArr[i] = (byte) charArray[i];
                }
                byte[] digest = messageDigest.digest(bArr);
                StringBuffer stringBuffer2 = new StringBuffer();
                for (byte b : digest) {
                    int i2 = b & 255;
                    if (i2 < 16) {
                        stringBuffer2.append("0");
                    }
                    stringBuffer2.append(Integer.toHexString(i2));
                }
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return stringBuffer;
    }
}
