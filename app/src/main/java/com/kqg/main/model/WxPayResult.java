package com.kqg.main.model;

import android.os.Bundle;
import com.ipaynow.wechatpay.plugin.manager.route.dto.ResponseParams;
import com.kqg.main.constant.KV;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
public class WxPayResult extends PayResult {
    private String errorCode;
    private String respCode;
    private String respMsg;

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public String getRespMsg() {
        return this.respMsg;
    }

    public void setRespMsg(String str) {
        this.respMsg = str;
    }

    public WxPayResult(Bundle bundle) {
        this.respCode = bundle.getString("respCode");
        this.errorCode = bundle.getString(Constant.KEY_ERROR_CODE);
        this.respMsg = bundle.getString("respMsg");
        setResult();
    }

    public WxPayResult(ResponseParams responseParams) {
        this.respCode = responseParams.respCode;
        this.errorCode = responseParams.errorCode;
        this.respMsg = responseParams.respMsg;
        setResult();
    }

    private void setResult() {
        if (this.respCode.equals("00")) {
            setCode(KV.PAY_SUCCESS);
            setSuccess(true);
            return;
        }
        setSuccess(false);
        if (this.respCode.equals("02")) {
            setCode(16001);
            setCancel(true);
        } else if (this.respCode.equals("01")) {
            setCode(KV.PAY_FAIL);
            setMes(this.respMsg);
            setFail(true);
        } else if (this.respCode.equals("03")) {
            setCode(KV.PAY_UNKNOW);
            setMes(this.respMsg);
            setFailUnKnown(true);
        }
    }
}
