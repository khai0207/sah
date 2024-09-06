package com.kqg.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class AccountManagerActivity extends BaseActivity {
    Button btn_change_phone;
    Button change_password;
    Button pwd_close;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_account_manager");
        registRemoveList();
        registBindRemoveList();
        this.pwd_close = (Button) getView("pwd_close");
        this.btn_change_phone = (Button) getView("btn_change_phone");
        Button button = (Button) getView("change_password");
        this.change_password = button;
        registOnClicks("click", this.pwd_close, this.btn_change_phone, button);
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
        } else if (id == UiUtils.getId("btn_change_phone")) {
            startActivity(new Intent(this, (Class<?>) ChangePhone.class));
        } else if (id == UiUtils.getId("change_password")) {
            startActivity(new Intent(this, (Class<?>) ChangePwdConfirmPhone.class));
        }
    }
}
