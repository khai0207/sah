package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Map;

/* compiled from: DatabaseTraceEventModel.java */
/* loaded from: classes.dex */
public class c extends a {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.netease.nimlib.n.e.c.1
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
    private String a;
    private long b;
    private String c;

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b
    public String o() {
        return "nim_sdk_database_trace";
    }

    public String x() {
        return this.a;
    }

    public void h(String str) {
        this.a = str;
    }

    public long y() {
        return this.b;
    }

    public void c(long j) {
        this.b = j;
    }

    public String z() {
        return this.c;
    }

    public void i(String str) {
        this.c = str;
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        super.a(map);
        map.put("db", x());
        return map;
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.apm.b.c> q() {
        return com.netease.nimlib.apm.b.c.CREATOR;
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof c) && super.equals(obj)) {
            return TextUtils.equals(x(), ((c) obj).x());
        }
        return false;
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), r(), s(), t(), Integer.valueOf(u()), v(), w(), x()});
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(x());
    }

    @Override // com.netease.nimlib.n.e.a, com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readString();
    }

    public c() {
        this.b = 0L;
    }

    protected c(Parcel parcel) {
        super(parcel);
        this.b = 0L;
        this.a = parcel.readString();
    }
}
