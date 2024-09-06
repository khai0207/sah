package com.kqg.main.model;

import com.ipaynow.wechatpay.plugin.utils.PreSignMessageUtil;
import com.kqg.main.wxapi.WXPayEntryActivity;

/* loaded from: classes.dex */
public class WxPayInfor extends PayInfor {
    private static final long serialVersionUID = -8409968223656030939L;
    private String appId;
    private String consumerId;
    private String consumerName;
    private String mhtCharset;
    private String mhtCurrencyType;
    private String mhtOrderType;
    private String payChannelType;
    private PreSignMessageUtil preSign;
    private String preSignStr;

    public WxPayInfor(PayInfor payInfor) {
        super(payInfor);
        setAppId(WXPayEntryActivity.wx_app_id);
        setMhtOrderType("01");
        setMhtCurrencyType("156");
        setMhtCharset("UTF-8");
        setPayChannelType("13");
        PreSignMessageUtil preSignMessageUtil = new PreSignMessageUtil();
        this.preSign = preSignMessageUtil;
        preSignMessageUtil.appId = getAppId();
        this.preSign.mhtOrderNo = getMhtOrderNo();
        this.preSign.mhtOrderName = getMhtOrderName();
        this.preSign.mhtOrderType = getMhtOrderType();
        this.preSign.mhtCurrencyType = getMhtCurrencyType();
        this.preSign.mhtOrderAmt = getMhtOrderAmt() + "";
        this.preSign.mhtOrderDetail = getMhtOrderDetail();
        this.preSign.mhtOrderTimeOut = getMhtOrderTimeOut() + "";
        this.preSign.mhtOrderStartTime = getMhtOrderStartTimeDes();
        this.preSign.notifyUrl = getNotifyUrl();
        this.preSign.mhtCharset = getMhtCharset();
        this.preSign.mhtReserved = getMhtReserved();
        this.preSign.consumerId = getConsumerId();
        this.preSign.consumerName = getConsumerName();
        this.preSign.payChannelType = getPayChannelType();
        setPreSignStr(this.preSign.generatePreSignMessage());
    }

    public String getPreSignStr() {
        return this.preSignStr;
    }

    public void setPreSignStr(String str) {
        this.preSignStr = str;
    }

    public String getPayChannelType() {
        return this.payChannelType;
    }

    public void setPayChannelType(String str) {
        this.payChannelType = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getMhtOrderType() {
        return this.mhtOrderType;
    }

    public void setMhtOrderType(String str) {
        this.mhtOrderType = str;
    }

    public String getMhtCurrencyType() {
        return this.mhtCurrencyType;
    }

    public void setMhtCurrencyType(String str) {
        this.mhtCurrencyType = str;
    }

    public String getMhtCharset() {
        return this.mhtCharset;
    }

    public void setMhtCharset(String str) {
        this.mhtCharset = str;
    }

    public String getConsumerId() {
        return this.consumerId;
    }

    public void setConsumerId(String str) {
        this.consumerId = str;
    }

    public String getConsumerName() {
        return this.consumerName;
    }

    public void setConsumerName(String str) {
        this.consumerName = str;
    }

    @Override // com.kqg.main.model.PayInfor
    public String toString() {
        return "WxPayInfor [appId=" + this.appId + ", mhtOrderType=" + this.mhtOrderType + ", mhtCurrencyType=" + this.mhtCurrencyType + ", mhtCharset=" + this.mhtCharset + ", consumerId=" + this.consumerId + ", consumerName=" + this.consumerName + ", payChannelType=" + this.payChannelType + ", preSignStr=" + this.preSignStr + ", preSign=" + this.preSign + "]";
    }
}
