package com.netease.nimlib.sdk.msg.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CaptureDeviceInfoConfig implements Parcelable, Serializable {
    public static final Parcelable.Creator<CaptureDeviceInfoConfig> CREATOR = new Parcelable.Creator<CaptureDeviceInfoConfig>() { // from class: com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CaptureDeviceInfoConfig createFromParcel(Parcel parcel) {
            return new CaptureDeviceInfoConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CaptureDeviceInfoConfig[] newArray(int i) {
            return new CaptureDeviceInfoConfig[i];
        }
    };
    public static final String KEY_CAPTURE_BRAND = "KEY_CAPTURE_BRAND";
    public static final String KEY_CAPTURE_MANUFACTURER = "KEY_CAPTURE_MANUFACTURER";
    public static final String KEY_CAPTURE_MODEL = "KEY_CAPTURE_MODEL";
    private final boolean captureBrand;
    private final boolean captureManufacturer;
    private final boolean captureModel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CaptureDeviceInfoConfig(boolean z, boolean z2, boolean z3) {
        this.captureModel = z;
        this.captureManufacturer = z2;
        this.captureBrand = z3;
    }

    public boolean isCaptureModel() {
        return this.captureModel;
    }

    public boolean isCaptureManufacturer() {
        return this.captureManufacturer;
    }

    public boolean isCaptureBrand() {
        return this.captureBrand;
    }

    public CaptureDeviceInfoConfig(Parcel parcel) {
        this.captureModel = parcel.readInt() > 0;
        this.captureManufacturer = parcel.readInt() > 0;
        this.captureBrand = parcel.readInt() > 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.captureModel ? 1 : 0);
        parcel.writeInt(this.captureManufacturer ? 1 : 0);
        parcel.writeInt(this.captureBrand ? 1 : 0);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(KEY_CAPTURE_MODEL, Boolean.valueOf(this.captureModel));
            jSONObject.putOpt(KEY_CAPTURE_MANUFACTURER, Boolean.valueOf(this.captureManufacturer));
            jSONObject.putOpt(KEY_CAPTURE_BRAND, Boolean.valueOf(this.captureBrand));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static CaptureDeviceInfoConfig fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new CaptureDeviceInfoConfig(jSONObject.optBoolean(KEY_CAPTURE_MODEL, false), jSONObject.optBoolean(KEY_CAPTURE_MANUFACTURER, false), jSONObject.optBoolean(KEY_CAPTURE_BRAND, false));
    }
}
