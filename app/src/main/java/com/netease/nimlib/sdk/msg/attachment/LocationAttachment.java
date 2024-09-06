package com.netease.nimlib.sdk.msg.attachment;

import com.netease.nimlib.o.k;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LocationAttachment implements MsgAttachment {
    private static final String KEY_DESC = "title";
    private static final String KEY_LATITUDE = "lat";
    private static final String KEY_LONGITUDE = "lng";
    private String address;
    private double latitude;
    private double longitude;

    public LocationAttachment() {
    }

    public LocationAttachment(String str) {
        fromJson(str);
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    private void fromJson(String str) {
        JSONObject a = k.a(str);
        this.latitude = k.d(a, KEY_LATITUDE);
        this.longitude = k.d(a, KEY_LONGITUDE);
        this.address = k.e(a, "title");
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachment
    public String toJson(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_LATITUDE, this.latitude);
            jSONObject.put(KEY_LONGITUDE, this.longitude);
            jSONObject.put("title", this.address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
