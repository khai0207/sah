package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: NosUploadEventModel.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.apm.b.b<com.netease.nimlib.n.c.j> {
    public static final Parcelable.Creator<h> CREATOR = new Parcelable.Creator<h>() { // from class: com.netease.nimlib.n.e.h.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i) {
            return new h[i];
        }
    };
    private long a;
    private long b;
    private long c;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "nos";
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.n.c.j> q() {
        return com.netease.nimlib.n.c.j.CREATOR;
    }

    public long r() {
        return this.b;
    }

    public void c(long j) {
        this.b = j;
    }

    public long s() {
        return this.c;
    }

    public void d(long j) {
        this.c = j;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        Object d = com.netease.nimlib.biz.i.a().d();
        if (d() != null) {
            map.put("user_id", d());
        }
        if (f() != null) {
            map.put("action", f());
        }
        if (d != null) {
            map.put("trace_id", d);
        }
        map.put("start_time", Long.valueOf(b()));
        map.put("duration", Long.valueOf(g()));
        map.put(TransferTable.COLUMN_STATE, i());
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
        parcel.writeLong(this.a);
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readLong();
    }

    public h() {
        this.a = 0L;
        this.b = 0L;
        this.c = 0L;
    }

    protected h(Parcel parcel) {
        super(parcel);
        this.a = 0L;
        this.b = 0L;
        this.c = 0L;
        this.a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readLong();
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        h hVar = (h) obj;
        return this.a == hVar.a && this.b == hVar.b && this.c == hVar.c;
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Long.valueOf(this.a), Long.valueOf(this.b), Long.valueOf(this.c)});
    }
}
