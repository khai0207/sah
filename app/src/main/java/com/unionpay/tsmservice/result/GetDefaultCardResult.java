package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class GetDefaultCardResult implements Parcelable {
    public static final Parcelable.Creator<GetDefaultCardResult> CREATOR = new Parcelable.Creator<GetDefaultCardResult>() { // from class: com.unionpay.tsmservice.result.GetDefaultCardResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetDefaultCardResult createFromParcel(Parcel parcel) {
            return new GetDefaultCardResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetDefaultCardResult[] newArray(int i) {
            return new GetDefaultCardResult[i];
        }
    };
    private String mDefaultCard;

    public GetDefaultCardResult() {
    }

    public GetDefaultCardResult(Parcel parcel) {
        this.mDefaultCard = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDefaultCard() {
        return this.mDefaultCard;
    }

    public void setDefaultCard(String str) {
        this.mDefaultCard = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDefaultCard);
    }
}
