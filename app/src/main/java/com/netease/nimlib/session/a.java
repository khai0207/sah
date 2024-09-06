package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.CollectInfo;

/* compiled from: CollectInfoImpl.java */
/* loaded from: classes.dex */
public class a implements CollectInfo {
    private long a;
    private int b;
    private String c;
    private String d;
    private String e;
    private long f;
    private long g;

    public a(com.netease.nimlib.push.packet.b.c cVar) {
        this(cVar.e(1), cVar.d(2), cVar.c(3), cVar.c(4), cVar.c(5), cVar.e(6), cVar.e(7));
    }

    public a(long j, int i, String str, String str2, String str3, long j2, long j3) {
        this.a = j;
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = j2;
        this.g = j3;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public long getId() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public int getType() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public String getData() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public String getExt() {
        return this.d;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public String getUniqueId() {
        return this.e;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public long getCreateTime() {
        return this.f;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfo
    public long getUpdateTime() {
        return this.g;
    }
}
