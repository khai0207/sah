package com.unionpay.sdk;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class aw {
    static boolean a = true;
    static boolean b = false;
    static boolean c = false;
    private static final ExecutorService d = Executors.newSingleThreadExecutor();

    static String a(Context context, String str) {
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(str);
            try {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                String string = new JSONObject(new String(bArr)).getString("channel_id");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused) {
                    }
                }
                return string;
            } catch (Throwable unused2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            }
        } catch (Throwable unused4) {
            inputStream = null;
        }
    }

    static final String a(String str) {
        return str.length() > 256 ? str.substring(0, 256) : str;
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            int i = b2 & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    static FileChannel a() {
        RandomAccessFile randomAccessFile;
        try {
            File file = new File(ad.c.getFilesDir(), "unionpaytd.lock");
            if (!file.exists()) {
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            return randomAccessFile.getChannel();
        } catch (Throwable th2) {
            th = th2;
            try {
                randomAccessFile.close();
            } catch (Exception e) {
                if (UPAgent.LOG_ON) {
                    e.printStackTrace();
                }
            }
            if (!UPAgent.LOG_ON) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    static void a(Class cls, ao aoVar, String str, String str2) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj = declaredField.get(null);
        Class<?> cls2 = Class.forName(str2);
        declaredField.set(null, Proxy.newProxyInstance(cls.getClass().getClassLoader(), new Class[]{cls2}, new ay(aoVar, obj)));
    }

    static void a(Object obj, ao aoVar, String str, String str2) {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj2 = declaredField.get(obj);
        Class<?> cls = Class.forName(str2);
        declaredField.set(obj, Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{cls}, new ax(aoVar, obj2)));
    }

    static void a(Runnable runnable) {
        d.execute(runnable);
    }

    static boolean a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    static boolean b(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    static final boolean b(String str) {
        return str == null || str.trim().isEmpty();
    }

    static String c(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    static String d(String str) {
        if (str == null) {
            return null;
        }
        try {
            return a(MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }
}
