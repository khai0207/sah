package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: ExceptionEventModel.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.apm.b.b<com.netease.nimlib.n.c.d> {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.netease.nimlib.n.e.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return new d[i];
        }
    };
    private String a;
    private Integer b;

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "exceptions";
    }

    public void d(String str) {
        this.a = str;
    }

    public void a(com.netease.nimlib.n.b.g gVar) {
        if (gVar == null) {
            this.b = Integer.valueOf(com.netease.nimlib.n.b.g.UNKNOWN.a());
        } else {
            this.b = Integer.valueOf(gVar.a());
        }
    }

    @Override // com.netease.nimlib.apm.b.b
    public List<com.netease.nimlib.n.c.d> l() {
        return super.l();
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(List<com.netease.nimlib.n.c.d> list) {
        super.a(list);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void c(String str) {
        super.c(str);
    }

    public void a(com.netease.nimlib.n.b.f fVar) {
        if (fVar == null) {
            com.netease.nimlib.log.b.H("EMExceptionActions is null");
        } else {
            c(String.valueOf(fVar.a()));
        }
    }

    @Override // com.netease.nimlib.apm.b.b
    public Integer i() {
        Integer i = super.i();
        return i == null ? Integer.valueOf(com.netease.nimlib.n.b.h.kUnknown.a()) : i;
    }

    @Override // com.netease.nimlib.apm.b.b
    public long n() {
        String f = f();
        if (C$r8$backportedMethods$utility$Objects$2$equals.equals(f, String.valueOf(com.netease.nimlib.n.b.f.kTCP.a())) || C$r8$backportedMethods$utility$Objects$2$equals.equals(f, String.valueOf(com.netease.nimlib.n.b.f.kBusiness.a()))) {
            return 10000L;
        }
        if (this.b == null) {
            return super.n();
        }
        if (!C$r8$backportedMethods$utility$Objects$2$equals.equals(f, String.valueOf(com.netease.nimlib.n.b.f.kHTTP.a())) || this.b.intValue() == com.netease.nimlib.n.b.g.UNKNOWN.a()) {
            return super.n();
        }
        return 10000L;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (f() != null) {
            map.put("action", f());
        }
        map.put("start_time", Long.valueOf(b()));
        map.put("duration", Long.valueOf(g()));
        Object i = i();
        if (i != null) {
            map.put(TransferTable.COLUMN_STATE, i);
        }
        if (!TextUtils.isEmpty(this.a)) {
            map.put("process_id", this.a);
        }
        Object obj = this.b;
        if (obj != null) {
            map.put("exception_service", obj);
        }
        List<com.netease.nimlib.n.c.d> l = l();
        if (l != null) {
            ArrayList arrayList = new ArrayList();
            for (com.netease.nimlib.n.c.d dVar : l) {
                if (dVar != null) {
                    arrayList.add(dVar.d());
                }
            }
            map.put("extension", arrayList);
        }
        return map;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.n.c.d> q() {
        return com.netease.nimlib.n.c.d.CREATOR;
    }

    public d() {
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeValue(this.b);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readString();
        this.b = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }

    protected d(Parcel parcel) {
        a(parcel);
    }
}
