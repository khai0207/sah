package com.talkingdata.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class bh {
    static final boolean a = false;
    private static final String g = "UTF-8";
    private static final byte l = 61;
    private static final String m = "US-ASCII";
    static final /* synthetic */ boolean f = !bh.class.desiredAssertionStatus();
    public static boolean b = true;
    public static String c = ab.p;
    public static boolean d = false;
    public static boolean e = false;
    private static String h = "ge";
    private static String i = "tp";
    private static String j = "rop";
    private static final ExecutorService k = Executors.newSingleThreadExecutor();
    private static final byte[] n = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static byte[] o = {1, 2, 3, 4, 5, 6, 7, 8};

    public static boolean a(Context context) {
        return false;
    }

    public static void execute(Runnable runnable) {
        ExecutorService executorService = k;
        if (executorService != null) {
            executorService.execute(runnable);
        }
    }

    public static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new JSONObject(new String(bArr)).getString("td_channel_id");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(Object obj, bf bfVar, String str, String str2) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            Class<?> cls = Class.forName(str2);
            declaredField.set(obj, Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{cls}, new bi(bfVar, obj2)));
        } catch (Throwable unused) {
        }
    }

    public static void a(Class cls, bf bfVar, String str, String str2) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj = declaredField.get(null);
        Class<?> cls2 = Class.forName(str2);
        declaredField.set(null, Proxy.newProxyInstance(cls.getClass().getClassLoader(), new Class[]{cls2}, new bj(bfVar, obj)));
    }

    public static final String a(String str) {
        if (str == null) {
            return null;
        }
        return str.length() > 256 ? str.substring(0, 256) : str;
    }

    public static final boolean b(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String c(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean b(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(int i2) {
        return Build.VERSION.SDK_INT >= i2;
    }

    public static String a(byte[] bArr) {
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArr) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] a(int[] iArr, int[] iArr2) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            try {
                iArr[i2] = ((iArr[i2] * iArr2[(iArr2.length - 1) - i2]) - (iArr[(iArr.length - 1) - i2] * iArr2[i2])) + "kiG9w0BAQUFADCBqjELMAkGA0JFSUpJTkcxEDAOBgNVBAcMB0JFSUpJTkcxFjAUBgNVB".charAt(i2);
                iArr2[i2] = ((iArr2[i2] * iArr[(iArr.length - 1) - i2]) + (iArr2[(iArr2.length - 1) - i2] * iArr[i2])) - "kiG9w0BAQUFADCBqjELMAkGA0JFSUpJTkcxEDAOBgNVBAcMB0JFSUpJTkcxFjAUBgNVB".charAt(67 - i2);
            } catch (Throwable unused) {
                return null;
            }
        }
        return (Arrays.toString(iArr) + Arrays.hashCode(iArr2)).getBytes();
    }

    private static byte[] a(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        try {
            byte[] bArr3 = n;
            int i5 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
            if (i3 == 1) {
                bArr2[i4] = bArr3[i5 >>> 18];
                bArr2[i4 + 1] = bArr3[(i5 >>> 12) & 63];
                bArr2[i4 + 2] = l;
                bArr2[i4 + 3] = l;
                return bArr2;
            }
            if (i3 == 2) {
                bArr2[i4] = bArr3[i5 >>> 18];
                bArr2[i4 + 1] = bArr3[(i5 >>> 12) & 63];
                bArr2[i4 + 2] = bArr3[(i5 >>> 6) & 63];
                bArr2[i4 + 3] = l;
                return bArr2;
            }
            if (i3 != 3) {
                return bArr2;
            }
            bArr2[i4] = bArr3[i5 >>> 18];
            bArr2[i4 + 1] = bArr3[(i5 >>> 12) & 63];
            bArr2[i4 + 2] = bArr3[(i5 >>> 6) & 63];
            bArr2[i4 + 3] = bArr3[i5 & 63];
            return bArr2;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    public static String b(byte[] bArr) {
        String str;
        try {
            str = a(bArr, 0, bArr.length);
        } catch (Throwable th) {
            if (!f) {
                throw new AssertionError(th.getMessage());
            }
            str = null;
        }
        if (f || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i2, int i3) {
        byte[] b2 = b(bArr, i2, i3);
        try {
            return new String(b2, m);
        } catch (Throwable unused) {
            return new String(b2);
        }
    }

    public static byte[] b(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i2);
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i3);
        }
        if (i2 + i3 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
        }
        int i4 = ((i3 / 3) * 4) + (i3 % 3 <= 0 ? 0 : 4);
        byte[] bArr2 = new byte[i4];
        int i5 = i3 - 2;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i5) {
            a(bArr, i6 + i2, 3, bArr2, i7);
            i6 += 3;
            i7 += 4;
        }
        if (i6 < i3) {
            a(bArr, i2 + i6, i3 - i6, bArr2, i7);
            i7 += 4;
        }
        if (i7 > i4 - 1) {
            return bArr2;
        }
        byte[] bArr3 = new byte[i7];
        System.arraycopy(bArr2, 0, bArr3, 0, i7);
        return bArr3;
    }

    public static String c(Context context, String str) {
        try {
            return a(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData, str);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static String a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            Iterator<String> it = bundle.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().equalsIgnoreCase(str)) {
                    return String.valueOf(bundle.get(str));
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(1, generateSecret, new IvParameterSpec(o));
            return cipher.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, generateSecret, new IvParameterSpec(o));
            return cipher.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(h + i + j).getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        try {
            return a(MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8")));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static FileChannel d(Context context, String str) {
        RandomAccessFile randomAccessFile;
        try {
            File file = new File(context.getFilesDir(), str + "td.lock");
            if (!file.exists()) {
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                return randomAccessFile.getChannel();
            } catch (Throwable unused) {
                try {
                    randomAccessFile.close();
                    return null;
                } catch (Throwable unused2) {
                    return null;
                }
            }
        } catch (Throwable unused3) {
            randomAccessFile = null;
        }
    }

    public static byte[] e(String str) {
        DeflaterOutputStream deflaterOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater(9, true);
        DeflaterOutputStream deflaterOutputStream2 = null;
        try {
            try {
                deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        try {
            deflaterOutputStream.write(str.getBytes("UTF-8"));
            deflaterOutputStream.close();
        } catch (Throwable unused3) {
            deflaterOutputStream2 = deflaterOutputStream;
            if (deflaterOutputStream2 != null) {
                deflaterOutputStream2.close();
            }
            deflater.end();
            return byteArrayOutputStream.toByteArray();
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] c(byte[] bArr) {
        Inflater inflater = new Inflater();
        inflater.reset();
        inflater.setInput(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        try {
            byte[] bArr2 = new byte[1024];
            while (!inflater.finished()) {
                byteArrayOutputStream.write(bArr2, 0, inflater.inflate(bArr2));
            }
            bArr = byteArrayOutputStream.toByteArray();
        } catch (Throwable unused) {
        }
        try {
            byteArrayOutputStream.close();
        } catch (Throwable unused2) {
        }
        inflater.end();
        return bArr;
    }
}
