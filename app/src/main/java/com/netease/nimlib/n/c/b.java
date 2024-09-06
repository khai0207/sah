package com.netease.nimlib.n.c;

import android.os.Parcel;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;
import java.util.Map;

/* compiled from: CommonEventExtension.java */
/* loaded from: classes.dex */
public abstract class b extends com.netease.nimlib.apm.b.a {
    private Integer a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private long f;
    private long g;

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = 0L;
        this.g = 0L;
    }

    protected b(Integer num, String str, String str2, String str3, long j, boolean z) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = 0L;
        this.g = 0L;
        this.a = num;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
    }

    public void a(int i) {
        this.a = Integer.valueOf(i);
    }

    public String e() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(long j) {
        this.f = j;
    }

    public void b(long j) {
        this.g = j;
    }

    public long f() {
        return this.g - this.f;
    }

    @Override // com.netease.nimlib.apm.b.a
    public Map<String, Object> d() {
        Map<String, Object> d = super.d();
        d.put("succeed", Boolean.valueOf(this.e));
        Integer num = this.a;
        if (num != null) {
            d.put("code", num);
        }
        String str = this.b;
        if (str != null) {
            d.put("operation_type", str);
        }
        String str2 = this.c;
        if (str2 != null) {
            d.put("target", str2);
        }
        String str3 = this.d;
        if (str3 != null) {
            d.put("description", str3);
        }
        d.put("duration", Long.valueOf(f()));
        return d;
    }

    @Override // com.netease.nimlib.apm.b.a
    public boolean a(com.netease.nimlib.apm.b.a aVar) {
        if (!(aVar instanceof b)) {
            return false;
        }
        b bVar = (b) aVar;
        if (equals(bVar)) {
            return true;
        }
        return super.a(aVar) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, bVar.a) && this.e == bVar.e && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, bVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, bVar.c) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.d, bVar.d);
    }

    @Override // com.netease.nimlib.apm.b.a
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return super.equals(obj) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, bVar.a) && this.e == bVar.e && this.f == bVar.f && this.g == bVar.g && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, bVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, bVar.c) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.d, bVar.d);
    }

    @Override // com.netease.nimlib.apm.b.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.a, this.b, this.c, this.d, Boolean.valueOf(this.e), Long.valueOf(this.f), Long.valueOf(this.g)});
    }

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
    }

    @Override // com.netease.nimlib.apm.b.a
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readByte() != 0;
        this.f = parcel.readLong();
        this.g = parcel.readLong();
    }

    protected b(Parcel parcel) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = 0L;
        this.g = 0L;
        a(parcel);
    }
}
