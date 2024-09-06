package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.AppDetail;

/* loaded from: classes.dex */
public class GetAssociatedAppResult implements Parcelable {
    public static final Parcelable.Creator<GetAssociatedAppResult> CREATOR = new Parcelable.Creator<GetAssociatedAppResult>() { // from class: com.unionpay.tsmservice.result.GetAssociatedAppResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAssociatedAppResult createFromParcel(Parcel parcel) {
            return new GetAssociatedAppResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAssociatedAppResult[] newArray(int i) {
            return new GetAssociatedAppResult[i];
        }
    };
    private AppDetail mAppDetail;

    public GetAssociatedAppResult() {
    }

    public GetAssociatedAppResult(Parcel parcel) {
        this.mAppDetail = (AppDetail) parcel.readParcelable(AppDetail.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppDetail getAppDetail() {
        return this.mAppDetail;
    }

    public void setAppDetail(AppDetail appDetail) {
        this.mAppDetail = appDetail;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAppDetail, i);
    }
}
