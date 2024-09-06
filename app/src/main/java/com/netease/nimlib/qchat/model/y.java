package com.netease.nimlib.qchat.model;

import com.netease.nimlib.sdk.qchat.enums.QChatPushMsgType;
import com.netease.nimlib.sdk.qchat.model.QChatPushConfig;

/* compiled from: QChatPushConfigImpl.java */
/* loaded from: classes.dex */
public class y implements QChatPushConfig {
    private boolean a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private QChatPushMsgType g;
    private boolean h;

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public boolean isPushShowNoDetail() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public void setPushShowNoDetail(boolean z) {
        this.a = z;
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public boolean isNoDisturbOpen() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public void setNoDisturbOpen(boolean z) {
        this.b = z;
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public String getStartTimeString() {
        return String.format("%s:%s", String.format("%02d", Integer.valueOf(this.c)), String.format("%02d", Integer.valueOf(this.d)));
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public String getStopTimeString() {
        return String.format("%s:%s", String.format("%02d", Integer.valueOf(this.e)), String.format("%02d", Integer.valueOf(this.f)));
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public void setStartTime(String str) {
        int[] a = a(str);
        if (a == null || a.length != 2) {
            return;
        }
        this.c = a[0];
        this.d = a[1];
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public void setStopTime(String str) {
        int[] a = a(str);
        if (a == null || a.length != 2) {
            return;
        }
        this.e = a[0];
        this.f = a[1];
    }

    private int[] a(String str) {
        try {
            String[] split = str.split(":");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            if (parseInt < 0 || parseInt > 24 || parseInt2 < 0 || parseInt2 >= 60) {
                parseInt2 = 0;
                parseInt = 0;
            }
            return new int[]{parseInt, parseInt2};
        } catch (Exception unused) {
            return null;
        }
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int b() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int c() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int d() {
        return this.f;
    }

    public void d(int i) {
        this.f = i;
    }

    public boolean e() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public QChatPushMsgType getPushMsgType() {
        return this.g;
    }

    @Override // com.netease.nimlib.sdk.qchat.model.QChatPushConfig
    public void setPushMsgType(QChatPushMsgType qChatPushMsgType) {
        this.g = qChatPushMsgType;
    }

    public boolean f() {
        return !this.b && this.c == 0 && this.d == 0 && this.e == 0 && this.f == 0 && this.g == null;
    }

    public String toString() {
        return "QChatPushConfigImpl{isPushShowNoDetail=" + this.a + ", isNoDisturbOpen=" + this.b + ", startHour=" + this.c + ", startMin=" + this.d + ", stopHour=" + this.e + ", stopMin=" + this.f + ", pushMsgType=" + this.g + ", pushDndValid=" + this.h + '}';
    }
}
