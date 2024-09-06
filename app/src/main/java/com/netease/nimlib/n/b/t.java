package com.netease.nimlib.n.b;

/* compiled from: MsgSendEventSessionTypeEnum.java */
/* loaded from: classes.dex */
public enum t {
    P2P(0),
    Team(1),
    ChatRoom(4),
    SUPER_TEAM(5);

    private int e;

    t(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }
}
