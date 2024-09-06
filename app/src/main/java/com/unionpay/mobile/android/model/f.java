package com.unionpay.mobile.android.model;

import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f implements e {
    private HashMap<String, Object> a = new HashMap<>();

    public final JSONObject a(String str) {
        Object obj = this.a.get(str);
        if (obj == null || !(obj instanceof JSONObject)) {
            return null;
        }
        return (JSONObject) obj;
    }

    public final void a(String str, Object obj) {
        this.a.put(str, obj);
    }
}
