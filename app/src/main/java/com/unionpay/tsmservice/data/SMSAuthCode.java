package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SMSAuthCode implements Parcelable {
    public static final Parcelable.Creator<SMSAuthCode> CREATOR = new Parcelable.Creator<SMSAuthCode>() { // from class: com.unionpay.tsmservice.data.SMSAuthCode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SMSAuthCode createFromParcel(Parcel parcel) {
            return new SMSAuthCode(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SMSAuthCode[] newArray(int i) {
            return new SMSAuthCode[i];
        }
    };
    private String mExpireNote;

    public SMSAuthCode() {
    }

    public SMSAuthCode(Parcel parcel) {
        this.mExpireNote = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getExpireNote() {
        return this.mExpireNote;
    }

    public void setExpireNote(String str) {
        this.mExpireNote = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mExpireNote);
    }
}
