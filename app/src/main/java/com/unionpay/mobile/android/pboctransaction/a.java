package com.unionpay.mobile.android.pboctransaction;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class a implements Parcelable.Creator<AppIdentification> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AppIdentification createFromParcel(Parcel parcel) {
        AppIdentification appIdentification = new AppIdentification((byte) 0);
        appIdentification.a = parcel.readString();
        appIdentification.b = parcel.readString();
        return appIdentification;
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ AppIdentification[] newArray(int i) {
        return new AppIdentification[i];
    }
}
