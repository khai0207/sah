package com.kqg.main.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.pc.ioc.verification.Rule;
import com.android.pc.ioc.verification.Validator;
import com.android.pc.util.ThreeMap;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.Login;
import com.kqg.main.model.Message;
import com.kqg.main.model.User;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import com.talkingdata.sdk.aj;
import com.unionpay.tsmservice.data.Constant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

/* loaded from: classes.dex */
public class QuickRegistActivity extends BaseActivity {
    private Button btn_cancel;
    private Button btn_sure;
    private TextView click_agree;
    String[] getCharacter = {"0", "1", "2", Constant.APPLY_MODE_DECIDED_BY_BANK, aj.a, "5", "6", "7", aj.c, "9", "a", ThreeMap.type_boolean, "c", "d", "e", ThreeMap.type_float, "g", "h", ThreeMap.type_int, "j", "k", ThreeMap.type_long, "m", "n", "o", "p", "q", "r", ThreeMap.type_string, ThreeMap.type, "u", ThreeMap.value, "w", "x", "y", "z"};
    private EditText register_user_name;
    private EditText register_user_password;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_quick_regist");
        this.btn_cancel = (Button) getView("btn_cancel");
        this.btn_sure = (Button) getView("btn_sure");
        this.register_user_name = (EditText) getView("register_user_name");
        this.register_user_password = (EditText) getView("register_user_password");
        TextView textView = (TextView) getView("click_agree");
        this.click_agree = textView;
        registOnClicks("click", this.btn_cancel, this.btn_sure, textView);
        registRemoveList();
        String userName = getUserName();
        this.register_user_name.setText(userName);
        this.register_user_password.setText(getUserPassward(userName));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkConfirmPassowrd() {
        ValidatorUtils.validatorUserPassword(this.register_user_password, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.QuickRegistActivity.1
            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationSucceeded() {
                User user = new User(QuickRegistActivity.this.register_user_name.getText().toString().trim(), QuickRegistActivity.this.register_user_password.getText().toString().trim(), "", 1002, false);
                Login login = new Login();
                login.setData(user);
                BackGroundMessage backGroundMessage = new BackGroundMessage(1002);
                backGroundMessage.setEntity(login);
                QuickRegistActivity.this.postMessageOnBackgroundThread(backGroundMessage);
            }

            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationFailed(View view, Rule<?> rule) {
                UiUtils.toast(rule.getFailureMessage());
            }
        });
    }

    public void checkPassword(int i, String str) {
        ValidatorUtils.validatorUserPassword(this.register_user_password, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.QuickRegistActivity.2
            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationSucceeded() {
                if (QuickRegistActivity.this.register_user_password.getText().toString().trim().equals(QuickRegistActivity.this.register_user_password.getText().toString().trim())) {
                    QuickRegistActivity.this.checkConfirmPassowrd();
                } else {
                    UiUtils.toast(UiUtils.getResString("password_confirm_error_input"));
                }
            }

            @Override // com.android.pc.ioc.verification.Validator.ValidationListener
            public void onValidationFailed(View view, Rule<?> rule) {
                UiUtils.toast(rule.getFailureMessage());
            }
        });
    }

    public void click(View view) {
        final int id = view.getId();
        if (id == UiUtils.getId("btn_cancel")) {
            this.register_user_name.setText("");
            this.register_user_password.setText("");
            finish();
        } else if (id == UiUtils.getId("click_agree")) {
            postMessageOnCurrentThread(new Message(KV.EVENT_SHOW_AGREE_CONTENT));
        } else {
            final String trim = this.register_user_name.getText().toString().trim();
            ValidatorUtils.validatorUserName(this.register_user_name, UiUtils.getResString("username_error_input"), 6, 30, new Validator.ValidationListener() { // from class: com.kqg.main.activity.QuickRegistActivity.3
                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationSucceeded() {
                    QuickRegistActivity.this.checkPassword(id, trim);
                }

                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationFailed(View view2, Rule<?> rule) {
                    UiUtils.toast(rule.getFailureMessage());
                }
            });
        }
    }

    private String getUserName() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(1));
        String str = "";
        sb.append("");
        String substring = sb.toString().substring(2);
        String str2 = (calendar.get(2) + 1) + "";
        String str3 = calendar.get(5) + "";
        for (int i = 0; i < 6; i++) {
            str = str + this.getCharacter[new Random().nextInt(36)];
        }
        return "guest" + substring + str2 + str3 + str;
    }

    private String getUserPassward(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append("0" + hexString);
                } else {
                    stringBuffer.append(hexString);
                }
            }
            return stringBuffer.toString().substring(0, 7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("buhuifasheng");
        }
    }
}
