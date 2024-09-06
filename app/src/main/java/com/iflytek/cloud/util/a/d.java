package com.iflytek.cloud.util.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes.dex */
public class d {
    public static int a(String str, String str2, boolean z) {
        int i = 0;
        try {
            File file = new File(str);
            if (file.exists()) {
                if (z) {
                    file.delete();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write(str2.getBytes("utf-8"));
                i = (int) randomAccessFile.length();
                randomAccessFile.close();
                return i;
            }
            file.createNewFile();
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(randomAccessFile2.length());
            randomAccessFile2.write(str2.getBytes("utf-8"));
            i = (int) randomAccessFile2.length();
            randomAccessFile2.close();
            return i;
        } catch (IOException unused) {
            com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "save file failed. " + str);
            return i;
        }
    }

    public static String a(String str) {
        FileInputStream fileInputStream;
        String str2;
        String str3 = null;
        try {
            fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            str2 = new String(bArr, "utf-8");
        } catch (IOException unused) {
        }
        try {
            fileInputStream.close();
            return str2;
        } catch (IOException unused2) {
            str3 = str2;
            com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "load file failed. " + str);
            return str3;
        }
    }
}
