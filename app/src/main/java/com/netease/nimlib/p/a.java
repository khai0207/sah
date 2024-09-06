package com.netease.nimlib.p;

import android.os.Build;
import com.netease.nimlib.c;
import com.netease.nimlib.log.b;
import com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig;

/* compiled from: Device.java */
/* loaded from: classes.dex */
public class a {
    public static String a() {
        CaptureDeviceInfoConfig captureDeviceInfoConfig = c.i().captureDeviceInfoConfig;
        if (captureDeviceInfoConfig == null || captureDeviceInfoConfig.isCaptureManufacturer()) {
            return Build.MANUFACTURER;
        }
        b.d("Device", "cancel getting manufacturer, denied by config");
        return "";
    }

    public static String b() {
        CaptureDeviceInfoConfig captureDeviceInfoConfig = c.i().captureDeviceInfoConfig;
        if (captureDeviceInfoConfig == null || captureDeviceInfoConfig.isCaptureModel()) {
            return Build.MODEL;
        }
        b.d("Device", "cancel getting model, denied by config");
        return "";
    }

    public static String c() {
        CaptureDeviceInfoConfig captureDeviceInfoConfig = c.i().captureDeviceInfoConfig;
        if (captureDeviceInfoConfig == null || captureDeviceInfoConfig.isCaptureBrand()) {
            return Build.BRAND;
        }
        b.d("Device", "cancel getting brand, denied by config");
        return "";
    }
}
