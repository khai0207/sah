package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.TransElement;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GetTransElementsResult implements Parcelable {
    public static final Parcelable.Creator<GetTransElementsResult> CREATOR = new Parcelable.Creator<GetTransElementsResult>() { // from class: com.unionpay.tsmservice.result.GetTransElementsResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetTransElementsResult createFromParcel(Parcel parcel) {
            return new GetTransElementsResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetTransElementsResult[] newArray(int i) {
            return new GetTransElementsResult[i];
        }
    };
    private TransElement[] mCommonElements;
    private TransElement[] mCreditElements;
    private TransElement[] mDebitElements;

    public GetTransElementsResult() {
    }

    public GetTransElementsResult(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(TransElement.class.getClassLoader());
        if (readParcelableArray != null) {
            this.mDebitElements = (TransElement[]) Arrays.copyOf(readParcelableArray, readParcelableArray.length, TransElement[].class);
        }
        Parcelable[] readParcelableArray2 = parcel.readParcelableArray(TransElement.class.getClassLoader());
        if (readParcelableArray2 != null) {
            this.mCreditElements = (TransElement[]) Arrays.copyOf(readParcelableArray2, readParcelableArray2.length, TransElement[].class);
        }
        Parcelable[] readParcelableArray3 = parcel.readParcelableArray(TransElement.class.getClassLoader());
        if (readParcelableArray3 != null) {
            this.mCommonElements = (TransElement[]) Arrays.copyOf(readParcelableArray3, readParcelableArray3.length, TransElement[].class);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransElement[] getCommonElements() {
        return this.mCommonElements;
    }

    public TransElement[] getCreditElements() {
        return this.mCreditElements;
    }

    public TransElement[] getDebitElements() {
        return this.mDebitElements;
    }

    public void setCommonElements(TransElement[] transElementArr) {
        this.mCommonElements = transElementArr;
    }

    public void setCreditElements(TransElement[] transElementArr) {
        this.mCreditElements = transElementArr;
    }

    public void setDebitElements(TransElement[] transElementArr) {
        this.mDebitElements = transElementArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.mDebitElements, i);
        parcel.writeParcelableArray(this.mCreditElements, i);
        parcel.writeParcelableArray(this.mCommonElements, i);
    }
}
