package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.PhysicalCardInfo;

/* loaded from: classes.dex */
public class AppDownloadApplyResult implements Parcelable {
    public static final Parcelable.Creator<AppDownloadApplyResult> CREATOR = new Parcelable.Creator<AppDownloadApplyResult>() { // from class: com.unionpay.tsmservice.result.AppDownloadApplyResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDownloadApplyResult createFromParcel(Parcel parcel) {
            return new AppDownloadApplyResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDownloadApplyResult[] newArray(int i) {
            return new AppDownloadApplyResult[i];
        }
    };
    private PhysicalCardInfo mPhysicalCardInfo;

    public AppDownloadApplyResult() {
    }

    public AppDownloadApplyResult(Parcel parcel) {
        this.mPhysicalCardInfo = (PhysicalCardInfo) parcel.readParcelable(PhysicalCardInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PhysicalCardInfo getPhysicalCardInfo() {
        return this.mPhysicalCardInfo;
    }

    public void setPhysicalCardInfo(PhysicalCardInfo physicalCardInfo) {
        this.mPhysicalCardInfo = physicalCardInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mPhysicalCardInfo, i);
    }
}
