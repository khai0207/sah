package com.android.pc.ioc.download;

import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.annotation.Foreign;
import com.android.pc.ioc.db.sqlite.WhereBuilder;

/* loaded from: classes.dex */
public class ThreadEntity {
    protected long end;

    @Foreign(column = "ThreadId", foreign = "id")
    public FileEntity fileEntity;
    private int id;
    protected long load;
    protected long start;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public long getLoad() {
        return this.load;
    }

    public void setLoad(long j) {
        this.load = j;
    }

    public FileEntity getFileEntity() {
        return this.fileEntity;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public static void delete(int i) {
        Ioc.getIoc().getDb().delete(ThreadEntity.class, WhereBuilder.b("ThreadId", "=", Integer.valueOf(i)));
    }

    public String toString() {
        return "ThreadEntity [id=" + this.id + ", start=" + this.start + ", end=" + this.end + ", load=" + this.load + "]";
    }
}
