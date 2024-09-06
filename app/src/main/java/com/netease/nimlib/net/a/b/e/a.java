package com.netease.nimlib.net.a.b.e;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: FileInput.java */
/* loaded from: classes.dex */
public class a {
    private static final String a = b.a(a.class);
    private final RandomAccessFile b;
    private final File c;
    private final String d;

    public a(File file, String str) throws FileNotFoundException {
        this.c = file;
        this.b = new RandomAccessFile(file, "r");
        this.d = (str == null || str.trim().length() <= 0) ? file.getName() : str;
    }

    public long a() {
        return this.c.length();
    }

    public void b() {
        RandomAccessFile randomAccessFile = this.b;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e(a, "close file exception", th);
            }
        }
    }

    public byte[] a(long j, int i) throws IOException {
        if (j == 0 && i == 0 && a() == 0) {
            return new byte[0];
        }
        if (j >= a()) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.b.seek(j);
        this.b.read(bArr);
        return bArr;
    }
}
