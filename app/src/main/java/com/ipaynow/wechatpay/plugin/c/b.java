package com.ipaynow.wechatpay.plugin.c;

/* loaded from: classes.dex */
public enum b {
    PE001("插件不支持该渠道交易"),
    PE002("网络连接超时"),
    PE003("现在支付获取交易号失败"),
    PE004("渠道方交易失败"),
    PE005("支付插件其他未知错误"),
    PE006("渠道方不支持此种支付方式"),
    PE007("用户未安装微信客户端"),
    PE008("支付用渠道客户端版本过低"),
    PE009("渠道交易结果未知"),
    PE010("渠道方其他未知失败"),
    PE011("现在支付接口其他失败情况");

    private String t;

    b(String str) {
        this.t = str;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] valuesCustom = values();
        int length = valuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(valuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }

    public final String d() {
        return this.t;
    }
}
