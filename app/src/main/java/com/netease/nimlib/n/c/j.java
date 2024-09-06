package com.netease.nimlib.n.c;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: NosEventExtension.java */
/* loaded from: classes.dex */
public class j extends b {
    public static final Parcelable.Creator<j> CREATOR = new Parcelable.Creator<j>() { // from class: com.netease.nimlib.n.c.j.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j[] newArray(int i) {
            return new j[i];
        }
    };

    public j() {
    }

    public j(Parcel parcel) {
        super(parcel);
    }
}
