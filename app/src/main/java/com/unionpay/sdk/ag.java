package com.unionpay.sdk;

import android.util.Base64;
import com.netease.nimlib.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
final class ag {
    private static final byte[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    static String a(String str) {
        SecretKeySpec secretKeySpec;
        try {
            String str2 = ad.h;
            String a2 = am.a();
            if (a2 != null) {
                secretKeySpec = new SecretKeySpec(Base64.decode(a2, 2), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            } else {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bytes = str2.getBytes("UTF-8");
                messageDigest.update(bytes, 0, bytes.length);
                byte[] digest = messageDigest.digest();
                String encodeToString = Base64.encodeToString(digest, 2);
                if (ad.c != null) {
                    an.a(ad.c, "unionpaypref_longtime", "unionpayaes_key", encodeToString);
                }
                secretKeySpec = new SecretKeySpec(digest, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            }
            byte[] bArr = a;
            byte[] bytes2 = str.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(bArr));
            return Base64.encodeToString(cipher.doFinal(bytes2), 2);
        } catch (Throwable unused) {
            boolean z = r.a;
            return null;
        }
    }

    static String b(String str) {
        try {
            String a2 = am.a();
            if (a2 == null) {
                throw new GeneralSecurityException();
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(a2, 2), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            byte[] decode = Base64.decode(str, 2);
            byte[] bArr = a;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(bArr));
            return new String(cipher.doFinal(decode), "UTF-8");
        } catch (Throwable unused) {
            boolean z = r.a;
            return null;
        }
    }
}
