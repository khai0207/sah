package com.kqg.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.constant.KV;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.Login;
import com.kqg.main.model.Message;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.view.DropEditText;
import com.kqg.main.view.WrapListViewAdapter;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class QuickLoginActivity extends BaseActivity {
    private static DropEditText edit_user_name = null;
    public static String passWord = "";
    public static User user1 = null;
    public static String userName = "";
    private Button bind_email;
    private Button bind_phone;
    private Button btn_login;
    private Button btn_register;
    private TextView forget_password;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_quicklogin");
        this.btn_register = (Button) getView("btn_register");
        this.btn_login = (Button) getView("btn_login");
        this.bind_phone = (Button) getView("bind_phone");
        this.bind_email = (Button) getView("bind_email");
        this.forget_password = (TextView) getView("forget_password");
        DropEditText dropEditText = (DropEditText) getView("edit_user_name");
        edit_user_name = dropEditText;
        dropEditText.getEditText().setEnabled(false);
        registOnClicks("click", this.btn_register, this.btn_login, this.bind_phone, this.forget_password);
        List<User> users = UserManager.getInstance().getUsers();
        edit_user_name.setAdapter(new WrapListViewAdapter(users, this));
        if (users != null && users.size() >= 1) {
            Collections.reverse(users);
            String username = users.get(0).getUsername();
            String uid = users.get(0).getUid();
            edit_user_name.getEditText().setText(username);
            edit_user_name.getEditText().setTag(uid);
        }
        registRemoveList();
    }

    @Override // com.kqg.main.base.BaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        resumeSetHandler();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("btn_register")) {
            postMessageOnCurrentThread(new Message(KV.EVENT_SHOW_REGIST));
            return;
        }
        if (id == UiUtils.getId("bind_phone")) {
            List<User> users = UserManager.getInstance().getUsers();
            if (users == null || users.size() < 1) {
                return;
            }
            Collections.reverse(users);
            user1 = UserManager.getInstance().getUserById((String) edit_user_name.getEditText().getTag());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userName", user1.getUsername());
                jSONObject.put("password", user1.getPassword());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Message message = new Message(1012);
            message.setObj(jSONObject);
            postMessageOnCurrentThread(message);
            return;
        }
        if (id == UiUtils.getId("bind_email")) {
            return;
        }
        if (id == UiUtils.getId("forget_password")) {
            showExitDialog();
            return;
        }
        List<User> users2 = UserManager.getInstance().getUsers();
        if (users2 == null || users2.size() < 1) {
            return;
        }
        Collections.reverse(users2);
        user1 = UserManager.getInstance().getUserById((String) edit_user_name.getEditText().getTag());
        if (id == UiUtils.getId("btn_login")) {
            Login login = new Login();
            login.setData(user1);
            Log.e("54613", login.toString());
            BackGroundMessage backGroundMessage = new BackGroundMessage(1001);
            backGroundMessage.setEntity(login);
            postMessageOnBackgroundThread(backGroundMessage);
        }
    }

    public static User getUser() {
        return user1;
    }

    public static User getUser1() {
        Collections.reverse(UserManager.getInstance().getUsers());
        return UserManager.getInstance().getUserById((String) edit_user_name.getEditText().getTag());
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this).setTitle("Mẹo").setMessage(UiUtils.getResString("find_password_by_helper2")).setPositiveButton("Xác nhận", (DialogInterface.OnClickListener) null).show();
    }
}
