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
public class BindActivity extends BaseActivity {
    protected static UiHandler handler;
    private static KaiQiGuSdk sdk;
    private EditText enter_phone;
    private Button get_code;
    private EditText identfying_code;
    private Handler mHandler;
    private Button pwd_close;
    private Button sure_bt;
    private boolean emailIsFit = false;
    private boolean codeIsFit = false;
    int time = 0;
    private Runnable runable = new Runnable() { // from class: com.kqg.main.activity.BindActivity.2
        @Override // java.lang.Runnable
        public void run() {
            BindActivity.this.get_code.setText(BindActivity.this.time + ThreeMap.type_string);
            BindActivity.this.get_code.setTextSize(12.0f);
            BindActivity.this.get_code.setTextColor(-1);
            BindActivity.this.time--;
            if (BindActivity.this.time != -1) {
                BindActivity.this.mHandler.postDelayed(BindActivity.this.runable, 1000L);
                return;
            }
            Log.e("DZ", "时间到了.............");
            BindActivity.this.get_code.setEnabled(true);
            BindActivity.this.get_code.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
            BindActivity.this.get_code.setText(UiUtils.getResString("find_password_get_code_des"));
            BindActivity.this.get_code.setTextSize(10.0f);
            BindActivity.this.get_code.setTextColor(-1);
            BindActivity.this.mHandler.removeCallbacks(BindActivity.this.runable);
        }
    };

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_bind_phone");
        this.pwd_close = (Button) getView("pwd_close");
        this.sure_bt = (Button) getView("sure_bt");
        this.get_code = (Button) getView("get_code");
        this.identfying_code = (EditText) getView("identfying_code");
        this.enter_phone = (EditText) getView("enter_phone");
        this.identfying_code.setEnabled(false);
        this.sure_bt.setEnabled(false);
        registRemoveList();
        registBindRemoveList();
        registOnClicks("click", this.pwd_close, this.sure_bt, this.get_code);
        this.identfying_code.addTextChangedListener(new TextWatcher() { // from class: com.kqg.main.activity.BindActivity.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    BindActivity.this.codeIsFit = true;
                    BindActivity.this.sure_bt.setEnabled(true);
                    BindActivity.this.sure_bt.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
                } else {
                    BindActivity.this.codeIsFit = false;
                    BindActivity.this.sure_bt.setEnabled(false);
                    BindActivity.this.sure_bt.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
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
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
            return;
        }
        if (id == UiUtils.getId("get_code")) {
            String trim = this.enter_phone.getText().toString().trim();
            if (phoneIsFit(trim, this.enter_phone)) {
                this.time = 15;
                buttonCountDown();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("phone", trim);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Message message = new Message(1013);
                message.setObj(jSONObject);
                postMessageOnCurrentThread(message);
                return;
            }
            return;
        }
        if (id == UiUtils.getId("sure_bt")) {
            String trim2 = this.identfying_code.getText().toString().trim();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("code", trim2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            Message message2 = new Message(KV.EVENT_BIND_ACCOUNT_BY_PHONE);
            message2.setObj(jSONObject2);
            postMessageOnCurrentThread(message2);
            return;
        }
        Toast.makeText(this, UiUtils.getResString("enter_email_null_error"), 0).show();
    }

    private void buttonCountDown() {
        this.get_code.setEnabled(false);
        this.get_code.setBackgroundResource(UiUtils.getDrawable("sdk_button_yellow"));
        this.mHandler.post(this.runable);
    }

    private boolean phoneIsFit(String str, EditText editText) {
        if (!str.equals("")) {
            return true;
        }
        Toast.makeText(this, UiUtils.getResString("enter_phone_null_error"), 0).show();
        return false;
    }

    @Override // com.kqg.main.base.BaseActivity, com.kqg.main.base.UiHandler.IHandler
    public void handleMessage(android.os.Message message) {
        int i = message.what;
        if (i == 1024) {
            finishBindActivities();
            return;
        }
        if (i == 1035) {
            finishBindActivities();
            return;
        }
        if (i == 1044) {
            this.identfying_code.setEnabled(true);
            this.enter_phone.setEnabled(false);
        } else {
            if (i != 9024) {
                return;
            }
            Toast.makeText(this, message.obj.toString(), 0).show();
        }
    }
}
