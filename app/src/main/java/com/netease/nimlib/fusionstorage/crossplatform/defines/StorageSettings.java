package com.netease.nimlib.fusionstorage.crossplatform.defines;

import java.util.List;

/* loaded from: classes.dex */
public class StorageSettings {
    private final Float policyTtlWaterLevel;
    private final List<String> scenes;
    private final ThumbUserSettings thumbSettings;

    public StorageSettings(ThumbUserSettings thumbUserSettings, Float f, List<String> list) {
        this.thumbSettings = thumbUserSettings;
        this.policyTtlWaterLevel = f;
        this.scenes = list;
    }

    public ThumbUserSettings getThumbSettings() {
        return this.thumbSettings;
    }

    public Float getPolicyTtlWaterLevel() {
        return this.policyTtlWaterLevel;
    }

    public List<String> getScenes() {
        return this.scenes;
    }
}
