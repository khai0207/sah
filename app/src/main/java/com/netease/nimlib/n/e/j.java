package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.netease.nimlib.n.b.q;
import com.netease.nimlib.n.c.l;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: SyncEventModel.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.apm.b.b<l> {
    public static final Parcelable.Creator<j> CREATOR = new Parcelable.Creator<j>() { // from class: com.netease.nimlib.n.e.j.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j[] newArray(int i) {
            return new j[i];
        }
    };
    private long a;
    private String b = null;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "nim_sdk_sync";
    }

    public long r() {
        return b();
    }

    public void c(long j) {
        a(j);
    }

    public long s() {
        return c();
    }

    public void d(long j) {
        b(j);
    }

    public long t() {
        return this.a - b();
    }

    public void e(long j) {
        this.a = j;
    }

    public void d(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.apm.b.b
    public void c(String str) {
        super.c(str);
    }

    public void a(q qVar) {
        if (qVar == null) {
            com.netease.nimlib.log.b.H("EMSyncAction is null");
        } else {
            c(String.valueOf(qVar.a()));
        }
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (f() != null) {
            map.put("action", f());
        }
        map.put("sync_begin_time", Long.valueOf(b()));
        map.put("sync_end_time", Long.valueOf(c()));
        map.put("sync_duration", Long.valueOf(g()));
        map.put("overall_duration", Long.valueOf(t()));
        if (!TextUtils.isEmpty(this.b)) {
            map.put("description", this.b);
        }
        List<l> l = l();
        if (l != null) {
            ArrayList arrayList = new ArrayList();
            for (l lVar : l) {
                if (lVar != null) {
                    arrayList.add(lVar.d());
                }
            }
            map.put("extension", arrayList);
        }
        return map;
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j) || !super.equals(obj)) {
            return false;
        }
        j jVar = (j) obj;
        return this.a == jVar.a && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, jVar.b);
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Long.valueOf(this.a), this.b});
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<l> q() {
        return l.CREATOR;
    }

    public j() {
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.a);
        parcel.writeString(this.b);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readLong();
        this.b = parcel.readString();
    }

    protected j(Parcel parcel) {
        a(parcel);
    }
}
