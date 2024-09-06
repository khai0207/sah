package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AssociatedCardInfo implements Parcelable {
    public static final Parcelable.Creator<AssociatedCardInfo> CREATOR = new Parcelable.Creator<AssociatedCardInfo>() { // from class: com.unionpay.tsmservice.data.AssociatedCardInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AssociatedCardInfo createFromParcel(Parcel parcel) {
            return new AssociatedCardInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AssociatedCardInfo[] newArray(int i) {
            return new AssociatedCardInfo[i];
        }
    };
    private String mCardNo;
    private String mCardType;

    public AssociatedCardInfo() {
    }

    public AssociatedCardInfo(Parcel parcel) {
        this.mCardType = parcel.readString();
        this.mCardNo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCardNo() {
        return this.mCardNo;
    }

    public String getCardType() {
        return this.mCardType;
    }

    public void setCardNo(String str) {
        this.mCardNo = str;
    }

    public void setCardType(String str) {
        this.mCardType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardType);
        parcel.writeString(this.mCardNo);
    }
}
