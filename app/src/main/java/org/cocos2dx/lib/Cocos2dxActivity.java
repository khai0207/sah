package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import org.cocos2dx.lib.Cocos2dxHandler;
import org.cocos2dx.lib.Cocos2dxHelper;

/* loaded from: classes.dex */
public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelper.Cocos2dxHelperListener {
    private static final String TAG = Cocos2dxActivity.class.getSimpleName();
    private static Context sContext = null;
    private Cocos2dxGLSurfaceView mGLSurfaceView;
    private Cocos2dxHandler mHandler;

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public String getChannel() {
        return "";
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void loadDataWebView(String str, String str2, String str3) {
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void loadUrlWebView(String str, String str2, String str3) {
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showWebView(String str, String str2, int i, int i2, int i3, int i4) {
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void visibleWebView(String str, String str2) {
    }

    public static Context getContext() {
        return sContext;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        init();
        Cocos2dxHelper.init(this, this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Cocos2dxHelper.onResume();
        this.mGLSurfaceView.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showDialog(String str, String str2) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Cocos2dxHandler.DialogMessage(str, str2);
        this.mHandler.sendMessage(message);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4) {
        Message message = new Message();
        message.what = 2;
        message.obj = new Cocos2dxHandler.EditBoxMessage(str, str2, i, i2, i3, i4);
        this.mHandler.sendMessage(message);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void notificationMsg(int i, String str, String str2, int i2, boolean z) {
        Log.i(TransferService.INTENT_KEY_NOTIFICATION, "notificationMsg notifId === " + i + " ; msg == " + str2 + " ; secont = " + i2);
        LocalNotificationManager.scheduleLocalNotification(i, str2, getApplicationName(), str2, i2);
    }

    public String getApplicationName() {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void removeNotificationById(int i) {
        Log.i(TransferService.INTENT_KEY_NOTIFICATION, "removeNotificationById notifId === " + i);
        LocalNotificationManager.cancelLocalNotification(i);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void runOnGLThread(Runnable runnable) {
        this.mGLSurfaceView.queueEvent(runnable);
    }

    public void init() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
        Cocos2dxEditText cocos2dxEditText = new Cocos2dxEditText(this);
        cocos2dxEditText.setLayoutParams(layoutParams2);
        frameLayout.addView(cocos2dxEditText);
        Cocos2dxGLSurfaceView onCreateView = onCreateView();
        this.mGLSurfaceView = onCreateView;
        frameLayout.addView(onCreateView);
        if (isAndroidEmulator()) {
            this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        }
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(cocos2dxEditText);
        setContentView(frameLayout);
    }

    public Cocos2dxGLSurfaceView onCreateView() {
        return new Cocos2dxGLSurfaceView(this);
    }

    private static final boolean isAndroidEmulator() {
        String str = Build.MODEL;
        Log.d(TAG, "model=" + str);
        String str2 = Build.PRODUCT;
        Log.d(TAG, "product=" + str2);
        boolean z = false;
        if (str2 != null && (str2.equals("sdk") || str2.contains("_sdk") || str2.contains("sdk_"))) {
            z = true;
        }
        Log.d(TAG, "isEmulator=" + z);
        return z;
    }
}
