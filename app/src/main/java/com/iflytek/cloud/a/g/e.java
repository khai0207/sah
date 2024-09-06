package com.iflytek.cloud.a.g;

import android.content.Context;
import android.os.MemoryFile;
import android.text.TextUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public class e {
    private static String a = "";

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        String str = absolutePath + "msclib/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        a = str;
        return str;
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void a(ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue, String str) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                b(str);
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    Iterator<byte[]> it = concurrentLinkedQueue.iterator();
                    while (it.hasNext()) {
                        fileOutputStream2.write(it.next());
                    }
                    fileOutputStream2.close();
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static boolean a(MemoryFile memoryFile, long j, String str) {
        boolean z = false;
        if (memoryFile == null || TextUtils.isEmpty(str)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    a(str);
                    b(str);
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                    try {
                        byte[] bArr = new byte[1024];
                        int i = 0;
                        while (true) {
                            long j2 = i;
                            if (j2 >= j) {
                                break;
                            }
                            long j3 = j - j2;
                            if (j3 > ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) {
                                j3 = 1024;
                            }
                            int i2 = (int) j3;
                            memoryFile.readBytes(bArr, i, 0, i2);
                            fileOutputStream2.write(bArr, 0, i2);
                            i += i2;
                        }
                        z = true;
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception unused2) {
        }
        return z;
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (!str.endsWith("/")) {
            file = file.getParentFile();
        }
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }
}
