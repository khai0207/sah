package com.netease.nimlib.n.e;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: LoginEventModel.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.apm.b.b<com.netease.nimlib.n.c.i> {
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() { // from class: com.netease.nimlib.n.e.e.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i) {
            return new e[i];
        }
    };
    private String a;

    @Override // com.netease.nimlib.apm.b.b
    public long n() {
        return 20000L;
    }

    @Override // com.netease.nimlib.apm.b.b
    public String o() {
        return "login";
    }

    public void d(String str) {
        this.a = str;
    }

    public String r() {
        return this.a;
    }

    @Override // com.netease.nimlib.apm.b.b
    public Parcelable.Creator<com.netease.nimlib.n.c.i> q() {
        return com.netease.nimlib.n.c.i.CREATOR;
    }

    public boolean s() {
        return "auto_login".equals(f());
    }

    public e() {
    }

    @Override // com.netease.nimlib.apm.b.b
    public Map<String, Object> a(Map<String, Object> map) {
        if (f() != null) {
            map.put("action", f());
        }
        map.put("start_time", Long.valueOf(b()));
        map.put("duration", Long.valueOf(g()));
        map.put("succeed", Boolean.valueOf(h()));
        if (!TextUtils.isEmpty(r())) {
            map.put("process_id", r());
        }
        List<com.netease.nimlib.n.c.i> l = l();
        if (l != null) {
            ArrayList arrayList = new ArrayList();
            for (com.netease.nimlib.n.c.i iVar : l) {
                if (iVar != null) {
                    arrayList.add(iVar.d());
                }
            }
            map.put("extension", arrayList);
        }
        return map;
    }

    protected e(Parcel parcel) {
        a(parcel);
    }

    @Override // com.netease.nimlib.apm.b.b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
    }

    @Override // com.netease.nimlib.apm.b.b
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readString();
    }

    @Override // com.netease.nimlib.apm.b.b
    public boolean equals(Object obj) {
        return super.equals(obj) && (obj instanceof e) && TextUtils.equals(this.a, ((e) obj).a);
    }

    @Override // com.netease.nimlib.apm.b.b
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.a});
    }
}
