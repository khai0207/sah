package com.components;

import org.json.JSONObject;

/* loaded from: classes.dex */
public interface ComponentProtocol {
    void SendMessage(String str, JSONObject jSONObject);

    void SendMessageToCpp(String str, JSONObject jSONObject);
}
