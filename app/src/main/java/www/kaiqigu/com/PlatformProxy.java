package www.kaiqigu.com;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.components.ComponentProtocol;
import com.components.ComponentStatistics;
import com.components.PlatformHelper;
import com.components.StatisticsHelper;
import com.components.StatisticsInterface;
import com.easyndk.classes.AndroidNDKHelper;
import com.umeng.analytics.MobclickAgent;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PlatformProxy implements ComponentProtocol {
    private static PlatformProxy proxy;
    public JinQu activity;
    Activity context;
    int[] grantResults;
    String[] permissions;
    int requestCode;
    private View startView;
    private PlatformHelper platformHelper = null;
    private StatisticsHelper statisticsHandler = null;
    private String TAG = "PlatformProxy";

    @Override // com.components.ComponentProtocol
    public void SendMessage(String str, JSONObject jSONObject) {
    }

    private PlatformProxy() {
    }

    public static PlatformProxy getInstance() {
        if (proxy == null) {
            proxy = new PlatformProxy();
        }
        return proxy;
    }

    public String getMetaDataValue(String str) {
        Object obj = null;
        try {
            ApplicationInfo applicationInfo = this.activity.getPackageManager().getApplicationInfo(this.activity.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                obj = applicationInfo.metaData.get(str);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("getMetadata", str + "not found");
        }
        return obj == null ? "" : obj.toString();
    }

    public String getPlatformChannel() {
        return getMetaDataValue("PLATFORM_CHANNEL");
    }

    public void initPlatform() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        AndroidNDKHelper.SetNDKReciever(this);
        int i = 0;
        try {
            try {
                try {
                    try {
                        try {
                            String metaDataValue = getMetaDataValue("PlatformClass");
                            Log.e("noplatform", metaDataValue);
                            if (metaDataValue != "") {
                                PlatformHelper platformHelper = (PlatformHelper) Class.forName(metaDataValue).newInstance();
                                this.platformHelper = platformHelper;
                                platformHelper.setCallHandler(this);
                                this.platformHelper.init(this.activity);
                            }
                            this.statisticsHandler = new StatisticsHelper();
                            while (true) {
                                if (i > 0) {
                                    str6 = "StatisticsClass" + i;
                                } else {
                                    str6 = "StatisticsClass";
                                }
                                String metaDataValue2 = getMetaDataValue(str6);
                                if (metaDataValue2 != null && metaDataValue2 != "") {
                                    i++;
                                    this.statisticsHandler.addComponent((ComponentStatistics) Class.forName(metaDataValue2).newInstance());
                                    String metaDataValue3 = getMetaDataValue("PLATFORM_CHANNEL_EXT");
                                    if (metaDataValue3.equals("")) {
                                        metaDataValue3 = getMetaDataValue("PLATFORM_CHANNEL");
                                    }
                                    if (this.platformHelper != null) {
                                        this.statisticsHandler.initChannel(this.activity, metaDataValue3);
                                    }
                                }
                                return;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            this.statisticsHandler = new StatisticsHelper();
                            while (true) {
                                if (i > 0) {
                                    str2 = "StatisticsClass" + i;
                                } else {
                                    str2 = "StatisticsClass";
                                }
                                String metaDataValue4 = getMetaDataValue(str2);
                                if (metaDataValue4 != null && metaDataValue4 != "") {
                                    i++;
                                    this.statisticsHandler.addComponent((ComponentStatistics) Class.forName(metaDataValue4).newInstance());
                                    String metaDataValue5 = getMetaDataValue("PLATFORM_CHANNEL_EXT");
                                    if (metaDataValue5.equals("")) {
                                        metaDataValue5 = getMetaDataValue("PLATFORM_CHANNEL");
                                    }
                                    if (this.platformHelper != null) {
                                        this.statisticsHandler.initChannel(this.activity, metaDataValue5);
                                    }
                                }
                                return;
                            }
                        } catch (NullPointerException unused) {
                            this.statisticsHandler = new StatisticsHelper();
                            while (true) {
                                if (i > 0) {
                                    str = "StatisticsClass" + i;
                                } else {
                                    str = "StatisticsClass";
                                }
                                String metaDataValue6 = getMetaDataValue(str);
                                if (metaDataValue6 != null && metaDataValue6 != "") {
                                    i++;
                                    this.statisticsHandler.addComponent((ComponentStatistics) Class.forName(metaDataValue6).newInstance());
                                    String metaDataValue7 = getMetaDataValue("PLATFORM_CHANNEL_EXT");
                                    if (metaDataValue7.equals("")) {
                                        metaDataValue7 = getMetaDataValue("PLATFORM_CHANNEL");
                                    }
                                    if (this.platformHelper != null) {
                                        this.statisticsHandler.initChannel(this.activity, metaDataValue7);
                                    }
                                }
                                return;
                            }
                        }
                    } catch (InstantiationException e2) {
                        e2.printStackTrace();
                        this.statisticsHandler = new StatisticsHelper();
                        while (true) {
                            if (i > 0) {
                                str3 = "StatisticsClass" + i;
                            } else {
                                str3 = "StatisticsClass";
                            }
                            String metaDataValue8 = getMetaDataValue(str3);
                            if (metaDataValue8 != null && metaDataValue8 != "") {
                                i++;
                                this.statisticsHandler.addComponent((ComponentStatistics) Class.forName(metaDataValue8).newInstance());
                                String metaDataValue9 = getMetaDataValue("PLATFORM_CHANNEL_EXT");
                                if (metaDataValue9.equals("")) {
                                    metaDataValue9 = getMetaDataValue("PLATFORM_CHANNEL");
                                }
                                if (this.platformHelper != null) {
                                    this.statisticsHandler.initChannel(this.activity, metaDataValue9);
                                }
                            }
                            return;
                        }
                    }
                } catch (ClassNotFoundException e3) {
                    e3.printStackTrace();
                    this.statisticsHandler = new StatisticsHelper();
                    while (true) {
                        if (i > 0) {
                            str4 = "StatisticsClass" + i;
                        } else {
                            str4 = "StatisticsClass";
                        }
                        String metaDataValue10 = getMetaDataValue(str4);
                        if (metaDataValue10 != null && metaDataValue10 != "") {
                            i++;
                            this.statisticsHandler.addComponent((ComponentStatistics) Class.forName(metaDataValue10).newInstance());
                            String metaDataValue11 = getMetaDataValue("PLATFORM_CHANNEL_EXT");
                            if (metaDataValue11.equals("")) {
                                metaDataValue11 = getMetaDataValue("PLATFORM_CHANNEL");
                            }
                            if (this.platformHelper != null) {
                                this.statisticsHandler.initChannel(this.activity, metaDataValue11);
                            }
                        }
                        return;
                    }
                }
            } catch (Throwable th) {
                this.statisticsHandler = new StatisticsHelper();
                while (true) {
                    if (i > 0) {
                        try {
                            str5 = "StatisticsClass" + i;
                        } catch (ClassNotFoundException e4) {
                            e4.printStackTrace();
                            throw th;
                        } catch (IllegalAccessException e5) {
                            e5.printStackTrace();
                            throw th;
                        } catch (InstantiationException e6) {
                            e6.printStackTrace();
                            throw th;
                        } catch (NullPointerException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    } else {
                        str5 = "StatisticsClass";
                    }
                    String metaDataValue12 = getMetaDataValue(str5);
                    if (metaDataValue12 == null || metaDataValue12 == "") {
                        break;
                    }
                    i++;
                    this.statisticsHandler.addComponent((ComponentStatistics) Class.forName(metaDataValue12).newInstance());
                    String metaDataValue13 = getMetaDataValue("PLATFORM_CHANNEL_EXT");
                    if (metaDataValue13.equals("")) {
                        metaDataValue13 = getMetaDataValue("PLATFORM_CHANNEL");
                    }
                    if (this.platformHelper != null) {
                        this.statisticsHandler.initChannel(this.activity, metaDataValue13);
                    }
                }
                throw th;
            }
        } catch (ClassNotFoundException e8) {
            e8.printStackTrace();
        } catch (IllegalAccessException e9) {
            e9.printStackTrace();
        } catch (InstantiationException e10) {
            e10.printStackTrace();
        } catch (NullPointerException e11) {
            e11.printStackTrace();
        }
    }

    public PlatformHelper getPlatformHelper() {
        return this.platformHelper;
    }

    public void setActivity(JinQu jinQu) {
        this.activity = jinQu;
    }

    public void onResume(Activity activity) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onResume(activity);
        }
        StatisticsHelper statisticsHelper = this.statisticsHandler;
        if (statisticsHelper != null) {
            statisticsHelper.onResume(activity);
        }
    }

    public void onStart(Activity activity) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onStart(activity);
        }
    }

    public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onRequestPermissionsResult(activity, i, strArr, iArr);
        }
    }

    public void onRestart(Activity activity) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onRestart(activity);
        }
    }

    public void onDestroy(Activity activity) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onDestroy(activity);
        }
    }

    public Boolean onKeyUp(Activity activity, int i, KeyEvent keyEvent) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            return platformHelper.onKeyUp(activity, i, keyEvent);
        }
        return false;
    }

    public void onStop(Activity activity) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onStop(activity);
        }
    }

    public void onPause(Activity activity) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onPause(activity);
        }
        StatisticsHelper statisticsHelper = this.statisticsHandler;
        if (statisticsHelper != null) {
            statisticsHelper.onPause(activity);
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onActivityResult(activity, i, i2, intent);
        }
    }

    public void onSaveInstanceState(Activity activity, Bundle bundle) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onSaveInstanceState(activity, bundle);
        }
    }

    public void onNewIntent(Activity activity, Intent intent) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onNewIntent(activity, intent);
        }
    }

    /* renamed from: www.kaiqigu.com.PlatformProxy$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String val$method;
        final /* synthetic */ JSONObject val$params;

        AnonymousClass1(String str, JSONObject jSONObject) {
            r2 = str;
            r3 = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            AndroidNDKHelper.SendMessageWithParameters(r2, r3);
        }
    }

    @Override // com.components.ComponentProtocol
    public void SendMessageToCpp(String str, JSONObject jSONObject) {
        this.activity.runOnGLThread(new Runnable() { // from class: www.kaiqigu.com.PlatformProxy.1
            final /* synthetic */ String val$method;
            final /* synthetic */ JSONObject val$params;

            AnonymousClass1(String str2, JSONObject jSONObject2) {
                r2 = str2;
                r3 = jSONObject2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AndroidNDKHelper.SendMessageWithParameters(r2, r3);
            }
        });
    }

    public void getPlatForm(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.getPlatForm(this.activity, jSONObject);
        }
    }

    public void sdkInit(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.sdkInit(this.activity, jSONObject);
        }
    }

    public void logIn(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.hideStartLoadingView();
            this.platformHelper.logIn(this.activity, jSONObject);
        }
    }

    public void enterMainGame(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.enterMainGame(this.activity, jSONObject);
        }
    }

    public void sendInfo(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.sendInfo(this.activity, jSONObject);
        }
    }

    public void logInResult(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.logInResult(this.activity, jSONObject);
        }
    }

    public void logOut(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.logOut(this.activity, jSONObject);
        }
    }

    public void isLogined(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.isLogined(this.activity, jSONObject);
        }
    }

    public void onLogin(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onLogin(this.activity, jSONObject);
        }
    }

    public void showFloatBar(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.showFloatBar(this.activity, jSONObject);
        }
    }

    public void onLogout(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onLogout(this.activity, jSONObject);
        }
    }

    public void onUserLevelChanged(JSONObject jSONObject) {
        ((StatisticsInterface) this.platformHelper).onUserLevelChangeToBi(this.activity, jSONObject);
        this.statisticsHandler.onUserLevelChangeToBi(this.activity, jSONObject);
    }

    public void exitGame(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.exitGame(this.activity, jSONObject);
        }
    }

    public void getUserName(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.getUserName(this.activity, jSONObject);
        }
    }

    public void onCharge(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.onCharge(this.activity, jSONObject);
        }
    }

    public void setGameServer(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.setGameServer(this.activity, jSONObject);
        }
    }

    public void paySuccess(JSONObject jSONObject) {
        this.statisticsHandler.paySuccessToBi(this.activity, jSONObject);
    }

    public void platformExit(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.platformExit(this.activity, jSONObject);
        }
    }

    public void buyProduct(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.buyProduct(this.activity, jSONObject);
        }
    }

    public void biRecord(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.biRecord(this.activity, jSONObject);
        }
    }

    public void platformName(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.platformName(this.activity, jSONObject);
        }
    }

    public void needShowSDKBtn(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.needShowSDKBtn(this.activity, jSONObject);
        }
    }

    public void enterUserCenter(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.enterUserCenter(this.activity, jSONObject);
        }
    }

    public void createrRole(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.createRole(this.activity, jSONObject);
        }
    }

    public void createNewPlayer(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.createNewPlayer(this.activity, jSONObject);
        }
    }

    public void playVideo(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.playVideo(this.activity, jSONObject);
        }
    }

    public void paseVideo(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.paseVideo(this.activity, jSONObject);
        }
    }

    public void resumeVideo(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.resumeVideo(this.activity, jSONObject);
        }
    }

    public void isLoginChat(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.isLoginChat(this.activity, jSONObject);
        }
    }

    public void loginChat(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.loginChat(this.activity, jSONObject);
        }
    }

    public void sendMsg(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.sendMsg(this.activity, jSONObject);
        }
    }

    public void sendRoomMsg(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.sendRoomMsg(this.activity, jSONObject);
        }
    }

    public void enterChatRoom(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.enterChatRoom(this.activity, jSONObject);
        }
    }

    public void exitCharRoom(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.exitCharRoom(this.activity, jSONObject);
        }
    }

    public void queryMsg(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.queryMsg(this.activity, jSONObject);
        }
    }

    public void queryTeamMsg(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.queryTeamMsg(this.activity, jSONObject);
        }
    }

    public void queryRoomMsg(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.queryRoomMsg(this.activity, jSONObject);
        }
    }

    public void addToBlackList(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.addToBlackList(this.activity, jSONObject);
        }
    }

    public void removeFromBlackList(JSONObject jSONObject) {
        PlatformHelper platformHelper = this.platformHelper;
        if (platformHelper != null) {
            platformHelper.removeFromBlackList(this.activity, jSONObject);
        }
    }

    public void umengStartLevel(JSONObject jSONObject) {
        try {
            jSONObject.getString("level");
            Log.e("ssss", "onUserLevelChanged");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void umengFailLevel(JSONObject jSONObject) {
        try {
            jSONObject.getString("level");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void umengFinishLevel(JSONObject jSONObject) {
        try {
            jSONObject.getString("level");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void umengPay(JSONObject jSONObject) {
        try {
            jSONObject.getInt("price");
            jSONObject.getInt("coin");
        } catch (Exception unused) {
        }
    }

    public void umengSetUserInfo(JSONObject jSONObject) {
        try {
            jSONObject.getString("username");
            jSONObject.getString("platform");
        } catch (Exception unused) {
        }
    }

    public void umengSetUserLevel(JSONObject jSONObject) {
        try {
            jSONObject.getInt("level");
        } catch (Exception unused) {
        }
    }

    public void umengOnEvent(JSONObject jSONObject) {
        try {
            MobclickAgent.onEvent(this.activity, jSONObject.getString("eventId"), jSONObject.getString("label"));
        } catch (Exception unused) {
        }
    }
}
