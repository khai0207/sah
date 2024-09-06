package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface MsgPinSyncResponseOptionWrapper extends Serializable {
    ArrayList<MsgPinSyncResponseOption> getMsgPinInfoList();

    long getTime();

    boolean isChanged();
}
