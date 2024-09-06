package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;
import java.util.Map;

/* compiled from: ChatRoomLoginEventModel.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.apm.b.b<com.netease.nimlib.apm.b.c> {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.netease.nimlib.n.e.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }
    };
    private long a;
    private String b;
    private String c;
    private String d;
    private int e;
    private String f;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "chatroomLogin";
    }

    public void d(String str) {
        a(str);
    }

    public long r() {
        return this.a;
    }

    public void c(long j) {
        this.a = j;
    }

    public String s() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public String t() {
        return this.c;
    }

    public void f(String str) {
        this.c = str;
    }

    public String u() {
        return this.d;
    }

    public void g(String str) {
        this.d = str;
    }

    public void d(long j) {
        a(j);
    }

    public int v() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public String w() {
        return this.f;
    }

    public void h(String str) {
        this.f = str;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.apm.b.c> q() {
        return com.netease.nimlib.apm.b.c.CREATOR;
    }

    public b() {
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (d() != null) {
            map.put("accid", d());
        }
        map.put("roomId", Long.valueOf(r()));
        if (s() != null) {
            map.put("serverIps", s());
        }
        if (t() != null) {
            map.put("currentServerIp", t());
        }
        map.put("network", u());
        map.put("time", Long.valueOf(b()));
        map.put("rt", Long.valueOf(g()));
        map.put("result", Integer.valueOf(v()));
        if (w() != null) {
            map.put("failReason", w());
        }
        return map;
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readString();
    }

    protected b(Parcel parcel) {
        super(parcel);
        this.a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readString();
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b) || !super.equals(obj)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.e == bVar.e && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, bVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, bVar.c) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.d, bVar.d) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.f, bVar.f);
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Long.valueOf(this.a), this.b, this.c, this.d, Integer.valueOf(this.e), this.f});
    }
}
