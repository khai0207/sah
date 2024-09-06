package com.netease.nimlib.n.b;

/* compiled from: EMExceptionService.java */
/* loaded from: classes.dex */
public enum g {
    UNKNOWN(0),
    LBS(1),
    FCS(2),
    NOS(3),
    S3(4),
    HTTP_DNS(5),
    LINK(6),
    AB_TEST(7),
    CDN(8),
    FCS_NEXT(9);

    private final int k;

    g(int i) {
        this.k = i;
    }

    public int a() {
        return this.k;
    }
}
