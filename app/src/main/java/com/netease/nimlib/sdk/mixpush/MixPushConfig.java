package com.netease.nimlib.sdk.mixpush;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MixPushConfig implements Serializable {
    public static final String KEY_AUTO_SELECT_PUSH_TYPE = "KEY_AUTO_SELECT_PUSH_TYPE";
    public static final String KEY_FCM_CERTIFICATE_NAME = "KEY_FCM_CERTIFICATE_NAME";
    public static final String KEY_HONOR_CERTIFICATE_NAME = "KEY_HONOR_CERTIFICATE_NAME";
    public static final String KEY_HW_APP_ID = "KEY_HW_APP_ID";
    public static final String KEY_HW_CERTIFICATE_NAME = "KEY_HW_CERTIFICATE_NAME";
    public static final String KEY_MZ_APP_ID = "KEY_MZ_APP_ID";
    public static final String KEY_MZ_APP_KEY = "KEY_MZ_APP_KEY";
    public static final String KEY_MZ_CERTIFICATE_NAME = "KEY_MZ_CERTIFICATE_NAME";
    public static final String KEY_OPPO_APP_ID = "KEY_OPPO_APP_ID";
    public static final String KEY_OPPO_APP_KEY = "KEY_OPPO_APP_KEY";
    public static final String KEY_OPPO_APP_SERCET = "KEY_OPPO_APP_SERCET";
    public static final String KEY_OPPO_CERTIFICATE_NAME = "KEY_OPPO_CERTIFICATE_NAME";
    public static final String KEY_VIVO_CERTIFICATE_NAME = "KEY_VIVO_CERTIFICATE_NAME";
    public static final String KEY_XM_APP_ID = "KEY_XM_APP_ID";
    public static final String KEY_XM_APP_KEY = "KEY_XM_APP_KEY";
    public static final String KEY_XM_CERTIFICATE_NAME = "KEY_XM_CERTIFICATE_NAME";
    public boolean autoSelectPushType = false;
    public String fcmCertificateName;
    public String honorCertificateName;
    public String hwAppId;
    public String hwCertificateName;
    public String mzAppId;
    public String mzAppKey;
    public String mzCertificateName;
    public String oppoAppId;
    public String oppoAppKey;
    public String oppoAppSercet;
    public String oppoCertificateName;
    public String vivoCertificateName;
    public String xmAppId;
    public String xmAppKey;
    public String xmCertificateName;

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(KEY_XM_APP_ID, this.xmAppId);
            jSONObject.putOpt(KEY_XM_APP_KEY, this.xmAppKey);
            jSONObject.putOpt(KEY_XM_CERTIFICATE_NAME, this.xmCertificateName);
            jSONObject.putOpt(KEY_HW_APP_ID, this.hwAppId);
            jSONObject.putOpt(KEY_HW_CERTIFICATE_NAME, this.hwCertificateName);
            jSONObject.putOpt(KEY_MZ_APP_ID, this.mzAppId);
            jSONObject.putOpt(KEY_MZ_APP_KEY, this.mzAppKey);
            jSONObject.putOpt(KEY_MZ_CERTIFICATE_NAME, this.mzCertificateName);
            jSONObject.putOpt(KEY_FCM_CERTIFICATE_NAME, this.fcmCertificateName);
            jSONObject.putOpt(KEY_VIVO_CERTIFICATE_NAME, this.vivoCertificateName);
            jSONObject.putOpt(KEY_OPPO_APP_ID, this.oppoAppId);
            jSONObject.putOpt(KEY_OPPO_APP_KEY, this.oppoAppKey);
            jSONObject.putOpt(KEY_OPPO_APP_SERCET, this.oppoAppSercet);
            jSONObject.putOpt(KEY_OPPO_CERTIFICATE_NAME, this.oppoCertificateName);
            jSONObject.putOpt(KEY_AUTO_SELECT_PUSH_TYPE, Boolean.valueOf(this.autoSelectPushType));
            jSONObject.putOpt(KEY_HONOR_CERTIFICATE_NAME, this.honorCertificateName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static MixPushConfig fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        MixPushConfig mixPushConfig = new MixPushConfig();
        mixPushConfig.xmAppId = jSONObject.optString(KEY_XM_APP_ID, null);
        mixPushConfig.xmAppKey = jSONObject.optString(KEY_XM_APP_KEY, null);
        mixPushConfig.xmCertificateName = jSONObject.optString(KEY_XM_CERTIFICATE_NAME, null);
        mixPushConfig.hwAppId = jSONObject.optString(KEY_HW_APP_ID, null);
        mixPushConfig.hwCertificateName = jSONObject.optString(KEY_HW_CERTIFICATE_NAME, null);
        mixPushConfig.mzAppId = jSONObject.optString(KEY_MZ_APP_ID, null);
        mixPushConfig.mzAppKey = jSONObject.optString(KEY_MZ_APP_KEY, null);
        mixPushConfig.mzCertificateName = jSONObject.optString(KEY_MZ_CERTIFICATE_NAME, null);
        mixPushConfig.fcmCertificateName = jSONObject.optString(KEY_FCM_CERTIFICATE_NAME, null);
        mixPushConfig.vivoCertificateName = jSONObject.optString(KEY_VIVO_CERTIFICATE_NAME, null);
        mixPushConfig.oppoAppId = jSONObject.optString(KEY_OPPO_APP_ID, null);
        mixPushConfig.oppoAppKey = jSONObject.optString(KEY_OPPO_APP_KEY, null);
        mixPushConfig.oppoAppSercet = jSONObject.optString(KEY_OPPO_APP_SERCET, null);
        mixPushConfig.oppoCertificateName = jSONObject.optString(KEY_OPPO_CERTIFICATE_NAME, null);
        mixPushConfig.autoSelectPushType = jSONObject.optBoolean(KEY_AUTO_SELECT_PUSH_TYPE, false);
        mixPushConfig.honorCertificateName = jSONObject.optString(KEY_HONOR_CERTIFICATE_NAME, null);
        return mixPushConfig;
    }
}
