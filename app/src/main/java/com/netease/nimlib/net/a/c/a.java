package com.netease.nimlib.net.a.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.netease.nimlib.n.b.i;
import com.netease.nimlib.o.y;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: AttachmentStore.java */
/* loaded from: classes.dex */
public class a {
    public static long a(String str, String str2) {
        return a(str, str2, -1L);
    }

    public static long a(String str, String str2, long j) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            File file = new File(str);
            if (!file.exists()) {
                return -1L;
            }
            if (str.equals(str2)) {
                return file.length();
            }
            long a = y.a();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(a(str2));
                    try {
                        FileChannel channel = fileInputStream.getChannel();
                        try {
                            FileChannel channel2 = fileOutputStream.getChannel();
                            try {
                                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4096);
                                while (channel.read(allocateDirect) != -1) {
                                    allocateDirect.flip();
                                    channel2.write(allocateDirect);
                                    allocateDirect.clear();
                                    if (j > 0) {
                                        long a2 = y.a() - a;
                                        if (a2 > j) {
                                            com.netease.nimlib.log.b.f("AttachmentStore", String.format("copy timeout %s duration %s", Long.valueOf(j), Long.valueOf(a2)));
                                            if (channel2 != null) {
                                                channel2.close();
                                            }
                                            if (channel != null) {
                                                channel.close();
                                            }
                                            fileOutputStream.close();
                                            fileInputStream.close();
                                            return -100L;
                                        }
                                    }
                                }
                                long length = file.length();
                                if (channel2 != null) {
                                    channel2.close();
                                }
                                if (channel != null) {
                                    channel.close();
                                }
                                fileOutputStream.close();
                                fileInputStream.close();
                                return length;
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e) {
                com.netease.nimlib.n.e.a(i.kCopy, str, "copy file failed,dstPath = " + str2 + ",exception = " + e);
                e.printStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("copy Exception:");
                sb.append(e);
                com.netease.nimlib.log.b.e("AttachmentStore", sb.toString(), e);
            }
        }
        return -1L;
    }

    public static boolean a(Context context, Uri uri, String str) {
        if (uri != null && !TextUtils.isEmpty(str)) {
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
                if (openFileDescriptor == null) {
                    return false;
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(a(str));
                        try {
                            FileChannel channel = fileInputStream.getChannel();
                            try {
                                FileChannel channel2 = fileOutputStream.getChannel();
                                try {
                                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4096);
                                    while (channel.read(allocateDirect) != -1) {
                                        allocateDirect.flip();
                                        channel2.write(allocateDirect);
                                        allocateDirect.clear();
                                    }
                                    if (channel2 != null) {
                                        channel2.close();
                                    }
                                    if (channel != null) {
                                        channel.close();
                                    }
                                    fileOutputStream.close();
                                    fileInputStream.close();
                                    return true;
                                } finally {
                                }
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } catch (Exception e) {
                    com.netease.nimlib.n.e.a(i.kCopy, uri.getPath(), "copy file failed,dstPath = " + str + ",exception = " + e);
                    com.netease.nimlib.log.b.e("AttachmentStore", "copy is error", e);
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                File file2 = new File(str2);
                if (file2.getParentFile() == null) {
                    return false;
                }
                if (!file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                try {
                    return file.renameTo(file2);
                } catch (Exception e) {
                    com.netease.nimlib.n.e.a(i.kMove, str, "move file failed,dstFilePath = " + str2 + ",excpetion = " + e);
                    throw e;
                }
            }
        }
        return false;
    }

    public static File a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            com.netease.nimlib.n.e.a(i.kCreate, str, "create file failed,exception = " + e);
            if (file.exists()) {
                file.delete();
            }
            return null;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            return a(file).delete();
        } catch (Throwable th) {
            com.netease.nimlib.n.e.a(i.kDelete, str, "delete file failed,exception = " + th);
            throw th;
        }
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                file.deleteOnExit();
            }
        } catch (Exception e) {
            com.netease.nimlib.n.e.a(i.kDelete, str, "delete file failed,exception = " + e);
            throw e;
        }
    }

    private static File a(File file) {
        File file2 = new File(file.getParent() + "/" + System.currentTimeMillis() + "_tmp");
        return file.renameTo(file2) ? file2 : file;
    }

    public static boolean d(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static boolean a(Bitmap bitmap, String str, boolean z) {
        if (bitmap != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(str);
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                            try {
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bufferedOutputStream);
                                bufferedOutputStream.close();
                                fileOutputStream.close();
                                if (z) {
                                    bitmap.recycle();
                                }
                                return true;
                            } finally {
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
                    } catch (Exception e) {
                        com.netease.nimlib.n.e.a(i.kWrite, str, "saveBitmap failed,exception = " + e);
                        if (z) {
                            bitmap.recycle();
                        }
                        return false;
                    }
                }
            } catch (Throwable th3) {
                if (z) {
                    bitmap.recycle();
                }
                throw th3;
            }
        }
        return false;
    }
}
