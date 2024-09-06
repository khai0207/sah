package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class TransElement implements Parcelable {
    public static final Parcelable.Creator<TransElement> CREATOR = new Parcelable.Creator<TransElement>() { // from class: com.unionpay.tsmservice.data.TransElement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final TransElement createFromParcel(Parcel parcel) {
            return new TransElement(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final TransElement[] newArray(int i) {
            return new TransElement[i];
        }
    };
    private boolean isMustShow;
    private String mHint;
    private String mLabel;
    private String mName;
    private String mType;
    private String mVerfyRule;

    public TransElement() {
        this.isMustShow = true;
        this.mLabel = "";
        this.mName = "";
        this.mHint = "";
        this.mVerfyRule = "";
        this.mType = "";
    }

    public TransElement(Parcel parcel) {
        this.isMustShow = true;
        this.mLabel = "";
        this.mName = "";
        this.mHint = "";
        this.mVerfyRule = "";
        this.mType = "";
        this.isMustShow = 1 == parcel.readInt();
        this.mLabel = parcel.readString();
        this.mName = parcel.readString();
        this.mHint = parcel.readString();
        this.mVerfyRule = parcel.readString();
        this.mType = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getHint() {
        return this.mHint;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getName() {
        return this.mName;
    }

    public String getType() {
        return this.mType;
    }

    public String getVerfyRule() {
        return this.mVerfyRule;
    }

    public boolean isMustShow() {
        return this.isMustShow;
    }

    public void setHint(String str) {
        this.mHint = str;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public void setMustShow(boolean z) {
        this.isMustShow = z;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVerfyRule(String str) {
        this.mVerfyRule = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.isMustShow ? 1 : 0);
        parcel.writeString(this.mLabel);
        parcel.writeString(this.mName);
        parcel.writeString(this.mHint);
        parcel.writeString(this.mVerfyRule);
        parcel.writeString(this.mType);
    }
}
