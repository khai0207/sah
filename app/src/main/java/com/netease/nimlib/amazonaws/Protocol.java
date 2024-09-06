package com.netease.nimlib.amazonaws;

import com.alipay.sdk.m.h.a;

/* loaded from: classes.dex */
public enum Protocol {
    HTTP(a.q),
    HTTPS("https");

    private final String protocol;

    Protocol(String str) {
        this.protocol = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
