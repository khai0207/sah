package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.SMSAuthCode;

/* loaded from: classes.dex */
public class GetSMSAuthCodeResult implements Parcelable {
    public static final Parcelable.Creator<GetSMSAuthCodeResult> CREATOR = new Parcelable.Creator<GetSMSAuthCodeResult>() { // from class: com.unionpay.tsmservice.result.GetSMSAuthCodeResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSMSAuthCodeResult createFromParcel(Parcel parcel) {
            return new GetSMSAuthCodeResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSMSAuthCodeResult[] newArray(int i) {
            return new GetSMSAuthCodeResult[i];
        }
    };
    private SMSAuthCode smsAuthCode;

    public GetSMSAuthCodeResult() {
    }

    public GetSMSAuthCodeResult(Parcel parcel) {
        this.smsAuthCode = (SMSAuthCode) parcel.readParcelable(SMSAuthCode.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SMSAuthCode getSmsAuthCode() {
        return this.smsAuthCode;
    }

    public void setSmsAuthCode(SMSAuthCode sMSAuthCode) {
        this.smsAuthCode = sMSAuthCode;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.smsAuthCode, i);
    }
}
