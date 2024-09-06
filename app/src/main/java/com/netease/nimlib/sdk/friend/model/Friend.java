package com.netease.nimlib.sdk.friend.model;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public interface Friend extends Serializable {
    String getAccount();

    String getAlias();

    Map<String, Object> getExtension();

    String getServerExtension();
}
