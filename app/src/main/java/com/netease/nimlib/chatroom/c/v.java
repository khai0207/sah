package com.netease.nimlib.chatroom.c;

import com.netease.nimlib.sdk.chatroom.model.ChatRoomSpatialLocation;
import u.aly.df;

/* compiled from: UpdateLocationRequest.java */
/* loaded from: classes.dex */
public class v extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 33;
    }

    public v(ChatRoomSpatialLocation chatRoomSpatialLocation) {
        this.a = a(chatRoomSpatialLocation);
    }

    private com.netease.nimlib.push.packet.b.c a(ChatRoomSpatialLocation chatRoomSpatialLocation) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        if (com.netease.nimlib.c.i().enableChatRoomLocation) {
            Double x = chatRoomSpatialLocation.getX();
            if (x != null) {
                cVar.a(1, x.doubleValue());
            }
            Double y = chatRoomSpatialLocation.getY();
            if (y != null) {
                cVar.a(2, y.doubleValue());
            }
            Double z = chatRoomSpatialLocation.getZ();
            if (z != null) {
                cVar.a(3, z.doubleValue());
            }
            Double distance = chatRoomSpatialLocation.getDistance();
            if (distance != null) {
                cVar.a(4, distance.doubleValue());
            }
        }
        return cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ update chatroom location request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "chatroom update location", this.a);
        com.netease.nimlib.log.b.J("************ update chatroom location request end ****************");
        return bVar;
    }
}
