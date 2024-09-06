package com.netease.nim.highavailable;

/* loaded from: classes.dex */
public class HighAvailableEnvironmentSettings {
    private static final int BUSINESS_TYPE_LENGTH = 32;
    private static final int DATA_PATH_LENGTH = 1024;
    private static final int LOG_PATH_LENGTH = 1024;
    protected final String appdataPath;
    protected final String businessType;
    protected final int enableHttpDns;
    protected final String logPath;

    /* loaded from: classes.dex */
    public interface HttpDnsState {
        public static final int DISABLE = -1;
        public static final int ENABLE = 1;
        public static final int UNDEFINE = 0;
    }

    public HighAvailableEnvironmentSettings(int i, String str, String str2, String str3) {
        if (str == null || str.length() > 32) {
            throw new IllegalArgumentException("businessType length invalid: " + str);
        }
        if (str2 == null || str2.length() > 1024) {
            throw new IllegalArgumentException("appdataPath length invalid: " + str2);
        }
        if (str3 == null || str3.length() > 1024) {
            throw new IllegalArgumentException("logPath length invalid: " + str3);
        }
        this.enableHttpDns = i;
        this.businessType = str;
        this.appdataPath = str2;
        this.logPath = str3;
    }
}
