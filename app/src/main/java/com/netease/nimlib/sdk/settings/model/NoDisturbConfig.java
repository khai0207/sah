package com.netease.nimlib.sdk.settings.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface NoDisturbConfig extends Serializable {
    String getStartTimeString();

    String getStopTimeString();

    boolean isOpen();

    void setOpen(boolean z);

    void setStartTime(String str);

    void setStopTime(String str);
}
