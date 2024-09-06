package com.netease.nimlib.ipc.a;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* compiled from: MixPushState.java */
/* loaded from: classes.dex */
public class c implements Parcelable, Serializable {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.netease.nimlib.ipc.a.c.1
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
    private int a;
    private byte b;
    private String c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public c(int i, boolean z, String str) {
        this.a = i;
        this.b = z ? (byte) 1 : (byte) 0;
        this.c = str;
    }

    public c(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readByte();
        this.c = parcel.readString();
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return this.b == 1;
    }

    public String c() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeByte(this.b);
        parcel.writeString(this.c);
    }

    public String toString() {
        return "MixPushState{pushType=" + this.a + ", hasPushed=" + ((int) this.b) + ", lastDeviceId='" + this.c + "'}";
    }
}
