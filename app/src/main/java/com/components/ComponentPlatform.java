package com.components;

import android.app.Activity;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class ComponentPlatform implements PlatformInterface {
    private ComponentProtocol callHandler;

    @Override // com.components.PlatformInterface
    public String getPlatformId() {
        return "kv_game";
    }

    @Override // com.components.PlatformInterface
    public void sdkInit(Activity activity, JSONObject jSONObject) {
    }

    public ComponentProtocol getCallHandler() {
        return this.callHandler;
    }

    public void setCallHandler(ComponentProtocol componentProtocol) {
        this.callHandler = componentProtocol;
    }

    public void dispatchMessageToProxy(String str, JSONObject jSONObject) {
        ComponentProtocol componentProtocol = this.callHandler;
        if (componentProtocol != null) {
            componentProtocol.SendMessageToCpp(str, jSONObject);
        }
    }
}
