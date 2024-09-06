package com.netease.nimlib.sdk.media.record;

/* loaded from: classes.dex */
public enum RecordType {
    AMR(2, ".amr"),
    AAC(1, ".aac");

    private int outputFormat;
    private String suffix;

    RecordType(int i, String str) {
        this.outputFormat = i;
        this.suffix = str;
    }

    public int getOutputFormat() {
        return this.outputFormat;
    }

    public String getFileSuffix() {
        return this.suffix;
    }
}
