package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface CollectInfo extends Serializable {
    long getCreateTime();

    String getData();

    String getExt();

    long getId();

    int getType();

    String getUniqueId();

    long getUpdateTime();
}
