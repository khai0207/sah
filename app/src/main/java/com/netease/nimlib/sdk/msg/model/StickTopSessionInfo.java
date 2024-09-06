package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public interface StickTopSessionInfo extends Serializable {
    long getCreateTime();

    String getExt();

    String getSessionId();

    SessionTypeEnum getSessionType();

    long getUpdateTime();
}
