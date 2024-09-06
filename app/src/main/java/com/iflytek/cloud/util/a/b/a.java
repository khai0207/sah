package com.iflytek.cloud.util.a.b;

import android.content.Context;
import android.net.Uri;
import android.provider.Contacts;
import com.alipay.sdk.m.h.c;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;

/* loaded from: classes.dex */
public class a extends com.iflytek.cloud.util.a.c.a {
    private static final String[] d = {TransferTable.COLUMN_ID, c.e};
    private static final String[] e = {c.e, "number", TransferTable.COLUMN_ID};
    private static final String[] f = {"person"};
    private static final String[] g = {"display_name"};
    private static final String[] h = {"number", "type", c.e};
    private static final String[] i = {TransferTable.COLUMN_ID, c.e, "number", "type"};
    private static final String[] j = {"number"};

    public a(Context context) {
        super(context);
        a(context);
    }

    @Override // com.iflytek.cloud.util.a.c.a
    public Uri a() {
        return Contacts.People.CONTENT_URI;
    }

    @Override // com.iflytek.cloud.util.a.c.a
    protected String[] b() {
        return d;
    }

    @Override // com.iflytek.cloud.util.a.c.a
    protected String c() {
        return c.e;
    }
}
