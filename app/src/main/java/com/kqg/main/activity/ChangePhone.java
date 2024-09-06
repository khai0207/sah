package com.kqg.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.iflytek.cloud.SpeechEvent;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.base.UiHandler;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.CommonEntity;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.utils.MyCountDownTimerForButton;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import com.kqg.main.view.VerificationSeekBar;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChangePhone extends BaseActivity {
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
        setView("kqg_change_phone");
        this.pwd_close = (Button) getView("pwd_close");
        this.sure_bt = (Button) getView("sure_bt");
        this.identfying_code = (EditText) getView("identfying_code");
        this.edit_old_phone = (EditText) getView("edit_old_phone");
        ((TextView) getView("desc_tx")).setText(UiUtils.getResString("change_phone_4"));
        Button button = (Button) getView("btn_get_phone_code");
        this.btn_get_phone_code = button;
        registOnClicks("click", this.pwd_close, this.sure_bt, button);
        registRemoveList();
        registBindRemoveList();
        VerificationSeekBar verificationSeekBar = (VerificationSeekBar) getView("verificationSeekBar");
        this.verificationSeekBar = verificationSeekBar;
        verificationSeekBar.setVisible(4);
        this.verificationSeekBar.setOnEndListener(new VerificationSeekBar.onEndListener() { // from class: com.kqg.main.activity.ChangePhone.1
            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void onEnd(String str) {
                ChangePhone.this.verificationSeekBar.setVisible(4);
                ChangePhone.this.verificationSeekBar.setProgress(0);
                if (str == "btn_get_phone_code") {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("phone", ChangePhone.this.edit_old_phone.getText().toString().trim());
                        jSONObject.put("type", Constant.APPLY_MODE_DECIDED_BY_BANK);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    CommonEntity commonEntity = new CommonEntity();
                    commonEntity.setJson(jSONObject);
                    BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_FIND_PSD_GET_CODE);
                    backGroundMessage.setEntity(commonEntity);
                    ChangePhone.this.postMessageOnBackgroundThread(backGroundMessage);
                }
            }

            @Override // com.kqg.main.view.VerificationSeekBar.onEndListener
            public void resetView(String str) {
                if (str == "btn_get_phone_code") {
                    ChangePhone.this.btn_get_phone_code.setVisibility(0);
                }
            }
        });
        long currentTimeMillis = System.currentTimeMillis() - CountDownTimerRecord.change_phone_code_time;
        if (currentTimeMillis < KV.GET_CODE_INTERVAL) {
            startCountDownTimer(KV.GET_CODE_INTERVAL - currentTimeMillis);
        }
        new Handler();
        handler = new UiHandler(Looper.getMainLooper());
        handler.setHandler(this);
        KaiQiGuSdk.getInstance().setHandler(handler);
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
            if (this.identfying_code.getText().toString().trim().equals("")) {
                UiUtils.showImageToast(UiUtils.getResString("please_enter_your_phone_code"));
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("phone", trim);
                jSONObject.put("code", this.identfying_code.getText().toString().trim());
                jSONObject.put(Constants.URL_ENCODING, KV.ACCOUNT_LOGIN_WITH_PHONE_CODE);
                jSONObject.put("isPost", "1");
                jSONObject.put("what", 1044);
                CommonEntity commonEntity = new CommonEntity();
                commonEntity.setJson(jSONObject);
                BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_CHECK_CODE);
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
            Log.v("kqgsdk", "修改新的手机--旧手机验证码完成");
            UiUtils.showImageToast(UiUtils.getResString("code_send_success"));
            CountDownTimerRecord.change_phone_code_time = System.currentTimeMillis();
            startCountDownTimer(KV.GET_CODE_INTERVAL);
            return;
        }
        if (i == 1025) {
            CountDownTimerRecord.change_phone_code_time = System.currentTimeMillis();
            startCountDownTimer(KV.GET_CODE_INTERVAL);
            return;
        }
        if (i != 1044) {
            if (i == 9024) {
                UiUtils.showImageToast(message.obj.toString());
                return;
            } else {
                super.handleMessage(message);
                return;
            }
        }
        Log.v("kqgsdk", "修改新的手机--旧手机完成");
        try {
            JSONObject jSONObject = (JSONObject) message.obj;
            String string = jSONObject.has(SpeechEvent.KEY_EVENT_SESSION_ID) ? jSONObject.getString(SpeechEvent.KEY_EVENT_SESSION_ID) : "";
            User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1001);
            Intent intent = new Intent(this, (Class<?>) ChangeNewPhone.class);
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, string);
            bundle.putString("old_phone", this.edit_old_phone.getText().toString().trim());
            intent.putExtra("bundle", bundle);
            intent.putExtra("user", userByJsonData);
            startActivity(intent);
            this.sure_bt.setEnabled(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void startCountDownTimer(long j) {
        new MyCountDownTimerForButton(j, 1000L, this.btn_get_phone_code).start();
    }
}
