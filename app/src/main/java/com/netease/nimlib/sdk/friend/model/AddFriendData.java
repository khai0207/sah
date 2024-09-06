package com.netease.nimlib.sdk.friend.model;

import com.netease.nimlib.sdk.friend.constant.VerifyType;
import java.io.Serializable;

/* loaded from: classes.dex */
public class AddFriendData implements Serializable {
    private String account;
    private String msg;
    private VerifyType verifyType;

    public AddFriendData(String str, VerifyType verifyType) {
        this.account = str;
        this.verifyType = verifyType;
    }

    public AddFriendData(String str, VerifyType verifyType, String str2) {
        this.account = str;
        this.verifyType = verifyType;
        this.msg = str2;
    }

    public String getAccount() {
        return this.account;
    }

    public VerifyType getVerifyType() {
        return this.verifyType;
    }

    public String getMsg() {
        return this.msg;
    }
}
