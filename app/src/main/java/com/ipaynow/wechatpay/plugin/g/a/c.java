package com.ipaynow.wechatpay.plugin.g.a;

/* loaded from: classes.dex */
public enum c {
    ISROOT("1"),
    UNROOT("0");

    private String g;

    c(String str) {
        this.g = str;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static c[] valuesCustom() {
        c[] valuesCustom = values();
        int length = valuesCustom.length;
        c[] cVarArr = new c[length];
        System.arraycopy(valuesCustom, 0, cVarArr, 0, length);
        return cVarArr;
    }

    public final String c() {
        return this.g;
    }
}
