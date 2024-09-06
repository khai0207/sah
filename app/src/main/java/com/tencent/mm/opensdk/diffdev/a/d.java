package com.tencent.mm.opensdk.diffdev.a;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* loaded from: classes.dex */
public enum d {
    UUID_EXPIRED(402),
    UUID_CANCELED(Constants.BUCKET_ACCESS_FORBIDDEN_STATUS_CODE),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(500);

    private int a;

    d(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "UUIDStatusCode:" + this.a;
    }
}
