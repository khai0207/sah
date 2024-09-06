package com.netease.nimlib.n.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Pair;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.util.UriUtils;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

/* compiled from: ExceptionEventExtension.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.apm.b.a {
    private int a;
    private String b;
    private int c;
    private String d;
    private String e;
    private Boolean f = null;
    private Boolean g = null;
    private static final AtomicLong h = new AtomicLong(0);
    private static Pair<Long, Long> i = null;
    private static final AtomicLong j = new AtomicLong(0);
    private static Pair<Boolean, Boolean> k = null;
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.netease.nimlib.n.c.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    };

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Integer e() {
        return Integer.valueOf(this.a);
    }

    public String f() {
        return this.b;
    }

    public Integer g() {
        return Integer.valueOf(this.c);
    }

    public String h() {
        return this.d;
    }

    public Boolean i() {
        return this.f;
    }

    public void a(Integer num) {
        this.a = num.intValue();
    }

    public void b(String str) {
        this.b = str;
    }

    public void b(Integer num) {
        this.c = num.intValue();
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        this.f = Boolean.valueOf(z);
    }

    public void b(boolean z) {
        this.g = Boolean.valueOf(z);
    }

    protected static JSONObject e(String str) {
        long j2;
        long longValue;
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        long j3 = -1;
        if (file.isFile()) {
            j2 = file.length();
        } else if (UriUtils.isFileOrContentUri(str)) {
            j2 = UriUtils.getFileSizeFromUri(com.netease.nimlib.c.e(), UriUtils.string2Uri(str));
        } else {
            arrayList.add("target is not a file or content uri: " + str);
            j2 = -1L;
        }
        long e = com.netease.nimlib.biz.a.e();
        long a = a(h.get());
        if (h.get() <= 0 || a >= e) {
            try {
                i = com.netease.nimlib.o.b.c.b();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("ExceptionEventExtension", "get disk info with error, e=" + th.getMessage(), th);
                arrayList.add("get disk info with error, e=" + th);
            }
            h.set(SystemClock.elapsedRealtime());
            j3 = ((Long) i.first).longValue();
            longValue = ((Long) i.second).longValue();
        } else {
            Pair<Long, Long> pair = i;
            if (pair != null) {
                j3 = ((Long) pair.first).longValue();
                longValue = ((Long) i.second).longValue();
            } else {
                arrayList.add(String.format("have not reached frequency control while disk info is null. currentDelay: %s, targetDelay: %s", Long.valueOf(a), Long.valueOf(e)));
                longValue = -1;
            }
        }
        if (j2 >= 0) {
            try {
                jSONObject.put("target_file_size", j2);
            } catch (Throwable th2) {
                com.netease.nimlib.log.b.e("ExceptionEventExtension", "generateContext failed, e=" + th2.getMessage(), th2);
            }
        }
        if (j3 >= 0) {
            jSONObject.put("disk_total_size", j3);
        }
        if (longValue >= 0) {
            jSONObject.put("disk_free_size", longValue);
        }
        long j4 = h.get();
        if (j4 > 0) {
            jSONObject.put("disk_info_delayed", a(j4));
        }
        String a2 = com.netease.nimlib.o.f.a(arrayList, "; ");
        if (w.b((CharSequence) a2)) {
            jSONObject.put("update_disk_info_failed_reason", a2);
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d7 A[Catch: all -> 0x00ec, TryCatch #2 {all -> 0x00ec, blocks: (B:16:0x00c1, B:19:0x00ca, B:21:0x00d7, B:22:0x00e0, B:24:0x00e6), top: B:15:0x00c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e6 A[Catch: all -> 0x00ec, TRY_LEAVE, TryCatch #2 {all -> 0x00ec, blocks: (B:16:0x00c1, B:19:0x00ca, B:21:0x00d7, B:22:0x00e0, B:24:0x00e6), top: B:15:0x00c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected static org.json.JSONObject j() {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.n.c.d.j():org.json.JSONObject");
    }

    private static long a(long j2) {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - j2);
    }

    @Override // com.netease.nimlib.apm.b.a
    public Map<String, Object> d() {
        Map<String, Object> d = super.d();
        d.put("operation_type", Integer.valueOf(this.a));
        String str = this.b;
        if (str != null) {
            d.put("target", str);
        }
        d.put("code", Integer.valueOf(this.c));
        String str2 = this.d;
        if (str2 != null) {
            d.put("description", str2);
        }
        String str3 = this.e;
        if (str3 != null) {
            d.put("context", str3);
        }
        Boolean bool = this.f;
        if (bool != null) {
            d.put("foreground", bool);
        }
        Boolean bool2 = this.g;
        if (bool2 != null) {
            d.put("foreg_backg_switch", bool2);
        }
        return d;
    }

    @Override // com.netease.nimlib.apm.b.a
    public boolean a(com.netease.nimlib.apm.b.a aVar) {
        if (aVar instanceof d) {
            return super.a(aVar) && equals((d) aVar);
        }
        return false;
    }

    @Override // com.netease.nimlib.apm.b.a
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return super.equals(obj) && this.a == dVar.a && this.c == dVar.c && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, dVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.d, dVar.d) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.e, dVar.e) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.f, dVar.f) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.g, dVar.g);
    }

    @Override // com.netease.nimlib.apm.b.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Integer.valueOf(this.a), this.b, Integer.valueOf(this.c), this.d, this.e, this.f, this.g});
    }

    @Override // com.netease.nimlib.apm.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        if (this.f == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Boolean.TRUE.equals(this.f) ? 1 : 0);
        }
        if (this.g == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Boolean.TRUE.equals(this.g) ? 1 : 0);
        }
    }

    @Override // com.netease.nimlib.apm.b.a
    public void a(Parcel parcel) {
        super.a(parcel);
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            this.f = null;
        } else if (readInt == 0) {
            this.f = false;
        } else if (readInt == 1) {
            this.f = true;
        }
        int readInt2 = parcel.readInt();
        if (readInt2 == -1) {
            this.g = null;
        } else if (readInt2 == 0) {
            this.g = false;
        } else {
            if (readInt2 != 1) {
                return;
            }
            this.g = true;
        }
    }

    public d() {
    }

    protected d(Parcel parcel) {
        a(parcel);
    }
}
