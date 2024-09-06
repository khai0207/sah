package com.netease.nimlib.sdk.chatroom.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.chatroom.model.a;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CdnRequestData implements Parcelable, Serializable {
    public static final Parcelable.Creator<CdnRequestData> CREATOR = new Parcelable.Creator<CdnRequestData>() { // from class: com.netease.nimlib.sdk.chatroom.model.CdnRequestData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CdnRequestData createFromParcel(Parcel parcel) {
            return new CdnRequestData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CdnRequestData[] newArray(int i) {
            return new CdnRequestData[i];
        }
    };
    private int failFinal;
    private String urlReqData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CdnRequestData(Map<String, a> map, int i) {
        this.failFinal = i;
        if (map == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, a> entry : map.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.urlReqData = jSONObject.toString();
    }

    public String getUrlReqData() {
        return this.urlReqData;
    }

    public int getFailFinal() {
        return this.failFinal;
    }

    protected CdnRequestData(Parcel parcel) {
        this.urlReqData = parcel.readString();
        this.failFinal = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.urlReqData);
        parcel.writeInt(this.failFinal);
    }
}
