package com.talkingdata.sdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public abstract class da {
    protected JSONObject b = new JSONObject();

    public Object a_() {
        return this.b;
    }

    protected void a(String str, Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (a(obj)) {
                return;
            }
            this.b.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected boolean a(Object obj) {
        return obj instanceof JSONObject ? ((JSONObject) obj).length() <= 0 : (obj instanceof JSONArray) && ((JSONArray) obj).length() <= 0;
    }
}
