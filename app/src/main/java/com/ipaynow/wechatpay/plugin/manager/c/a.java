package com.ipaynow.wechatpay.plugin.manager.c;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public final class a {
    private Context bc;
    private final String bh;
    private final String bi;
    private final String bj;
    private final String bk;
    private final String bl;
    private final String bm;
    private final String bn;
    private final String bo;
    private final String bp;
    private final String bq;
    private final String br;
    private final String bs;
    private final String bt;
    private final String bu;
    private final String bv;
    private final String bw;
    private String bx;
    private String by;

    private a() {
        this.bc = null;
        String str = "so" + File.separator;
        this.bh = str;
        this.bi = "arm64-v8a";
        this.bj = String.valueOf(str) + "arm64-v8a";
        this.bk = "armeabi";
        this.bl = String.valueOf(this.bh) + "armeabi";
        this.bm = "armeabi-v7a";
        this.bn = String.valueOf(this.bh) + "armeabi-v7a";
        this.bo = "mips";
        this.bp = String.valueOf(this.bh) + "mips";
        this.bq = "mips64";
        this.br = String.valueOf(this.bh) + "mips64";
        this.bs = "x86";
        this.bt = String.valueOf(this.bh) + "x86";
        this.bu = "x86_64";
        this.bv = String.valueOf(this.bh) + "x86_64";
        this.bw = "libonlywechat_plugin.so";
        this.bx = null;
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static a K() {
        a aVar;
        aVar = b.bz;
        return aVar;
    }

    private boolean M() {
        String str;
        try {
            if (!Build.CPU_ABI.equalsIgnoreCase("arm64-v8a")) {
                if (Build.CPU_ABI.equalsIgnoreCase("armeabi")) {
                    this.by = "armeabi";
                    str = this.bl;
                } else if (Build.CPU_ABI.equalsIgnoreCase("armeabi-v7a")) {
                    this.by = "armeabi-v7a";
                    str = this.bn;
                } else if (Build.CPU_ABI.equalsIgnoreCase("mips")) {
                    this.by = "mips";
                    str = this.bp;
                } else if (Build.CPU_ABI.equalsIgnoreCase("mips64")) {
                    this.by = "mips64";
                    str = this.br;
                } else if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
                    this.by = "x86";
                    str = this.bt;
                } else if (Build.CPU_ABI.equalsIgnoreCase("x86_64")) {
                    this.by = "x86_64";
                    str = this.bv;
                }
                this.bx = str;
                this.bc.getAssets().open(String.valueOf(this.bx) + File.separator + "libonlywechat_plugin.so").close();
                return true;
            }
            this.bc.getAssets().open(String.valueOf(this.bx) + File.separator + "libonlywechat_plugin.so").close();
            return true;
        } catch (IOException unused) {
            return false;
        }
        this.by = "arm64-v8a";
        str = this.bj;
        this.bx = str;
    }

    private boolean N() {
        File file = new File(this.bc.getDir("lib", 0), "libonlywechat_plugin.so");
        if (!file.exists()) {
            com.ipaynow.wechatpay.plugin.utils.b.a(this.bc, String.valueOf(this.bx) + File.separator + "libonlywechat_plugin.so", file);
        }
        try {
            System.load(file.getAbsolutePath());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final boolean L() {
        if (M()) {
            N();
            return true;
        }
        try {
            System.loadLibrary("onlywechat_plugin");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final void clear() {
        this.bc = null;
    }

    public final a d(Context context) {
        this.bc = context;
        return this;
    }
}
