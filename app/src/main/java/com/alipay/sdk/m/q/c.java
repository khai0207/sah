package com.alipay.sdk.m.q;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            JSONObject[] jSONObjectArr = {jSONObject, jSONObject2};
            for (int i = 0; i < 2; i++) {
                JSONObject jSONObject4 = jSONObjectArr[i];
                if (jSONObject4 != null) {
                    Iterator<String> keys = jSONObject4.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject3.put(next, jSONObject4.get(next));
                    }
                }
            }
        } catch (JSONException e) {
            d.a(e);
        }
        return jSONObject3;
    }
}
