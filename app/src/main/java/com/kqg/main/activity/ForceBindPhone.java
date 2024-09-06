package com.kqg.main.activity;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.iflytek.cloud.SpeechEvent;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.CommonEntity;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.utils.MyCountDownTimerForButton;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import com.kqg.main.view.VerificationSeekBar;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ForceBindPhone extends BaseActivity {
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
        setView("kqg_force_bind_phone");
        this.sure_bt = (Button) getView("sure_bt");
        this.identfying_code = (EditText) getView("identfying_code");
        this.edit_old_phone = (EditText) getView("edit_old_phone");
        Button button = (Button) getView("btn_get_phone_code");
        this.btn_get_phone_code = button;
        registOnClicks("click", this.sure_bt, button);
        registRemoveList();
        VerificationSeekBar verificationSeekBar = (VerificationSeekBar) getView("verificationSeekBar");
        this.verificationSeekBar = verificationSeekBar;
        verificationSeekBar.setVisible(4);
        this.verificationSeekBar.setOnEndListener(new VerificationSeekBar.onEndListener() { // from class: com.kqg.main.activity.ForceBindPhone.1
            AnonymousClass1() {
            }

            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void onEnd(String str) {
                ForceBindPhone.this.verificationSeekBar.setVisible(4);
                ForceBindPhone.this.verificationSeekBar.setProgress(0);
                if (str == "btn_get_phone_code") {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("phone", ForceBindPhone.this.edit_old_phone.getText().toString().trim());
                        jSONObject.put("type", "2");
                        jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, UserManager.getInstance().getCurrentUser().getSession_id());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    CommonEntity commonEntity = new CommonEntity();
                    commonEntity.setJson(jSONObject);
                    BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_FIND_PSD_GET_CODE);
                    backGroundMessage.setEntity(commonEntity);
                    ForceBindPhone.this.postMessageOnBackgroundThread(backGroundMessage);
                }
            }

            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void resetView(String str) {
                if (str == "btn_get_phone_code") {
                    ForceBindPhone.this.btn_get_phone_code.setVisibility(0);
                }
            }
        });
        long currentTimeMillis = System.currentTimeMillis() - CountDownTimerRecord.force_bind_phone_code_time;
        if (currentTimeMillis < KV.GET_CODE_INTERVAL) {
            startCountDownTimer(KV.GET_CODE_INTERVAL - currentTimeMillis);
        }
    }

    /* renamed from: com.kqg.main.activity.ForceBindPhone$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements VerificationSeekBar.onEndListener {
        AnonymousClass1() {
        }

        @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
        public void onEnd(String str) {
            ForceBindPhone.this.verificationSeekBar.setVisible(4);
            ForceBindPhone.this.verificationSeekBar.setProgress(0);
            if (str == "btn_get_phone_code") {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("phone", ForceBindPhone.this.edit_old_phone.getText().toString().trim());
                    jSONObject.put("type", "2");
                    jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, UserManager.getInstance().getCurrentUser().getSession_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CommonEntity commonEntity = new CommonEntity();
                commonEntity.setJson(jSONObject);
                BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_FIND_PSD_GET_CODE);
                backGroundMessage.setEntity(commonEntity);
                ForceBindPhone.this.postMessageOnBackgroundThread(backGroundMessage);
            }
        }

        @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
        public void resetView(String str) {
            if (str == "btn_get_phone_code") {
                ForceBindPhone.this.btn_get_phone_code.setVisibility(0);
            }
        }
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
            String trim = this.edit_old_phone.getText().toString().trim();
            if (!ValidatorUtils.validatorPhone(trim)) {
                UiUtils.showImageToast(UiUtils.getResString("enter_phone_formet_error"));
                return;
            }
            if ("".equals(this.identfying_code.getText().toString().trim())) {
                UiUtils.showImageToast(UiUtils.getResString("please_enter_your_phone_code"));
                return;
            }
            JSONObject jSONObject = new JSONObject();
            User currentUser = UserManager.getInstance().getCurrentUser();
            try {
                jSONObject.put("phone", trim);
                jSONObject.put("code", this.identfying_code.getText().toString().trim());
                jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, currentUser.getSession_id());
                CommonEntity commonEntity = new CommonEntity();
                commonEntity.setJson(jSONObject);
                BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_FORCE_BIND_PHONE);
                backGroundMessage.setEntity(commonEntity);
                postMessageOnBackgroundThread(backGroundMessage);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        if (id == UiUtils.getId("btn_get_phone_code")) {
            if (!ValidatorUtils.validatorPhone(this.edit_old_phone.getText().toString().trim())) {
                UiUtils.showImageToast(UiUtils.getResString("enter_phone_formet_error"));
                return;
            }
            this.verificationSeekBar.setVisible(0);
            this.btn_get_phone_code.setVisibility(4);
            this.verificationSeekBar.setRegisterName("btn_get_phone_code");
        }
    }

    @Override // com.kqg.main.base.BaseActivity, com.kqg.main.base.UiHandler.IHandler
    public void handleMessage(Message message) {
        UiUtils.hideLoadingDialog();
        int i = message.what;
        if (i == 1020) {
            Log.e("DZ", "发送验证码成功的消息回来了。。。。。");
            UiUtils.showImageToast(UiUtils.getResString("code_send_success"));
            CountDownTimerRecord.force_bind_phone_code_time = System.currentTimeMillis();
            startCountDownTimer(KV.GET_CODE_INTERVAL);
            return;
        }
        if (i == 1025) {
            CountDownTimerRecord.force_bind_phone_code_time = System.currentTimeMillis();
            startCountDownTimer(KV.GET_CODE_INTERVAL);
        } else if (i == 9024) {
            UiUtils.showImageToast(message.obj.toString());
        } else {
            super.handleMessage(message);
        }
    }

    private void startCountDownTimer(long j) {
        new MyCountDownTimerForButton(j, 1000L, this.btn_get_phone_code).start();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        KaiQiGuSdk.getInstance().goToLogin();
    }
}
