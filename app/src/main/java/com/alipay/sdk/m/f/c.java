package com.alipay.sdk.m.f;

import com.alipay.sdk.app.OpenAuthTask;

/* loaded from: classes.dex */
public enum c {
    SUCCEEDED(OpenAuthTask.OK, "处理成功"),
    FAILED(4000, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    ACTIVITY_NOT_START_EXIT(6007, "支付未完成"),
    PARAMS_ERROR(4001, "参数错误"),
    DOUBLE_REQUEST(OpenAuthTask.Duplex, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");

    public int a;
    public String b;

    c(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public void a(int i) {
        this.a = i;
    }

    public int b() {
        return this.a;
    }

    public static c b(int i) {
        if (i == 4001) {
            return PARAMS_ERROR;
        }
        if (i == 5000) {
            return DOUBLE_REQUEST;
        }
        if (i == 8000) {
            return PAY_WAITTING;
        }
        if (i == 9000) {
            return SUCCEEDED;
        }
        if (i == 6001) {
            return CANCELED;
        }
        if (i != 6002) {
            return FAILED;
        }
        return NETWORK_ERROR;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }
}
