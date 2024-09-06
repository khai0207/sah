package com.tendcloud.appcpa;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class ShoppingCart extends JSONArray {
    static final String keyCategory = "category";
    static final String keyCount = "count";
    static final String keyId = "id";
    static final String keyName = "name";
    static final String keyUnitPrice = "unitPrice";
    JSONArray array = null;

    private ShoppingCart() {
    }

    public static ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }

    public synchronized ShoppingCart addItem(String str, String str2, String str3, int i, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(keyId, str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put(keyCategory, str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("name", str3);
            }
            jSONObject.put(keyUnitPrice, i);
            jSONObject.put(keyCount, i2);
            put(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
}
