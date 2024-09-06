package com.netease.nimlib.log.a;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.m.q.g;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: SimpleMMapWriter.java */
/* loaded from: classes.dex */
public class a {
    private final int a;
    private final int b;
    private RandomAccessFile c;
    private MappedByteBuffer d;
    private File e;
    private File f;
    private int g;

    public a() {
        this(0, 0);
    }

    public a(int i, int i2) {
        this.g = 0;
        this.a = (i <= 0 || i <= i2) ? 131072 : i;
        this.b = (i2 <= 0 || i2 >= i) ? 65536 : i2;
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (g()) {
            a();
        }
        try {
            File a = com.netease.nimlib.log.c.a.a.a(str2);
            this.f = a;
            if (a == null) {
                b("dest file path invalid, path=" + str2);
                return false;
            }
            File a2 = com.netease.nimlib.log.c.a.a.a(str);
            this.e = a2;
            if (a2 == null) {
                b("mapped file path invalid, path=" + str);
                return false;
            }
            c("try to open mapped file, path=" + this.e.getCanonicalPath());
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.e, "rw");
            this.c = randomAccessFile;
            if (randomAccessFile.length() <= 0) {
                this.c.setLength(this.a);
            }
            this.d = this.c.getChannel().map(FileChannel.MapMode.READ_WRITE, 0L, this.a);
            this.d.position(d());
            c();
            c("open file success, path=" + this.e.getCanonicalPath() + ", offset=" + this.d.position() + ", file length=" + this.e.length());
            return true;
        } catch (IOException e) {
            b("open file error, e=" + e.getMessage());
            return true;
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!g()) {
            b("SimpleMappedByteBuffer is invalid when do write");
            return;
        }
        if (this.e == null) {
            b("mapped file is null, write failed!");
            return;
        }
        if (this.f == null) {
            b("dest file is null, write failed!");
            return;
        }
        try {
            byte[] bytes = str.getBytes("UTF8");
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] == 0) {
                    bytes[i] = 32;
                }
            }
            if (bytes.length >= this.d.remaining()) {
                b("write content is more larger than mapped buffer's remaining size, append to dest file directly, content size=" + bytes.length + ", buffer remaining=" + this.d.remaining() + ", buffer limit=" + this.d.limit() + ", content=" + str);
                a(bytes);
                return;
            }
            int position = this.d.position();
            try {
                this.d.put(bytes);
                e();
                f();
                StringBuilder sb = new StringBuilder();
                sb.append("write position from ");
                sb.append(position);
                sb.append(" to ");
                sb.append(this.d.position() - 1);
                sb.append("/");
                sb.append(this.d.limit());
                sb.append(", add ");
                sb.append(bytes.length);
                c(sb.toString());
                if (this.d.position() >= this.b) {
                    c("mapped buffer should flush to dest file, position=" + this.d.position() + "/" + this.d.limit());
                    c();
                }
            } catch (Exception e) {
                b("write MappedByteBuffer error, e=" + e.getMessage());
            }
        } catch (UnsupportedEncodingException e2) {
            b("content get bytes error! give up to write, e=" + e2.getMessage());
            e2.printStackTrace();
        }
    }

    public void a() {
        if (this.d != null) {
            b();
            this.d.clear();
            this.d = null;
        }
        com.netease.nimlib.log.c.a.a.a(this.c);
        c("file close success");
    }

    private void c() {
        if (!g()) {
            b("SimpleMappedByteBuffer is invalid when do flush");
            return;
        }
        if (this.f == null) {
            b("dest file is null when do flush");
            return;
        }
        try {
            this.d.position(0);
            int i = this.d.getInt();
            if (i < 4 || i >= this.d.limit()) {
                i = e();
            }
            if (i <= 4) {
                c("no need to flush, offset=" + i);
                return;
            }
            byte[] bArr = new byte[i - 4];
            this.d.position(4);
            this.d.get(bArr);
            com.netease.nimlib.log.c.a.a.a(bArr, this.f.getAbsolutePath());
            this.d.position(0);
            int e = e();
            this.d.force();
            this.d.position(e);
            c("flush file success, new offset=" + e);
        } catch (Exception e2) {
            c("flush file failed, exception = " + e2);
        }
    }

    private void a(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        c();
        File file = this.f;
        if (file == null) {
            b("dest file is null when do directly append");
            return;
        }
        boolean a = com.netease.nimlib.log.c.a.a.a(bArr, file.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        sb.append("append to dest file directly ");
        sb.append(a ? Constant.CASH_LOAD_SUCCESS : g.j);
        c(sb.toString());
    }

    private int d() {
        this.d.position(0);
        int i = this.d.getInt();
        if (i >= 4 && i < this.d.limit()) {
            this.d.position(i);
            return i;
        }
        this.d.position(0);
        return e();
    }

    private int e() {
        int position = this.d.position();
        if (position < 4) {
            position = 4;
        }
        this.d.position(0);
        this.d.putInt(position);
        this.d.position(position);
        return position;
    }

    public void b() {
        if (g()) {
            c("force flush to dest file");
            c();
        }
    }

    private void f() {
        int i = this.g + 1;
        this.g = i;
        if (i < 100 || !g()) {
            return;
        }
        this.d.force();
        this.g = 0;
        c("flush to mapped file");
    }

    private boolean g() {
        return (this.c == null || this.d == null) ? false : true;
    }

    private void b(String str) {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.e("SimpleMMapWriter", str);
        }
    }

    private void c(String str) {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i("SimpleMMapWriter", str);
        }
    }
}
