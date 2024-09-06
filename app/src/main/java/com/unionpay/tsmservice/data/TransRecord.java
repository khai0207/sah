package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class TransRecord implements Parcelable {
    public static final Parcelable.Creator<TransRecord> CREATOR = new Parcelable.Creator<TransRecord>() { // from class: com.unionpay.tsmservice.data.TransRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final TransRecord createFromParcel(Parcel parcel) {
            return new TransRecord(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final TransRecord[] newArray(int i) {
            return new TransRecord[i];
        }
    };
    private String mMerchantName;
    private String mTransAmount;
    private String mTransTime;
    private String mTransType;

    public TransRecord() {
    }

    public TransRecord(Parcel parcel) {
        this.mTransTime = parcel.readString();
        this.mMerchantName = parcel.readString();
        this.mTransType = parcel.readString();
        this.mTransAmount = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getMerchantName() {
        return this.mMerchantName;
    }

    public String getTransAmount() {
        return this.mTransAmount;
    }

    public String getTransTime() {
        return this.mTransTime;
    }

    public String getTransType() {
        return this.mTransType;
    }

    public void setMerchantName(String str) {
        this.mMerchantName = str;
    }

    public void setTransAmount(String str) {
        this.mTransAmount = str;
    }

    public void setTransTime(String str) {
        this.mTransTime = str;
    }

    public void setTransType(String str) {
        this.mTransType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTransTime);
        parcel.writeString(this.mMerchantName);
        parcel.writeString(this.mTransType);
        parcel.writeString(this.mTransAmount);
    }
}
