package com.talkingdata.sdk;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: td */
/* loaded from: classes.dex */
final class al implements Parcelable.Creator {
    al() {
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ak createFromParcel(Parcel parcel) {
        try {
            return new ak(parcel);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ak[] newArray(int i) {
        try {
            return new ak[i];
        } catch (Throwable unused) {
            return null;
        }
    }
}
