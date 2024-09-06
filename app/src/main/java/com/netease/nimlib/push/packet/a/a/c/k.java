package com.netease.nimlib.push.packet.a.a.c;

import java.security.SecureRandom;

/* compiled from: ParametersWithRandom.java */
/* loaded from: classes.dex */
public class k implements com.netease.nimlib.push.packet.a.a.a {
    private SecureRandom a;
    private com.netease.nimlib.push.packet.a.a.a b;

    public k(com.netease.nimlib.push.packet.a.a.a aVar, SecureRandom secureRandom) {
        this.a = secureRandom;
        this.b = aVar;
    }

    public k(com.netease.nimlib.push.packet.a.a.a aVar) {
        this(aVar, com.netease.nimlib.push.packet.a.a.c.a());
    }

    public SecureRandom a() {
        return this.a;
    }

    public com.netease.nimlib.push.packet.a.a.a b() {
        return this.b;
    }
}
