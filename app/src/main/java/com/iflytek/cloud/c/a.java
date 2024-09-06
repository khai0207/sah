package com.iflytek.cloud.c;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.c.f;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes.dex */
public class a extends f {
    private int a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private final short f;
    private int g;
    private int h;
    private byte[] i;
    private RandomAccessFile j;
    private String k;
    private f.a l;

    public a(int i, int i2, int i3, String str) {
        super(i, i2, i3);
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.e = 16000;
        this.f = (short) 16;
        this.g = 40;
        this.h = 40;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.e = i;
        this.g = i2;
        this.h = i2;
        this.k = str;
    }

    private int a() throws SpeechError {
        RandomAccessFile randomAccessFile = this.j;
        int i = 0;
        if (randomAccessFile != null && this.l != null) {
            if (this.b >= this.a) {
                try {
                    this.b = 0;
                    int read = randomAccessFile.read(this.i, 0, this.i.length);
                    this.a = read;
                    if (read < 0) {
                        return -1;
                    }
                } catch (IOException unused) {
                    throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                }
            }
            int i2 = this.a;
            if (i2 > 0 && this.l != null) {
                int i3 = this.b;
                int i4 = i2 - i3;
                int i5 = this.c;
                i = i4 > i5 ? i5 : i2 - i3;
                this.l.a(this.i, this.b, i);
                this.b += i;
            }
        }
        return i;
    }

    private void b() {
        if (this.j != null) {
            com.iflytek.cloud.a.g.a.a.a("release record begin");
            try {
                this.j.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.j = null;
            f.a aVar = this.l;
            if (aVar != null) {
                aVar.k();
                this.l = null;
            }
            com.iflytek.cloud.a.g.a.a.a("release record over");
        }
        if (this.i != null) {
            this.i = null;
        }
    }

    @Override // com.iflytek.cloud.c.f
    public void a(f.a aVar) throws SpeechError {
        this.l = aVar;
        setPriority(10);
        start();
    }

    @Override // com.iflytek.cloud.c.f
    public void a(short s, int i, int i2) throws SpeechError {
        int i3 = ((((i * 40) / 1000) * s) * 16) / 8;
        this.c = i3;
        this.i = new byte[i3 * 10];
        try {
            this.j = new RandomAccessFile(this.k, "r");
        } catch (FileNotFoundException unused) {
            throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
        }
    }

    @Override // com.iflytek.cloud.c.f
    public void a(boolean z) {
        this.d = true;
    }

    @Override // com.iflytek.cloud.c.f
    protected void finalize() throws Throwable {
        b();
        super.finalize();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
    
        r3.d = true;
     */
    @Override // com.iflytek.cloud.c.f, java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r3 = this;
            int r0 = r3.e     // Catch: java.lang.Exception -> L25
            int r1 = r3.g     // Catch: java.lang.Exception -> L25
            r2 = 1
            r3.a(r2, r0, r1)     // Catch: java.lang.Exception -> L25
            com.iflytek.cloud.c.f$a r0 = r3.l     // Catch: java.lang.Exception -> L25
            if (r0 == 0) goto L11
            com.iflytek.cloud.c.f$a r0 = r3.l     // Catch: java.lang.Exception -> L25
            r0.c(r2)     // Catch: java.lang.Exception -> L25
        L11:
            boolean r0 = r3.d     // Catch: java.lang.Exception -> L25
            if (r0 != 0) goto L37
            int r0 = r3.a()     // Catch: java.lang.Exception -> L25
            if (r0 >= 0) goto L1e
            r3.d = r2     // Catch: java.lang.Exception -> L25
            goto L37
        L1e:
            int r0 = r3.h     // Catch: java.lang.Exception -> L25
            long r0 = (long) r0     // Catch: java.lang.Exception -> L25
            sleep(r0)     // Catch: java.lang.Exception -> L25
            goto L11
        L25:
            r0 = move-exception
            r0.printStackTrace()
            com.iflytek.cloud.c.f$a r0 = r3.l
            if (r0 == 0) goto L37
            com.iflytek.cloud.SpeechError r1 = new com.iflytek.cloud.SpeechError
            r2 = 20006(0x4e26, float:2.8034E-41)
            r1.<init>(r2)
            r0.b(r1)
        L37:
            r3.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.c.a.run():void");
    }
}
