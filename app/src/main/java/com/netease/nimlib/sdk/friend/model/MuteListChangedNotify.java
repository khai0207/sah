package com.netease.nimlib.sdk.friend.model;

/* loaded from: classes.dex */
public class MuteListChangedNotify {
    private String account;
    private boolean mute;

    public MuteListChangedNotify(String str, boolean z) {
        this.account = str;
        this.mute = z;
    }

    public String getAccount() {
        return this.account;
    }

    public boolean isMute() {
        return this.mute;
    }
}
