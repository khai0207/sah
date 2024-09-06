package com.kqg.main.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.base.UiHandler;
import com.kqg.main.constant.KV;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* loaded from: classes.dex */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";
    public static final String wx_app_id = "wx43f143b3892d0b17";
    private IWXAPI api;
    private UiHandler handler;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this, wx_app_id, true);
        this.api = createWXAPI;
        createWXAPI.handleIntent(getIntent(), this);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        Log.e("支付结果", "onReq: ");
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        Log.e("支付结果", "onResp: " + baseResp.errCode);
        Message message = new Message();
        message.what = KV.EVENT_PAY_WEIXIN_RESULT;
        message.obj = Integer.valueOf(baseResp.errCode);
        KaiQiGuSdk.getInstance().sendEventResultMessageToUi(message);
        finish();
    }
}
