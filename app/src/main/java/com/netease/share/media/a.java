package com.netease.share.media;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.o.x;
import com.netease.share.media.internal.audio.AudioProcessModule;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import u.aly.df;

/* compiled from: AudioRecord.java */
/* loaded from: classes.dex */
public class a {
    private Context a;
    private int[] b;
    private String c;
    private int d;
    private int e;
    private long f;
    private int g;
    private int h;
    private int i;
    private byte j;
    private byte k;
    private int l;
    private int m;
    private int n;
    private AtomicInteger o;
    private AtomicBoolean p;
    private int q;
    private byte[] r;
    private byte[] s;
    private AudioRecord t;

    /* renamed from: u, reason: collision with root package name */
    private AtomicLong f30u;
    private Thread v;
    private int w;
    private Handler x;
    private c y;

    public int a() {
        if (this.o.get() == 3) {
            int i = this.n;
            this.n = 0;
            return i;
        }
        this.n = 0;
        return 0;
    }

    public a(Context context, String str, int i) throws IllegalArgumentException, UnsatisfiedLinkError {
        this(context, str, 1, i);
    }

    public a(Context context, String str, int i, int i2) throws IllegalArgumentException, UnsatisfiedLinkError {
        this.b = new int[]{44100, 22050, 16000, 8000};
        this.o = new AtomicInteger(1);
        this.p = new AtomicBoolean(false);
        this.v = null;
        this.x = new Handler(Looper.getMainLooper());
        this.y = null;
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "AudioRecord() called");
        }
        this.o.set(1);
        this.c = str;
        this.g = 0;
        this.h = 16;
        this.i = 2;
        this.j = df.n;
        this.k = (byte) 1;
        this.l = 44100;
        this.d = i;
        this.e = i2;
        this.m = 44100;
        this.a = context;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid path.");
        }
        int i3 = this.d;
        if (i3 < 1 || i3 > 2) {
            throw new IllegalArgumentException("Invalid audio codec.");
        }
        e();
        this.w = new Random().nextInt();
    }

    public void a(int i) {
        this.m = i;
    }

    public void b(int i) throws IllegalArgumentException {
        if (i < 1 || i > 2) {
            throw new IllegalArgumentException("Invalid audio codec.");
        }
        this.d = i;
    }

    public void a(c cVar) {
        this.y = cVar;
    }

    private void e() {
        x.a("ne_audio");
    }

    private void f() {
        boolean i;
        boolean z;
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "init() called");
        }
        if (this.d == 1) {
            int i2 = 0;
            i = false;
            while (true) {
                int[] iArr = this.b;
                if (i2 >= iArr.length) {
                    break;
                }
                int i3 = iArr[i2];
                this.l = i3;
                if (i3 <= this.m && (i = i())) {
                    break;
                } else {
                    i2++;
                }
            }
        } else {
            this.l = 8000;
            i = i();
        }
        if (i) {
            i = AudioProcessModule.a(this.l, (byte) this.d, Runtime.getRuntime().availableProcessors() >= 2);
        }
        if (!i) {
            h();
        }
        if (i) {
            File file = new File(this.c);
            if (file.exists()) {
                file.delete();
            }
            try {
                z = file.createNewFile();
            } catch (IOException unused) {
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.e("AudioRecord", "create file error");
                }
                z = false;
            }
            if (z) {
                this.p = new AtomicBoolean(false);
                this.f30u = new AtomicLong(0L);
                int i4 = this.e;
                if (i4 == Integer.MAX_VALUE) {
                    this.f = Long.MAX_VALUE;
                } else {
                    this.f = (((this.l * this.j) * this.k) * i4) / 8000;
                }
                this.o.set(2);
            }
        }
    }

    private void g() {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "releaseAudioProcessModule() called");
        }
        AudioProcessModule.a();
    }

    private void h() {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "releaseSystemAudioRecord() called");
        }
        AudioRecord audioRecord = this.t;
        if (audioRecord != null) {
            audioRecord.release();
            this.t = null;
        }
    }

    private boolean i() {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "initSystemAudioRecord() called");
        }
        int i = this.l;
        this.q = (i * 30) / 1000;
        try {
            AudioRecord audioRecord = new AudioRecord(this.g, this.l, this.h, this.i, AudioRecord.getMinBufferSize(i, this.h, this.i) * 3);
            this.t = audioRecord;
            if (audioRecord.getState() != 1) {
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.e("AudioRecord", "init system audio record state error");
                }
                return false;
            }
            this.r = new byte[((this.q * this.j) / 8) * this.k];
            this.s = new byte[8820];
            return true;
        } catch (IllegalArgumentException e) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.e("AudioRecord", "init system audio record error:" + e);
            }
            return false;
        }
    }

    public synchronized void b() throws IllegalStateException {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "stopRecording() called");
        }
        this.p.set(true);
        if (this.o.get() == 5) {
            throw new IllegalStateException("stopRecording() called on illegal state");
        }
        int andSet = this.o.getAndSet(5);
        if (andSet != 2 && andSet != 3) {
            this.o.set(1);
        }
        j();
        k();
    }

    private void j() {
        AudioRecord audioRecord = this.t;
        if (audioRecord != null) {
            try {
                audioRecord.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void k() {
        h();
        g();
        this.o.set(1);
    }

    public synchronized boolean c() throws IllegalStateException, IOException {
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "startRecording() called");
        }
        if (-1 == this.a.checkCallingOrSelfPermission("android.permission.RECORD_AUDIO")) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.d("AudioRecord", "startRecording() false-> No Permission");
            }
            return false;
        }
        if (this.o.get() != 1) {
            throw new IllegalStateException("startRecording() called on error state.");
        }
        f();
        if (this.o.get() != 2) {
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        }
        this.t.startRecording();
        if (this.t.getRecordingState() != 3) {
            k();
            throw new IOException("startRecording() called failed");
        }
        if (com.netease.nimlib.log.b.a.a()) {
            Log.d("AudioRecord", "startRecording() Ok");
        }
        Thread thread = new Thread(new Runnable() { // from class: com.netease.share.media.a.1
            private BufferedOutputStream c;
            private boolean b = false;
            private int d = -1;
            private int e = -1;

            @Override // java.lang.Runnable
            public void run() {
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.d("AudioRecord", "audio record read thread start");
                }
                if (!this.b) {
                    try {
                        Process.setThreadPriority(-19);
                        this.c = new BufferedOutputStream(new FileOutputStream(a.this.c), 4096);
                        if (a.this.d == 2) {
                            this.c.write("#!AMR\n".getBytes());
                        }
                        this.b = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.d = 2;
                    }
                }
                while (true) {
                    if (a.this.p.get() || a.this.t == null || !this.b) {
                        break;
                    }
                    int read = a.this.t.read(a.this.r, 0, a.this.r.length);
                    if (read <= 0) {
                        if (read == -3) {
                            this.d = 2;
                            break;
                        }
                    } else {
                        a aVar = a.this;
                        aVar.a(aVar.r, read);
                        try {
                            a.this.a(this.c, a.this.r, read);
                            a.this.f30u.addAndGet(read);
                        } catch (Throwable th) {
                            th.printStackTrace();
                            this.d = 2;
                            com.netease.nimlib.log.b.e("AudioRecord", "processAudio Throwable:" + th, th);
                        }
                    }
                    if (a.this.f30u.get() >= a.this.f) {
                        this.d = 1;
                        this.e = a.this.e;
                        break;
                    }
                }
                BufferedOutputStream bufferedOutputStream = this.c;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.flush();
                        this.c.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (!a.this.p.get()) {
                    a.this.x.post(new Runnable() { // from class: com.netease.share.media.a.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                a.this.b();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    });
                    if (this.d != -1 && a.this.y != null) {
                        a.this.x.post(new Runnable() { // from class: com.netease.share.media.a.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (a.this.y != null) {
                                    a.this.y.onInfo(a.this.w, AnonymousClass1.this.d, AnonymousClass1.this.e);
                                }
                            }
                        });
                    }
                }
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.d("AudioRecord", "audio record read thread stop");
                }
            }
        });
        this.v = thread;
        thread.start();
        this.o.set(3);
        return true;
    }

    public int d() {
        AtomicLong atomicLong = this.f30u;
        if (atomicLong == null) {
            return 0;
        }
        return (int) (((atomicLong.get() * 8) * 1000) / ((this.l * this.j) * this.k));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(OutputStream outputStream, byte[] bArr, int i) throws IOException {
        int a = AudioProcessModule.a(bArr, i, this.s);
        if (a > 0) {
            outputStream.write(this.s, 0, a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr, int i) {
        int i2 = 0;
        if (this.j != 16) {
            while (i2 < i) {
                if (bArr[i2] > this.n) {
                    this.n = bArr[i2];
                }
                i2++;
            }
            return;
        }
        while (i2 < i / 2) {
            int i3 = i2 * 2;
            short s = (short) ((bArr[i3 + 1] << 8) | bArr[i3]);
            if (s > this.n) {
                this.n = s;
            }
            i2++;
        }
    }
}
