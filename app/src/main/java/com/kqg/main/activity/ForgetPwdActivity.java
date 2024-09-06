package com.kqg.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class ForgetPwdActivity extends BaseActivity {
    private Button by_email;
    private Button by_helper;
    private Button by_phone;
    private Button pwd_close;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_forget_pwd");
        this.pwd_close = (Button) getView("pwd_close");
        this.by_phone = (Button) getView("by_phone");
        Button button = (Button) getView("by_helper");
        this.by_helper = button;
        registOnClicks("click", this.pwd_close, this.by_phone, button);
        registRemoveList();
        registBindRemoveList();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
        } else if (id == UiUtils.getId("by_phone")) {
            startActivity(new Intent(this, (Class<?>) ChangePwdConfirmPhone.class));
        } else if (id == UiUtils.getId("by_helper")) {
            new AlertDialog.Builder(this).setTitle("提示！").setMessage(UiUtils.getResString("find_password_by_helper2")).setPositiveButton("确定", (DialogInterface.OnClickListener) null).show();
        }
    }
}
