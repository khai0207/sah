package com.netease.nim.highavailable.enums;

/* loaded from: classes.dex */
public enum HAvailableFCSUploadPluginTag {
    kUploadPluginTagUnknown(-1),
    kUploadPluginTagAWSS3(0),
    kUploadPluginTagNOS(1);

    private int value;

    HAvailableFCSUploadPluginTag(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static HAvailableFCSUploadPluginTag typeOfValue(int i) {
        for (HAvailableFCSUploadPluginTag hAvailableFCSUploadPluginTag : values()) {
            if (hAvailableFCSUploadPluginTag.getValue() == i) {
                return hAvailableFCSUploadPluginTag;
            }
        }
        return null;
    }
}
