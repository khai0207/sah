package com.netease.nimlib.sdk.auth;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface OnlineClient extends Serializable {
    String getClientIp();

    int getClientType();

    int getCustomClientType();

    String getCustomTag();

    long getLoginTime();

    String getOs();
}
