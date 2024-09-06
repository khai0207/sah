package com.netease.nimlib.chatroom.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.log.b;
import com.unionpay.tsmservice.data.Constant;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CdnURLRequestData.java */
/* loaded from: classes.dex */
public class a implements Parcelable, Serializable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.netease.nimlib.chatroom.model.a.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }
    };
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public a() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }

    public void a(boolean z, int i) {
        if (z) {
            this.a++;
            this.c += i;
            this.e = Math.max(this.e, i);
        } else {
            this.b++;
            this.d += i;
            this.f = Math.max(this.f, i);
        }
        b.d("CdnURLRequestData", String.format("after record, data=%s", a()));
    }

    protected a(Parcel parcel) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
    }

    public JSONObject a() {
        double d;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constant.CASH_LOAD_SUCCESS, this.a);
            jSONObject.put(Constant.CASH_LOAD_FAIL, this.b);
            double d2 = 0.0d;
            if (this.a <= 0) {
                d = 0.0d;
            } else {
                double d3 = this.c;
                double d4 = this.a;
                Double.isNaN(d3);
                Double.isNaN(d4);
                d = d3 / d4;
            }
            jSONObject.put("sr", d);
            if (this.b > 0) {
                double d5 = this.d;
                double d6 = this.b;
                Double.isNaN(d5);
                Double.isNaN(d6);
                d2 = d5 / d6;
            }
            jSONObject.put("fr", d2);
            jSONObject.put("smr", this.e);
            jSONObject.put("fmr", this.f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return a().toString();
    }
}
