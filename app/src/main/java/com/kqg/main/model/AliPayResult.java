package com.kqg.main.model;

import android.text.TextUtils;
import com.alipay.sdk.m.q.h;
import com.alipay.sdk.m.q.k;

/* loaded from: classes.dex */
public class AliPayResult {
    private String memo;
    private String result;
    private String resultStatus;

    public AliPayResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(";")) {
            if (str2.startsWith(k.a)) {
                this.resultStatus = gatValue(str2, k.a);
            }
            if (str2.startsWith("result")) {
                this.result = gatValue(str2, "result");
            }
            if (str2.startsWith(k.b)) {
                this.memo = gatValue(str2, k.b);
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + h.d;
    }

    private String gatValue(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(h.d));
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }
}
