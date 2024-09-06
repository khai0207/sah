package www.kaiqigu.com;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import com.umeng.analytics.MobclickAgent;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;

/* loaded from: classes.dex */
public class JinQu extends Cocos2dxActivity {
    public static Context STATIC_REF;
    private PlatformProxy proxy;
    private VoiceHelper voicehelper;
    protected PowerManager m_powerManager = null;
    protected PowerManager.WakeLock m_wakeLock = null;
    private boolean isAppForeground = true;

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        STATIC_REF = this;
        MobclickAgent.openActivityDurationTrack(false);
        setRequestedOrientation(0);
        PowerManager powerManager = (PowerManager) getSystemService("power");
        this.m_powerManager = powerManager;
        this.m_wakeLock = powerManager.newWakeLock(6, "wake lock");
        PlatformProxy platformProxy = PlatformProxy.getInstance();
        this.proxy = platformProxy;
        platformProxy.setActivity(this);
        this.proxy.initPlatform();
        VoiceHelper voiceHelper = new VoiceHelper();
        this.voicehelper = voiceHelper;
        voiceHelper.setActivity(this);
        this.voicehelper.initPlatform();
    }

    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService("activity");
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    public void onResume() {
        Log.e("JinQu", "onResume-----------");
        super.onResume();
        MobclickAgent.onPageStart("JinQu");
        MobclickAgent.onResume(this);
        if (!this.isAppForeground) {
            this.isAppForeground = true;
        }
        getWindow().addFlags(128);
        this.proxy.onResume(this);
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("JinQu");
        MobclickAgent.onPause(this);
        getWindow().clearFlags(128);
        this.proxy.onPause(this);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.proxy.onKeyUp(this, i, keyEvent).booleanValue();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.proxy.onActivityResult(this, i, i2, intent);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.proxy.onSaveInstanceState(this, bundle);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.proxy.onNewIntent(this, intent);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        this.proxy.onRestart(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.proxy.onDestroy(this);
        super.onDestroy();
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.proxy.onRequestPermissionsResult(this, i, strArr, iArr);
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onStop() {
        Log.e("JinQu", "onStop-----------");
        super.onStop();
        if (!isAppOnForeground()) {
            this.isAppForeground = false;
        }
        this.proxy.onStop(this);
    }

    public static Context getContext() {
        return STATIC_REF;
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public String getChannel() {
        return this.proxy.getPlatformChannel();
    }

    static {
        System.loadLibrary("jinqu");
    }
}
