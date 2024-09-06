package com.netease.nimlib.log.c.a;

import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.n.b.i;
import com.netease.nimlib.n.e;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* compiled from: FileUtils.java */
/* loaded from: classes.dex */
public class a {
    public static File a(String str) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.e("FileUtils", "file's parent dir is null, path=" + file.getCanonicalPath());
                }
                return null;
            }
            if (!parentFile.exists()) {
                if (parentFile.getParentFile().exists()) {
                    parentFile.mkdir();
                } else {
                    parentFile.mkdirs();
                }
            }
            if (file.exists() || file.createNewFile()) {
                return file;
            }
            if (com.netease.nimlib.log.b.a.a()) {
                Log.e("FileUtils", "can not create dest file, path=" + str);
            }
            return null;
        } catch (Throwable th) {
            e.a(i.kCreate, str, "FileUtils getFile failed,exception = " + th);
            if (com.netease.nimlib.log.b.a.a()) {
                Log.e("FileUtils", "create dest file error, path=" + str, th);
            }
            return null;
        }
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return a(str.getBytes(StandardCharsets.UTF_8), str2);
    }

    public static boolean a(byte[] bArr, String str) {
        boolean z = false;
        if (bArr == null || bArr.length <= 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str, true);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                    return true;
                } catch (Exception e) {
                    e = e;
                    z = true;
                    e.printStackTrace();
                    return z;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable unused) {
                    }
                    throw th2;
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static synchronized void a(String str, int i, int i2) {
        FileOutputStream fileOutputStream;
        synchronized (a.class) {
            File file = new File(str);
            if (file.length() < i) {
                return;
            }
            if (file.length() > 2147483647L) {
                file.delete();
                return;
            }
            File file2 = new File(str + "_tmp");
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        long j = i2;
                        fileInputStream2.getChannel().position(file.length() - j);
                        fileOutputStream.getChannel().transferFrom(fileInputStream2.getChannel(), 0L, j);
                        a(fileInputStream2);
                    } catch (Exception e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                        try {
                            e.a(i.kWrite, str, "FileUtils shrink failed,exception = " + e);
                            e.printStackTrace();
                            a(fileInputStream);
                            a(fileOutputStream);
                            if (file2.exists()) {
                                file2.renameTo(file);
                            }
                        } catch (Throwable th) {
                            th = th;
                            a(fileInputStream);
                            a(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        a(fileInputStream);
                        a(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
            }
            a(fileOutputStream);
            if (file2.exists() && file.delete()) {
                file2.renameTo(file);
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String b(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            if (file.getParentFile().exists()) {
                file.mkdir();
            } else {
                file.mkdirs();
            }
        }
        if (str.endsWith("/")) {
            return str + str2;
        }
        return str + File.separator + str2;
    }

    public static boolean b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf > -1 && lastIndexOf < str.length() - 1;
    }

    public static String c(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(46)) <= -1 || lastIndexOf >= str.length() + (-1)) ? "" : str.substring(lastIndexOf + 1);
    }

    public static String d(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(47)) <= -1 || lastIndexOf >= str.length() + (-1)) ? str : str.substring(lastIndexOf + 1);
    }

    public static String e(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(46)) <= -1 || lastIndexOf >= str.length()) ? str : str.substring(0, lastIndexOf);
    }
}
