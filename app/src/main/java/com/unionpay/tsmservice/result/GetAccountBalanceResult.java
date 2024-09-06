package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.AssociatedCardBalance;

/* loaded from: classes.dex */
public class GetAccountBalanceResult implements Parcelable {
    public static final Parcelable.Creator<GetAccountBalanceResult> CREATOR = new Parcelable.Creator<GetAccountBalanceResult>() { // from class: com.unionpay.tsmservice.result.GetAccountBalanceResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAccountBalanceResult createFromParcel(Parcel parcel) {
            return new GetAccountBalanceResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAccountBalanceResult[] newArray(int i) {
            return new GetAccountBalanceResult[i];
        }
    };
    private AssociatedCardBalance mAssociatedCardBalance;

    public GetAccountBalanceResult() {
    }

    public GetAccountBalanceResult(Parcel parcel) {
        this.mAssociatedCardBalance = (AssociatedCardBalance) parcel.readParcelable(AssociatedCardBalance.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AssociatedCardBalance getAssociatedCardBalance() {
        return this.mAssociatedCardBalance;
    }

    public void setAssociatedCardBalance(AssociatedCardBalance associatedCardBalance) {
        this.mAssociatedCardBalance = associatedCardBalance;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAssociatedCardBalance, i);
    }
}
