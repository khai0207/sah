package com.iflytek.cloud.a.g;

import android.util.DisplayMetrics;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public class c {
    public static DisplayMetrics a;

    public static String a(long j) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").format(Long.valueOf(j));
    }
}
