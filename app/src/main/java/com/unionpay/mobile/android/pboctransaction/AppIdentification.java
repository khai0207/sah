package com.unionpay.mobile.android.pboctransaction;

import android.os.Parcel;
import android.os.Parcelable;
import com.alipay.sdk.m.q.h;

/* loaded from: classes.dex */
public class AppIdentification implements Parcelable, Comparable<AppIdentification> {
    public static final Parcelable.Creator<AppIdentification> CREATOR = new a();
    private String a;
    private String b;

    private AppIdentification() {
        this.a = "";
        this.b = "";
    }

    /* synthetic */ AppIdentification(byte b) {
        this();
    }

    public AppIdentification(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        try {
            return this.a.substring(14, 16);
        } catch (Exception unused) {
            return "";
        }
    }

    public final boolean c() {
        String str = this.a;
        if (str != null) {
            return str.startsWith("A000000333");
        }
        return false;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(AppIdentification appIdentification) {
        String str;
        String str2;
        AppIdentification appIdentification2 = appIdentification;
        if (!this.a.equalsIgnoreCase(appIdentification2.a)) {
            str = this.a;
            str2 = appIdentification2.a;
        } else {
            if (this.b.equalsIgnoreCase(appIdentification2.b)) {
                return 0;
            }
            str = this.b;
            str2 = appIdentification2.b;
        }
        return str.compareTo(str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof AppIdentification)) {
            AppIdentification appIdentification = (AppIdentification) obj;
            if (this.a.equalsIgnoreCase(appIdentification.a) && this.b.equalsIgnoreCase(appIdentification.b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.a.hashCode() + 31) * 31) + this.b.hashCode();
    }

    public String toString() {
        return "{appId:" + this.a + ", appVersion:" + this.b + h.d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
