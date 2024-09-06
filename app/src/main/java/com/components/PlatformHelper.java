package com.components;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.os.EnvironmentCompat;
import com.alipay.sdk.m.l.e;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.video.VideoView;
import com.video.VideoViewInterface;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class PlatformHelper implements PlatformInterface {
    private static String TAG = "PlatformHelper";
    private ComponentProtocol callHandler;
    protected boolean isStartVideoPlayEnd = false;
    protected boolean isSDKInitSuccess = false;
    private boolean isGameCallSdkInit = false;
    private View startView = null;
    private VideoViewInterface viewInterface = new VideoViewInterface() { // from class: com.components.PlatformHelper.1
        @Override // com.video.VideoViewInterface
        public void onComplete() {
            PlatformHelper.this.dispatchMessageToProxy("playVideo", new JSONObject());
        }
    };

    @Override // com.components.PlatformInterface
    public void addToBlackList(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void biRecord(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void buyProduct(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void createNewPlayer(Activity activity, JSONObject jSONObject) {
    }

    public void createRole(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void enterChatRoom(Activity activity, JSONObject jSONObject) {
    }

    public void enterGame(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void enterMainGame(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void enterUserCenter(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void exitCharRoom(Activity activity, JSONObject jSONObject) {
    }

    public abstract String getPlatFormName();

    @Override // com.components.PlatformInterface
    public String getPlatformId() {
        return "kv_game";
    }

    @Override // com.components.PlatformInterface
    public void getUserName(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void isLoginChat(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void loginChat(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void needShowSDKBtn(Activity activity, JSONObject jSONObject) {
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    @Override // com.components.PlatformInterface
    public void onCharge(Activity activity, JSONObject jSONObject) {
    }

    public void onConfigurationChanged(Activity activity) {
    }

    public void onConfigurationChanged(Activity activity, Configuration configuration) {
    }

    public void onDestroy(Activity activity) {
    }

    @Override // com.components.PlatformInterface
    public void onLogin(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void onLogout(Activity activity, JSONObject jSONObject) {
    }

    public void onNewIntent(Activity activity, Intent intent) {
    }

    public void onPause(Activity activity) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
    }

    public void onRestart(Activity activity) {
    }

    public void onResume(Activity activity) {
    }

    public void onSaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onStart(Activity activity) {
    }

    public void onStop(Activity activity) {
    }

    public void onUserLevelChanged(Activity activity, JSONObject jSONObject) {
    }

    public void onWindowFocusChanged(Activity activity, boolean z) {
    }

    @Override // com.components.PlatformInterface
    public void paseVideo(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void platformExit(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void platformName(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void queryMsg(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void queryRoomMsg(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void queryTeamMsg(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void removeFromBlackList(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void resumeVideo(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void sendAllData(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void sendInfo(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void sendMsg(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void sendRoomMsg(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void showFloatBar(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformInterface
    public void showGameCenter(Activity activity, JSONObject jSONObject) {
    }

    public void weChatShare(Activity activity, JSONObject jSONObject) {
    }

    public ComponentProtocol getCallHandler() {
        return this.callHandler;
    }

    public void setCallHandler(ComponentProtocol componentProtocol) {
        this.callHandler = componentProtocol;
    }

    @Override // com.components.PlatformInterface
    public void init(Activity activity) {
        initPlatformSDK(activity);
    }

    @Override // com.components.PlatformInterface
    public void initPlatformSDK(Activity activity) {
        initPlatformSDKSuccess(activity);
    }

    public void pauseVideo(JSONObject jSONObject) {
        VideoView.pauseVideo();
    }

    public void resumeVideo(JSONObject jSONObject) {
        VideoView.resumeVideo();
    }

    @Override // com.components.PlatformInterface
    public void playVideo(Activity activity, JSONObject jSONObject) {
        Log.e("vedio", "????????????????????");
        try {
            String string = jSONObject.getString("video");
            int i = jSONObject.getInt("skip");
            Log.e("vedio", "name == " + string);
            VideoView.playVideo(string, activity, this.viewInterface, i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.components.PlatformInterface
    public void initPlatformSDKSuccess(Activity activity) {
        this.isSDKInitSuccess = true;
        doSdkInitSuccessStep(activity);
    }

    public void doSdkInitSuccessStep(Activity activity) {
        if (this.isStartVideoPlayEnd && this.isSDKInitSuccess && this.isGameCallSdkInit) {
            this.isGameCallSdkInit = false;
            sdkInitSuccessCallBack(activity);
        }
    }

    @Override // com.components.PlatformInterface
    public void sdkInitSuccessCallBack(Activity activity) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", true);
            jSONObject.put("noplatform", true);
            dispatchMessageToProxy("sdkInit", jSONObject);
        } catch (Exception unused) {
        }
    }

    @Override // com.components.PlatformInterface
    public void showStartLoadingView(Activity activity) {
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(0);
        try {
            linearLayout.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeStream(activity.getAssets().open("iphonehd/ccbResources/dl_login_bg.png"))));
        } catch (Exception unused) {
        }
        this.startView = linearLayout;
        if (linearLayout != null) {
            activity.addContentView(linearLayout, layoutParams);
        }
    }

    public void showStartLoadingView(Activity activity, String str) {
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(0);
        try {
            linearLayout.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeStream(activity.getAssets().open(str))));
        } catch (Exception unused) {
        }
        this.startView = linearLayout;
        if (linearLayout != null) {
            activity.addContentView(linearLayout, layoutParams);
        }
    }

    @Override // com.components.PlatformInterface
    public void hideStartLoadingView() {
        View view = this.startView;
        if (view != null) {
            view.setVisibility(8);
            this.startView = null;
        }
    }

    public void dispatchMessageToProxy(String str, JSONObject jSONObject) {
        ComponentProtocol componentProtocol = this.callHandler;
        if (componentProtocol != null) {
            componentProtocol.SendMessageToCpp(str, jSONObject);
        }
    }

    public void dispatchMessageToProxy1(String str, JSONObject jSONObject) {
        ComponentProtocol componentProtocol = this.callHandler;
        if (componentProtocol != null) {
            componentProtocol.SendMessage(str, jSONObject);
        }
    }

    public Boolean onKeyUp(Activity activity, int i, KeyEvent keyEvent) {
        if (i == 4) {
            exitGameByBackKey(activity);
            return true;
        }
        return false;
    }

    public Boolean onKeyDown(Activity activity, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.components.PlatformInterface
    public void exitGameByBackKey(final Activity activity) {
        new AlertDialog.Builder(activity).setTitle("退出").setIcon(R.drawable.ic_dialog_info).setMessage("确定退出游戏?").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.components.PlatformHelper.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
                System.exit(0);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.components.PlatformHelper.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).create().show();
    }

    public void initPlatformFailed(final Activity activity) {
        new AlertDialog.Builder(activity).setTitle("提示").setIcon(R.drawable.ic_dialog_info).setMessage("平台授权失败，请确认有网络连接!").setPositiveButton("退出", new DialogInterface.OnClickListener() { // from class: com.components.PlatformHelper.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
                System.exit(0);
            }
        }).setNegativeButton("重试", new DialogInterface.OnClickListener() { // from class: com.components.PlatformHelper.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                PlatformHelper.this.initPlatformSDK(activity);
            }
        }).create().show();
    }

    public String getVersion(Activity activity) {
        try {
            return activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String trim(String str) {
        if (str == null) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return str.replaceAll(" ", "_");
    }

    @Override // com.components.PlatformInterface
    public void getPlatForm(Activity activity, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("platform", trim(getPlatFormName()));
            jSONObject2.put("version", trim(getVersion(activity)));
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService("phone");
            jSONObject2.put("deviceid", trim(telephonyManager.getDeviceId()));
            jSONObject2.put(e.p, trim(Build.MODEL));
            jSONObject2.put("sysversion", trim(telephonyManager.getDeviceSoftwareVersion()));
            dispatchMessageToProxy("getPlatForm", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.components.PlatformInterface
    public void sdkInit(Activity activity, JSONObject jSONObject) {
        this.isGameCallSdkInit = true;
        doSdkInitSuccessStep(activity);
    }

    @Override // com.components.PlatformInterface
    public void logIn(Activity activity, JSONObject jSONObject) {
        dispatchMessageToProxy("logIn", new JSONObject());
    }

    @Override // com.components.PlatformInterface
    public void logInResult(Activity activity, JSONObject jSONObject) {
        dispatchMessageToProxy("logInResult", new JSONObject());
    }

    @Override // com.components.PlatformInterface
    public void sdkSwitchAccount(Activity activity, JSONObject jSONObject) {
        dispatchMessageToProxy("sdkSwitchAccount", new JSONObject());
    }

    @Override // com.components.PlatformInterface
    public void logOut(Activity activity, JSONObject jSONObject) {
        dispatchMessageToProxy("logOut", new JSONObject());
    }

    @Override // com.components.PlatformInterface
    public void isLogined(Activity activity, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(TransferTable.COLUMN_STATE, "1");
            dispatchMessageToProxy("isLogined", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.components.PlatformInterface
    public void exitGame(Activity activity, JSONObject jSONObject) {
        activity.finish();
    }

    @Override // com.components.PlatformInterface
    public void checkUpdate(Activity activity, JSONObject jSONObject) {
        dispatchMessageToProxy("checkUpdate", new JSONObject());
    }

    public void switchAccount(Activity activity, JSONObject jSONObject) {
        dispatchMessageToProxy("switchAccount", new JSONObject());
    }

    public void weChatShareResp(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", i);
            jSONObject.put("errorMsg", str);
            dispatchMessageToProxy("weChatShare", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void userChanged() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("loginresult", "ok");
            dispatchMessageToProxy("userlogOut", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, e.getMessage());
        }
    }

    @Override // com.components.PlatformInterface
    public void setGameServer(Activity activity, JSONObject jSONObject) {
        Log.e("setGameServer", jSONObject.toString());
        try {
            jSONObject.getString("server");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
