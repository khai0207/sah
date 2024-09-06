package org.cocos2dx.lib;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public class Cocos2dxHelper {
    private static final String PREFS_NAME = "Cocos2dxPrefsFile";
    private static boolean sAccelerometerEnabled;
    private static AssetManager sAssetManager;
    private static Cocos2dxMusic sCocos2dMusic;
    private static Cocos2dxSound sCocos2dSound;
    private static Cocos2dxAccelerometer sCocos2dxAccelerometer;
    private static Cocos2dxHelperListener sCocos2dxHelperListener;
    private static Context sContext;
    private static String sFileDirectory;
    private static String sPackageName;

    /* loaded from: classes.dex */
    public interface Cocos2dxHelperListener {
        String getChannel();

        void loadDataWebView(String str, String str2, String str3);

        void loadUrlWebView(String str, String str2, String str3);

        void notificationMsg(int i, String str, String str2, int i2, boolean z);

        void removeNotificationById(int i);

        void runOnGLThread(Runnable runnable);

        void showDialog(String str, String str2);

        void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4);

        void showWebView(String str, String str2, int i, int i2, int i3, int i4);

        void visibleWebView(String str, String str2);
    }

    private static native void nativeSetApkPath(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEditTextDialogResult(byte[] bArr);

    public static void init(Context context, Cocos2dxHelperListener cocos2dxHelperListener) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        sContext = context;
        sCocos2dxHelperListener = cocos2dxHelperListener;
        sPackageName = applicationInfo.packageName;
        sFileDirectory = context.getFilesDir().getAbsolutePath();
        nativeSetApkPath(applicationInfo.sourceDir);
        sCocos2dxAccelerometer = new Cocos2dxAccelerometer(context);
        sCocos2dMusic = new Cocos2dxMusic(context);
        sCocos2dSound = new Cocos2dxSound(context);
        sAssetManager = context.getAssets();
        Cocos2dxBitmap.setContext(context);
        Cocos2dxETCLoader.setContext(context);
    }

    public static String getCocos2dxPackageName() {
        return sPackageName;
    }

    public static String getCocos2dxWritablePath() {
        return sFileDirectory;
    }

    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static AssetManager getAssetManager() {
        return sAssetManager;
    }

    public static void enableAccelerometer() {
        sAccelerometerEnabled = true;
        sCocos2dxAccelerometer.enable();
    }

    public static void setAccelerometerInterval(float f) {
        sCocos2dxAccelerometer.setInterval(f);
    }

    public static void disableAccelerometer() {
        sAccelerometerEnabled = false;
        sCocos2dxAccelerometer.disable();
    }

    public static void preloadBackgroundMusic(String str) {
        sCocos2dMusic.preloadBackgroundMusic(str);
    }

    public static void playBackgroundMusic(String str, boolean z) {
        sCocos2dMusic.playBackgroundMusic(str, z);
    }

    public static void resumeBackgroundMusic() {
        sCocos2dMusic.resumeBackgroundMusic();
    }

    public static void pauseBackgroundMusic() {
        sCocos2dMusic.pauseBackgroundMusic();
    }

    public static void stopBackgroundMusic() {
        sCocos2dMusic.stopBackgroundMusic();
    }

    public static void rewindBackgroundMusic() {
        sCocos2dMusic.rewindBackgroundMusic();
    }

    public static boolean isBackgroundMusicPlaying() {
        return sCocos2dMusic.isBackgroundMusicPlaying();
    }

    public static float getBackgroundMusicVolume() {
        return sCocos2dMusic.getBackgroundVolume();
    }

    public static void setBackgroundMusicVolume(float f) {
        sCocos2dMusic.setBackgroundVolume(f);
    }

    public static void preloadEffect(String str) {
        sCocos2dSound.preloadEffect(str);
    }

    public static int playEffect(String str, boolean z) {
        return sCocos2dSound.playEffect(str, z);
    }

    public static void resumeEffect(int i) {
        sCocos2dSound.resumeEffect(i);
    }

    public static void pauseEffect(int i) {
        sCocos2dSound.pauseEffect(i);
    }

    public static void stopEffect(int i) {
        sCocos2dSound.stopEffect(i);
    }

    public static float getEffectsVolume() {
        return sCocos2dSound.getEffectsVolume();
    }

    public static void setEffectsVolume(float f) {
        sCocos2dSound.setEffectsVolume(f);
    }

    public static void unloadEffect(String str) {
        sCocos2dSound.unloadEffect(str);
    }

    public static void pauseAllEffects() {
        sCocos2dSound.pauseAllEffects();
    }

    public static void resumeAllEffects() {
        sCocos2dSound.resumeAllEffects();
    }

    public static void stopAllEffects() {
        sCocos2dSound.stopAllEffects();
    }

    public static void end() {
        sCocos2dMusic.end();
        sCocos2dSound.end();
    }

    public static void onResume() {
        if (sAccelerometerEnabled) {
            sCocos2dxAccelerometer.enable();
        }
    }

    public static void onPause() {
        if (sAccelerometerEnabled) {
            sCocos2dxAccelerometer.disable();
        }
    }

    public static void terminateProcess() {
        Process.killProcess(Process.myPid());
    }

    private static void showDialog(String str, String str2) {
        sCocos2dxHelperListener.showDialog(str, str2);
    }

    private static void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4) {
        sCocos2dxHelperListener.showEditTextDialog(str, str2, i, i2, i3, i4);
    }

    private static void showWebView(String str, String str2, int i, int i2, int i3, int i4) {
        sCocos2dxHelperListener.showWebView(str, str2, i, i2, i3, i4);
    }

    private static void visibleWebView(String str, String str2) {
        sCocos2dxHelperListener.visibleWebView(str, str2);
    }

    private static void loadUrlWebView(String str, String str2, String str3) {
        sCocos2dxHelperListener.loadUrlWebView(str, str2, str3);
    }

    private static void loadDataWebView(String str, String str2, String str3) {
        sCocos2dxHelperListener.loadDataWebView(str, str2, str3);
    }

    private static void openUrlOutside(String str) {
        sContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static String getPlatformChannel() {
        String channel = sCocos2dxHelperListener.getChannel();
        return channel == null ? "" : channel;
    }

    public static String getDeviceId() {
        String macAddress;
        StringBuilder sb = new StringBuilder();
        try {
            macAddress = ((WifiManager) sContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (macAddress != null && !macAddress.equals("")) {
            sb.append("wifi");
            sb.append(macAddress);
            Log.e("getDeviceId : ", sb.toString());
            return sb.toString();
        }
        TelephonyManager telephonyManager = (TelephonyManager) sContext.getSystemService("phone");
        String deviceId = telephonyManager.getDeviceId();
        if (deviceId != null && !deviceId.equals("")) {
            sb.append("imei");
            sb.append(deviceId);
            Log.e("getDeviceId : ", sb.toString());
            return sb.toString();
        }
        String simSerialNumber = telephonyManager.getSimSerialNumber();
        if (simSerialNumber != null && !simSerialNumber.equals("")) {
            sb.append("sn");
            sb.append(simSerialNumber);
            Log.e("getDeviceId : ", sb.toString());
            return sb.toString();
        }
        Log.e("getDeviceId : ", sb.toString());
        Log.e("getDeviceId : ", sb.toString());
        return sb.toString();
    }

    public static double getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) sContext.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        Log.i("getAvailableMemory", "系统剩余内存:" + (memoryInfo.availMem >> 10) + "k");
        StringBuilder sb = new StringBuilder();
        sb.append("系统是否处于低内存运行：");
        sb.append(memoryInfo.lowMemory);
        Log.i("getAvailableMemory", sb.toString());
        Log.i("getAvailableMemory", "当系统剩余内存低于" + (memoryInfo.threshold >> 10) + "k时是低内存运行");
        return memoryInfo.availMem;
    }

    public static double getUsedMemory() {
        double d;
        ActivityManager activityManager = (ActivityManager) sContext.getSystemService("activity");
        Iterator<ActivityManager.RunningAppProcessInfo> it = activityManager.getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                d = 0.0d;
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            int i = next.pid;
            int i2 = next.uid;
            String str = next.processName;
            if (str.equals(sContext.getPackageName())) {
                Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{i});
                d = processMemoryInfo[0].dalvikPrivateDirty + processMemoryInfo[0].dalvikSharedDirty;
                Log.i("getUsedMemory", "processName: " + str + "  pid: " + i + " uid:" + i2 + " memorySize is -->" + d + "kb");
                break;
            }
        }
        double nativeHeapSize = Debug.getNativeHeapSize();
        Double.isNaN(nativeHeapSize);
        double d2 = (d * 1024.0d) + nativeHeapSize;
        Log.i("getUsedMemory", "NativeHeapSizeTotal:" + Debug.getNativeHeapSize());
        return d2;
    }

    public static double getTotalMemory() {
        double d = 0.0d;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            d = Double.valueOf(bufferedReader.readLine().split("\\s+")[1]).doubleValue();
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return d * 1024.0d;
    }

    public static boolean isDirectoryExist(String str) {
        return new File(str).isDirectory();
    }

    private static void notificationMsg(int i, String str, String str2, int i2) {
        sCocos2dxHelperListener.notificationMsg(i, str, str2, i2, true);
    }

    private static void removeNotificationById(int i) {
        sCocos2dxHelperListener.removeNotificationById(i);
    }

    public static void setEditTextDialogResult(String str) {
        try {
            final byte[] bytes = str.getBytes("UTF8");
            sCocos2dxHelperListener.runOnGLThread(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxHelper.nativeSetEditTextDialogResult(bytes);
                }
            });
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public static int getDPI() {
        Display defaultDisplay;
        if (sContext == null) {
            return -1;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = ((Activity) sContext).getWindowManager();
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return -1;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return (int) (displayMetrics.density * 160.0f);
    }

    public static boolean getBoolForKey(String str, boolean z) {
        return ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).getBoolean(str, z);
    }

    public static int getIntegerForKey(String str, int i) {
        return ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).getInt(str, i);
    }

    public static float getFloatForKey(String str, float f) {
        return ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).getFloat(str, f);
    }

    public static double getDoubleForKey(String str, double d) {
        return ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).getFloat(str, (float) d);
    }

    public static String getStringForKey(String str, String str2) {
        return ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).getString(str, str2);
    }

    public static void setBoolForKey(String str, boolean z) {
        SharedPreferences.Editor edit = ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static void setIntegerForKey(String str, int i) {
        SharedPreferences.Editor edit = ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void setFloatForKey(String str, float f) {
        SharedPreferences.Editor edit = ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    public static void setDoubleForKey(String str, double d) {
        SharedPreferences.Editor edit = ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putFloat(str, (float) d);
        edit.commit();
    }

    public static void setStringForKey(String str, String str2) {
        SharedPreferences.Editor edit = ((Activity) sContext).getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }
}
