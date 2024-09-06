package com.netease.nimlib.o;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: PermissionUtil.java */
/* loaded from: classes.dex */
public class s {
    public static boolean a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                return context.getPackageManager().checkPermission(str, context.getApplicationInfo().packageName) == 0;
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.e("PermissionUtil", String.format("checkPermission %s Exception", str), e);
            }
        }
        return false;
    }
}
