package com.android.pc.ioc.download;

import java.io.File;

/* loaded from: classes.dex */
public class FileResultEntity {
    public static final int status_fail = 2;
    public static final int status_loading = 0;
    public static final int status_start = 1;
    public static final int status_sucess = 3;
    private File file;
    private long length;
    private long loadedLength;
    private int progress;
    protected boolean range;
    private int status;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isRange() {
        return this.range;
    }

    public void setRange(boolean z) {
        this.range = z;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long j) {
        this.length = j;
    }

    public long getLoadedLength() {
        return this.loadedLength;
    }

    public void setLoadedLength(long j) {
        this.loadedLength = j;
    }

    public String toString() {
        return "FileResultEntity [url=" + this.url + ", progress=" + this.progress + ", status=" + this.status + ", length=" + this.length + ", loadedLength=" + this.loadedLength + ", file=" + this.file + ", range=" + this.range + "]";
    }
}
