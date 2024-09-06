package com.kqg.main.activity;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.android.pc.util.ThreeMap;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.base.UiHandler;
import com.kqg.main.constant.KV;
import com.kqg.main.model.Message;
import com.kqg.main.utils.UiUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FindPasswordByEmail extends BaseActivity {
    protected static UiHandler handler;
    private static KaiQiGuSdk sdk;
    private EditText enter_email;
    private Button find_password;
    private Button get_code;
    private EditText identfying_code;
    private Handler mHandler;
    private EditText new_password;
    private Button pwd_close;
    private EditText user_name;
    private boolean codeIsFit = false;
    int time = 0;
    private boolean emailIsFit = false;
    private Runnable runable = new Runnable() { // from class: com.kqg.main.activity.FindPasswordByEmail.2
        @Override // java.lang.Runnable
        public void run() {
            FindPasswordByEmail.this.get_code.setText(FindPasswordByEmail.this.time + ThreeMap.type_string);
            FindPasswordByEmail.this.get_code.setTextSize(12.0f);
            FindPasswordByEmail.this.get_code.setTextColor(-1);
            FindPasswordByEmail.this.time--;
            if (FindPasswordByEmail.this.time != -1) {
                FindPasswordByEmail.this.mHandler.postDelayed(FindPasswordByEmail.this.runable, 1000L);
                return;
            }
            Log.e("DZ", "时间到了.............");
            FindPasswordByEmail.this.get_code.setEnabled(true);
            FindPasswordByEmail.this.get_code.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
            FindPasswordByEmail.this.get_code.setText(UiUtils.getResString("find_password_get_code_des"));
            FindPasswordByEmail.this.get_code.setTextSize(10.0f);
            FindPasswordByEmail.this.get_code.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            FindPasswordByEmail.this.mHandler.removeCallbacks(FindPasswordByEmail.this.runable);
        }
    };

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_findpwy_byemail");
        this.pwd_close = (Button) getView("pwd_close");
        this.get_code = (Button) getView("get_code");
        this.find_password = (Button) getView("find_password");
        this.user_name = (EditText) getView("user_name");
        this.enter_email = (EditText) getView("enter_email");
        this.new_password = (EditText) getView("new_password");
        EditText editText = (EditText) getView("identfying_code");
        this.identfying_code = editText;
        editText.setEnabled(false);
        this.find_password.setEnabled(false);
        registOnClicks("click", this.pwd_close, this.get_code, this.find_password);
        registRemoveList();
        this.identfying_code.addTextChangedListener(new TextWatcher() { // from class: com.kqg.main.activity.FindPasswordByEmail.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    FindPasswordByEmail.this.codeIsFit = true;
                    FindPasswordByEmail.this.find_password.setEnabled(true);
                    FindPasswordByEmail.this.find_password.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
                } else {
                    FindPasswordByEmail.this.codeIsFit = false;
                    FindPasswordByEmail.this.find_password.setEnabled(false);
                    FindPasswordByEmail.this.find_password.setBackgroundResource(UiUtils.getDrawable("sdk_button_hui"));
                }
            }
        });
        this.mHandler = new Handler();
        UiHandler uiHandler = new UiHandler(Looper.getMainLooper());
        handler = uiHandler;
        uiHandler.setHandler(this);
        KaiQiGuSdk kaiQiGuSdk = KaiQiGuSdk.getInstance();
        sdk = kaiQiGuSdk;
        kaiQiGuSdk.setHandler(handler);
    }

    public void click(View view) {
        String trim = this.user_name.getText().toString().trim();
        String trim2 = this.enter_email.getText().toString().trim();
        String trim3 = this.new_password.getText().toString().trim();
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
            return;
        }
        if (id == UiUtils.getId("get_code")) {
            if (checkEnterIsFit(trim, trim3, trim2, this.enter_email)) {
                this.time = 15;
                buttonCountDown();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("username", trim);
                    jSONObject.put("email", trim2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Message message = new Message(KV.EVENT_FIND_PSD_GET_CODE_EMAIL);
                message.setObj(jSONObject);
                postMessageOnCurrentThread(message);
                return;
            }
            return;
        }
        if (id == UiUtils.getId("find_password") && checkEnterIsFit(trim, trim3, trim2, this.enter_email) && this.codeIsFit) {
            String trim4 = this.identfying_code.getText().toString().trim();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("userName", trim);
                jSONObject2.put("code", trim4);
                jSONObject2.put("password", trim3);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            Message message2 = new Message(KV.EVENT_FIND_PASSWORD_BY_EMAIL);
            message2.setObj(jSONObject2);
            postMessageOnCurrentThread(message2);
        }
    }

    private void buttonCountDown() {
        this.get_code.setEnabled(false);
        this.get_code.setBackgroundResource(UiUtils.getDrawable("sdk_button_hui"));
        this.mHandler.post(this.runable);
    }

    private boolean checkEnterIsFit(String str, String str2, String str3, EditText editText) {
        if (str == null || str.equals("")) {
            Toast.makeText(this, UiUtils.getResString("username_error_null"), 0).show();
            return false;
        }
        if (str2 == null || str2.equals("")) {
            Toast.makeText(this, UiUtils.getResString("new_password_error_null"), 0).show();
            return false;
        }
        if (str3 != null && !str3.equals("")) {
            return true;
        }
        Toast.makeText(this, UiUtils.getResString("phone_number_error_null"), 0).show();
        return false;
    }

    @Override // com.kqg.main.base.BaseActivity, com.kqg.main.base.UiHandler.IHandler
    public void handleMessage(android.os.Message message) {
        int i = message.what;
        if (i == 1020) {
            Log.e("DZ", "发送验证码成功的消息回来了。。。。。");
            this.user_name.setEnabled(false);
            this.enter_email.setEnabled(false);
            this.new_password.setEnabled(false);
            this.identfying_code.setEnabled(true);
            return;
        }
        if (i == 1021) {
            finishAllActivities();
        } else {
            if (i != 9024) {
                return;
            }
            Toast.makeText(this, message.obj.toString(), 0).show();
        }
    }
}
