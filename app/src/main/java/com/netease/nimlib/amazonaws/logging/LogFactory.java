package com.netease.nimlib.amazonaws.logging;

import androidx.appcompat.widget.ActivityChooserView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class LogFactory {
    private static final String TAG = LogFactory.class.getSimpleName();
    private static final Map<String, Log> logMap = new HashMap();
    private static Level globalLogLevel = null;

    public static synchronized Log getLog(Class<?> cls) {
        Log log;
        synchronized (LogFactory.class) {
            log = getLog(getTruncatedLogTag(cls.getSimpleName()));
        }
        return log;
    }

    public static synchronized Log getLog(String str) {
        Log nimS3Log;
        synchronized (LogFactory.class) {
            String truncatedLogTag = getTruncatedLogTag(str);
            Log log = logMap.get(truncatedLogTag);
            if (log != null) {
                return log;
            }
            if (Environment.isJUnitTest()) {
                nimS3Log = new ConsoleLog(truncatedLogTag);
            } else {
                nimS3Log = new NimS3Log(truncatedLogTag);
            }
            logMap.put(truncatedLogTag, nimS3Log);
            return nimS3Log;
        }
    }

    public static void setLevel(Level level) {
        globalLogLevel = level;
    }

    public static Level getLevel() {
        return globalLogLevel;
    }

    private static String getTruncatedLogTag(String str) {
        if (str.length() <= 23) {
            return str;
        }
        getLog(TAG).warn("Truncating log tag length as it exceed 23, the limit imposed by Android on certain API Levels");
        return str.substring(0, 23);
    }

    /* loaded from: classes.dex */
    public enum Level {
        ALL(Integer.MIN_VALUE),
        TRACE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4),
        OFF(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);

        private final int value;

        public int getValue() {
            return this.value;
        }

        Level(int i) {
            this.value = i;
        }
    }
}
