package com.ipaynow.wechatpay.plugin.c;

/* loaded from: classes.dex */
public enum a {
    CALL_MHT_SUCCESS("交易成功", "00"),
    CALL_MHT_FAIL("交易失败", "01"),
    CALL_MHT_CANCEL("交易取消", "02"),
    CALL_MHT_UNKNOWN("交易未知", "03");

    private String g;
    private String name;

    a(String str, String str2) {
        this.g = null;
        this.name = null;
        this.g = str2;
        this.name = str;
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
