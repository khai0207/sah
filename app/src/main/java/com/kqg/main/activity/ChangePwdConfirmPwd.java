package com.kqg.main.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.pc.ioc.verification.Rule;
import com.android.pc.ioc.verification.Validator;
import com.iflytek.cloud.SpeechEvent;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.base.UiHandler;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.CommonEntity;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.utils.EncodeUtils;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChangePwdConfirmPwd extends BaseActivity {
    EditText edit_old_phone;
    EditText identfying_code;
    protected TextView old_phone;
    protected Button pwd_close;
    protected Button sure_bt;
    protected boolean codeIsFit = false;
    private String session_id = "";
    private User user = null;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        this.session_id = getIntent().getBundleExtra("bundle").getString(SpeechEvent.KEY_EVENT_SESSION_ID);
        this.user = (User) getIntent().getSerializableExtra("user");
        setView("kqg_change_pwd_confirm_pwd");
        this.pwd_close = (Button) getView("pwd_close");
        this.sure_bt = (Button) getView("sure_bt");
        this.identfying_code = (EditText) getView("identfying_code");
        this.edit_old_phone = (EditText) getView("edit_old_phone");
        registOnClicks("click", this.pwd_close, this.sure_bt);
        registRemoveList();
        registBindRemoveList();
        this.identfying_code.addTextChangedListener(new TextWatcher() { // from class: com.kqg.main.activity.ChangePwdConfirmPwd.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            AnonymousClass1() {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.length();
            }
        });
        new Handler();
        handler = new UiHandler(Looper.getMainLooper());
        handler.setHandler(this);
        KaiQiGuSdk.getInstance().setHandler(handler);
    }

    /* renamed from: com.kqg.main.activity.ChangePwdConfirmPwd$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        AnonymousClass1() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            charSequence.length();
        }
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finishBindActivities();
        } else if (id == UiUtils.getId("sure_bt")) {
            ValidatorUtils.validatorUserPassword(this.edit_old_phone, UiUtils.getResString("password_error_input"), 6, 10, new Validator.ValidationListener() { // from class: com.kqg.main.activity.ChangePwdConfirmPwd.2
                AnonymousClass2() {
                }

                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationSucceeded() {
                    if (ChangePwdConfirmPwd.this.edit_old_phone.getText().toString().trim().equals(ChangePwdConfirmPwd.this.identfying_code.getText().toString().trim())) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("newpassword", EncodeUtils.encrypt("8dQnbuOO6Z3t2eRm", ChangePwdConfirmPwd.this.identfying_code.getText().toString().trim()));
                            jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, ChangePwdConfirmPwd.this.session_id);
                            jSONObject.put(Constants.URL_ENCODING, KV.CHANGE_NEW_PASSWORD_CHECK_CODE);
                            jSONObject.put("isPost", "0");
                            jSONObject.put("what", KV.EVENT_CHECK_CODE4);
                            CommonEntity commonEntity = new CommonEntity();
                            commonEntity.setJson(jSONObject);
                            BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_CHECK_CODE);
                            backGroundMessage.setEntity(commonEntity);
                            ChangePwdConfirmPwd.this.postMessageOnBackgroundThread(backGroundMessage);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    UiUtils.showImageToast(UiUtils.getResString("change_pwd_12"));
                }

                @Override // com.android.pc.ioc.verification.Validator.ValidationListener
                public void onValidationFailed(View view2, Rule<?> rule) {
                    UiUtils.showImageToast(rule.getFailureMessage());
                }
            });
        }
    }

    /* renamed from: com.kqg.main.activity.ChangePwdConfirmPwd$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Validator.ValidationListener {
        AnonymousClass2() {
        }

        @Override // com.android.pc.ioc.verification.Validator.ValidationListener
        public void onValidationSucceeded() {
            if (ChangePwdConfirmPwd.this.edit_old_phone.getText().toString().trim().equals(ChangePwdConfirmPwd.this.identfying_code.getText().toString().trim())) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("newpassword", EncodeUtils.encrypt("8dQnbuOO6Z3t2eRm", ChangePwdConfirmPwd.this.identfying_code.getText().toString().trim()));
                    jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, ChangePwdConfirmPwd.this.session_id);
                    jSONObject.put(Constants.URL_ENCODING, KV.CHANGE_NEW_PASSWORD_CHECK_CODE);
                    jSONObject.put("isPost", "0");
                    jSONObject.put("what", KV.EVENT_CHECK_CODE4);
                    CommonEntity commonEntity = new CommonEntity();
                    commonEntity.setJson(jSONObject);
                    BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_CHECK_CODE);
                    backGroundMessage.setEntity(commonEntity);
                    ChangePwdConfirmPwd.this.postMessageOnBackgroundThread(backGroundMessage);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            UiUtils.showImageToast(UiUtils.getResString("change_pwd_12"));
        }

        @Override // com.android.pc.ioc.verification.Validator.ValidationListener
        public void onValidationFailed(View view2, Rule<?> rule) {
            UiUtils.showImageToast(rule.getFailureMessage());
        }
    }

    @Override // com.kqg.main.base.BaseActivity, com.kqg.main.base.UiHandler.IHandler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what != 1047) {
            return;
        }
        Log.v("kqgsdk", "修改新的密码--完成");
        UiUtils.showImageToast(UiUtils.getResString("change_pwd_success"));
        this.user.setPassword(EncodeUtils.encrypt("8dQnbuOO6Z3t2eRm", this.identfying_code.getText().toString().trim()));
        UserManager.getInstance().setFirstUser(this.user);
        finishAllActivities();
        startActivity(new Intent(this, (Class<?>) LoginActivity.class));
    }
}
