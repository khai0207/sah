package com.iflytek.cloud.a.g;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;

/* loaded from: classes.dex */
public class g {
    private static int a;

    public static void a(View view) {
        view.setLayerType(1, null);
    }

    public static boolean a(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            ((AudioManager) context.getSystemService("audio")).requestAudioFocus(onAudioFocusChangeListener, 3, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(onAudioFocusChangeListener);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
