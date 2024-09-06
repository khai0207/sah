package com.netease.nimlib.apm.b;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.network.f;
import com.netease.nimlib.o.p;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BaseEventExtension.java */
/* loaded from: classes.dex */
public abstract class a implements Parcelable, Serializable {
    private d a = d.STAT_NET_TYPE_UNKNOWN;
    private Boolean b = null;
    private String c = null;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected a() {
    }

    public void a() {
        Context e = com.netease.nimlib.c.e();
        this.a = d.b(p.j(e));
        this.b = Boolean.valueOf(f.a(e));
    }

    public d b() {
        return this.a;
    }

    public Boolean c() {
        return this.b;
    }

    public void a(String str) {
        this.c = str;
    }

    public Map<String, Object> d() {
        HashMap hashMap = new HashMap();
        d dVar = this.a;
        if (dVar != null) {
            hashMap.put("net_type", Integer.valueOf(dVar.a()));
        }
        Boolean bool = this.b;
        if (bool != null) {
            hashMap.put("net_connect", bool);
        }
        String str = this.c;
        if (str != null) {
            hashMap.put("detect_task_id", str);
        }
        return hashMap;
    }

    public boolean a(a aVar) {
        if (aVar == null) {
            return false;
        }
        if (equals(aVar)) {
            return true;
        }
        return C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, aVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, aVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, aVar.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, aVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, aVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, aVar.c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        d dVar = this.a;
        if (dVar == null) {
            dVar = d.STAT_NET_TYPE_UNKNOWN;
        }
        parcel.writeInt(dVar.a());
        parcel.writeByte(Boolean.TRUE.equals(this.b) ? (byte) 1 : (byte) 0);
        parcel.writeString(this.c);
    }

    public void a(Parcel parcel) {
        this.a = d.a(parcel.readInt());
        this.b = Boolean.valueOf(parcel.readByte() > 0);
        this.c = parcel.readString();
    }
}
