package com.netease.nimlib.log;

import com.netease.nimlib.sdk.Observer;

/* loaded from: classes.dex */
public class LogWs {
    private static final Config config = new Config();

    /* loaded from: classes.dex */
    private static class Config {
        private boolean isEnable;
        private Observer<String> observer;

        private Config() {
            this.isEnable = false;
            this.observer = null;
        }
    }

    public static boolean isEnable() {
        return config.isEnable;
    }

    public static Observer<String> getObserver() {
        return config.observer;
    }

    public static void sendLog(String str) {
        if (config.observer != null) {
            config.observer.onEvent(str);
        }
    }
}
