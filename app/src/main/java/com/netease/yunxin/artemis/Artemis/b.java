package com.netease.yunxin.artemis.Artemis;

import android.text.TextUtils;
import java.util.Date;
import org.json.JSONObject;

/* compiled from: YXArtemisItem.java */
/* loaded from: classes.dex */
public abstract class b implements Runnable {
    private Date date;
    private long delay;
    long exeTime;
    protected YXArtemisRunTaskCallback mCallback;
    protected String mClient_ipv4;
    protected String mClient_ipv6;
    private String mId;
    protected String mIpVersion;
    public String mReportAddr;
    protected String mRequestId;
    private int mTaskType;

    protected abstract void aggregateResult();

    protected abstract void finishTask();

    protected abstract void taskRun();

    public b() {
    }

    public b(Date date) {
        this();
        setDate(date);
    }

    public b(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, String str7) {
        this(str, str2, str3, i, jSONObject, str4, str5, i2, str6, str7, null);
    }

    public b(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, String str7, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        this();
        this.mClient_ipv4 = str;
        this.mClient_ipv6 = str2;
        this.mIpVersion = "ipv4";
        this.mId = str3;
        this.mTaskType = i;
        this.mReportAddr = str6;
        if (!TextUtils.isEmpty(str2)) {
            this.mIpVersion = "ipv6";
        }
        if (!TextUtils.isEmpty(str)) {
            this.mIpVersion = "ipv4";
        }
        if (!TextUtils.isEmpty(str7)) {
            this.mRequestId = str7;
        }
        this.mCallback = yXArtemisRunTaskCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (c.a().c() >= c.a().e) {
            taskRun();
            c.a().a.remove(this);
            aggregateResult();
            finishTask();
            return;
        }
        if (c.a().d != null) {
            a.a();
        }
        c.a().a(c.a().f);
    }

    public long getDelay() {
        return this.delay;
    }

    public void setDelay(long j) {
        if (j < 0) {
            j = 0;
        }
        this.delay = j;
        updateExeTime(j);
    }

    public void setDate(Date date) {
        this.date = date;
        computeDelay();
    }

    void computeDelay() {
        setDelay(this.date.getTime() - System.currentTimeMillis());
    }

    void updateExeTime(long j) {
        this.exeTime = j + System.currentTimeMillis();
    }

    public String getId() {
        return this.mId;
    }

    public int getTaskType() {
        return this.mTaskType;
    }

    public String getClient_ipv4() {
        return this.mClient_ipv4;
    }

    public String getClient_ipv6() {
        return this.mClient_ipv6;
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
