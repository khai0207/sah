package com.netease.nimlib.n.c;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SyncEventItem.java */
/* loaded from: classes.dex */
public class m implements Parcelable, Serializable {
    public static final Parcelable.Creator<m> CREATOR = new Parcelable.Creator<m>() { // from class: com.netease.nimlib.n.c.m.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public m createFromParcel(Parcel parcel) {
            return new m(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public m[] newArray(int i) {
            return new m[i];
        }
    };
    private int a = 0;
    private long b = 0;
    private long c = 0;
    private long d = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Map<String, Object> a() {
        HashMap hashMap = new HashMap(6);
        hashMap.put("total", Integer.valueOf(this.a));
        hashMap.put("pre_item_recv_time", Long.valueOf(this.b));
        hashMap.put("cur_item_recv_time", Long.valueOf(this.c));
        hashMap.put("callback_time", Long.valueOf(this.d));
        return hashMap;
    }

    public int b() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(long j) {
        this.b = j;
    }

    public long c() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public void c(long j) {
        this.d = j;
    }

    public long d() {
        return this.c - this.b;
    }

    public long e() {
        return this.d - this.c;
    }

    public String toString() {
        return a().toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.a == mVar.a && this.b == mVar.b && this.c == mVar.c && this.d == mVar.d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.a), Long.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.d)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
    }

    public void a(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
    }

    public m() {
    }

    protected m(Parcel parcel) {
        a(parcel);
    }
}
