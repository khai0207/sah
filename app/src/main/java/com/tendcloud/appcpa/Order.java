package com.tendcloud.appcpa;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class Order extends JSONObject {
    static final String keyAmount = "count";
    static final String keyCategory = "category";
    public static final String keyCurrencyType = "keyCurrencyType";
    static final String keyId = "id";
    static final String keyName = "name";
    public static final String keyOrderDetail = "keyOrderDetail";
    public static final String keyOrderId = "keyOrderId";
    public static final String keyTotalPrice = "keyTotalPrice";
    static final String keyUnitPrice = "unitPrice";
    JSONArray orderDetails = null;

    private Order(String str, int i, String str2) {
        try {
            put(keyOrderId, str);
            put(keyTotalPrice, i);
            put(keyCurrencyType, str2);
        } catch (JSONException unused) {
        }
    }

    private Order() {
    }

    public static Order createOrder(String str, int i, String str2) {
        return new Order(str, i, str2);
    }

    public synchronized Order addItem(String str, String str2, int i, int i2) {
        try {
            if (this.orderDetails == null) {
                JSONArray jSONArray = new JSONArray();
                this.orderDetails = jSONArray;
                put(keyOrderDetail, jSONArray);
            }
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(keyCategory, str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("name", str2);
            }
            jSONObject.put(keyUnitPrice, i);
            jSONObject.put(keyAmount, i2);
            this.orderDetails.put(jSONObject);
        } catch (JSONException unused) {
        }
        return this;
    }

    public synchronized Order addItem(String str, String str2, String str3, int i, int i2) {
        try {
            if (this.orderDetails == null) {
                JSONArray jSONArray = new JSONArray();
                this.orderDetails = jSONArray;
                put(keyOrderDetail, jSONArray);
            }
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
            jSONObject.put(keyAmount, i2);
            this.orderDetails.put(jSONObject);
        } catch (JSONException unused) {
        }
        return this;
    }
}
