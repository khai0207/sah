package com.talkingdata.sdk;

/* compiled from: td */
/* loaded from: classes.dex */
/* synthetic */ class dj {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[dk.values().length];
        a = iArr;
        try {
            iArr[dk.WIFI.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[dk.CELLULAR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[dk.BLUETOOTH.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
