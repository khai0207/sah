package com.netease.nimlib.abtest.a;

import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import org.json.JSONObject;

/* compiled from: ABTestVariate.java */
/* loaded from: classes.dex */
public class c {
    private String a;
    private String b;
    private String c;

    public Integer a() {
        try {
            return Integer.valueOf(Integer.parseInt(this.b));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Long b() {
        try {
            return Long.valueOf(Long.parseLong(this.b));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Boolean c() {
        try {
            return Boolean.valueOf(Boolean.parseBoolean(this.b));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public static JSONObject a(c cVar) {
        if (cVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (cVar.a != null) {
                jSONObject.put(TransferTable.COLUMN_KEY, cVar.a);
            }
            if (cVar.b != null) {
                jSONObject.put("value", cVar.b);
            }
            if (cVar.c != null) {
                jSONObject.put("type", cVar.c);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static c a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            c cVar = new c();
            if (jSONObject.has(TransferTable.COLUMN_KEY)) {
                cVar.a(jSONObject.optString(TransferTable.COLUMN_KEY));
            }
            if (jSONObject.has("value")) {
                cVar.b(jSONObject.optString("value"));
            }
            if (jSONObject.has("type")) {
                cVar.c(jSONObject.optString("type"));
            }
            return cVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return a(this).toString();
    }
}
