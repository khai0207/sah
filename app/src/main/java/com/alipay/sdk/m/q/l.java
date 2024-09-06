package com.alipay.sdk.m.q;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* loaded from: classes.dex */
public class l {
    public static final String a = "content://com.alipay.android.app.settings.data.ServerProvider/current_server";

    public static String a(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse(a), null, null, null, null);
        if (query != null && query.getCount() > 0) {
            r0 = query.moveToFirst() ? query.getString(query.getColumnIndex(Constants.URL_ENCODING)) : null;
            query.close();
        }
        return r0;
    }

    public static String b(Context context) {
        if (EnvUtils.isSandBox()) {
            return com.alipay.sdk.m.h.a.b;
        }
        if (context == null) {
            return com.alipay.sdk.m.h.a.a;
        }
        String str = com.alipay.sdk.m.h.a.a;
        return TextUtils.isEmpty(str) ? com.alipay.sdk.m.h.a.a : str;
    }
}
