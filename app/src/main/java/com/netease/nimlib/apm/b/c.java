package com.netease.nimlib.apm.b;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/* compiled from: EmptyEventExtension.java */
/* loaded from: classes.dex */
public class c extends a {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.netease.nimlib.apm.b.c.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i) {
            return new c[i];
        }
    };

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    protected c(Parcel parcel) {
        super.a(parcel);
    }

    @Override // com.netease.nimlib.apm.b.a
    public Map<String, Object> d() {
        return super.d();
    }
}
