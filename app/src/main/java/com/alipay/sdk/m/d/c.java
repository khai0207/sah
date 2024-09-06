package com.alipay.sdk.m.d;

/* loaded from: classes.dex */
public final class c extends b {
    public final String e;

    public c(String str) {
        this.e = str;
    }

    @Override // com.alipay.sdk.m.d.b
    public void a() throws Exception {
        this.a = (byte) 1;
        byte[] bytes = this.e.getBytes("UTF-8");
        this.c = bytes;
        this.b = (byte) bytes.length;
    }
}
