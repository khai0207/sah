package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface CollectInfoPage extends Serializable {
    ArrayList<CollectInfo> getCollectList();

    long getTotal();
}
