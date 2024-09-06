package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import u.aly.df;

/* loaded from: classes.dex */
public final class b {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyyMMddhhmmss");

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0065, code lost:
    
        if (com.unionpay.utils.UPUtils.forConfig(r2, r1).equals(a(com.unionpay.utils.UPUtils.a(r7 + r8))) != false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r8) {
        /*
            java.lang.String r0 = "configs"
            java.lang.String r1 = com.unionpay.utils.UPUtils.a(r8, r0)
            java.lang.String r2 = "mode"
            java.lang.String r2 = com.unionpay.utils.UPUtils.a(r8, r2)
            java.lang.String r3 = "or"
            java.lang.String r8 = com.unionpay.utils.UPUtils.a(r8, r3)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            r4 = 2
            r5 = 0
            java.lang.String r6 = ""
            if (r3 != 0) goto L68
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L68
            boolean r3 = android.text.TextUtils.isEmpty(r8)
            if (r3 != 0) goto L68
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L68
            r3.<init>(r1)     // Catch: org.json.JSONException -> L68
            java.lang.String r1 = "sign"
            java.lang.String r1 = com.unionpay.utils.f.a(r3, r1)     // Catch: org.json.JSONException -> L68
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L38 org.json.JSONException -> L68
            goto L39
        L38:
            r2 = 0
        L39:
            java.lang.String r7 = new java.lang.String     // Catch: org.json.JSONException -> L68
            java.lang.String r0 = r3.getString(r0)     // Catch: org.json.JSONException -> L68
            byte[] r0 = android.util.Base64.decode(r0, r4)     // Catch: org.json.JSONException -> L68
            r7.<init>(r0)     // Catch: org.json.JSONException -> L68
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L68
            r0.<init>()     // Catch: org.json.JSONException -> L68
            r0.append(r7)     // Catch: org.json.JSONException -> L68
            r0.append(r8)     // Catch: org.json.JSONException -> L68
            java.lang.String r8 = r0.toString()     // Catch: org.json.JSONException -> L68
            java.lang.String r8 = com.unionpay.utils.UPUtils.a(r8)     // Catch: org.json.JSONException -> L68
            java.lang.String r8 = a(r8)     // Catch: org.json.JSONException -> L68
            java.lang.String r0 = com.unionpay.utils.UPUtils.forConfig(r2, r1)     // Catch: org.json.JSONException -> L68
            boolean r8 = r0.equals(r8)     // Catch: org.json.JSONException -> L68
            if (r8 == 0) goto L68
            goto L69
        L68:
            r7 = r6
        L69:
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch: org.json.JSONException -> L9d
            r8.<init>(r7)     // Catch: org.json.JSONException -> L9d
            int r0 = r8.length()
        L72:
            if (r5 >= r0) goto L9d
            java.lang.Object r1 = com.unionpay.utils.f.a(r8, r5)
            if (r1 == 0) goto L9a
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            java.lang.String r2 = "type"
            java.lang.String r2 = com.unionpay.utils.f.a(r1, r2)
            java.lang.String r3 = "app"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L9a
            java.lang.String r8 = "ca"
            java.lang.String r8 = com.unionpay.utils.f.a(r1, r8)
            java.lang.String r0 = new java.lang.String
            byte[] r8 = android.util.Base64.decode(r8, r4)
            r0.<init>(r8)
            return r0
        L9a:
            int r5 = r5 + 1
            goto L72
        L9d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.a(android.content.Context):java.lang.String");
    }

    public static String a(String str) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (byte b : str.getBytes()) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & df.m]);
        }
        return sb.toString().trim();
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String b(Context context, String str) {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        String str2 = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e3) {
            e3.printStackTrace();
            x509Certificate = null;
        }
        try {
            str2 = a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
        } catch (CertificateEncodingException e5) {
            e5.printStackTrace();
        }
        return str2.replaceAll(":", "");
    }

    public static String b(String str) {
        File file = new File(str);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            try {
                MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(map);
                String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                try {
                    fileInputStream.close();
                    return bigInteger;
                } catch (IOException e) {
                    e.printStackTrace();
                    return bigInteger;
                }
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String c(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return context.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return "";
    }

    public static void c(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    c(file2.getPath());
                }
            }
            file.delete();
        }
    }
}
