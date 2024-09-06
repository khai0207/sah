package com.netease.nimlib.sdk.antispam.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AntiSpamConfig implements Serializable {
    private String antiSpamBusinessId;

    public AntiSpamConfig() {
    }

    public AntiSpamConfig(String str) {
        this.antiSpamBusinessId = str;
    }

    public String getAntiSpamBusinessId() {
        return this.antiSpamBusinessId;
    }

    public void setAntiSpamBusinessId(String str) {
        this.antiSpamBusinessId = str;
    }

    public String toString() {
        return "AntiSpamConfig{antiSpamBusinessId='" + this.antiSpamBusinessId + "'}";
    }
}
