package com.netease.nimlib.ipc.a;

import com.netease.nimlib.sdk.StatusCodeInfo;
import com.netease.nimlib.sdk.auth.LoginInfo;
import java.io.Serializable;

/* compiled from: StatusChangeData.java */
/* loaded from: classes.dex */
public class e implements Serializable {
    public final StatusCodeInfo a;
    public final LoginInfo b;
    public final int c;
    public final int d;

    public e(StatusCodeInfo statusCodeInfo, int i, LoginInfo loginInfo, int i2) {
        this.a = statusCodeInfo;
        this.c = i;
        this.b = loginInfo;
        this.d = i2;
    }
}
