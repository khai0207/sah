package com.kqg.main.activity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.model.Message;
import com.kqg.main.utils.UiUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BindToEnterPassword extends BaseActivity {
    public static Activity mMainActivity;
    EditText edit_user_password;
    Button pwd_close;
    Button sure_bt;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_enter_password");
        mMainActivity = this;
        this.sure_bt = (Button) getView("sure_bt");
        this.pwd_close = (Button) getView("pwd_close");
        this.edit_user_password = (EditText) getView("edit_user_password");
        registOnClicks("click", this.pwd_close, this.sure_bt);
        registBindRemoveList();
        registRemoveList();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
            return;
        }
        if (id == UiUtils.getId("sure_bt")) {
            String str = QuickLoginActivity.userName;
            String trim = this.edit_user_password.getText().toString().trim();
            if (IsFit(trim)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("userName", str);
                    jSONObject.put("password", trim);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Message message = new Message(1012);
                message.setObj(jSONObject);
                postMessageOnCurrentThread(message);
            }
        }
    }

    private boolean IsFit(String str) {
        if (str.equals("") || str == null) {
            Toast.makeText(this, UiUtils.getResString("enter_password_null_error"), 0).show();
            return false;
        }
        int length = str.length();
        if (length >= 6 && length <= 11) {
            return true;
        }
        Toast.makeText(this, UiUtils.getResString("password_error_input"), 0).show();
        this.edit_user_password.setText("");
        return false;
    }
}
