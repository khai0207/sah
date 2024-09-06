package com.netease.nimlib.j.a;

import com.netease.nimlib.sdk.migration.model.IHistoryRecord;
import com.netease.nimlib.session.j;
import java.io.Serializable;
import java.util.Map;

/* compiled from: HistoryRecord.java */
/* loaded from: classes.dex */
public class a implements IHistoryRecord, Serializable {
    private final String a;
    private final String b;
    private final String c;
    private final Map<String, Object> d;
    private final int e;
    private final int f;

    public a(String str, String str2, String str3, String str4, int i, int i2) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j.c(str4);
        this.e = i;
        this.f = i2;
    }

    @Override // com.netease.nimlib.sdk.migration.model.IHistoryRecord
    public String getUrl() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.migration.model.IHistoryRecord
    public String getSecretKey() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.migration.model.IHistoryRecord
    public String getTag() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.migration.model.IHistoryRecord
    public Map<String, Object> getAttach() {
        return this.d;
    }

    @Override // com.netease.nimlib.sdk.migration.model.IHistoryRecord
    public int getClientType() {
        return this.f;
    }
}
