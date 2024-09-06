package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.session.ac;
import com.talkingdata.sdk.df;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: MsgReceiveEventModel.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.apm.b.b<com.netease.nimlib.apm.b.c> {
    public static final Parcelable.Creator<f> CREATOR = new Parcelable.Creator<f>() { // from class: com.netease.nimlib.n.e.f.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i) {
            return new f[i];
        }
    };
    private String a;
    private String b;
    private long c;
    private long d;
    private long e;
    private long f;
    private int g;
    private long h;
    private String i;
    private String j;
    private String k;
    private String l;
    private int m;
    private long n;
    private String o;
    private int p;
    private String q;
    private boolean r;
    private long s;
    private long t;

    /* renamed from: u, reason: collision with root package name */
    private long f27u;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public long n() {
        return -10000L;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "msgReceive";
    }

    public String r() {
        return this.a;
    }

    public void d(String str) {
        this.a = str;
    }

    public String s() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public void c(long j) {
        this.c = j;
    }

    public void d(long j) {
        this.d = j;
    }

    public void e(long j) {
        this.e = j;
    }

    public void a(ac acVar) {
        if (acVar == null) {
            return;
        }
        long a = acVar.a();
        if (a > 0) {
            c(a);
        }
        long c = acVar.c();
        if (c > 0) {
            d(c);
        }
        long b = acVar.b();
        if (b > 0) {
            e(b);
        }
    }

    public long t() {
        return b();
    }

    public void f(long j) {
        a(j);
    }

    public long u() {
        return this.f;
    }

    public void g(long j) {
        this.f = j;
    }

    public long v() {
        return c();
    }

    public void h(long j) {
        b(j);
    }

    public int w() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public long x() {
        return this.h;
    }

    public void i(long j) {
        this.h = j;
    }

    public String y() {
        return this.i;
    }

    public void f(String str) {
        this.i = str;
    }

    public String z() {
        return this.j;
    }

    public void g(String str) {
        this.j = str;
    }

    public String A() {
        return this.k;
    }

    public void h(String str) {
        this.k = str;
    }

    public String B() {
        return this.l;
    }

    public void i(String str) {
        this.l = str;
    }

    public int C() {
        return this.m;
    }

    public void c(int i) {
        this.m = i;
    }

    public long D() {
        return this.n;
    }

    public void j(long j) {
        this.n = j;
    }

    public String E() {
        return this.o;
    }

    public void j(String str) {
        this.o = str;
    }

    public int F() {
        return this.p;
    }

    public void d(int i) {
        this.p = i;
    }

    public String G() {
        return this.q;
    }

    public void k(String str) {
        this.q = str;
    }

    public boolean H() {
        return this.r;
    }

    public void c(boolean z) {
        this.r = z;
    }

    public void k(long j) {
        this.s = j;
    }

    public void l(long j) {
        this.t = j;
    }

    public void m(long j) {
        this.f27u = j;
    }

    public long I() {
        return v() - this.f;
    }

    public boolean J() {
        if (com.netease.nimlib.abtest.b.d()) {
            return t() - this.f <= com.netease.nimlib.abtest.b.e();
        }
        return true;
    }

    public JSONObject K() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.s > 0) {
                jSONObject.put("reference_delay", this.s);
            }
            if (this.t > 0) {
                jSONObject.put("last_fg_switch_time", this.t);
            }
            if (this.f27u > 0) {
                jSONObject.put("last_bg_switch_time", this.f27u);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgReceiveEventModel", "getContext error", th);
        }
        return jSONObject;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.apm.b.c> q() {
        return com.netease.nimlib.apm.b.c.CREATOR;
    }

    public f() {
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (r() != null) {
            map.put("msgId", r());
        }
        if (s() != null) {
            map.put("clientId", s());
        }
        long j = this.c;
        if (j > 0) {
            map.put("apiCallingTime", Long.valueOf(j));
            map.put("attachUploadDuration", Long.valueOf(this.d));
            map.put("sendTime", Long.valueOf(this.e));
        }
        map.put("serverTime", Long.valueOf(u()));
        map.put("receiveTime", Long.valueOf(t()));
        map.put("callbackTime", Long.valueOf(v()));
        map.put("queueSize", Integer.valueOf(w()));
        map.put("preHandleTime", Long.valueOf(x()));
        if (y() != null) {
            map.put("fromAccid", y());
        }
        if (z() != null) {
            map.put("toAccid", z());
        }
        if (A() != null) {
            map.put(df.c, A());
        }
        if (B() != null) {
            map.put("eid", B());
        }
        map.put("type", Integer.valueOf(C()));
        if (D() > 0) {
            map.put("roomId", Long.valueOf(D()));
        }
        if (E() != null) {
            map.put("tid", E());
        }
        map.put("rt", Long.valueOf(I()));
        map.put("result", Integer.valueOf(F()));
        if (G() != null) {
            map.put("failReason", G());
        }
        map.put("is_ignored", Boolean.valueOf(H()));
        if (com.netease.nimlib.abtest.b.d()) {
            map.put("is_dt_reliable", Integer.valueOf(J() ? 1 : 0));
            map.put("context", K().toString());
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
        parcel.writeInt(this.g);
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeInt(this.m);
        parcel.writeLong(this.n);
        parcel.writeString(this.o);
        parcel.writeInt(this.p);
        parcel.writeString(this.q);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.s);
        parcel.writeLong(this.t);
        parcel.writeLong(this.f27u);
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
        this.g = parcel.readInt();
        this.h = parcel.readLong();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readInt();
        this.n = parcel.readLong();
        this.o = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readString();
        this.r = parcel.readByte() != 0;
        this.s = parcel.readLong();
        this.t = parcel.readLong();
        this.f27u = parcel.readLong();
    }

    protected f(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readInt();
        this.h = parcel.readLong();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readInt();
        this.n = parcel.readLong();
        this.o = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readString();
        this.r = parcel.readByte() != 0;
        this.s = parcel.readLong();
        this.t = parcel.readLong();
        this.f27u = parcel.readLong();
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f) || !super.equals(obj)) {
            return false;
        }
        f fVar = (f) obj;
        return this.f == fVar.f && this.g == fVar.g && this.h == fVar.h && this.m == fVar.m && this.n == fVar.n && this.p == fVar.p && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, fVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, fVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.c), Long.valueOf(fVar.c)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.d), Long.valueOf(fVar.d)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(Long.valueOf(this.e), Long.valueOf(fVar.e)) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.i, fVar.i) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.j, fVar.j) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.k, fVar.k) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.l, fVar.l) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.o, fVar.o) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.q, fVar.q) && this.r == fVar.r && this.s == fVar.s && this.t == fVar.t && this.f27u == fVar.f27u;
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.a, this.b, Long.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.f), Integer.valueOf(this.g), Long.valueOf(this.h), this.i, this.j, this.k, this.l, Integer.valueOf(this.m), Long.valueOf(this.n), this.o, Integer.valueOf(this.p), this.q, Boolean.valueOf(this.r), Long.valueOf(this.s), Long.valueOf(this.t), Long.valueOf(this.f27u)});
    }
}
