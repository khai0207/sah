package com.talkingdata.sdk;

/* compiled from: td */
/* loaded from: classes.dex */
public enum dk {
    WIFI("wifi"),
    CELLULAR("cellular"),
    BLUETOOTH("bluetooth");

    private String d;

    dk(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }
}
