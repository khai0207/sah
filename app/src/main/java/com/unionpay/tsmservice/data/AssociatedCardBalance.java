package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AssociatedCardBalance implements Parcelable {
    public static final Parcelable.Creator<AssociatedCardBalance> CREATOR = new Parcelable.Creator<AssociatedCardBalance>() { // from class: com.unionpay.tsmservice.data.AssociatedCardBalance.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AssociatedCardBalance createFromParcel(Parcel parcel) {
            return new AssociatedCardBalance(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AssociatedCardBalance[] newArray(int i) {
            return new AssociatedCardBalance[i];
        }
    };
    private String mBalance;

    public AssociatedCardBalance() {
    }

    public AssociatedCardBalance(Parcel parcel) {
        this.mBalance = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBalance() {
        return this.mBalance;
    }

    public void setBalance(String str) {
        this.mBalance = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mBalance);
    }
}
