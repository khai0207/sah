package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.RevokeType;
import java.io.Serializable;

/* loaded from: classes.dex */
public class RevokeMsgNotification implements Serializable {
    private String attach;
    private String callbackExt;
    private String customInfo;
    private IMMessage message;
    private int notificationType;
    private String revokeAccount;
    private RevokeType revokeType;

    public RevokeMsgNotification(IMMessage iMMessage, String str, String str2, String str3, int i, RevokeType revokeType, String str4) {
        this.message = iMMessage;
        this.attach = str;
        this.revokeAccount = str2;
        this.customInfo = str3;
        this.notificationType = i;
        this.revokeType = revokeType;
        this.callbackExt = str4;
    }

    public IMMessage getMessage() {
        return this.message;
    }

    public String getAttach() {
        return this.attach;
    }

    public String getRevokeAccount() {
        return this.revokeAccount;
    }

    public String getCustomInfo() {
        return this.customInfo;
    }

    public int getNotificationType() {
        return this.notificationType;
    }

    public RevokeType getRevokeType() {
        return this.revokeType;
    }

    public String getCallbackExt() {
        return this.callbackExt;
    }
}
