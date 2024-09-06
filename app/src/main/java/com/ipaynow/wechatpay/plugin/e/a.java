package com.ipaynow.wechatpay.plugin.e;

/* loaded from: classes.dex */
public enum a {
    Verbose,
    Debug,
    Info,
    Warn,
    Error,
    Wtf;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static a[] valuesCustom() {
        a[] valuesCustom = values();
        int length = valuesCustom.length;
        a[] aVarArr = new a[length];
        System.arraycopy(valuesCustom, 0, aVarArr, 0, length);
        return aVarArr;
    }
}
