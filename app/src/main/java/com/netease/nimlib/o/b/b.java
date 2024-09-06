package com.netease.nimlib.o.b;

/* compiled from: NimStorageType.java */
/* loaded from: classes.dex */
public enum b {
    TYPE_LOG(a.LOG_DIRECTORY_NAME),
    TYPE_FILE(a.FILE_DIRECTORY_NAME),
    TYPE_TEMP(a.TEMP_DIRECTORY_NAME),
    TYPE_AUDIO(a.AUDIO_DIRECTORY_NAME),
    TYPE_IMAGE(a.IMAGE_DIRECTORY_NAME),
    TYPE_VIDEO(a.VIDEO_DIRECTORY_NAME),
    TYPE_THUMB_IMAGE(a.THUMB_DIRECTORY_NAME);

    private a h;
    private long i;

    public String a() {
        return this.h.a();
    }

    public long b() {
        return this.i;
    }

    b(a aVar) {
        this(aVar, 41943040L);
    }

    b(a aVar, long j2) {
        this.h = aVar;
        this.i = j2;
    }

    /* compiled from: NimStorageType.java */
    /* loaded from: classes.dex */
    enum a {
        AUDIO_DIRECTORY_NAME("audio/"),
        TEMP_DIRECTORY_NAME("temp/"),
        FILE_DIRECTORY_NAME("file/"),
        LOG_DIRECTORY_NAME("log/"),
        IMAGE_DIRECTORY_NAME("image/"),
        THUMB_DIRECTORY_NAME("thumb/"),
        VIDEO_DIRECTORY_NAME("video/");

        private final String h;

        public final String a() {
            return this.h;
        }

        a(String str) {
            this.h = str;
        }
    }
}
