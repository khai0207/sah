package com.android.pc.util;

import android.content.Context;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.android.pc.ioc.app.Ioc;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class Handler_System {
    public static final int DEFAULT_THREAD_POOL_SIZE;
    public static String UA = Build.MODEL;
    private static List<NeighboringCellInfo> mCellinfos = null;
    private static String mDeviceID = null;
    private static String mIMEI = null;
    private static String mMobileVersion = null;
    private static String mNetType = null;
    private static String mNetwrokIso = null;
    private static String mSIM = null;
    static TelephonyManager mTm = null;
    private static HashMap<String, Integer> map = null;
    public static final String systemHeight = "height";
    public static final String systemWidth = "width";

    public static int getBarHeight() {
        return 5;
    }

    public static boolean isNetworkAvailable(Context context) {
        return true;
    }

    static {
        init();
        DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();
        mTm = null;
    }

    public static String getAppName() {
        return getAppName(null);
    }

    public static String getAppName(String str) {
        if (str == null) {
            str = Ioc.getIoc().getApplication().getPackageName();
        }
        try {
            return Ioc.getIoc().getApplication().getString(Ioc.getIoc().getApplication().getPackageManager().getPackageInfo(str, 0).applicationInfo.labelRes);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return "";
        }
    }

    public static String getAppVersionNumber() {
        return getAppVersionNumber(null);
    }

    public static String getAppVersionNumber(String str) {
        if (str == null) {
            str = Ioc.getIoc().getApplication().getPackageName();
        }
        try {
            return Ioc.getIoc().getApplication().getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return "";
        }
    }

    public static String getAppVersionCode() {
        return getAppVersionCode(null);
    }

    public static String getAppVersionCode(String str) {
        if (str == null) {
            str = Ioc.getIoc().getApplication().getPackageName();
        }
        try {
            return Integer.toString(Ioc.getIoc().getApplication().getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return "";
        }
    }

    public static int getSdkVersion() {
        try {
            return Build.VERSION.class.getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return 3;
        }
    }

    public static boolean isRelease(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("Release signature string is null or missing.");
        }
        Signature signature = new Signature(str);
        try {
            for (Signature signature2 : Ioc.getIoc().getApplication().getPackageManager().getPackageInfo(Ioc.getIoc().getApplication().getPackageName(), 64).signatures) {
                if (signature2.equals(signature)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return true;
        }
    }

    public static boolean isEmulator() {
        return Build.MODEL.equals("sdk") || Build.MODEL.equals("google_sdk");
    }

    public static String getMobileInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Field field : Build.class.getDeclaredFields()) {
                field.setAccessible(true);
                stringBuffer.append(field.getName() + "=" + field.get(null).toString());
                stringBuffer.append("\n");
            }
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
        return stringBuffer.toString();
    }

    public static HashMap<String, Integer> getDisplayMetrics() {
        if (map == null) {
            map = new HashMap<>();
            Display defaultDisplay = ((WindowManager) Ioc.getIoc().getApplication().getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            map.put(systemWidth, Integer.valueOf(width));
            map.put(systemHeight, Integer.valueOf(height));
        }
        return map;
    }

    public static float getWidthRoate() {
        if (map == null) {
            map = new HashMap<>();
            Display defaultDisplay = ((WindowManager) Ioc.getIoc().getApplication().getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            map.put(systemWidth, Integer.valueOf(width));
            map.put(systemHeight, Integer.valueOf(height));
        }
        return (map.get(systemWidth).intValue() * 1.0f) / Ioc.getIoc().getMode_w();
    }

    public static float getRoate() {
        if (map == null) {
            map = new HashMap<>();
            Display defaultDisplay = ((WindowManager) Ioc.getIoc().getApplication().getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            map.put(systemWidth, Integer.valueOf(width));
            map.put(systemHeight, Integer.valueOf(height));
        }
        float intValue = (map.get(systemWidth).intValue() * 1.0f) / Ioc.getIoc().getMode_w();
        float intValue2 = (map.get(systemHeight).intValue() * 1.0f) / Ioc.getIoc().getMode_h();
        return intValue > intValue2 ? intValue : intValue2;
    }

    public static float getPadRoate() {
        if (map == null) {
            map = new HashMap<>();
            Display defaultDisplay = ((WindowManager) Ioc.getIoc().getApplication().getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            map.put(systemWidth, Integer.valueOf(width));
            map.put(systemHeight, Integer.valueOf(height));
        }
        float intValue = (map.get(systemWidth).intValue() * 1.0f) / Ioc.getIoc().getMode_w();
        float intValue2 = (map.get(systemHeight).intValue() * 1.0f) / Ioc.getIoc().getMode_h();
        return intValue < intValue2 ? intValue : intValue2;
    }

    public static float getHeightRoate() {
        if (map == null) {
            map = new HashMap<>();
            Display defaultDisplay = ((WindowManager) Ioc.getIoc().getApplication().getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            map.put(systemWidth, Integer.valueOf(width));
            map.put(systemHeight, Integer.valueOf(height));
        }
        return (map.get(systemHeight).intValue() * 1.0f) / Ioc.getIoc().getMode_h();
    }

    public static int dip2px(float f) {
        return (int) ((f * Ioc.getIoc().getApplication().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float f) {
        return (int) ((f / Ioc.getIoc().getApplication().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean hasGingerbreadMR1() {
        return Build.VERSION.SDK_INT >= 10;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean isHoneycombTablet(Context context) {
        return hasHoneycomb() && isTablet(context);
    }

    public static boolean isGingerbread() {
        return Build.VERSION.SDK_INT >= 9 && Build.VERSION.SDK_INT <= 10;
    }

    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    public static int getDefaultThreadPoolSize(int i) {
        int availableProcessors = (Runtime.getRuntime().availableProcessors() * 2) + 1;
        return availableProcessors > i ? i : availableProcessors;
    }

    public static void Vibrate(long j) {
        ((Vibrator) Ioc.getIoc().getApplication().getSystemService("vibrator")).vibrate(j);
    }

    public static void init() {
        TelephonyManager telephonyManager = (TelephonyManager) Ioc.getIoc().getApplication().getSystemService("phone");
        mTm = telephonyManager;
        mIMEI = telephonyManager.getDeviceId();
        mMobileVersion = mTm.getDeviceSoftwareVersion();
        mNetwrokIso = mTm.getNetworkCountryIso();
        mSIM = mTm.getSimSerialNumber();
        mDeviceID = getDeviceId();
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) Ioc.getIoc().getApplication().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                mNetType = activeNetworkInfo.getTypeName();
            }
        } catch (Exception unused) {
        }
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private static String getDeviceId() {
        return Settings.Secure.getString(Ioc.getIoc().getApplication().getContentResolver(), "android_id");
    }

    public static String getImei() {
        return mIMEI;
    }

    public static String getSIM() {
        return mSIM;
    }

    public static String getUA() {
        return UA;
    }

    public static String getDeviceInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IMEI:");
        stringBuffer.append(getImei());
        stringBuffer.append("\n");
        stringBuffer.append("SIM:");
        stringBuffer.append(getSIM());
        stringBuffer.append("\n");
        stringBuffer.append("UA:");
        stringBuffer.append(getUA());
        stringBuffer.append("\n");
        stringBuffer.append("MobileVersion:");
        stringBuffer.append(mMobileVersion);
        stringBuffer.append("\n");
        stringBuffer.append("SDK: ");
        stringBuffer.append(Build.VERSION.SDK);
        stringBuffer.append("\n");
        stringBuffer.append(getCallState());
        stringBuffer.append("\n");
        stringBuffer.append("SIM_STATE: ");
        stringBuffer.append(getSimState());
        stringBuffer.append("\n");
        stringBuffer.append("SIM: ");
        stringBuffer.append(getSIM());
        stringBuffer.append("\n");
        stringBuffer.append(getSimOpertorName());
        stringBuffer.append("\n");
        stringBuffer.append(getPhoneType());
        stringBuffer.append("\n");
        stringBuffer.append(getPhoneSettings());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public static String getSimState() {
        int simState = mTm.getSimState();
        return simState != 1 ? simState != 2 ? simState != 3 ? simState != 4 ? simState != 5 ? "未知SIM状态_0" : "就绪SIM状态_5" : "锁定SIM状态_需要网络的PIN码解锁_4" : "锁定SIM状态_需要用户的PUK码解锁_3" : "锁定SIM状态_需要用户的PIN码解锁_2" : "没插SIM卡_1";
    }

    public static String getPhoneType() {
        int phoneType = mTm.getPhoneType();
        return phoneType != 1 ? phoneType != 2 ? "PhoneType: 无信号_0" : "PhoneType: CDMA信号_2" : "PhoneType: GSM信号_1";
    }

    public static String getSimOpertorName() {
        if (mTm.getSimState() == 5) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SimOperatorName: ");
            stringBuffer.append(mTm.getSimOperatorName());
            stringBuffer.append("\n");
            stringBuffer.append("SimOperator: ");
            stringBuffer.append(mTm.getSimOperator());
            stringBuffer.append("\n");
            stringBuffer.append("Phone:");
            stringBuffer.append(mTm.getLine1Number());
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("SimOperatorName: ");
        stringBuffer2.append("未知");
        stringBuffer2.append("\n");
        stringBuffer2.append("SimOperator: ");
        stringBuffer2.append("未知");
        return stringBuffer2.toString();
    }

    public static String getPhoneSettings() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Settings.Secure.getString(Ioc.getIoc().getApplication().getContentResolver(), "bluetooth_on");
        stringBuffer.append("蓝牙:");
        if (string.equals("0")) {
            stringBuffer.append("禁用");
        } else {
            stringBuffer.append("开启");
        }
        String string2 = Settings.Secure.getString(Ioc.getIoc().getApplication().getContentResolver(), "bluetooth_on");
        stringBuffer.append("WIFI:");
        stringBuffer.append(string2);
        String string3 = Settings.Secure.getString(Ioc.getIoc().getApplication().getContentResolver(), "install_non_market_apps");
        stringBuffer.append("APP位置来源:");
        stringBuffer.append(string3);
        return stringBuffer.toString();
    }

    public static String getCallState() {
        int callState = mTm.getCallState();
        return (callState == 0 || callState == 1 || callState == 2) ? "电话状态[CallState]: 无活动" : "电话状态[CallState]: 未知";
    }

    public static String getNetwrokIso() {
        return mNetwrokIso;
    }

    public String getmDeviceID() {
        return mDeviceID;
    }

    public String getNetType() {
        return mNetType;
    }
}
