package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.AppDetail;

/* loaded from: classes.dex */
public class GetAppListResult implements Parcelable {
    public static final Parcelable.Creator<GetAppListResult> CREATOR = new Parcelable.Creator<GetAppListResult>() { // from class: com.unionpay.tsmservice.result.GetAppListResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAppListResult createFromParcel(Parcel parcel) {
            return new GetAppListResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetAppListResult[] newArray(int i) {
            return new GetAppListResult[i];
        }
    };
    private AppDetail[] mAppList;

    public GetAppListResult() {
    }

    public GetAppListResult(Parcel parcel) {
        this.mAppList = (AppDetail[]) parcel.createTypedArray(AppDetail.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppDetail[] getAppList() {
        return this.mAppList;
    }

    public void setAppList(AppDetail[] appDetailArr) {
        this.mAppList = appDetailArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mAppList, i);
    }
}
