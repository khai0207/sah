package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class q implements Parcelable.Creator<WakeuperResult> {
    q() {
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WakeuperResult createFromParcel(Parcel parcel) {
        return new WakeuperResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WakeuperResult[] newArray(int i) {
        return new WakeuperResult[i];
    }
}
