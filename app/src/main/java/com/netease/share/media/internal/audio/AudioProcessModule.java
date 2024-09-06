package com.netease.share.media.internal.audio;

/* loaded from: classes.dex */
public class AudioProcessModule {
    static native boolean Create(int i, byte b, boolean z);

    static native void Free();

    static native int Process(byte[] bArr, int i, byte[] bArr2);

    public static synchronized boolean a(int i, byte b, boolean z) {
        boolean Create;
        synchronized (AudioProcessModule.class) {
            Create = Create(i, b, z);
        }
        return Create;
    }

    public static synchronized void a() {
        synchronized (AudioProcessModule.class) {
            Free();
        }
    }

    public static synchronized int a(byte[] bArr, int i, byte[] bArr2) {
        int Process;
        synchronized (AudioProcessModule.class) {
            Process = Process(bArr, i, bArr2);
        }
        return Process;
    }
}
