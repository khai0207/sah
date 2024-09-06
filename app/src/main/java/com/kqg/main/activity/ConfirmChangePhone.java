package com.kqg.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.model.Message;
import com.kqg.main.utils.UiUtils;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
public class ConfirmChangePhone extends BaseActivity {
    private Button cancel;
    private Button sure;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_confirm_change_phone");
        this.cancel = (Button) getView(Constant.CASH_LOAD_CANCEL);
        Button button = (Button) getView("sure");
        this.sure = button;
        registOnClicks("click", this.cancel, button);
        registBindRemoveList();
        registRemoveList();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId(Constant.CASH_LOAD_CANCEL)) {
            finish();
        } else if (id == UiUtils.getId("sure")) {
            postMessageOnCurrentThread(new Message(1015));
            startActivity(new Intent(this, (Class<?>) ChangePhone.class));
            finish();
        }
    }
}
