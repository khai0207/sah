package com.talkingdata.sdk;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CRC32;

/* compiled from: td */
/* loaded from: classes.dex */
public class ax {
    private static final long l = 3145728;
    private static final String m = "td-cache";
    private static final String n = "td-cache_pos_pref";
    Context a;
    File b;
    RandomAccessFile c;
    String d;
    FileLock g;
    long i;
    long j;
    CRC32 e = new CRC32();
    Lock f = new ReentrantLock();
    Lock h = new ReentrantLock();
    long k = -1;

    public ax(Context context, String str) {
        this.i = 0L;
        this.j = 0L;
        try {
            this.a = context;
            this.d = str;
            this.b = context.getDir(m, 0);
            g();
            a();
            long b = bd.b(context, n, str, 0L);
            this.j = b;
            this.i = b;
            try {
                h();
            } catch (IOException unused) {
            }
            if (this.c.length() > l) {
                f();
            }
        } catch (Throwable unused2) {
        }
        b();
    }

    public void a() {
        this.h.lock();
        this.g = this.c.getChannel().lock();
    }

    public void b() {
        FileLock fileLock = this.g;
        if (fileLock != null) {
            try {
                fileLock.release();
                this.h.unlock();
            } catch (Throwable unused) {
            }
        }
    }

    public List a(int i) {
        LinkedList linkedList = new LinkedList();
        try {
            long b = bd.b(this.a, n, this.d, 0L);
            this.j = b;
            this.c.seek(b);
            while (this.j < this.c.length()) {
                byte[] a = a(this.j, false);
                if (a != null) {
                    linkedList.add(a);
                }
                if (linkedList.size() >= i) {
                    break;
                }
            }
        } catch (IOException unused) {
        }
        if (linkedList.size() == 0) {
            this.i = this.j;
        }
        return linkedList;
    }

    public void c() {
        b(this.j);
        long j = this.j;
        this.i = j;
        bd.a(this.a, n, this.d, j);
    }

    public void write(byte[] bArr) {
        a(bArr);
    }

    public void d() {
        this.c.getFD().sync();
    }

    public void e() {
        d();
        this.c.close();
    }

    private void f() {
        long j = this.i;
        long j2 = this.k;
        if (j < j2) {
            j = j2;
        }
        this.j = j;
        File file = new File(this.b, this.d + ".tmp");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (this.j < this.c.length()) {
            try {
                byte[] a = a(this.j, false);
                if (a != null) {
                    fileOutputStream.write(a);
                }
            } catch (Throwable th) {
                fileOutputStream.flush();
                fileOutputStream.close();
                this.c.close();
                throw th;
            }
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        this.c.close();
        File file2 = new File(this.b, this.d);
        file2.delete();
        file.renameTo(file2);
        g();
        this.i = 0L;
        this.j = 0L;
        bd.a(this.a, n, this.d, 0L);
    }

    private void g() {
        this.c = new RandomAccessFile(new File(this.b, this.d), "rw");
    }

    private void h() {
        boolean z = false;
        while (this.j < this.c.length()) {
            if (this.k == -1) {
                long length = this.c.length();
                long j = this.j;
                if (length - j < l) {
                    this.k = j;
                }
            }
            long j2 = this.j;
            if (a(j2) && !z) {
                z = true;
                if (this.i == 0) {
                    this.i = j2;
                }
            }
        }
    }

    private boolean a(long j) {
        byte readByte;
        try {
            this.f.lock();
            try {
                this.c.seek(j);
                readByte = this.c.readByte();
            } catch (Exception unused) {
            }
            if (readByte == 31) {
                int readInt = this.c.readInt();
                short readShort = this.c.readShort();
                if (readShort >= 0 && this.c.getFilePointer() + readShort <= this.c.length()) {
                    this.e.reset();
                    for (int i = 0; i < readShort; i++) {
                        this.e.update(this.c.read());
                    }
                    if (this.c.readByte() == 31 && readInt == ((int) this.e.getValue())) {
                        this.j = this.c.getFilePointer();
                        this.f.unlock();
                        return true;
                    }
                }
            } else if (readByte == 46) {
                int readInt2 = this.c.readInt();
                byte readByte2 = this.c.readByte();
                if (readInt2 >= 0) {
                    long j2 = readInt2;
                    if (j2 < this.c.length() && readByte2 == 46) {
                        this.j = this.c.getFilePointer();
                        this.i = j2;
                        return false;
                    }
                }
            }
            this.j = j + 1;
            return false;
        } finally {
            this.f.unlock();
        }
    }

    private byte[] a(long j, boolean z) {
        byte readByte;
        try {
            this.f.lock();
            try {
                this.c.seek(j);
                readByte = this.c.readByte();
            } catch (Exception unused) {
            }
            if (readByte == 31) {
                int readInt = this.c.readInt();
                int readShort = this.c.readShort();
                if (readShort >= 0 && this.c.getFilePointer() + readShort <= this.c.length()) {
                    byte[] bArr = new byte[readShort];
                    this.c.readFully(bArr);
                    if (this.c.readByte() == 31) {
                        this.e.reset();
                        this.e.update(bArr);
                        if (readInt == ((int) this.e.getValue())) {
                            this.j = this.c.getFilePointer();
                            return bArr;
                        }
                    }
                }
            } else if (readByte == 46) {
                int readInt2 = this.c.readInt();
                byte readByte2 = this.c.readByte();
                if (readInt2 >= 0) {
                    long j2 = readInt2;
                    if (j2 < this.c.length() && readByte2 == 46) {
                        this.j = this.c.getFilePointer();
                        if (z) {
                            this.i = j2;
                        }
                        return null;
                    }
                }
            }
            this.j = j + 1;
            return null;
        } finally {
            this.f.unlock();
        }
    }

    private void a(byte[] bArr) {
        try {
            this.f.lock();
            this.c.seek(this.c.length());
            this.c.writeByte(31);
            this.e.reset();
            this.e.update(bArr);
            this.c.writeInt((int) this.e.getValue());
            this.c.writeShort(bArr.length);
            this.c.write(bArr);
            this.c.writeByte(31);
        } finally {
            this.f.unlock();
        }
    }

    private void b(long j) {
        try {
            this.f.lock();
            this.c.seek(this.c.length());
            this.c.writeByte(46);
            this.c.writeInt((int) j);
            this.c.writeByte(46);
        } finally {
            this.f.unlock();
        }
    }
}
