package com.ta.utdid2.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.pc.util.ThreeMap;
import com.ta.utdid2.a.a.f;

/* loaded from: classes.dex */
public class a {
    public SharedPreferences.Editor a = null;

    /* renamed from: a, reason: collision with other field name */
    public SharedPreferences f5a;

    /* renamed from: a, reason: collision with other field name */
    public String f6a;
    public boolean e;
    public Context mContext;

    public a(Context context, String str, String str2, boolean z, boolean z2) {
        this.f6a = "";
        this.f5a = null;
        this.mContext = null;
        this.e = false;
        this.e = z2;
        this.f6a = str2;
        this.mContext = context;
        if (context != null) {
            this.f5a = context.getSharedPreferences(str2, 0);
        }
    }

    private void a() {
        SharedPreferences sharedPreferences;
        if (this.a != null || (sharedPreferences = this.f5a) == null) {
            return;
        }
        this.a = sharedPreferences.edit();
    }

    public boolean commit() {
        boolean z;
        Context context;
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor editor = this.a;
        if (editor != null) {
            if (!this.e && this.f5a != null) {
                editor.putLong(ThreeMap.type, currentTimeMillis);
            }
            if (!this.a.commit()) {
                z = false;
                if (this.f5a != null && (context = this.mContext) != null) {
                    this.f5a = context.getSharedPreferences(this.f6a, 0);
                }
                return z;
            }
        }
        z = true;
        if (this.f5a != null) {
            this.f5a = context.getSharedPreferences(this.f6a, 0);
        }
        return z;
    }

    public String getString(String str) {
        SharedPreferences sharedPreferences = this.f5a;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            if (!f.m59a(string)) {
                return string;
            }
        }
        return "";
    }

    public void putString(String str, String str2) {
        if (f.m59a(str) || str.equals(ThreeMap.type)) {
            return;
        }
        a();
        SharedPreferences.Editor editor = this.a;
        if (editor != null) {
            editor.putString(str, str2);
        }
    }

    public void remove(String str) {
        if (f.m59a(str) || str.equals(ThreeMap.type)) {
            return;
        }
        a();
        SharedPreferences.Editor editor = this.a;
        if (editor != null) {
            editor.remove(str);
        }
    }
}
