package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: GetRoomHistoryRequest.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.d.a {
    private long a;
    private int b;
    private boolean c;
    private int[] d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 9;
    }

    public j(long j, int i) {
        this(j, i, false);
    }

    public j(long j, int i, boolean z) {
        this(j, i, z, null);
    }

    public j(long j, int i, boolean z, int[] iArr) {
        this.a = j;
        this.b = i;
        this.c = z;
        this.d = iArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        int[] iArr = this.d;
        if (iArr != null && iArr.length > 0) {
            bVar.b(iArr.length);
            int length = this.d.length;
            for (int i = 0; i < length; i++) {
                bVar.a(r1[i]);
            }
        }
        return bVar;
    }
}
