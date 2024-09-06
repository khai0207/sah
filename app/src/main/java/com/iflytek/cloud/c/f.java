package com.iflytek.cloud.c;

import android.media.AudioRecord;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechError;

/* loaded from: classes.dex */
public class f extends Thread {
    private final short a = 16;
    private byte[] b = null;
    private AudioRecord c = null;
    private a d = null;
    private a e = null;
    private volatile boolean f = false;
    private int g;
    private int h;
    private int i;
    private int j;

    /* loaded from: classes.dex */
    public interface a {
        void a(byte[] bArr, int i, int i2);

        void b(SpeechError speechError);

        void c(boolean z);

        void k();
    }

    public f(int i, int i2, int i3) {
        this.g = 16000;
        this.h = 40;
        this.i = 40;
        this.j = i3;
        this.g = i;
        this.h = i2;
        if (i2 < 40 || i2 > 100) {
            this.h = 40;
        }
        this.i = 10;
    }

    private int a() throws SpeechError {
        a aVar;
        AudioRecord audioRecord = this.c;
        if (audioRecord == null || this.d == null) {
            return 0;
        }
        byte[] bArr = this.b;
        int read = audioRecord.read(bArr, 0, bArr.length);
        if (read > 0 && (aVar = this.d) != null) {
            aVar.a(this.b, 0, read);
        }
        return read;
    }

    private void b() {
        synchronized (this) {
            try {
                if (this.c != null) {
                    com.iflytek.cloud.a.g.a.a.a("release record begin");
                    this.c.release();
                    this.c = null;
                    if (this.e != null) {
                        this.e.k();
                        this.e = null;
                    }
                    com.iflytek.cloud.a.g.a.a.a("release record over");
                }
            } catch (Exception e) {
                com.iflytek.cloud.a.g.a.a.b(e.toString());
            }
        }
    }

    public void a(a aVar) throws SpeechError {
        this.d = aVar;
        setPriority(10);
        start();
    }

    public void a(short s, int i, int i2) throws SpeechError {
        if (this.c != null) {
            b();
        }
        int i3 = (i2 * i) / 1000;
        int i4 = (((i3 * 4) * 16) * s) / 8;
        int i5 = s == 1 ? 2 : 3;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i5, 2);
        if (i4 < minBufferSize) {
            i4 = minBufferSize;
        }
        this.c = new AudioRecord(this.j, i, i5, 2, i4);
        this.b = new byte[((s * i3) * 16) / 8];
        com.iflytek.cloud.a.g.a.a.a("\nSampleRate:" + i + "\nChannel:" + i5 + "\nFormat:2\nFramePeriod:" + i3 + "\nBufferSize:" + i4 + "\nMinBufferSize:" + minBufferSize + "\nActualBufferSize:" + this.b.length + "\n");
        if (this.c.getState() == 1) {
            return;
        }
        com.iflytek.cloud.a.g.a.a.a("create AudioRecord error");
        throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
    }

    public void a(boolean z) {
        this.f = true;
        if (this.e == null) {
            this.e = this.d;
        }
        this.d = null;
        if (z) {
            synchronized (this) {
                try {
                    com.iflytek.cloud.a.g.a.a.a("stopRecord...release");
                    if (this.c != null) {
                        if (3 == this.c.getRecordingState() && 1 == this.c.getState()) {
                            com.iflytek.cloud.a.g.a.a.a("stopRecord releaseRecording ing...");
                            this.c.release();
                            com.iflytek.cloud.a.g.a.a.a("stopRecord releaseRecording end...");
                            this.c = null;
                        }
                        if (this.e != null) {
                            this.e.k();
                            this.e = null;
                        }
                    }
                } catch (Exception e) {
                    com.iflytek.cloud.a.g.a.a.b(e.toString());
                }
            }
        }
        com.iflytek.cloud.a.g.a.a.a("stop record");
    }

    protected void finalize() throws Throwable {
        b();
        super.finalize();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i = 0;
        int i2 = 0;
        while (!this.f) {
            try {
                try {
                    a((short) 1, this.g, this.h);
                    break;
                } catch (Exception unused) {
                    i2++;
                    if (i2 >= 10) {
                        throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                    }
                    sleep(40L);
                }
            } catch (Exception e) {
                e.printStackTrace();
                a aVar = this.d;
                if (aVar != null) {
                    aVar.b(new SpeechError(ErrorCode.ERROR_AUDIO_RECORD));
                }
            }
        }
        while (!this.f) {
            try {
                this.c.startRecording();
                if (this.c.getRecordingState() == 3) {
                    break;
                } else {
                    throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                }
            } catch (Exception unused2) {
                i++;
                if (i >= 10) {
                    throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                }
                sleep(40L);
            }
        }
        if (this.d != null) {
            this.d.c(true);
        }
        while (!this.f) {
            a();
            sleep(this.i);
        }
        b();
    }
}
