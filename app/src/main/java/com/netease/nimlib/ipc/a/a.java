package com.netease.nimlib.ipc.a;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* compiled from: AppStatus.java */
/* loaded from: classes.dex */
public class a implements Parcelable, Serializable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.netease.nimlib.ipc.a.a.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }
    };
    private byte a;
    private String b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public a(boolean z) {
        this.a = z ? (byte) 1 : (byte) 0;
    }

    public a(String str) {
        this.b = str;
    }

    public boolean a() {
        return this.a == 1;
    }

    public String b() {
        return this.b;
    }

    private a(Parcel parcel) {
        this.a = parcel.readByte();
        this.b = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a);
        parcel.writeString(this.b);
    }
}
