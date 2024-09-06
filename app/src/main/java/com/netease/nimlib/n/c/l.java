package com.netease.nimlib.n.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.netease.nimlib.n.b.r;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: SyncEventExtension.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.apm.b.a {
    public static final Parcelable.Creator<l> CREATOR = new Parcelable.Creator<l>() { // from class: com.netease.nimlib.n.c.l.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l createFromParcel(Parcel parcel) {
            return new l(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l[] newArray(int i) {
            return new l[i];
        }
    };
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private long d = 0;
    private long e = 0;
    private String f = null;
    private List<m> g = null;

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.a;
    }

    public void a(r rVar) {
        if (rVar != null) {
            this.a = rVar.a();
        }
    }

    public void b(String str) {
        this.f = str;
    }

    public List<m> f() {
        return this.g;
    }

    public void a(m mVar) {
        if (mVar == null) {
            return;
        }
        this.b++;
        List<m> list = this.g;
        if (list == null) {
            this.g = new ArrayList();
        } else if (!list.isEmpty()) {
            mVar.a(this.g.get(r0.size() - 1).c());
        }
        this.g.add(mVar);
        this.c += mVar.b();
        this.d += mVar.d();
        this.e += mVar.e();
    }

    @Override // com.netease.nimlib.apm.b.a
    public Map<String, Object> d() {
        Map<String, Object> d = super.d();
        d.put("sync_type", Integer.valueOf(this.a));
        d.put("times", Integer.valueOf(this.b));
        d.put("total", Integer.valueOf(this.c));
        d.put("sync_duration", Long.valueOf(this.d));
        d.put("proc_duration", Long.valueOf(this.e));
        if (!TextUtils.isEmpty(this.f)) {
            d.put("description", this.f);
        }
        if (this.g != null) {
            ArrayList arrayList = new ArrayList();
            for (m mVar : this.g) {
                if (mVar != null) {
                    arrayList.add(mVar.a());
                }
            }
            d.put("items", arrayList);
        }
        return d;
    }

    public String toString() {
        return d().toString();
    }

    @Override // com.netease.nimlib.apm.b.a
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return super.equals(obj) && this.a == lVar.a && this.b == lVar.b && this.c == lVar.c && this.d == lVar.d && this.e == lVar.e && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.f, lVar.f) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.g, lVar.g);
    }

    @Override // com.netease.nimlib.apm.b.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e), this.f, this.g});
    }

    @Override // com.netease.nimlib.apm.b.a
    public boolean a(com.netease.nimlib.apm.b.a aVar) {
        if (aVar instanceof l) {
            return super.a(aVar) && this.a == ((l) aVar).a;
        }
        return false;
    }

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        parcel.writeString(this.f);
        parcel.writeTypedList(this.g);
    }

    @Override // com.netease.nimlib.apm.b.a
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readString();
        this.g = parcel.createTypedArrayList(m.CREATOR);
    }

    public l() {
    }

    protected l(Parcel parcel) {
        a(parcel);
    }
}
