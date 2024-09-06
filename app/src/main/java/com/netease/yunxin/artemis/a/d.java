package com.netease.yunxin.artemis.a;

import android.os.Build;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* compiled from: DesUtil.java */
/* loaded from: classes.dex */
public final class d {
    public static String a(String str, String str2) {
        if (str.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes("utf-8")));
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, generateSecret, new IvParameterSpec("12345678".getBytes("utf-8")));
            if (Build.VERSION.SDK_INT >= 26) {
                return new String(cipher.doFinal(Base64.getDecoder().decode(str2.getBytes("utf-8"))), "utf-8");
            }
            return new String(cipher.doFinal(a.a(str2)), "utf-8");
        } catch (Throwable unused) {
            return str2;
        }
    }
}
