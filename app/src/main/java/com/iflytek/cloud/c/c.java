package com.iflytek.cloud.c;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import com.iflytek.cloud.SpeechError;

/* loaded from: classes.dex */
public class c {
    AudioManager.OnAudioFocusChangeListener a;
    private AudioTrack b;
    private com.iflytek.cloud.c.b c;
    private Context d;
    private b e;
    private a f;
    private volatile int g;
    private int h;
    private boolean i;
    private int j;
    private boolean k;
    private boolean l;
    private Object m;
    private int n;
    private Handler o;

    /* loaded from: classes.dex */
    public interface a {
        void a();

        void a(int i, int i2, int i3);

        void a(SpeechError speechError);

        void b();

        void c();
    }

    /* loaded from: classes.dex */
    private class b extends Thread {
        private b() {
        }

        /* synthetic */ b(c cVar, d dVar) {
            this();
        }

        /* JADX WARN: Code restructure failed: missing block: B:56:0x021f, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x021c, code lost:
        
            if (r8.a.i == false) goto L52;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 630
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.c.c.b.run():void");
        }
    }

    public c(Context context) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 3;
        this.i = true;
        this.k = false;
        this.l = false;
        this.m = new Object();
        this.a = new d(this);
        this.n = 0;
        this.o = new e(this, Looper.getMainLooper());
        this.d = context;
    }

    public c(Context context, int i, boolean z) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 3;
        this.i = true;
        this.k = false;
        this.l = false;
        this.m = new Object();
        this.a = new d(this);
        this.n = 0;
        this.o = new e(this, Looper.getMainLooper());
        this.d = context;
        this.h = i;
        this.k = z;
    }

    private void f() throws Exception {
        com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "createAudio start");
        int a2 = this.c.a();
        this.j = AudioTrack.getMinBufferSize(a2, 2, 2);
        if (this.b != null) {
            b();
        }
        com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "createAudio || mStreamType = " + this.h);
        this.b = new AudioTrack(this.h, a2, 2, 2, this.j * 2, 1);
        int i = this.j;
        if (i == -2 || i == -1) {
            throw new Exception();
        }
        com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "createAudio end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() throws Exception {
        AudioTrack audioTrack = this.b;
        if (audioTrack == null || audioTrack.getStreamType() != this.h) {
            com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "prepAudioPlayer || audiotrack stream type is change.");
            f();
        }
    }

    public int a() {
        return this.g;
    }

    public boolean a(com.iflytek.cloud.c.b bVar, a aVar) {
        com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "play mPlaytate= " + this.g + ",mAudioFocus= " + this.i);
        if (this.g != 4 && this.g != 0 && this.g != 3 && this.e != null) {
            return false;
        }
        this.c = bVar;
        this.f = aVar;
        b bVar2 = new b(this, null);
        this.e = bVar2;
        bVar2.start();
        return true;
    }

    public void b() {
        synchronized (this.m) {
            if (this.b != null) {
                if (this.b.getPlayState() == 3) {
                    this.b.stop();
                }
                this.b.release();
                this.b = null;
            }
            com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "mAudioTrack released");
        }
    }

    public boolean c() {
        if (this.g == 4 || this.g == 3) {
            return false;
        }
        this.g = 3;
        return true;
    }

    public boolean d() {
        if (this.g != 3) {
            return false;
        }
        this.g = 2;
        return true;
    }

    public void e() {
        this.g = 4;
    }
}
