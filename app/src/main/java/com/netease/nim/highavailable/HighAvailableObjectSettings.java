package com.netease.nim.highavailable;

/* loaded from: classes.dex */
public class HighAvailableObjectSettings {
    private static final int BUSINESS_PUBLIC_VERSION_LENGTH = 128;
    private static final int BUSINESS_TOKEN_LENGTH = 128;
    protected final String businessPublicVersion;
    protected final String businessToken;
    protected final int clientType;
    protected final int internalVersion;
    protected final int protocolVersion;

    public HighAvailableObjectSettings(String str, String str2, int i, int i2, int i3) {
        this.businessToken = str;
        this.businessPublicVersion = str2;
        this.internalVersion = i;
        this.protocolVersion = i2;
        this.clientType = i3;
    }
}
