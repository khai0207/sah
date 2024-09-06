package com.kqg.main.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.model.PayInfor;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class PayResultActivity extends BaseActivity {
    private TextView finish_time;
    private TextView goods;
    private PayInfor infor;
    private TextView order_money;
    private TextView order_num;
    private TextView provider;
    private Button pwd_close;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_pay_result");
        this.pwd_close = (Button) getView("pwd_close");
        this.goods = (TextView) getView("goods");
        this.order_num = (TextView) getView("order_num");
        this.provider = (TextView) getView("provider");
        this.order_money = (TextView) getView("order_money");
        this.finish_time = (TextView) getView("finish_time");
        registOnClicks("click", this.pwd_close);
        registRemoveList();
        PayInfor payInfor = (PayInfor) getIntent().getExtras().getSerializable("PayInfor");
        this.infor = payInfor;
        this.goods.setText(payInfor.getMhtOrderName());
        this.order_num.setText(this.infor.getMhtOrderNo());
        this.provider.setText(this.infor.getMhtOrderProvider());
        this.order_money.setText(this.infor.getMhtOrderAmtDes());
        this.finish_time.setText(this.infor.getMhtOrderFinishTimeDes());
    }

    public void click(View view) {
        if (view.getId() == UiUtils.getId("pwd_close")) {
            finish();
        }
    }
}
