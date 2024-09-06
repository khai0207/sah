package com.kqg.main.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.CommonEntity;
import com.kqg.main.model.Message;
import com.kqg.main.utils.UiUtils;
import java.lang.Character;

/* loaded from: classes.dex */
public class CertificationActivity extends BaseActivity {
    private static EditText edit_identify;
    private static EditText edit_true_name;
    private BaseActivity base;
    private Button btn_idenfity;
    private Button pwd_close;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_certificationuser");
        this.base = new BaseActivity();
        edit_true_name = (EditText) getView("edit_true_name");
        edit_identify = (EditText) getView("edit_identify");
        this.btn_idenfity = (Button) getView("btn_idenfity");
        this.pwd_close = (Button) getView("pwd_close");
        registOnClicks("click", this.btn_idenfity, edit_true_name, edit_identify);
        getIntent();
        registRemoveList();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("btn_idenfity")) {
            boolean isChinese = isChinese(edit_true_name.getText().toString());
            boolean z = edit_identify.getText().toString().length() == 18;
            if (!isChinese) {
                edit_true_name.setText("");
                edit_identify.setText("");
                edit_true_name.setFocusable(true);
                UiUtils.showImageToast(UiUtils.getResString("name_is_error"));
                return;
            }
            if (!z) {
                edit_true_name.setText("");
                edit_identify.setText("");
                edit_true_name.setFocusable(true);
                UiUtils.showImageToast(UiUtils.getResString("idenfity_is_error"));
                return;
            }
            CommonEntity commonEntity = new CommonEntity();
            BackGroundMessage backGroundMessage = new BackGroundMessage(KV.EVENT_CERTIFICATION);
            backGroundMessage.setEntity(commonEntity);
            postMessageOnBackgroundThread(backGroundMessage);
            return;
        }
        if (id == UiUtils.getId("pwd_close")) {
            this.base.finishAllActivities();
            postMessageOnCurrentThread(new Message(1008));
        }
    }

    public static String[] getCertificationData() {
        return new String[]{edit_true_name.getText().toString(), edit_identify.getText().toString()};
    }

    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        char[] charArray = str.toCharArray();
        int i = 0;
        boolean z = false;
        while (i < charArray.length) {
            Character.UnicodeBlock of = Character.UnicodeBlock.of(charArray[i]);
            if (of != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS && of != Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS && of != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A && of != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B && of != Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION && of != Character.UnicodeBlock.GENERAL_PUNCTUATION) {
                return false;
            }
            i++;
            z = true;
        }
        return z;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        this.base.finishAllActivities();
        KaiQiGuSdk.getInstance().goToLogin();
    }
}
