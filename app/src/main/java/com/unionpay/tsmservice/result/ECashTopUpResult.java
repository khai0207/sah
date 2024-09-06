package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ECashTopUpResult implements Parcelable {
    public static final Parcelable.Creator<ECashTopUpResult> CREATOR = new Parcelable.Creator<ECashTopUpResult>() { // from class: com.unionpay.tsmservice.result.ECashTopUpResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ECashTopUpResult createFromParcel(Parcel parcel) {
            return new ECashTopUpResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ECashTopUpResult[] newArray(int i) {
            return new ECashTopUpResult[i];
        }
    };
    private String balance;
    private String overdraw;

    public ECashTopUpResult() {
    }

    public ECashTopUpResult(Parcel parcel) {
        this.balance = parcel.readString();
        this.overdraw = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBalance() {
        return this.balance;
    }

    public String getOverdraw() {
        return this.overdraw;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public void setOverdraw(String str) {
        this.overdraw = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.balance);
        parcel.writeString(this.overdraw);
    }
}
