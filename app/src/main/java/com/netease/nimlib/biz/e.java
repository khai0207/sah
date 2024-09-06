package com.netease.nimlib.biz;

import com.netease.nimlib.sdk.settings.model.NoDisturbConfig;

/* compiled from: NoDisturbConfigImpl.java */
/* loaded from: classes.dex */
public class e implements NoDisturbConfig {
    private boolean a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;

    @Override // com.netease.nimlib.sdk.settings.model.NoDisturbConfig
    public String getStartTimeString() {
        return String.format("%s:%s", String.format("%02d", Integer.valueOf(this.c)), String.format("%02d", Integer.valueOf(this.d)));
    }

    @Override // com.netease.nimlib.sdk.settings.model.NoDisturbConfig
    public String getStopTimeString() {
        return String.format("%s:%s", String.format("%02d", Integer.valueOf(this.e)), String.format("%02d", Integer.valueOf(this.f)));
    }

    @Override // com.netease.nimlib.sdk.settings.model.NoDisturbConfig
    public void setStartTime(String str) {
        int[] a = a(str);
        if (a == null || a.length != 2) {
            return;
        }
        this.c = a[0];
        this.d = a[1];
    }

    @Override // com.netease.nimlib.sdk.settings.model.NoDisturbConfig
    public void setStopTime(String str) {
        int[] a = a(str);
        if (a == null || a.length != 2) {
            return;
        }
        this.e = a[0];
        this.f = a[1];
    }

    private int[] a(String str) {
        try {
            String[] split = str.split(":");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            if (parseInt < 0 || parseInt > 24 || parseInt2 < 0 || parseInt2 >= 60) {
                parseInt2 = 0;
                parseInt = 0;
            }
            return new int[]{parseInt, parseInt2};
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean a() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    @Override // com.netease.nimlib.sdk.settings.model.NoDisturbConfig
    public boolean isOpen() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.settings.model.NoDisturbConfig
    public void setOpen(boolean z) {
        this.b = z;
    }

    public int b() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int c() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int d() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int e() {
        return this.f;
    }

    public void d(int i) {
        this.f = i;
    }

    public boolean f() {
        return this.a;
    }

    public void b(boolean z) {
        this.a = z;
    }

    public boolean g() {
        return !this.b && this.c == 0 && this.d == 0 && this.e == 0 && this.f == 0;
    }
}
