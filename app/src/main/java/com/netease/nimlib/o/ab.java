package com.netease.nimlib.o;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* compiled from: ZipUtil.java */
/* loaded from: classes.dex */
public class ab {
    public static void a(String str, List<String> list, String str2) throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                a(str, it.next(), zipOutputStream);
            }
            zipOutputStream.finish();
            zipOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    zipOutputStream.close();
                } catch (Throwable unused) {
                }
                throw th2;
            }
        }
    }

    private static void a(String str, String str2, ZipOutputStream zipOutputStream) throws Exception {
        String name;
        if (zipOutputStream == null) {
            return;
        }
        File file = new File(str2);
        if (str2.startsWith(str)) {
            name = str2.substring(str.length()).replace("\\", "/");
        } else {
            name = file.getName();
        }
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(name);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                zipOutputStream.putNextEntry(zipEntry);
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read != -1) {
                        zipOutputStream.write(bArr, 0, read);
                    } else {
                        zipOutputStream.closeEntry();
                        return;
                    }
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } finally {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable unused) {
                    }
                }
            }
        } else {
            String[] list = file.list();
            if (list.length <= 0) {
                zipOutputStream.putNextEntry(new ZipEntry(name + "/"));
                zipOutputStream.closeEntry();
            }
            for (String str3 : list) {
                a(str, str2 + File.separator + str3, zipOutputStream);
            }
        }
    }
}
