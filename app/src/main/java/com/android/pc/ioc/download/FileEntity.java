package com.android.pc.ioc.download;

import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.annotation.Finder;
import com.android.pc.ioc.db.annotation.Transient;
import com.android.pc.ioc.db.sqlite.Selector;
import com.android.pc.ioc.db.sqlite.WhereBuilder;
import com.android.pc.ioc.update.NotificationHelper;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.util.List;

/* loaded from: classes.dex */
public class FileEntity {

    @Transient
    private boolean again;

    @Transient
    private NotificationHelper helper;
    protected int id;
    protected boolean isSucess;

    @Transient
    private boolean isUpdate;
    protected long length;

    @Transient
    private long loadedLength;

    @Transient
    private NotfiEntity notfi;
    protected String path;
    protected boolean range;

    @Transient
    private String real_url;
    protected int threads;

    @Finder(targetColumn = "ThreadId", valueColumn = "id")
    private List<ThreadEntity> threadsEntities;
    protected String url;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long j) {
        this.length = j;
    }

    public int getThreads() {
        return this.threads;
    }

    public void setThreads(int i) {
        this.threads = i;
    }

    public boolean isRange() {
        return this.range;
    }

    public void setRange(boolean z) {
        this.range = z;
    }

    public boolean isSucess() {
        return this.isSucess;
    }

    public void setSucess(boolean z) {
        this.isSucess = z;
    }

    public List<ThreadEntity> getThreadsEntities() {
        return this.threadsEntities;
    }

    public void setThreadsEntities(List<ThreadEntity> list) {
        this.threadsEntities = list;
    }

    public boolean isAgain() {
        return this.again;
    }

    public void setAgain(boolean z) {
        this.again = z;
    }

    public String toString() {
        return "FileEntity [id=" + this.id + ", url=" + this.url + ", path=" + this.path + ", length=" + this.length + ", threads=" + this.threads + ", range=" + this.range + ", isSucess=" + this.isSucess + ", again=" + this.again + ", isUpdate=" + this.isUpdate + ", notfi=" + this.notfi + ", loadedLength=" + this.loadedLength + ", real_url=" + this.real_url + ", threadsEntities=" + this.threadsEntities + ", helper=" + this.helper + "]";
    }

    public long getLoadedLength() {
        return this.loadedLength;
    }

    public void setLoadedLength(long j) {
        this.loadedLength = j;
    }

    public static FileEntity getEntityByUrl(String str) {
        Selector from = Selector.from(FileEntity.class);
        from.select(" * ").where(WhereBuilder.b(Constants.URL_ENCODING, "=", str));
        List findAll = Ioc.getIoc().getDb().findAll(from);
        if (findAll == null || findAll.size() == 0) {
            return null;
        }
        FileEntity fileEntity = (FileEntity) findAll.get(0);
        Selector from2 = Selector.from(ThreadEntity.class);
        from2.select(" * ").where(WhereBuilder.b("ThreadId", "=", Integer.valueOf(fileEntity.getId())));
        fileEntity.setThreadsEntities(Ioc.getIoc().getDb().findAll(from2));
        return fileEntity;
    }

    public static List<FileEntity> getAllFinishEntity() {
        Selector from = Selector.from(FileEntity.class);
        from.select(" * ").where(WhereBuilder.b("isSucess", "=", true));
        return Ioc.getIoc().getDb().findAll(from);
    }

    public static List<FileEntity> getAllFailureEntity() {
        Selector from = Selector.from(FileEntity.class);
        from.select(" * ").where(WhereBuilder.b("isSucess", "=", false));
        return Ioc.getIoc().getDb().findAll(from);
    }

    public static List<FileEntity> getAllEntity() {
        return Ioc.getIoc().getDb().findAll(Selector.from(FileEntity.class));
    }

    public void update() {
        Ioc.getIoc().getDb().update(this);
    }

    public String getReal_url() {
        return this.real_url;
    }

    public void setReal_url(String str) {
        this.real_url = str;
    }

    public boolean isUpdate() {
        return this.isUpdate;
    }

    public void setUpdate(boolean z) {
        this.isUpdate = z;
    }

    public NotfiEntity getNotfi() {
        return this.notfi;
    }

    public void setNotfi(NotfiEntity notfiEntity) {
        this.notfi = notfiEntity;
    }

    public NotificationHelper getHelper() {
        return this.helper;
    }

    public void setHelper(NotificationHelper notificationHelper) {
        this.helper = notificationHelper;
    }
}
