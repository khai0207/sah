package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SeAppListItem implements Parcelable {
    public static final Parcelable.Creator<SeAppListItem> CREATOR = new Parcelable.Creator<SeAppListItem>() { // from class: com.unionpay.tsmservice.data.SeAppListItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SeAppListItem createFromParcel(Parcel parcel) {
            return new SeAppListItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SeAppListItem[] newArray(int i) {
            return new SeAppListItem[i];
        }
    };
    private AppDetail mAppDetail;
    private VirtualCardInfo mVirtualCardInfo;

    public SeAppListItem() {
    }

    public SeAppListItem(Parcel parcel) {
        this.mAppDetail = (AppDetail) parcel.readParcelable(AppDetail.class.getClassLoader());
        this.mVirtualCardInfo = (VirtualCardInfo) parcel.readParcelable(VirtualCardInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppDetail getAppDetail() {
        return this.mAppDetail;
    }

    public VirtualCardInfo getVirtualCardInfo() {
        return this.mVirtualCardInfo;
    }

    public void setAppDetail(AppDetail appDetail) {
        this.mAppDetail = appDetail;
    }

    public void setVirtualCardInfo(VirtualCardInfo virtualCardInfo) {
        this.mVirtualCardInfo = virtualCardInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAppDetail, i);
        parcel.writeParcelable(this.mVirtualCardInfo, i);
    }
}
