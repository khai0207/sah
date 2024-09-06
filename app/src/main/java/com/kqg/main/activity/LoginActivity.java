package com.kqg.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.android.pc.ioc.verification.Rule;
import com.android.pc.ioc.verification.Validator;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.CommonEntity;
import com.kqg.main.model.Login;
import com.kqg.main.model.Message;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.utils.MyCountDownTimerForButton;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import com.kqg.main.view.DropEditText;
import com.kqg.main.view.VerificationSeekBar;
import com.kqg.main.view.WrapListViewAdapter;
import com.kqg.main.view.WrapListViewAdapterEmpty;
import com.kqg.main.view.WrapListViewCallBack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LoginActivity extends BaseActivity {
    private CheckBox agreement_checkBox;
    private Button btn_forget_password;
    private Button btn_get_phone_code;
    private Button btn_login;
    private Button btn_quick_regist;
    private Button btn_register;
    private Button btn_setting;
    private SwitchViewStatus curSwitchViewStatus = null;
    private EditText edit_phone;
    private EditText edit_phone_code;
    DropEditText edit_user_name;
    private EditText edit_user_password;
    private TextView forget_pwd_btn;
    private ImageView img_switchPasswordLogin;
    private ImageView img_switchPhoneLogin;
    SharedPreferences preferences;
    private RelativeLayout relativeLayout_password_login;
    private RelativeLayout relativeLayout_phone_login;
    private TextView text_agreement;
    private TextView text_switchPasswordLogin;
    private TextView text_switchPhoneLogin;
    VerificationSeekBar verificationSeekBar;
    private CheckBox vn_checkBox;

    /* loaded from: classes.dex */
    private enum SwitchViewStatus {
        isPasswordLogin,
        isPhoneLogin
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_login");
        this.btn_register = (Button) getView("btn_register");
        this.btn_quick_regist = (Button) getView("btn_quick_regist");
        this.forget_pwd_btn = (TextView) getView("forget_pwd_btn");
        this.text_switchPasswordLogin = (TextView) getView("text_switchPasswordLogin");
        this.text_switchPhoneLogin = (TextView) getView("text_switchPhoneLogin");
        this.img_switchPasswordLogin = (ImageView) getView("img_switchPasswordLogin");
        this.img_switchPhoneLogin = (ImageView) getView("img_switchPhoneLogin");
        this.relativeLayout_phone_login = (RelativeLayout) getView("relativeLayout_phone_login");
        this.relativeLayout_password_login = (RelativeLayout) getView("relativeLayout_password_login");
        this.curSwitchViewStatus = UserManager.getInstance().getUsers().size() == 0 ? SwitchViewStatus.isPhoneLogin : SwitchViewStatus.isPasswordLogin;
        this.btn_login = (Button) getView("btn_login");
        this.btn_get_phone_code = (Button) getView("btn_get_phone_code");
        this.btn_forget_password = (Button) getView("btn_forget_password");
        this.edit_phone = (EditText) getView("edit_phone");
        this.edit_phone_code = (EditText) getView("edit_phone_code");
        this.edit_user_name = (DropEditText) getView("edit_user_name");
        this.edit_user_password = (EditText) getView("edit_user_password");
        Button button = (Button) getView("btn_setting");
        this.btn_setting = button;
        registOnClicks("click", this.text_switchPasswordLogin, this.text_switchPhoneLogin, this.btn_login, this.btn_get_phone_code, this.btn_forget_password, button);
        refreshStatus();
        registRemoveList();
        this.text_agreement = (TextView) getView("text_agreement");
        this.preferences = getSharedPreferences("agreement", 0);
        refreshUserList();
        VerificationSeekBar verificationSeekBar = (VerificationSeekBar) getView("verificationSeekBar");
        this.verificationSeekBar = verificationSeekBar;
        verificationSeekBar.setVisible(4);
        this.verificationSeekBar.setOnEndListener(new VerificationSeekBar.onEndListener() { // from class: com.kqg.main.activity.LoginActivity.1
            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void onEnd(String str) {
                LoginActivity.this.verificationSeekBar.setVisible(4);
                LoginActivity.this.verificationSeekBar.setProgress(0);
                if (str != "btn_get_phone_code") {
                    if (str == "btn_forget_password") {
                        Log.v("kqgsdk", "忘记密码");
                        LoginActivity.postMessageOnCurrentThread(new Message(4000));
                        return;
                    }
                    return;
                }
                Log.v("kqgsdk", "获取手机验证码");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("phone", LoginActivity.this.edit_phone.getText().toString().trim());
                    jSONObject.put("type", "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CommonEntity commonEntity = new CommonEntity();
                commonEntity.setJson(jSONObject);
                BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_GET_PHONE_CODE_TO_LOGIN);
                backGroundMessage.setEntity(commonEntity);
                LoginActivity.this.postMessageOnBackgroundThread(backGroundMessage);
            }

            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void resetView(String str) {
                if (str == "btn_get_phone_code") {
                    LoginActivity.this.btn_get_phone_code.setVisibility(0);
                    LoginActivity.this.edit_phone_code.setVisibility(0);
                } else if (str == "btn_forget_password") {
                    LoginActivity.this.btn_forget_password.setVisibility(0);
                    LoginActivity.this.edit_user_password.setVisibility(0);
                }
            }
        });
        long currentTimeMillis = System.currentTimeMillis() - CountDownTimerRecord.phone_login_code_time;
        if (currentTimeMillis < KV.GET_CODE_INTERVAL) {
            startCountDownTimer(KV.GET_CODE_INTERVAL - currentTimeMillis);
        }
    }

    public void checkPasswordAndLogin(final int i, final String str) {
        ValidatorUtils.validatorUserPassword(this.edit_user_password, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.LoginActivity.2
            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationSucceeded() {
                String trim = LoginActivity.this.edit_user_password.getText().toString().trim();
                int i2 = i == UiUtils.getId("btn_login") ? 1001 : 0;
                User user = new User(str, trim, "0", i2, false);
                Login login = new Login();
                login.setData(user);
                BackGroundMessage backGroundMessage = new BackGroundMessage(i2);
                backGroundMessage.setEntity(login);
                LoginActivity.this.postMessageOnBackgroundThread(backGroundMessage);
            }

            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationFailed(View view, Rule<?> rule) {
                UiUtils.showImageToast(rule.getFailureMessage());
            }
        });
    }

    public void click(View view) {
        int id = view.getId();
        VerificationSeekBar verificationSeekBar = this.verificationSeekBar;
        if (verificationSeekBar != null && verificationSeekBar.getVisibility() == 0) {
            this.verificationSeekBar.setVisible(4);
        }
        if (id == UiUtils.getId("btn_register")) {
            postMessageOnCurrentThread(new Message(KV.EVENT_SHOW_REGIST));
            return;
        }
        if (id == UiUtils.getId("forget_pwd_btn")) {
            showExitDialog();
            return;
        }
        if (id == UiUtils.getId("bind_account")) {
            postMessageOnCurrentThread(new Message(10000));
            return;
        }
        if (id == UiUtils.getId("btn_quick_regist")) {
            postMessageOnCurrentThread(new Message(KV.EVENT_QUICK_REGIST));
            return;
        }
        if (id == UiUtils.getId("text_switchPasswordLogin")) {
            Log.v("kqgsdk", "点击了 text_switchPasswordLogin");
            if (this.curSwitchViewStatus == SwitchViewStatus.isPasswordLogin) {
                return;
            }
            this.curSwitchViewStatus = SwitchViewStatus.isPasswordLogin;
            refreshStatus();
            return;
        }
        if (id == UiUtils.getId("text_switchPhoneLogin")) {
            Log.v("kqgsdk", "点击了 text_switchPhoneLogin");
            if (this.curSwitchViewStatus == SwitchViewStatus.isPhoneLogin) {
                return;
            }
            this.curSwitchViewStatus = SwitchViewStatus.isPhoneLogin;
            refreshStatus();
            return;
        }
        if (id == UiUtils.getId("btn_login")) {
            Log.v("kqgsdk", "点击了 登录按钮");
            if (this.curSwitchViewStatus == SwitchViewStatus.isPasswordLogin) {
                checkUserName();
                return;
            }
            if (this.curSwitchViewStatus == SwitchViewStatus.isPhoneLogin) {
                String trim = this.edit_phone.getText().toString().trim();
                if (!ValidatorUtils.validatorPhone(trim)) {
                    UiUtils.showImageToast(UiUtils.getResString("enter_phone_formet_error"));
                    return;
                }
                String trim2 = this.edit_phone_code.getText().toString().trim();
                if (trim2 == null || trim2.equals("")) {
                    UiUtils.showImageToast(UiUtils.getResString("please_enter_your_phone_code"));
                    return;
                } else {
                    loginWithPhone(id, trim, trim2);
                    return;
                }
            }
            return;
        }
        if (id == UiUtils.getId("btn_get_phone_code")) {
            String trim3 = this.edit_phone.getText().toString().trim();
            if (trim3 == null || trim3.equals("")) {
                UiUtils.showImageToast(UiUtils.getResString("please_enter_your_phone"));
                return;
            }
            this.verificationSeekBar.setVisible(0);
            this.verificationSeekBar.setRegisterName("btn_get_phone_code");
            this.btn_get_phone_code.setVisibility(4);
            this.edit_phone_code.setVisibility(4);
            return;
        }
        if (id == UiUtils.getId("btn_forget_password")) {
            postMessageOnCurrentThread(new Message(4000));
        } else if (id == UiUtils.getId("btn_setting")) {
            postMessageOnCurrentThread(new Message(KV.EVENT_OPEN_ACCOUNT_MANAGER_ACTIVITY));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginWithPassword(int i, String str, String str2, boolean z) {
        int i2 = i == UiUtils.getId("btn_login") ? KV.EVENT_LOGIN_WITH_PASSWORD : 0;
        User user = new User(str, str2, "0", i2, z);
        Login login = new Login();
        login.setData(user);
        BackGroundMessage backGroundMessage = new BackGroundMessage(i2);
        backGroundMessage.setEntity(login);
        postMessageOnBackgroundThread(backGroundMessage);
    }

    private void loginWithPhone(int i, String str, String str2) {
        int i2 = i == UiUtils.getId("btn_login") ? KV.EVENT_LOGIN_WITH_PHONE : 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("phone", this.edit_phone.getText().toString().trim());
            jSONObject.put("code", this.edit_phone_code.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonEntity commonEntity = new CommonEntity();
        commonEntity.setMsg(jSONObject.toString());
        BackGroundMessage backGroundMessage = new BackGroundMessage(i2);
        backGroundMessage.setEntity(commonEntity);
        postMessageOnBackgroundThread(backGroundMessage);
    }

    private void refreshStatus() {
        if (this.curSwitchViewStatus == SwitchViewStatus.isPasswordLogin) {
            this.img_switchPasswordLogin.setVisibility(0);
            this.img_switchPhoneLogin.setVisibility(4);
            this.text_switchPasswordLogin.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.text_switchPhoneLogin.setTextColor(-8882056);
            this.relativeLayout_phone_login.setVisibility(4);
            this.relativeLayout_password_login.setVisibility(0);
            return;
        }
        if (this.curSwitchViewStatus == SwitchViewStatus.isPhoneLogin) {
            this.img_switchPasswordLogin.setVisibility(4);
            this.img_switchPhoneLogin.setVisibility(0);
            this.text_switchPhoneLogin.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.text_switchPasswordLogin.setTextColor(-8882056);
            this.relativeLayout_phone_login.setVisibility(0);
            this.relativeLayout_password_login.setVisibility(4);
            this.edit_user_name.closePopup();
        }
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this).setTitle("Mẹo").setMessage(UiUtils.getResString("find_password_by_helper2")).setPositiveButton("Xác nhận", (DialogInterface.OnClickListener) null).show();
    }

    @Override // com.kqg.main.base.BaseActivity, com.kqg.main.base.UiHandler.IHandler
    public void handleMessage(android.os.Message message) {
        UiUtils.hideLoadingDialog();
        int i = message.what;
        if (i == 1020) {
            Log.e("DZ", "发送验证码成功的消息回来了。。。。。");
            UiUtils.showImageToast(UiUtils.getResString("code_send_success"));
            CountDownTimerRecord.phone_login_code_time = System.currentTimeMillis();
            startCountDownTimer(KV.GET_CODE_INTERVAL);
            return;
        }
        if (i == 1025) {
            CountDownTimerRecord.phone_login_code_time = System.currentTimeMillis();
            startCountDownTimer(KV.GET_CODE_INTERVAL);
        } else {
            super.handleMessage(message);
        }
    }

    private void startCountDownTimer(long j) {
        new MyCountDownTimerForButton(j, 1000L, this.btn_get_phone_code).start();
    }

    private void checkUserName() {
        ValidatorUtils.validatorUserName(this.edit_user_name.getEditText(), UiUtils.getResString("username_error_input"), 6, 30, new Validator.ValidationListener() { // from class: com.kqg.main.activity.LoginActivity.3
            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationSucceeded() {
                LoginActivity.this.checkPassword();
            }

            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationFailed(View view, Rule<?> rule) {
                UiUtils.showImageToast(rule.getFailureMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPassword() {
        if (ValidatorUtils.checkIsMd5Pwd(this.edit_user_password.getText().toString().trim())) {
            loginWithPassword(UiUtils.getId("btn_login"), this.edit_user_name.getEditText().getText().toString().trim(), this.edit_user_password.getText().toString().trim(), true);
        } else {
            ValidatorUtils.validatorUserPassword(this.edit_user_password, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.LoginActivity.4
                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationSucceeded() {
                    LoginActivity.this.loginWithPassword(UiUtils.getId("btn_login"), LoginActivity.this.edit_user_name.getEditText().getText().toString().trim(), LoginActivity.this.edit_user_password.getText().toString().trim(), false);
                }

                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationFailed(View view, Rule<?> rule) {
                    UiUtils.showImageToast(rule.getFailureMessage());
                }
            });
        }
    }

    private void refreshUserList() {
        ArrayList arrayList = new ArrayList(UserManager.getInstance().getUsers());
        Collections.reverse(arrayList);
        this.edit_user_name.setHint(UiUtils.getResString("edit_user_name"));
        this.edit_user_name.setAdapter(new WrapListViewAdapter(arrayList, this, new WrapListViewCallBack() { // from class: com.kqg.main.activity.LoginActivity.5
            @Override // com.kqg.main.view.WrapListViewCallBack
            public void onDeleteItem(List<User> list) {
                if (list.size() == 0) {
                    LoginActivity.this.edit_user_name.getPopViewEmpty().setVisibility(0);
                } else {
                    LoginActivity.this.edit_user_name.getPopViewEmpty().setVisibility(4);
                }
            }
        }));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(1);
        this.edit_user_name.setEmptyAdapter(new WrapListViewAdapterEmpty(arrayList2, this));
        this.edit_user_name.setEditTextPassWord(this.edit_user_password);
        if (arrayList.size() >= 1) {
            String username = ((User) arrayList.get(0)).getUsername();
            String uid = ((User) arrayList.get(0)).getUid();
            String password = ((User) arrayList.get(0)).getPassword();
            if (ValidatorUtils.validatorPhone(username)) {
                this.edit_phone.setText(username);
            } else {
                this.edit_phone.setText("");
            }
            this.edit_phone_code.setText("");
            this.edit_user_name.getEditText().setText(username);
            this.edit_user_name.getEditText().setTag(uid);
            this.edit_user_password.setText(password);
            return;
        }
        startActivity(new Intent(this, (Class<?>) ChangePwdConfirmPhone.class));
    }
}
