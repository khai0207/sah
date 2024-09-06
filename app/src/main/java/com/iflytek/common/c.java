package com.iflytek.common;

import android.util.Log;

/* loaded from: classes.dex */
final class c {
    static com.iflytek.common.b.a a;

    protected static com.iflytek.common.b.a a() {
        com.iflytek.common.b.a aVar = a;
        if (aVar != null) {
            return aVar;
        }
        try {
            com.iflytek.common.b.a aVar2 = (com.iflytek.common.b.a) Class.forName("com.iflytek.common.push.impl.PushImpl").newInstance();
            a = aVar2;
            if (aVar2 != null) {
                return aVar2;
            }
            return null;
        } catch (Exception unused) {
            Log.e("PushFactory", "getPushInstance not found push instance.");
            return null;
        }
    }
}
