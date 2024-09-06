package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.AppDetail;

/* loaded from: classes.dex */
public class GetAppDetailResult implements Parcelable {
    public static final Parcelable.Creator<GetAppDetailResult> CREATOR = new Parcelable.Creator<GetAppDetailResult>() { // from class: com.unionpay.tsmservice.result.GetAppDetailResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAppDetailResult createFromParcel(Parcel parcel) {
            return new GetAppDetailResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAppDetailResult[] newArray(int i) {
            return new GetAppDetailResult[i];
        }
    };
    private AppDetail mAppDetail;

    public GetAppDetailResult() {
    }

    public GetAppDetailResult(Parcel parcel) {
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
