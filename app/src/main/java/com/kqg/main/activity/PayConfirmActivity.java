package com.kqg.main.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.pc.ioc.event.EventBus;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.constant.KV;
import com.kqg.main.model.ActivityResult;
import com.kqg.main.model.AliPayInfor;
import com.kqg.main.model.Message;
import com.kqg.main.model.PayInfor;
import com.kqg.main.model.UpPayInfor;
import com.kqg.main.model.WxPayInfor;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class PayConfirmActivity extends BaseActivity {
    private Button btn_pay;
    private TextView goods;
    private PayInfor infor;
    private TextView order_money;
    private TextView order_num;
    private Button pwd_close;
    private int what;

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        Intent intent = getIntent();
        onNewIntent(intent);
        setView("kqg_pay_confirm");
        this.btn_pay = (Button) getView("btn_pay");
        this.pwd_close = (Button) getView("pwd_close");
        this.goods = (TextView) getView("goods");
        this.order_num = (TextView) getView("order_num");
        this.order_money = (TextView) getView("order_money");
        registOnClicks("click", this.btn_pay, this.pwd_close);
        setNotSwallowKeyDown(false);
        registRemoveList();
        this.infor = (PayInfor) intent.getSerializableExtra("PayInfor");
        Log.e(EventBus.TAG, "Payinfor=" + this.infor);
        this.what = intent.getIntExtra("what", 0);
        this.order_num.setText(this.infor.getMhtOrderNo());
        this.goods.setText(this.infor.getMhtOrderName());
        this.order_money.setText(this.infor.getMhtOrderAmtDes());
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
            return;
        }
        if (id == UiUtils.getId("btn_pay")) {
            Message message = new Message(this.what);
            switch (this.what) {
                case 18000:
                    message.setObj(new WxPayInfor(this.infor));
                    break;
                case KV.EVENT_PAY_ALI /* 18001 */:
                    message.setObj(new AliPayInfor(this.infor));
                    break;
                case KV.EVENT_PAY_UP /* 18002 */:
                    message.setObj(new UpPayInfor(this.infor));
                    break;
            }
            postMessageOnCurrentThread(message);
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (intent == null) {
            return;
        }
        ActivityResult activityResult = new ActivityResult(i, i2, intent);
        activityResult.setAtt(this.infor);
        Message message = new Message(this.what + 18000);
        message.setObj(activityResult);
        postMessageOnCurrentThread(message);
    }
}
