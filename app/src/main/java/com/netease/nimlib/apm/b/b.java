package com.netease.nimlib.apm.b;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.apm.b.a;
import com.netease.nimlib.n.b.h;
import com.netease.nimlib.network.f;
import com.netease.nimlib.o.p;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BaseEventModel.java */
/* loaded from: classes.dex */
public abstract class b<T extends a> implements Parcelable, Serializable {
    private String b;
    private List<T> f;
    private String a = null;
    private String c = null;
    private boolean d = false;
    private Integer e = null;
    private long g = 0;
    private long h = 0;
    private boolean i = false;
    private d j = d.STAT_NET_TYPE_UNKNOWN;
    private Boolean k = null;

    public abstract Map<String, Object> a(Map<String, Object> map);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long n() {
        return 0L;
    }

    public abstract String o();

    public abstract Parcelable.Creator<T> q();

    public boolean a() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public long b() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }

    public long c() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public String d() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String e() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String f() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public long g() {
        return this.h - this.g;
    }

    public boolean h() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
        this.e = Integer.valueOf((z ? h.kSucceed : h.kFailed).a());
    }

    public Integer i() {
        return this.e;
    }

    public void a(int i) {
        this.e = Integer.valueOf(i);
        this.d = i == h.kSucceed.a();
    }

    public d j() {
        return this.j;
    }

    public Boolean k() {
        return this.k;
    }

    public List<T> l() {
        return this.f;
    }

    public void a(List<T> list) {
        this.f = list;
    }

    public Map<String, Object> m() {
        HashMap hashMap = new HashMap();
        if (d() != null) {
            hashMap.put("user_id", d());
        }
        if (e() != null) {
            hashMap.put("trace_id", e());
        }
        return a(hashMap);
    }

    protected void p() {
        Context e = com.netease.nimlib.c.e();
        this.j = d.b(p.j(e));
        this.k = Boolean.valueOf(f.a(e));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeValue(this.e);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeTypedList(this.f);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
    }

    public void a(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readByte() != 0;
        this.e = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.f = parcel.createTypedArrayList(q());
        this.i = parcel.readByte() != 0;
    }

    public b() {
    }

    protected b(Parcel parcel) {
        a(parcel);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.d == bVar.d && this.g == bVar.g && this.h == bVar.h && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, bVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, bVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, bVar.c) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.e, bVar.e) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.f, bVar.f);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c, Boolean.valueOf(this.d), this.e, this.f, Long.valueOf(this.g), Long.valueOf(this.h)});
    }
}
