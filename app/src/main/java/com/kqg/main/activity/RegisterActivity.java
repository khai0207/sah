package com.kqg.main.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.pc.ioc.verification.Rule;
import com.android.pc.ioc.verification.Validator;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.Login;
import com.kqg.main.model.Message;
import com.kqg.main.model.User;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;

/* loaded from: classes.dex */
public class RegisterActivity extends BaseActivity {
    private Button btn_cancel;
    private Button btn_sure;
    private TextView click_agree;
    private EditText register_confirm_user_password;
    private EditText register_user_name;
    private EditText register_user_password;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_regist");
        this.btn_cancel = (Button) getView("btn_cancel");
        this.btn_sure = (Button) getView("btn_sure");
        this.register_user_name = (EditText) getView("register_user_name");
        this.register_user_password = (EditText) getView("register_user_password");
        this.register_confirm_user_password = (EditText) getView("register_confirm_user_password");
        TextView textView = (TextView) getView("click_agree");
        this.click_agree = textView;
        registOnClicks("click", this.btn_cancel, this.btn_sure, textView);
        registRemoveList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkConfirmPassowrd() {
        ValidatorUtils.validatorUserPassword(this.register_confirm_user_password, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.RegisterActivity.1
            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationSucceeded() {
                User user = new User(RegisterActivity.this.register_user_name.getText().toString().trim(), RegisterActivity.this.register_user_password.getText().toString().trim(), "0", 1002, false);
                Login login = new Login();
                login.setData(user);
                BackGroundMessage backGroundMessage = new BackGroundMessage(1002);
                backGroundMessage.setEntity(login);
                RegisterActivity.this.postMessageOnBackgroundThread(backGroundMessage);
            }

            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationFailed(View view, Rule<?> rule) {
                UiUtils.toast(rule.getFailureMessage());
            }
        });
    }

    public void checkPassword(int i, String str) {
        ValidatorUtils.validatorUserPassword(this.register_user_password, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.RegisterActivity.2
            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationSucceeded() {
                if (RegisterActivity.this.register_user_password.getText().toString().trim().equals(RegisterActivity.this.register_confirm_user_password.getText().toString().trim())) {
                    RegisterActivity.this.checkConfirmPassowrd();
                } else {
                    UiUtils.showImageToast(UiUtils.getResString("password_confirm_error_input"));
                }
            }

            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationFailed(View view, Rule<?> rule) {
                UiUtils.showImageToast(rule.getFailureMessage());
            }
        });
    }

    public void click(View view) {
        final int id = view.getId();
        if (id == UiUtils.getId("btn_cancel")) {
            finish();
        } else if (id == UiUtils.getId("click_agree")) {
            postMessageOnCurrentThread(new Message(KV.EVENT_SHOW_AGREE_CONTENT));
        } else {
            final String trim = this.register_user_name.getText().toString().trim();
            ValidatorUtils.validatorUserName(this.register_user_name, UiUtils.getResString("username_error_input"), 6, 30, new Validator.ValidationListener() { // from class: com.kqg.main.activity.RegisterActivity.3
                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationSucceeded() {
                    RegisterActivity.this.checkPassword(id, trim);
                }

                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationFailed(View view2, Rule<?> rule) {
                    UiUtils.toast(rule.getFailureMessage());
                }
            });
        }
    }
}
