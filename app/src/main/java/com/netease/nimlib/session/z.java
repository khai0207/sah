package com.netease.nimlib.session;

import android.util.Pair;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;

/* compiled from: StickTopSessionInfoImpl.java */
/* loaded from: classes.dex */
public class z implements StickTopSessionInfo {
    private final String a;
    private final SessionTypeEnum b;
    private final String c;
    private final long d;
    private final long e;

    public z(com.netease.nimlib.push.packet.b.c cVar) {
        Pair<SessionTypeEnum, String> d = j.d(cVar.c(1));
        this.a = (String) d.second;
        this.b = (SessionTypeEnum) d.first;
        this.c = cVar.c(2);
        this.d = cVar.e(3);
        this.e = cVar.e(4);
    }

    public z(String str, SessionTypeEnum sessionTypeEnum, String str2, long j, long j2) {
        this.a = str;
        this.b = sessionTypeEnum;
        this.c = str2;
        this.d = j;
        this.e = j2;
    }

    @Override // com.netease.nimlib.sdk.msg.model.StickTopSessionInfo
    public String getSessionId() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.StickTopSessionInfo
    public SessionTypeEnum getSessionType() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.StickTopSessionInfo
    public String getExt() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.msg.model.StickTopSessionInfo
    public long getCreateTime() {
        return this.d;
    }

    @Override // com.netease.nimlib.sdk.msg.model.StickTopSessionInfo
    public long getUpdateTime() {
        return this.e;
    }
}
