package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;
import java.util.Map;

/* compiled from: ApiTraceEventModel.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.apm.b.b<com.netease.nimlib.apm.b.c> {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.netease.nimlib.n.e.a.1
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
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private Integer f;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public long n() {
        return -20000L;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "nim_api_trace";
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

    public String t() {
        return this.c;
    }

    public void f(String str) {
        this.c = str;
    }

    public int u() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public String v() {
        return this.e;
    }

    public void g(String str) {
        this.e = str;
    }

    public Integer w() {
        return this.f;
    }

    public void a(com.netease.nimlib.n.b.h hVar) {
        super.a(hVar.a());
    }

    @Override // com.netease.nimlib.apm.b.b
    public void c(String str) {
        super.c(str);
    }

    public void a(com.netease.nimlib.n.b.a aVar) {
        if (aVar == null) {
            com.netease.nimlib.log.b.H("EMApiTraceAction is null");
        } else {
            c(String.valueOf(aVar.a()));
        }
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (f() != null) {
            map.put("action", f());
        }
        map.put("start_time", Long.valueOf(b()));
        map.put("duration", Long.valueOf(g()));
        if (i() != null) {
            map.put(TransferTable.COLUMN_STATE, i());
        }
        map.put("class_name", r());
        map.put(com.alipay.sdk.m.l.e.k, s());
        if (!TextUtils.isEmpty(t())) {
            map.put("error_msg", t());
        }
        map.put("error_code", Integer.valueOf(u()));
        map.put("active_id", v());
        if (w() != null) {
            map.put("lag", w());
        }
        return map;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.apm.b.c> q() {
        return com.netease.nimlib.apm.b.c.CREATOR;
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a) || !super.equals(obj)) {
            return false;
        }
        a aVar = (a) obj;
        return this.d == aVar.d && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, aVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, aVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, aVar.c) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.e, aVar.e) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.f, aVar.f);
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.a, this.b, this.c, Integer.valueOf(this.d), this.e, this.f});
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeValue(this.f);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }

    public a() {
    }

    protected a(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }
}
