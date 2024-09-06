package com.kqg.main.model;

import java.util.Map;

/* loaded from: classes.dex */
public class AliPayInfor extends PayInfor {
    private static final long serialVersionUID = 5961076104639756101L;
    public Map<String, String> alipayResult;
    private String result;

    public AliPayInfor(PayInfor payInfor) {
        super(payInfor);
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }
}
