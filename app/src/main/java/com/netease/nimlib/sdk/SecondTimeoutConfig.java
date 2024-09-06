package com.netease.nimlib.sdk;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SecondTimeoutConfig implements Serializable {
    public static final String KEY_SEND_MESSAGE_SECOND_TIMEOUT = "KEY_SEND_MESSAGE_SECOND_TIMEOUT";
    public long sendMessageSecondTimeout = 0;

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(KEY_SEND_MESSAGE_SECOND_TIMEOUT, Long.valueOf(this.sendMessageSecondTimeout));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static SecondTimeoutConfig fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        long optLong = jSONObject.optLong(KEY_SEND_MESSAGE_SECOND_TIMEOUT, 0L);
        SecondTimeoutConfig secondTimeoutConfig = new SecondTimeoutConfig();
        secondTimeoutConfig.sendMessageSecondTimeout = optLong;
        return secondTimeoutConfig;
    }
}
