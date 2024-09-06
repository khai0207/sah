package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.SeAppListItem;

/* loaded from: classes.dex */
public class GetSeAppListResult implements Parcelable {
    public static final Parcelable.Creator<GetSeAppListResult> CREATOR = new Parcelable.Creator<GetSeAppListResult>() { // from class: com.unionpay.tsmservice.result.GetSeAppListResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeAppListResult createFromParcel(Parcel parcel) {
            return new GetSeAppListResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeAppListResult[] newArray(int i) {
            return new GetSeAppListResult[i];
        }
    };
    private SeAppListItem[] mSeAppList;

    public GetSeAppListResult() {
    }

    public GetSeAppListResult(Parcel parcel) {
        this.mSeAppList = (SeAppListItem[]) parcel.createTypedArray(SeAppListItem.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SeAppListItem[] getSeAppList() {
        return this.mSeAppList;
    }

    public void setSeAppList(SeAppListItem[] seAppListItemArr) {
        this.mSeAppList = seAppListItemArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mSeAppList, i);
    }
}
