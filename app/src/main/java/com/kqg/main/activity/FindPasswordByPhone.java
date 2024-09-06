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
public class FindPasswordByPhone extends BaseActivity {
    protected static UiHandler handler;
    private static KaiQiGuSdk sdk;
    private EditText enter_phone;
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
    private Runnable runable = new Runnable() { // from class: com.kqg.main.activity.FindPasswordByPhone.2
        @Override // java.lang.Runnable
        public void run() {
            FindPasswordByPhone.this.get_code.setText(FindPasswordByPhone.this.time + ThreeMap.type_string);
            FindPasswordByPhone.this.get_code.setTextSize(12.0f);
            FindPasswordByPhone.this.get_code.setTextColor(-1);
            FindPasswordByPhone.this.time--;
            if (FindPasswordByPhone.this.time != -1) {
                FindPasswordByPhone.this.mHandler.postDelayed(FindPasswordByPhone.this.runable, 1000L);
                return;
            }
            Log.e("DZ", "时间到了.............");
            FindPasswordByPhone.this.get_code.setEnabled(true);
            FindPasswordByPhone.this.get_code.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
            FindPasswordByPhone.this.get_code.setText(UiUtils.getResString("find_password_get_code_des"));
            FindPasswordByPhone.this.get_code.setTextSize(10.0f);
            FindPasswordByPhone.this.get_code.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            FindPasswordByPhone.this.mHandler.removeCallbacks(FindPasswordByPhone.this.runable);
        }
    };

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_findpwy_byphone");
        this.pwd_close = (Button) getView("pwd_close");
        this.get_code = (Button) getView("get_code");
        this.find_password = (Button) getView("find_password");
        this.user_name = (EditText) getView("user_name");
        this.enter_phone = (EditText) getView("enter_phone");
        this.new_password = (EditText) getView("new_password");
        EditText editText = (EditText) getView("identfying_code");
        this.identfying_code = editText;
        editText.setEnabled(false);
        this.find_password.setEnabled(false);
        registOnClicks("click", this.pwd_close, this.get_code, this.find_password);
        registRemoveList();
        this.identfying_code.addTextChangedListener(new TextWatcher() { // from class: com.kqg.main.activity.FindPasswordByPhone.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    FindPasswordByPhone.this.codeIsFit = true;
                    FindPasswordByPhone.this.find_password.setEnabled(true);
                    FindPasswordByPhone.this.find_password.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
                } else {
                    FindPasswordByPhone.this.codeIsFit = false;
                    FindPasswordByPhone.this.find_password.setEnabled(false);
                    FindPasswordByPhone.this.find_password.setBackgroundResource(UiUtils.getDrawable("sdk_button_hui"));
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
        String trim2 = this.enter_phone.getText().toString().trim();
        String trim3 = this.new_password.getText().toString().trim();
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
            return;
        }
        if (id == UiUtils.getId("get_code")) {
            if (checkEnterIsFit(trim, trim3, trim2, this.enter_phone)) {
                this.time = 15;
                buttonCountDown();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("userName", trim);
                    jSONObject.put("phone", trim2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Message message = new Message(KV.EVENT_FIND_PSD_GET_CODE);
                message.setObj(jSONObject);
                postMessageOnCurrentThread(message);
                return;
            }
            return;
        }
        if (id == UiUtils.getId("find_password") && checkEnterIsFit(trim, trim3, trim2, this.enter_phone) && this.codeIsFit) {
            String trim4 = this.identfying_code.getText().toString().trim();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("userName", trim);
                jSONObject2.put("code", trim4);
                jSONObject2.put("password", trim3);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            Message message2 = new Message(KV.EVENT_FIND_PASSWORD_BY_PHONE);
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
        if (str3 == null || str3.equals("")) {
            Toast.makeText(this, UiUtils.getResString("phone_number_error_null"), 0).show();
            return false;
        }
        int length = str.length();
        int length2 = str2.length();
        int length3 = str3.length();
        if (length < 6 || length >= 30) {
            Toast.makeText(this, UiUtils.getResString("username_error_input"), 0).show();
            this.user_name.setText("");
            this.new_password.setText("");
            this.enter_phone.setText("");
            return false;
        }
        if (length2 < 6 || length2 > 10) {
            Toast.makeText(this, UiUtils.getResString("password_error_input"), 0).show();
            this.new_password.setText("");
            return false;
        }
        if (length3 == 11) {
            return true;
        }
        Toast.makeText(this, UiUtils.getResString("phone_number_error_input"), 0).show();
        this.enter_phone.setText("");
        return false;
    }

    @Override // com.kqg.main.base.BaseActivity, com.kqg.main.base.UiHandler.IHandler
    public void handleMessage(android.os.Message message) {
        int i = message.what;
        if (i == 1020) {
            Log.e("DZ", "发送验证码成功的消息回来了。。。。。");
            this.user_name.setEnabled(false);
            this.enter_phone.setEnabled(false);
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
