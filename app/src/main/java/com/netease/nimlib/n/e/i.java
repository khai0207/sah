package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: ResourceEventModel.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.apm.b.b<com.netease.nimlib.n.c.j> {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.netease.nimlib.n.e.i.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i) {
            return new i[i];
        }
    };
    int a;
    String b;
    long c;
    long d;
    long e;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "nim_sdk_resources";
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.n.c.j> q() {
        return com.netease.nimlib.n.c.j.CREATOR;
    }

    public static i r() {
        i iVar = new i();
        iVar.p();
        return iVar;
    }

    public int s() {
        return this.a;
    }

    public void b(int i) {
        this.a = i;
    }

    public String d(String str) {
        String str2 = this.b;
        return str2 == null ? str : str2;
    }

    public void e(String str) {
        this.b = str;
    }

    public long t() {
        return this.c;
    }

    public void c(long j) {
        this.c = j;
    }

    public long u() {
        return this.d;
    }

    public void d(long j) {
        this.d = j;
    }

    public long v() {
        return this.e;
    }

    public void e(long j) {
        this.e = j;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Integer i() {
        Integer i = super.i();
        return i == null ? Integer.valueOf(com.netease.nimlib.n.b.h.kUnknown.a()) : i;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        Object d = com.netease.nimlib.biz.i.a().d();
        if (f() != null) {
            map.put("action", f());
        }
        if (d != null) {
            map.put("trace_id", d);
        }
        map.put("start_time", Long.valueOf(b()));
        map.put("duration", Long.valueOf(g()));
        if (i() != null) {
            map.put(TransferTable.COLUMN_STATE, i());
        }
        map.put("operation_type", Integer.valueOf(s()));
        map.put("remote_addr", d(""));
        map.put("offset", Long.valueOf(t()));
        map.put("full_size", Long.valueOf(u()));
        map.put("transferred_size", Long.valueOf(v()));
        if (j() != null) {
            map.put("net_type", Integer.valueOf(j().a()));
        }
        if (k() != null) {
            map.put("net_connect", k());
        }
        List<com.netease.nimlib.n.c.j> l = l();
        if (l != null) {
            ArrayList arrayList = new ArrayList();
            for (com.netease.nimlib.n.c.j jVar : l) {
                if (jVar != null) {
                    arrayList.add(jVar.d());
                }
            }
            map.put("extension", arrayList);
        }
        return map;
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
    }

    public i() {
    }

    protected i(Parcel parcel) {
        super(parcel);
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i) || !super.equals(obj)) {
            return false;
        }
        i iVar = (i) obj;
        return this.a == iVar.a && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, iVar.b) && this.c == iVar.c && this.d == iVar.d && this.e == iVar.e;
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Integer.valueOf(this.a), this.b, Long.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e)});
    }
}
