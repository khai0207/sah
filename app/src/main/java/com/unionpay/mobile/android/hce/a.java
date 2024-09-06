package com.unionpay.mobile.android.hce;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/* loaded from: classes.dex */
public final class a {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0061 A[Catch: Exception -> 0x0069, TRY_LEAVE, TryCatch #0 {Exception -> 0x0069, blocks: (B:3:0x0005, B:6:0x000e, B:9:0x0015, B:11:0x0027, B:13:0x0043, B:15:0x0061), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0068 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "0123456789ABCDEF"
            java.lang.String r1 = "UTF-8"
            r2 = 0
            byte[] r11 = r11.getBytes(r1)     // Catch: java.lang.Exception -> L69
            java.lang.String r3 = ""
            r4 = 2
            if (r10 == 0) goto L42
            boolean r5 = r10.equals(r3)     // Catch: java.lang.Exception -> L69
            if (r5 == 0) goto L15
            goto L42
        L15:
            java.lang.String r10 = r10.toUpperCase()     // Catch: java.lang.Exception -> L69
            int r5 = r10.length()     // Catch: java.lang.Exception -> L69
            int r5 = r5 / r4
            char[] r10 = r10.toCharArray()     // Catch: java.lang.Exception -> L69
            byte[] r6 = new byte[r5]     // Catch: java.lang.Exception -> L69
            r7 = 0
        L25:
            if (r7 >= r5) goto L43
            int r8 = r7 * 2
            char r9 = r10[r8]     // Catch: java.lang.Exception -> L69
            int r9 = r0.indexOf(r9)     // Catch: java.lang.Exception -> L69
            byte r9 = (byte) r9     // Catch: java.lang.Exception -> L69
            int r9 = r9 << 4
            int r8 = r8 + 1
            char r8 = r10[r8]     // Catch: java.lang.Exception -> L69
            int r8 = r0.indexOf(r8)     // Catch: java.lang.Exception -> L69
            byte r8 = (byte) r8     // Catch: java.lang.Exception -> L69
            r8 = r8 | r9
            byte r8 = (byte) r8     // Catch: java.lang.Exception -> L69
            r6[r7] = r8     // Catch: java.lang.Exception -> L69
            int r7 = r7 + 1
            goto L25
        L42:
            r6 = r2
        L43:
            javax.crypto.spec.DESedeKeySpec r10 = new javax.crypto.spec.DESedeKeySpec     // Catch: java.lang.Exception -> L69
            r10.<init>(r11)     // Catch: java.lang.Exception -> L69
            java.lang.String r11 = "desede"
            javax.crypto.SecretKeyFactory r11 = javax.crypto.SecretKeyFactory.getInstance(r11)     // Catch: java.lang.Exception -> L69
            javax.crypto.SecretKey r10 = r11.generateSecret(r10)     // Catch: java.lang.Exception -> L69
            java.lang.String r11 = "DESEDE/ECB/PKCS5Padding"
            javax.crypto.Cipher r11 = javax.crypto.Cipher.getInstance(r11)     // Catch: java.lang.Exception -> L69
            r11.init(r4, r10)     // Catch: java.lang.Exception -> L69
            byte[] r10 = r11.doFinal(r6)     // Catch: java.lang.Exception -> L69
            if (r10 == 0) goto L68
            java.lang.String r11 = new java.lang.String     // Catch: java.lang.Exception -> L69
            r11.<init>(r10, r1)     // Catch: java.lang.Exception -> L69
            r2 = r11
            goto L6d
        L68:
            return r3
        L69:
            r10 = move-exception
            r10.printStackTrace()
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.hce.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String b(String str, String str2) {
        byte[] bArr;
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            byte[] bytes2 = str.getBytes();
            SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bytes));
            Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
            cipher.init(1, generateSecret);
            bArr = cipher.doFinal(bytes2);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        return a(bArr);
    }
}
