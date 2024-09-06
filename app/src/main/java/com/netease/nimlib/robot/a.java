package com.netease.nimlib.robot;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.robot.model.NimRobotInfo;

/* compiled from: NimRobotInfoImpl.java */
/* loaded from: classes.dex */
public class a implements NimRobotInfo {
    private String a;
    private String b;
    private String c;
    private String d;
    private long e;
    private long f;
    private String g;

    public static a a(c cVar) {
        a aVar = new a();
        aVar.a(cVar.c(4));
        aVar.c(cVar.c(5));
        aVar.d(cVar.c(6));
        aVar.e(cVar.c(7));
        aVar.a(cVar.e(10));
        aVar.b(cVar.e(11));
        aVar.b(cVar.c(13));
        return aVar;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.UserInfo
    public String getAccount() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.sdk.robot.model.NimRobotInfo
    public String getBotId() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.UserInfo
    public String getName() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.UserInfo
    public String getAvatar() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    @Override // com.netease.nimlib.sdk.robot.model.NimRobotInfo
    public String getIntroduce() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public long a() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public long b() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }
}
