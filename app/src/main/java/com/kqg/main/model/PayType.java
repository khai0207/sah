package com.kqg.main.model;

import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public enum PayType {
    WXPAY(0, "sdk_pay_weixin", "name_wxpay"),
    ALIPAY(1, "sdk_pay_alipay", "name_alipay"),
    UPAY(2, "sdk_pay_upay", "name_upay");

    private int drawableId;
    private int payType;
    private String resString;

    PayType(int i, String str, String str2) {
        this.payType = i;
        this.drawableId = UiUtils.getDrawable(str);
        this.resString = UiUtils.getResString(str2);
    }

    public PaySelectType toPaySelectType() {
        return new PaySelectType(this.payType, this.drawableId, this.resString);
    }
}
