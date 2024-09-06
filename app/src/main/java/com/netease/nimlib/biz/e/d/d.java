package com.netease.nimlib.biz.e.d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: GetMixStoreAuthorizationResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"29"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    private byte[] c;
    private List<com.netease.nimlib.push.packet.b.c> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        byte[] array = fVar.b().array();
        this.c = Arrays.copyOf(array, array.length);
        int g = fVar.g();
        this.d = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.d.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        return null;
    }

    public byte[] a() {
        return this.c;
    }

    public List<com.netease.nimlib.push.packet.b.c> b() {
        return this.d;
    }

    /* compiled from: GetMixStoreAuthorizationResponse.java */
    /* loaded from: classes.dex */
    public enum a {
        provider(0),
        accessKeyId(1),
        secretAccessKey(2),
        sessionToken(3),
        Token(4),
        expireAt(5),
        bucket(6),
        objectName(7),
        fileExpireSec(8),
        tag(9),
        shortUrl(10),
        region(11);

        private final int m;

        public int a() {
            return this.m;
        }

        a(int i) {
            this.m = i;
        }
    }
}
