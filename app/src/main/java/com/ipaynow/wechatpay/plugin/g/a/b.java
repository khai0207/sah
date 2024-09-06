package com.ipaynow.wechatpay.plugin.g.a;

/* loaded from: classes.dex */
public enum b {
    ANDROID("0"),
    IOS("1");

    private String g;

    b(String str) {
        this.g = str;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] valuesCustom = values();
        int length = valuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(valuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }

    public final String c() {
        return this.g;
    }
}
