package com.kqg.main.base;

import android.content.Context;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.alipay.sdk.m.o.a;
import com.kqg.main.model.PaySelectTypeManager;
import com.kqg.main.model.PayType;

/* loaded from: classes.dex */
public class ConfigInfor {
    private String appId;
    private int appKey;
    private String app_id;
    private Context ctx;
    private String device;
    private String deviceid;
    private String imei;
    private String os;
    private String osVer;
    private String publicKey;
    private String version;

    public void setSupportPayType(PayType... payTypeArr) {
        PaySelectTypeManager.initPayList(payTypeArr);
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public int getAppKey() {
        return this.appKey;
    }

    public void setAppKey(int i) {
        this.appKey = i;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public Context getCtx() {
        return this.ctx;
    }

    public void setCtx(Context context) {
        this.ctx = context;
    }

    public String toString() {
        return "ConfigInfor [appId=" + this.appId + ", appKey=" + this.appKey + ", publicKey=" + this.publicKey + ", ctx=" + this.ctx + "]";
    }

    public ConfigInfor(String str, int i, String str2, Context context) {
        this.appId = str;
        this.appKey = i;
        this.publicKey = str2;
        this.ctx = context;
    }

    public ConfigInfor() {
    }

    private String trim(String str) {
        if (str == null) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return str.replaceAll(" ", "_");
    }

    public void fillDeviceInfor() {
        if (this.ctx != null) {
            this.device = trim(Build.MODEL);
            this.osVer = "";
            this.os = "2";
            this.app_id = this.appId + "";
            this.version = "";
            this.imei = "";
        }
    }

    public String appendDeviceInfor() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("deviceid=");
        stringBuffer.append(this.deviceid);
        stringBuffer.append(a.l);
        stringBuffer.append("device=");
        stringBuffer.append(this.device);
        stringBuffer.append(a.l);
        stringBuffer.append("osVer=");
        stringBuffer.append(this.osVer);
        stringBuffer.append(a.l);
        stringBuffer.append("os=");
        stringBuffer.append(this.os);
        stringBuffer.append(a.l);
        stringBuffer.append("app_id=");
        stringBuffer.append(this.app_id);
        stringBuffer.append(a.l);
        stringBuffer.append("version=");
        stringBuffer.append(this.version);
        stringBuffer.append(a.l);
        stringBuffer.append("deviceid1=");
        stringBuffer.append(this.imei);
        stringBuffer.append(a.l);
        return stringBuffer.toString();
    }
}
