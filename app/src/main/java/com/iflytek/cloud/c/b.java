package com.iflytek.cloud.c;

import android.content.Context;
import android.media.AudioTrack;
import android.os.MemoryFile;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class b {
    private ArrayList<a> b;
    private Context c;
    private int d;
    private volatile int e;
    private volatile long g;
    private String k;
    private int a = 3145728;
    private MemoryFile f = null;
    private volatile int h = 0;
    private a i = null;
    private String j = "";
    private byte[] l = null;
    private int m = 0;
    private int n = 0;

    /* loaded from: classes.dex */
    public class a {
        long a;
        long b;
        int c;
        int d;

        public a(long j, long j2, int i, int i2) {
            this.a = j;
            this.b = j2;
            this.c = i;
            this.d = i2;
        }
    }

    public b(Context context, int i, String str) {
        this.b = null;
        this.c = null;
        this.d = 16000;
        this.e = 0;
        this.g = 0L;
        this.k = null;
        this.c = context;
        this.e = 0;
        this.b = new ArrayList<>();
        this.g = 0L;
        this.d = i;
        this.k = str;
    }

    private void a(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        if (this.f == null) {
            this.j = i();
            MemoryFile memoryFile = new MemoryFile(this.j, this.a);
            this.f = memoryFile;
            memoryFile.allowPurging(false);
        }
        this.f.writeBytes(bArr, 0, (int) this.g, bArr.length);
        this.g += bArr.length;
    }

    private void b(int i) throws IOException {
        if (this.l == null) {
            this.l = new byte[i * 10];
        }
        int length = this.l.length;
        int i2 = (int) (this.g - this.h);
        if (i2 < length) {
            length = i2;
        }
        this.f.readBytes(this.l, this.h, 0, length);
        this.h += length;
        this.m = 0;
        this.n = length;
    }

    private String i() {
        return com.iflytek.cloud.a.g.e.a(this.c) + System.currentTimeMillis() + "tts.pcm";
    }

    public int a() {
        return this.d;
    }

    public void a(AudioTrack audioTrack, int i) throws IOException {
        if (this.m >= this.n) {
            b(i);
        }
        int i2 = i * 2;
        int i3 = this.n;
        int i4 = this.m;
        int i5 = i2 > i3 - i4 ? i3 - i4 : i;
        audioTrack.write(this.l, this.m, i5);
        this.m += i5;
        if (f()) {
            b(audioTrack, i);
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int length = (str.length() / 5) * 4 * 32 * 1024;
        this.a = length;
        if (length <= 614400) {
            length = 614400;
        }
        this.a = length;
    }

    public void a(ArrayList<byte[]> arrayList, int i, int i2, int i3) throws IOException {
        com.iflytek.cloud.a.g.a.a.a("buffer percent = " + i + " beg=" + i2 + " end=" + i3);
        a aVar = new a(this.g, this.g, i2, i3);
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            a(arrayList.get(i4));
        }
        aVar.b = this.g;
        this.e = i;
        synchronized (this.b) {
            this.b.add(aVar);
        }
        com.iflytek.cloud.a.g.a.a.a("allSize = " + this.g + " maxSize=" + this.a);
    }

    public boolean a(int i) {
        if (this.e > 95) {
            return true;
        }
        return this.g / 32 >= ((long) i) && 0 < this.g;
    }

    public void b(AudioTrack audioTrack, int i) {
        audioTrack.write(new byte[i], 0, i);
    }

    public boolean b() {
        com.iflytek.cloud.a.g.a.a.a("save to local: totalSize = " + this.g + " maxSize=" + this.a);
        return com.iflytek.cloud.a.g.e.a(this.f, this.g, this.k);
    }

    public void c() throws IOException {
        this.h = 0;
        this.i = null;
        if (this.b.size() > 0) {
            this.i = this.b.get(0);
        }
    }

    public int d() {
        if (this.g <= 0) {
            return 0;
        }
        return (int) (((this.h - (this.n - this.m)) * this.e) / this.g);
    }

    public a e() {
        if (this.i == null) {
            return null;
        }
        if (this.h >= this.i.a && this.h <= this.i.b) {
            return this.i;
        }
        synchronized (this.b) {
            Iterator<a> it = this.b.iterator();
            while (it.hasNext()) {
                this.i = it.next();
                if (this.h >= this.i.a && this.h <= this.i.b) {
                    return this.i;
                }
            }
            return null;
        }
    }

    public boolean f() {
        return 100 == this.e && ((long) this.h) >= this.g && this.m >= this.n;
    }

    protected void finalize() throws Throwable {
        h();
        super.finalize();
    }

    public boolean g() {
        return ((long) this.h) < this.g || this.m < this.n;
    }

    public void h() {
        com.iflytek.cloud.a.g.a.a.a("deleteFile");
        try {
            if (this.f != null) {
                this.f.close();
                this.f = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
