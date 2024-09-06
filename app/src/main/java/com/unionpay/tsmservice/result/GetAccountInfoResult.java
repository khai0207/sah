package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.AssociatedCardInfo;

/* loaded from: classes.dex */
public class GetAccountInfoResult implements Parcelable {
    public static final Parcelable.Creator<GetAccountInfoResult> CREATOR = new Parcelable.Creator<GetAccountInfoResult>() { // from class: com.unionpay.tsmservice.result.GetAccountInfoResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAccountInfoResult createFromParcel(Parcel parcel) {
            return new GetAccountInfoResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAccountInfoResult[] newArray(int i) {
            return new GetAccountInfoResult[i];
        }
    };
    private AssociatedCardInfo mAssociatedCardInfo;

    public GetAccountInfoResult() {
    }

    public GetAccountInfoResult(Parcel parcel) {
        this.mAssociatedCardInfo = (AssociatedCardInfo) parcel.readParcelable(AssociatedCardInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AssociatedCardInfo getAssociatedCardInfo() {
        return this.mAssociatedCardInfo;
    }

    public void setAssociatedCardInfo(AssociatedCardInfo associatedCardInfo) {
        this.mAssociatedCardInfo = associatedCardInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAssociatedCardInfo, i);
    }
}
