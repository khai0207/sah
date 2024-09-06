package com.netease.nimlib.sdk.migration.model;

import java.util.Map;

/* loaded from: classes.dex */
public interface IHistoryRecord {
    Map<String, Object> getAttach();

    int getClientType();

    String getSecretKey();

    String getTag();

    String getUrl();
}
