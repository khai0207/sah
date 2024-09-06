package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.TransRecord;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GetTransRecordResult implements Parcelable {
    public static final Parcelable.Creator<GetTransRecordResult> CREATOR = new Parcelable.Creator<GetTransRecordResult>() { // from class: com.unionpay.tsmservice.result.GetTransRecordResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetTransRecordResult createFromParcel(Parcel parcel) {
            return new GetTransRecordResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetTransRecordResult[] newArray(int i) {
            return new GetTransRecordResult[i];
        }
    };
    private TransRecord[] mTransRecord;

    public GetTransRecordResult() {
    }

    public GetTransRecordResult(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(TransRecord.class.getClassLoader());
        if (readParcelableArray != null) {
            this.mTransRecord = (TransRecord[]) Arrays.copyOf(readParcelableArray, readParcelableArray.length, TransRecord[].class);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransRecord[] getTransRecord() {
        return this.mTransRecord;
    }

    public void setTransRecord(TransRecord[] transRecordArr) {
        this.mTransRecord = transRecordArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.mTransRecord, i);
    }
}
