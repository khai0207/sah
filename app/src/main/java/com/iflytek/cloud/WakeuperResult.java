package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class WakeuperResult implements Parcelable {
    public static final Parcelable.Creator<WakeuperResult> CREATOR = new q();
    private String a;

    public WakeuperResult(Parcel parcel) {
        this.a = "";
        this.a = parcel.readString();
    }

    public WakeuperResult(String str) {
        this.a = "";
        if (str != null) {
            this.a = str;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getResultString() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
