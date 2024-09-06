package com.kqg.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.constant.KV;
import com.kqg.main.model.Message;
import com.kqg.main.utils.PermissionsManager;

/* loaded from: classes.dex */
public class CheckAppPermissionsActivity extends BaseActivity {
    PermissionsManager mPermissionsManager;
    int permissionsCode = 1;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        oncreate();
    }

    private void oncreate() {
        PermissionsManager permissionsManager = new PermissionsManager(this) { // from class: com.kqg.main.activity.CheckAppPermissionsActivity.1
            @Override // com.kqg.main.utils.PermissionsManager
            public void authorized(int i) {
                CheckAppPermissionsActivity.this.startActivity(new Intent(CheckAppPermissionsActivity.this, (Class<?>) InitActivity.class));
                KaiQiGuSdk.getInstance().fillDeviceInfor();
                CheckAppPermissionsActivity.this.finish();
            }

            @Override // com.kqg.main.utils.PermissionsManager
            public void noAuthorization(int i, String[] strArr) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckAppPermissionsActivity.this);
                builder.setTitle("提示");
                builder.setMessage("缺少需要的权限！");
                builder.setPositiveButton("前往设置权限", new DialogInterface.OnClickListener() { // from class: com.kqg.main.activity.CheckAppPermissionsActivity.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        PermissionsManager.startAppSettings(CheckAppPermissionsActivity.this.getApplicationContext());
                        CheckAppPermissionsActivity.postMessageOnCurrentThread(new Message(KV.INIT_ERROR));
                        CheckAppPermissionsActivity.this.finish();
                    }
                });
                builder.create().show();
            }

            @Override // com.kqg.main.utils.PermissionsManager
            public void ignore() {
                CheckAppPermissionsActivity.this.startActivity(new Intent(CheckAppPermissionsActivity.this, (Class<?>) InitActivity.class));
                KaiQiGuSdk.getInstance().fillDeviceInfor();
                CheckAppPermissionsActivity.this.finish();
            }
        };
        this.mPermissionsManager = permissionsManager;
        permissionsManager.checkPermissions(this.permissionsCode, new String[0]);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.mPermissionsManager.recheckPermissions(i, strArr, iArr);
    }
}
