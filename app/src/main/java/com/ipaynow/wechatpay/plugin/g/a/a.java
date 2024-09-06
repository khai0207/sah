package com.ipaynow.wechatpay.plugin.g.a;

import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
public enum a {
    DIANXIN("0"),
    LIANTONG("1"),
    YIDONG("2"),
    UNKNOWN(Constant.APPLY_MODE_DECIDED_BY_BANK);

    private String g;

    a(String str) {
        this.g = str;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static a[] valuesCustom() {
        a[] valuesCustom = values();
        int length = valuesCustom.length;
        a[] aVarArr = new a[length];
        System.arraycopy(valuesCustom, 0, aVarArr, 0, length);
        return aVarArr;
    }

    public final String c() {
        return this.g;
    }
}
