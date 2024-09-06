package com.netease.nimlib.chatroom.c;

import android.text.TextUtils;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomUpdateInfo;
import u.aly.df;

/* compiled from: UpdateRoomInfoRequest.java */
/* loaded from: classes.dex */
public class y extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private boolean b;
    private String c;
    private com.netease.nimlib.push.packet.b.c d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.l;
    }

    public y(String str, ChatRoomUpdateInfo chatRoomUpdateInfo, boolean z, String str2) {
        this.a = a(str, chatRoomUpdateInfo);
        this.b = z;
        this.c = str2;
    }

    public y(String str, ChatRoomUpdateInfo chatRoomUpdateInfo, boolean z, String str2, AntiSpamConfig antiSpamConfig) {
        this.a = a(str, chatRoomUpdateInfo);
        this.b = z;
        this.c = str2;
        if (antiSpamConfig != null) {
            this.d = new com.netease.nimlib.push.packet.b.c();
            String antiSpamBusinessId = antiSpamConfig.getAntiSpamBusinessId();
            if (TextUtils.isEmpty(antiSpamBusinessId)) {
                return;
            }
            this.d.a(1, antiSpamBusinessId);
        }
    }

    private com.netease.nimlib.push.packet.b.c a(String str, ChatRoomUpdateInfo chatRoomUpdateInfo) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(3, chatRoomUpdateInfo.getName());
        cVar.a(4, chatRoomUpdateInfo.getAnnouncement());
        cVar.a(5, chatRoomUpdateInfo.getBroadcastUrl());
        cVar.a(16, chatRoomUpdateInfo.getQueueLevel());
        if (chatRoomUpdateInfo.getExtension() != null) {
            cVar.a(12, com.netease.nimlib.session.j.a(chatRoomUpdateInfo.getExtension()));
        }
        return cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        com.netease.nimlib.push.packet.b.c cVar = this.d;
        if (cVar != null) {
            bVar.a(cVar);
        }
        com.netease.nimlib.log.b.J("************ update chatroom info request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "property", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "needNotify = " + this.b);
        com.netease.nimlib.log.b.a(b(), c(), "notifyExt = " + this.c);
        com.netease.nimlib.log.b.a(b(), c(), "antispamTag", this.d);
        com.netease.nimlib.log.b.J("************ update chatroom info request end ****************");
        return bVar;
    }
}
