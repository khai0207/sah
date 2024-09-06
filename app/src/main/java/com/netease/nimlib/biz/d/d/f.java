package com.netease.nimlib.biz.d.d;

/* compiled from: GetMixStoreAuthorizationRequest2.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private final Integer a;
    private final int b;
    private final int c = 20;
    private final long d;
    private final String e;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 29;
    }

    public f(Integer num, int i, long j, String str) {
        this.a = num;
        this.b = i;
        this.d = j;
        this.e = str;
    }

    public int d() {
        return this.b;
    }

    public String e() {
        return this.e;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(a.provider.a(), this.b);
        cVar.a(a.tokenCount.a(), 20);
        cVar.a(a.tag.a(), this.e);
        cVar.a(a.fileExpireSec.a(), this.d);
        if (this.a != null) {
            cVar.a(a.policyVersion.a(), this.a.intValue());
        }
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(cVar);
        return bVar;
    }

    /* compiled from: GetMixStoreAuthorizationRequest2.java */
    /* loaded from: classes.dex */
    public enum a {
        provider(0),
        tokenCount(1),
        fileExpireSec(2),
        tag(3),
        returnBody(4),
        policyVersion(5);

        private final int g;

        public int a() {
            return this.g;
        }

        a(int i) {
            this.g = i;
        }
    }
}
