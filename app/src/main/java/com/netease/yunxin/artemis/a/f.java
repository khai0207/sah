package com.netease.yunxin.artemis.a;

import com.netease.yunxin.artemis.Artemis.YXArtemisLogCallback;

/* compiled from: LogUtils.java */
/* loaded from: classes.dex */
public final class f {
    public static YXArtemisLogCallback a;

    public static void a(String str) {
        YXArtemisLogCallback yXArtemisLogCallback = a;
        if (yXArtemisLogCallback != null) {
            yXArtemisLogCallback.onLog(str);
        }
    }
}
