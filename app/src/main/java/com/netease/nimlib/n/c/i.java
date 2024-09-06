package com.netease.nimlib.n.c;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: LoginEventExtension.java */
/* loaded from: classes.dex */
public class i extends b {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.netease.nimlib.n.c.i.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i) {
            return new i[i];
        }
    };
    private Boolean a;

    public void b(boolean z) {
        this.a = Boolean.valueOf(z);
    }

    public static i a(Integer num, String str, String str2, String str3, long j, boolean z) {
        i iVar = new i(num, str, str2, str3, j, z);
        iVar.a();
        return iVar;
    }

    public static i g() {
        i iVar = new i();
        iVar.a();
        return iVar;
    }

    public i(Integer num, String str, String str2, String str3, long j, boolean z) {
        super(num, str, str2, str3, j, z);
    }

    public i() {
    }

    @Override // com.netease.nimlib.n.c.b, com.netease.nimlib.apm.b.a
    public Map<String, Object> d() {
        Map<String, Object> d = super.d();
        Boolean bool = this.a;
        if (bool != null) {
            d.put("quick", bool);
        }
        return d;
    }

    @Override // com.netease.nimlib.n.c.b, com.netease.nimlib.apm.b.a
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof i) && super.equals(obj)) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.netease.nimlib.n.c.b, com.netease.nimlib.apm.b.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode())});
    }

    protected i(Parcel parcel) {
        super(parcel);
    }

    @Override // com.netease.nimlib.n.c.b, com.netease.nimlib.apm.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        Boolean bool = this.a;
        if (bool != null) {
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else {
            parcel.writeInt(-1);
        }
    }

    @Override // com.netease.nimlib.n.c.b, com.netease.nimlib.apm.b.a
    public void a(Parcel parcel) {
        super.a(parcel);
        int readInt = parcel.readInt();
        if (readInt == 0) {
            b(false);
        } else if (readInt == 1) {
            b(true);
        }
    }
}
