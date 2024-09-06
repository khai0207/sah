package com.netease.nimlib.l;

import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import java.util.List;

/* compiled from: RemoveNotificationOptions.java */
/* loaded from: classes.dex */
public class i {
    private final int a;
    private List<SessionAckInfo> b;

    public i(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public List<SessionAckInfo> b() {
        return this.b;
    }

    public void a(List<SessionAckInfo> list) {
        this.b = list;
    }
}
