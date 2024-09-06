package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.session.n;
import java.io.Serializable;

/* loaded from: classes.dex */
public class MsgPinDbOption implements Serializable {
    private final MsgPinOption pinOption;
    private final String sessionId;
    private final String uuid;

    public MsgPinDbOption(String str, String str2, String str3, String str4, long j, long j2) {
        this(str, str2, new n(str3, str4, j, j2));
    }

    public MsgPinDbOption(String str, String str2, MsgPinOption msgPinOption) {
        this.uuid = str;
        this.sessionId = str2;
        this.pinOption = msgPinOption;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public MsgPinOption getPinOption() {
        return this.pinOption;
    }
}
