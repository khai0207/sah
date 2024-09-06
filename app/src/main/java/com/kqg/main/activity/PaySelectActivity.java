package com.kqg.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.model.Message;
import com.kqg.main.model.PayInfor;
import com.kqg.main.model.PayResult;
import com.kqg.main.model.PaySelectType;
import com.kqg.main.model.PaySelectTypeManager;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.view.PaySelectAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class PaySelectActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private PaySelectAdapter adapter;
    private Button btn_pay;
    private PayInfor infor;
    private TextView item_num;
    private ImageView left_select;
    private TextView order_money;
    private ListView pay_list;
    private Button pwd_close;
    private ImageView right_select;
    private List<PaySelectType> types;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_pay_select");
        this.btn_pay = (Button) getView("btn_pay");
        this.pwd_close = (Button) getView("pwd_close");
        this.order_money = (TextView) getView("order_money");
        this.item_num = (TextView) getView("item_num");
        this.pay_list = (ListView) getView("pay_list");
        this.left_select = (ImageView) getView("left_select");
        ImageView imageView = (ImageView) getView("right_select");
        this.right_select = imageView;
        registOnClicks("click", this.btn_pay, this.pwd_close, imageView, this.left_select);
        setNotSwallowKeyDown(false);
        registRemoveList();
        this.types = PaySelectTypeManager.getTypes();
        PayInfor payInfor = (PayInfor) getIntent().getSerializableExtra("PayInfor");
        this.infor = payInfor;
        if (payInfor != null) {
            this.order_money.setText(payInfor.getMhtOrderAmtDes());
            this.item_num.setText(this.infor.getMhtOrderName());
            this.btn_pay.setText("确认支付 (" + this.infor.getMhtOrderAmtDes() + ")");
        }
        refreshSelectType(1);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PaySelectType paySelectType = this.types.get(i);
        if (paySelectType.isSelected()) {
            return;
        }
        paySelectType.setSelected(!paySelectType.isSelected());
        resetOtherSelected(i);
    }

    private int getCurrentSelectType() {
        int size = this.types.size();
        for (int i = 0; i < size; i++) {
            PaySelectType paySelectType = this.types.get(i);
            if (paySelectType.isSelected()) {
                return paySelectType.getType();
            }
        }
        return 0;
    }

    private void resetOtherSelected(int i) {
        int size = this.types.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != i) {
                this.types.get(i2).setSelected(false);
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            PaySelectTypeManager.clearSelect();
            if (KaiQiGuSdk.getInstance().payListener != null) {
                PayResult payResult = new PayResult();
                payResult.setFail(true);
                payResult.setMes("用户取消");
                KaiQiGuSdk.getInstance().payListener.onPayFail(payResult);
            }
            finish();
            return;
        }
        if (id == UiUtils.getId("btn_pay")) {
            postMessageOnCurrentThread(new Message(getCurrentSelectType(), this.infor));
        } else if (id == UiUtils.getId("left_select")) {
            refreshSelectType(1);
        } else if (id == UiUtils.getId("right_select")) {
            refreshSelectType(0);
        }
    }

    private void refreshSelectType(int i) {
        int size = this.types.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            PaySelectType paySelectType = this.types.get(i2);
            if (paySelectType.getType() == 1) {
                paySelectType.setSelected(i == 1);
            } else if (paySelectType.getType() == 0) {
                paySelectType.setSelected(i == 0);
            } else {
                paySelectType.setSelected(false);
            }
            i2++;
        }
        this.left_select.setImageResource(UiUtils.getDrawable(i == 1 ? "x_zfbj02" : "x_zfb01"));
        this.right_select.setImageResource(UiUtils.getDrawable(i != 0 ? "x_zfb01" : "x_zfbj02"));
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (intent == null) {
            return;
        }
        getCurrentSelectType();
    }
}
