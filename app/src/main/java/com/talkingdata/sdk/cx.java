package com.talkingdata.sdk;

import com.iflytek.cloud.SpeechConstant;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class cx extends da {
    public cx(String str, String str2) {
        a(SpeechConstant.DOMAIN, str);
        a(com.alipay.sdk.m.h.c.e, str2);
    }

    public void setData(Map map) {
        if (map != null) {
            a("data", new JSONObject(map));
        }
    }
}
