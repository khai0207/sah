package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import com.talkingdata.sdk.df;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;
import java.util.Map;

/* compiled from: MsgSendEventModel.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.apm.b.b<com.netease.nimlib.apm.b.c> {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() { // from class: com.netease.nimlib.n.e.g.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g[] newArray(int i) {
            return new g[i];
        }
    };
    private String a;
    private String b;
    private long c;
    private long d;
    private long e;
    private long f;
    private long g;
    private String h;
    private String i;
    private String j;
    private int k;
    private long l;
    private String m;
    private int n;
    private String o;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "msgSend";
    }

    public static g r() {
        g gVar = new g();
        gVar.p();
        return gVar;
    }

    public String s() {
        return this.a;
    }

    public void d(String str) {
        this.a = str;
    }

    public String t() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public long u() {
        return this.c;
    }

    public void c(long j) {
        this.c = j;
        a(j);
    }

    public long v() {
        return this.d;
    }

    public void d(long j) {
        this.d = j;
    }

    public long w() {
        return this.e;
    }

    public void e(long j) {
        this.e = j;
    }

    public long x() {
        return this.f;
    }

    public void f(long j) {
        this.f = j;
    }

    public long y() {
        return this.g;
    }

    public void g(long j) {
        this.g = j;
    }

    public String z() {
        return d();
    }

    public void f(String str) {
        a(str);
    }

    public String A() {
        return this.h;
    }

    public void g(String str) {
        this.h = str;
    }

    public String B() {
        return this.i;
    }

    public void h(String str) {
        this.i = str;
    }

    public String C() {
        return this.j;
    }

    public void i(String str) {
        this.j = str;
    }

    public int D() {
        return this.k;
    }

    public void b(int i) {
        this.k = i;
    }

    public long E() {
        return this.l;
    }

    public void h(long j) {
        this.l = j;
    }

    public String F() {
        return this.m;
    }

    public void j(String str) {
        this.m = str;
    }

    public int G() {
        return this.n;
    }

    public void c(int i) {
        this.n = i;
    }

    public String H() {
        return this.o;
    }

    public void k(String str) {
        this.o = str;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.apm.b.c> q() {
        return com.netease.nimlib.apm.b.c.CREATOR;
    }

    public g() {
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (s() != null) {
            map.put("msgId", s());
        }
        if (t() != null) {
            map.put("clientId", t());
        }
        map.put("apiCallingTime", Long.valueOf(u()));
        map.put("attachUploadDuration", Long.valueOf(v()));
        map.put("sendTime", Long.valueOf(w()));
        map.put("apiCallbackTime", Long.valueOf(y()));
        map.put("msgTime", Long.valueOf(x()));
        if (z() != null) {
            map.put("fromAccid", z());
        }
        if (A() != null) {
            map.put("toAccid", A());
        }
        if (B() != null) {
            map.put(df.c, B());
        }
        if (C() != null) {
            map.put("eid", C());
        }
        map.put("type", Integer.valueOf(D()));
        if (E() > 0) {
            map.put("roomId", Long.valueOf(E()));
        }
        if (F() != null) {
            map.put("tid", F());
        }
        map.put("rt", Long.valueOf(g()));
        map.put("result", Integer.valueOf(G()));
        if (H() != null) {
            map.put("failReason", H());
        }
        if (j() != null) {
            map.put("net_type", Integer.valueOf(j().a()));
        }
        if (k() != null) {
            map.put("net_connect", k());
        }
        return map;
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        parcel.writeLong(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        this.l = parcel.readLong();
        this.m = parcel.readString();
        this.n = parcel.readInt();
        this.o = parcel.readString();
    }

    protected g(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        this.l = parcel.readLong();
        this.m = parcel.readString();
        this.n = parcel.readInt();
        this.o = parcel.readString();
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g) || !super.equals(obj)) {
            return false;
        }
        g gVar = (g) obj;
        return this.k == gVar.k && this.l == gVar.l && this.n == gVar.n && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, gVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, gVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.c), Long.valueOf(gVar.c)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.d), Long.valueOf(gVar.d)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.e), Long.valueOf(gVar.e)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.f), Long.valueOf(gVar.f)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.g), Long.valueOf(gVar.g)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.h, gVar.h) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.i, gVar.i) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.j, gVar.j) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.m, gVar.m) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.o, gVar.o);
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.a, this.b, Long.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.f), Long.valueOf(this.g), this.h, this.i, this.j, Integer.valueOf(this.k), Long.valueOf(this.l), this.m, Integer.valueOf(this.n), this.o});
    }
}
