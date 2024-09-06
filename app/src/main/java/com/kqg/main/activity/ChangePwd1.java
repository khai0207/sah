package com.kqg.main.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.view.VerificationSeekBar;

/* loaded from: classes.dex */
public class ChangePwd1 extends BaseActivity {
    protected Button btn_get_phone_code;
    protected boolean codeIsFit = false;
    EditText edit_old_phone;
    EditText identfying_code;
    protected TextView old_phone;
    protected Button pwd_close;
    protected Button sure_bt;
    VerificationSeekBar verificationSeekBar;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_change_pwd_1");
        this.pwd_close = (Button) getView("pwd_close");
        this.sure_bt = (Button) getView("sure_bt");
        this.identfying_code = (EditText) getView("identfying_code");
        this.edit_old_phone = (EditText) getView("edit_old_phone");
        Button button = (Button) getView("btn_get_phone_code");
        this.btn_get_phone_code = button;
        registOnClicks("click", this.pwd_close, this.sure_bt, button);
        registRemoveList();
        registBindRemoveList();
        this.identfying_code.addTextChangedListener(new TextWatcher() { // from class: com.kqg.main.activity.ChangePwd1.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        VerificationSeekBar verificationSeekBar = (VerificationSeekBar) getView("verificationSeekBar");
        this.verificationSeekBar = verificationSeekBar;
        verificationSeekBar.setVisible(4);
        this.verificationSeekBar.setOnEndListener(new VerificationSeekBar.onEndListener() { // from class: com.kqg.main.activity.ChangePwd1.2
            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void onEnd(String str) {
                ChangePwd1.this.verificationSeekBar.setVisible(4);
                ChangePwd1.this.verificationSeekBar.setProgress(0);
                if (str == "btn_get_phone_code") {
                    Log.v("kqgsdk", "btn_get_phone_code");
                }
            }

            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void resetView(String str) {
                if (str == "btn_get_phone_code") {
                    ChangePwd1.this.btn_get_phone_code.setVisibility(0);
                }
            }
        });
    }

    public void click(View view) {
        VerificationSeekBar verificationSeekBar = this.verificationSeekBar;
        if (verificationSeekBar != null && verificationSeekBar.getVisibility() == 0) {
            this.verificationSeekBar.setVisible(4);
        }
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finishBindActivities();
            return;
        }
        if (id == UiUtils.getId("sure_bt")) {
            startActivity(new Intent(this, (Class<?>) ChangeNewPhone.class));
            return;
        }
        if (id == UiUtils.getId("btn_get_phone_code")) {
            String trim = this.edit_old_phone.getText().toString().trim();
            if (trim == null || trim.equals("")) {
                UiUtils.showImageToast(UiUtils.getResString("please_enter_your_phone"));
                return;
            }
            this.verificationSeekBar.setVisible(0);
            this.btn_get_phone_code.setVisibility(4);
            this.verificationSeekBar.setRegisterName("btn_get_phone_code");
        }
    }
}
