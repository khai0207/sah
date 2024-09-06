package com.ipaynow.wechatpay.plugin.view;

/* loaded from: classes.dex */
public enum m {
    SPIN_INDETERMINATE,
    PIE_DETERMINATE,
    ANNULAR_DETERMINATE,
    BAR_DETERMINATE;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static m[] valuesCustom() {
        m[] valuesCustom = values();
        int length = valuesCustom.length;
        m[] mVarArr = new m[length];
        System.arraycopy(valuesCustom, 0, mVarArr, 0, length);
        return mVarArr;
    }
}
