package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.CollectInfo;
import com.netease.nimlib.sdk.msg.model.CollectInfoPage;
import java.util.ArrayList;

/* compiled from: CollectInfoPageImpl.java */
/* loaded from: classes.dex */
public class b implements CollectInfoPage {
    private final long a;
    private final ArrayList<CollectInfo> b;

    public b(long j, ArrayList<a> arrayList) {
        this.a = j;
        this.b = new ArrayList<>(arrayList);
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfoPage
    public long getTotal() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.CollectInfoPage
    public ArrayList<CollectInfo> getCollectList() {
        return this.b;
    }
}
