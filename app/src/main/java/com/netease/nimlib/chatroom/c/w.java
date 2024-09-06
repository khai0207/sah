package com.netease.nimlib.chatroom.c;

import android.text.TextUtils;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMemberUpdate;
import u.aly.df;

/* compiled from: UpdateMyRoomRoleRequest.java */
/* loaded from: classes.dex */
public class w extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private boolean b;
    private String c;
    private boolean d;
    private com.netease.nimlib.push.packet.b.c e;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.m;
    }

    public w(ChatRoomMemberUpdate chatRoomMemberUpdate, boolean z, String str) {
        this.a = a(chatRoomMemberUpdate);
        this.b = z;
        this.c = str;
        this.d = chatRoomMemberUpdate.isNeedSave();
    }

    public w(ChatRoomMemberUpdate chatRoomMemberUpdate, boolean z, String str, AntiSpamConfig antiSpamConfig) {
        this.a = a(chatRoomMemberUpdate);
        this.b = z;
        this.c = str;
        this.d = chatRoomMemberUpdate.isNeedSave();
        if (antiSpamConfig != null) {
            this.e = new com.netease.nimlib.push.packet.b.c();
            String antiSpamBusinessId = antiSpamConfig.getAntiSpamBusinessId();
            if (TextUtils.isEmpty(antiSpamBusinessId)) {
                return;
            }
            this.e.a(1, antiSpamBusinessId);
        }
    }

    private com.netease.nimlib.push.packet.b.c a(ChatRoomMemberUpdate chatRoomMemberUpdate) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(5, chatRoomMemberUpdate.getNick());
        cVar.a(6, chatRoomMemberUpdate.getAvatar());
        cVar.a(7, com.netease.nimlib.session.j.a(chatRoomMemberUpdate.getExtension()));
        return cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        bVar.a(this.d);
        com.netease.nimlib.push.packet.b.c cVar = this.e;
        if (cVar != null) {
            bVar.a(cVar);
        }
        com.netease.nimlib.log.b.J("************ update my room role info request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "property", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "needNotify = " + this.b);
        com.netease.nimlib.log.b.a(b(), c(), "notifyExt = " + this.c);
        com.netease.nimlib.log.b.a(b(), c(), "needSave = " + this.d);
        com.netease.nimlib.log.b.a(b(), c(), "antispamTag", this.e);
        com.netease.nimlib.log.b.J("************ update my room role info request end ****************");
        return bVar;
    }
}
