package com.netease.nimlib.push;

import android.text.TextUtils;
import com.netease.nimlib.o.m;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: DeviceIdStore.java */
/* loaded from: classes.dex */
public class b {
    private static final Map<String, String> a = new HashMap();

    public static synchronized boolean a() {
        synchronized (b.class) {
            File file = new File(a("nim_device"));
            if (!file.exists()) {
                com.netease.nimlib.log.b.d("DeviceIdStore", "no need to delete old device id file, not exist");
                return true;
            }
            try {
                boolean delete = file.delete();
                com.netease.nimlib.log.b.d("DeviceIdStore", "delete old device id file result=" + delete);
                return delete;
            } catch (Throwable th) {
                com.netease.nimlib.log.b.c("DeviceIdStore", "delete old device id file error", th);
                return false;
            }
        }
    }

    public static synchronized String b() {
        synchronized (b.class) {
            FileLock a2 = a(a("nim_device"), false);
            FileChannel channel = a2 != null ? a2.channel() : null;
            String a3 = a(channel, "nim_device");
            a(a2, channel);
            if (a3 != null && !a3.isEmpty()) {
                String a4 = m.a(a3.toLowerCase());
                com.netease.nimlib.log.b.d("DeviceIdStore", "old device id tag is " + a4);
                return a4;
            }
            com.netease.nimlib.log.b.d("DeviceIdStore", "old device id is empty");
            return "";
        }
    }

    public static synchronized String c() {
        synchronized (b.class) {
            try {
                String g = com.netease.nimlib.c.g();
                String str = a.get(g);
                if (!TextUtils.isEmpty(str)) {
                    com.netease.nimlib.log.b.d("DeviceIdStore", "read device id from ram: " + str);
                    return str;
                }
                FileLock a2 = a(a("nim_device2"), true);
                FileChannel channel = a2 != null ? a2.channel() : null;
                String a3 = a(channel, "nim_device2");
                if (!TextUtils.isEmpty(a3)) {
                    a(a2, channel);
                    com.netease.nimlib.log.b.d("DeviceIdStore", "read device id from nim_device2: " + a3);
                    a.put(g, a3);
                    return a3;
                }
                String uuid = UUID.randomUUID().toString();
                a(uuid, channel);
                a(a2, channel);
                com.netease.nimlib.log.b.d("DeviceIdStore", "generate new device id: " + uuid);
                a.put(g, uuid);
                return uuid;
            } catch (Throwable th) {
                com.netease.nimlib.log.b.f("DeviceIdStore", "getOrCreateDeviceId exception：" + th);
                return "";
            }
        }
    }

    private static String a(String str) {
        return com.netease.nimlib.a.a(com.netease.nimlib.c.g()) + "/" + str;
    }

    private static String a(FileChannel fileChannel, String str) {
        if (fileChannel == null) {
            com.netease.nimlib.log.b.d("DeviceIdStore", "read deviceId from file, channel == null ");
            return null;
        }
        try {
            int length = (int) new File(a(str)).length();
            if (length <= 0) {
                return null;
            }
            ByteBuffer allocate = ByteBuffer.allocate(length);
            fileChannel.read(allocate);
            return new String(allocate.array());
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("DeviceIdStore", "read deviceId from file error, e = " + th);
            return null;
        }
    }

    private static void a(String str, FileChannel fileChannel) {
        if (fileChannel == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            fileChannel.write(ByteBuffer.wrap(str.getBytes()));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("DeviceIdStore", "write deviceId to file error, e = " + th);
        }
    }

    private static FileLock a(String str, boolean z) {
        RandomAccessFile randomAccessFile;
        if (z) {
            try {
                if (c(str) == null) {
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
                com.netease.nimlib.log.b.d("DeviceIdStore", "try fileLock error, e = " + th);
                com.netease.nimlib.log.c.a.a.a(randomAccessFile);
                return null;
            }
        }
        if (b(str) == null) {
            return null;
        }
        randomAccessFile = new RandomAccessFile(str, "rw");
        try {
            return randomAccessFile.getChannel().lock();
        } catch (Throwable th2) {
            th = th2;
            com.netease.nimlib.log.b.d("DeviceIdStore", "try fileLock error, e = " + th);
            com.netease.nimlib.log.c.a.a.a(randomAccessFile);
            return null;
        }
    }

    private static File b(String str) {
        try {
            File file = new File(str);
            Object[] objArr = new Object[2];
            objArr[0] = file.exists() ? "get file succeed" : "dest file no exist";
            objArr[1] = str;
            com.netease.nimlib.log.b.d("DeviceIdStore", String.format("%s, path=%s", objArr));
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("DeviceIdStore", "get dest file error, path= " + str + " e = " + th);
            return null;
        }
    }

    private static File c(String str) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                com.netease.nimlib.log.b.d("DeviceIdStore", "file's parent dir is null, path=" + file.getCanonicalPath());
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
            com.netease.nimlib.log.b.d("DeviceIdStore", "can not create dest file, path=" + str);
            return null;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("DeviceIdStore", "create dest file error, path= " + str + " e = " + th);
            return null;
        }
    }

    private static void a(FileLock fileLock, FileChannel fileChannel) {
        if (fileLock != null) {
            try {
                fileLock.release();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d("DeviceIdStore", "release fileLock error, e = " + th);
            }
        }
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (Throwable th2) {
                com.netease.nimlib.log.b.d("DeviceIdStore", "close fileChannel error, e = " + th2);
            }
        }
    }
}
