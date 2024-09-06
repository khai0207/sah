package com.netease.nimlib.sdk.msg.model;

import android.util.Pair;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public interface RecentSession extends Serializable {
    public static final String KEY_EXT = "ext";

    String getExt();

    String getLastMsg();

    int getLastMsgType();

    RevokeMsgNotification getRevokeNotification();

    String getSessionId();

    long getUpdateTime();

    Pair<SessionTypeEnum, String> parseSessionId();

    RecentContact toRecentContact();
}
