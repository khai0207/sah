package com.kqg.main.callback;

import com.ipaynow.wechatpay.plugin.manager.route.dto.ResponseParams;
import com.ipaynow.wechatpay.plugin.manager.route.impl.ReceivePayResult;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.model.Message;
import com.kqg.main.model.PayInfor;
import com.kqg.main.model.WxPayResult;

/* loaded from: classes.dex */
public class WxRecivePayResult implements ReceivePayResult {
    private PayInfor infor;

    public WxRecivePayResult(PayInfor payInfor) {
        this.infor = payInfor.copy(payInfor);
    }

    @Override // com.ipaynow.wechatpay.plugin.manager.route.impl.ReceivePayResult
    public void onIpaynowTransResult(ResponseParams responseParams) {
        KaiQiGuSdk.getInstance().onPayResultBack(new Message(16000, new WxPayResult(responseParams)), this.infor);
    }
}
