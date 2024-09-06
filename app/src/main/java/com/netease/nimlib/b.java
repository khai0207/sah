package com.netease.nimlib;

import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConfigParams.java */
/* loaded from: classes.dex */
public class b {
    private final LoginInfo a;
    private final SDKOptions b;

    public b(LoginInfo loginInfo, SDKOptions sDKOptions) {
        this.a = loginInfo;
        this.b = sDKOptions;
    }

    public LoginInfo a() {
        return this.a;
    }

    public SDKOptions b() {
        return this.b;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("KEY_LOGIN_INFO", this.a == null ? null : this.a.toJson());
            f a = f.a(this.b);
            if (a != null) {
                jSONObject.putOpt("KEY_SDK_OPTIONS_INIT_PUSH", a.a());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static b a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new b(LoginInfo.fromJson(jSONObject.optJSONObject("KEY_LOGIN_INFO")), f.a(f.a(jSONObject.optJSONObject("KEY_SDK_OPTIONS_INIT_PUSH"))));
    }
}
