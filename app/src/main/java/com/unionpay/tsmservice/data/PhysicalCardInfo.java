package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PhysicalCardInfo implements Parcelable {
    public static final Parcelable.Creator<PhysicalCardInfo> CREATOR = new Parcelable.Creator<PhysicalCardInfo>() { // from class: com.unionpay.tsmservice.data.PhysicalCardInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PhysicalCardInfo createFromParcel(Parcel parcel) {
            return new PhysicalCardInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PhysicalCardInfo[] newArray(int i) {
            return new PhysicalCardInfo[i];
        }
    };
    public static final String TYPE_CREDIT = "02";
    public static final String TYPE_DEBIT = "01";
    private String mBankLogo;
    private String mBankName;
    private String mCardIcon;
    private String mCardNo;
    private String mCardType;
    private String mReferenceID;

    public PhysicalCardInfo() {
        this.mReferenceID = "";
        this.mCardNo = "";
        this.mCardType = "";
        this.mCardIcon = "";
        this.mBankName = "";
        this.mBankLogo = "";
    }

    public PhysicalCardInfo(Parcel parcel) {
        this.mReferenceID = "";
        this.mCardNo = "";
        this.mCardType = "";
        this.mCardIcon = "";
        this.mBankName = "";
        this.mBankLogo = "";
        this.mReferenceID = parcel.readString();
        this.mCardNo = parcel.readString();
        this.mCardType = parcel.readString();
        this.mCardIcon = parcel.readString();
        this.mBankName = parcel.readString();
        this.mBankLogo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBankLogo() {
        return this.mBankLogo;
    }

    public String getBankName() {
        return this.mBankName;
    }

    public String getCardIcon() {
        return this.mCardIcon;
    }

    public String getCardNo() {
        return this.mCardNo;
    }

    public String getCardType() {
        return this.mCardType;
    }

    public String getReferenceID() {
        return this.mReferenceID;
    }

    public void setBankLogo(String str) {
        this.mBankLogo = str;
    }

    public void setBankName(String str) {
        this.mBankName = str;
    }

    public void setCardIcon(String str) {
        this.mCardIcon = str;
    }

    public void setCardNo(String str) {
        this.mCardNo = str;
    }

    public void setCardType(String str) {
        this.mCardType = str;
    }

    public void setReferenceID(String str) {
        this.mReferenceID = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mReferenceID);
        parcel.writeString(this.mCardNo);
        parcel.writeString(this.mCardType);
        parcel.writeString(this.mCardIcon);
        parcel.writeString(this.mBankName);
        parcel.writeString(this.mBankLogo);
    }
}
