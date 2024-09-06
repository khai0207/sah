package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.VirtualCardInfo;

/* loaded from: classes.dex */
public class GetCardInfoResult implements Parcelable {
    public static final Parcelable.Creator<GetCardInfoResult> CREATOR = new Parcelable.Creator<GetCardInfoResult>() { // from class: com.unionpay.tsmservice.result.GetCardInfoResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetCardInfoResult createFromParcel(Parcel parcel) {
            return new GetCardInfoResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetCardInfoResult[] newArray(int i) {
            return new GetCardInfoResult[i];
        }
    };
    private VirtualCardInfo[] mVirtualCardInfo;

    public GetCardInfoResult() {
    }

    public GetCardInfoResult(Parcel parcel) {
        this.mVirtualCardInfo = (VirtualCardInfo[]) parcel.createTypedArray(VirtualCardInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VirtualCardInfo[] getVirtualCardInfo() {
        return this.mVirtualCardInfo;
    }

    public void setVirtualCardInfo(VirtualCardInfo[] virtualCardInfoArr) {
        this.mVirtualCardInfo = virtualCardInfoArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mVirtualCardInfo, i);
    }
}
