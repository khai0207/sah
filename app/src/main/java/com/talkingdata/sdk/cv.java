package com.talkingdata.sdk;

import java.net.URLEncoder;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class cv extends da {
    static cv a;

    private cv() {
    }

    public static synchronized cv a() {
        cv cvVar;
        synchronized (cv.class) {
            if (a == null) {
                a = new cv();
            }
            cvVar = a;
        }
        return cvVar;
    }

    public void setSessionId(String str) {
        a("sessionId", str);
    }

    public void setCurrentPageName(String str) {
        a("page", str);
    }

    public void setAccount(JSONObject jSONObject) {
        a("account", jSONObject);
    }

    public void setSubaccount(JSONObject jSONObject) {
        a("subaccount", jSONObject);
    }

    public void setDeepLink(String str) {
        try {
            String encode = URLEncoder.encode(str, "utf-8");
            a("deeplink", encode);
            ap.setDeepLink(encode);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void setSessionStartTime(long j) {
        a("sessionStartTime", Long.valueOf(j));
    }

    public void setPushInfo(String str) {
        a("push", str);
    }

    public void setAntiCheatingstatus(int i) {
        a("antiCheating", Integer.valueOf(i));
    }
}
