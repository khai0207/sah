package com.talkingdata.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.talkingdata.sdk.ay;

/* compiled from: td */
/* loaded from: classes.dex */
public class ak {
    public static final Parcelable.Creator e = new al();
    public boolean a;
    public int b;
    public final String c;
    public final int d;
    private final ay.a f;

    public ak(int i) {
        this.d = i;
        this.c = a(i);
        ay.a a = ay.a.a(i);
        this.f = a;
        try {
            ay.b b = a.b("cpuacct");
            this.a = !this.f.b("cpu").c.contains("bg_non_interactive");
            this.b = Integer.parseInt(b.c.split("/")[1].replace("uid_", ""));
        } catch (Throwable th) {
            ce.postSDKError(th);
            if (d() != null) {
                this.b = d().a();
            }
        }
    }

    public String a() {
        try {
            return this.c.split(":")[0];
        } catch (Throwable unused) {
            return "";
        }
    }

    public String b() {
        try {
            if (this.c.split(":").length <= 1) {
                return "";
            }
            return ":" + this.c.split(":")[1];
        } catch (Throwable unused) {
            return "";
        }
    }

    public ay.a c() {
        return this.f;
    }

    protected ak(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.f = (ay.a) parcel.readParcelable(ay.a.class.getClassLoader());
        this.a = parcel.readByte() != 0;
    }

    static String a(int i) {
        String str = null;
        try {
            str = ay.a(String.format("/proc/%d/cmdline", Integer.valueOf(i))).trim();
            if (TextUtils.isEmpty(str)) {
                return ay.c.a(i).b();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        return str;
    }

    public ay.d d() {
        try {
            return ay.d.a(this.d);
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    public ay.c e() {
        try {
            return ay.c.a(this.d);
        } catch (Throwable unused) {
            return null;
        }
    }
}
