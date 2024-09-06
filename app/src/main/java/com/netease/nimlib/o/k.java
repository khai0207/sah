package com.netease.nimlib.o;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSONHelper.java */
/* loaded from: classes.dex */
public class k {
    public static final JSONObject a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("JSONHelper", "parse " + str, e);
            return null;
        }
    }

    public static final JSONArray b(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final List<String> c(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (JSONException e) {
            com.netease.nimlib.log.b.e("JSONHelper", "parseList " + str, e);
            return null;
        }
    }

    public static final int a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getInt(str);
        } catch (JSONException unused) {
            return 0;
        }
    }

    public static final long b(JSONObject jSONObject, String str) {
        try {
            Object obj = jSONObject.get(str);
            if (obj instanceof Long) {
                return ((Long) obj).longValue();
            }
            if (obj instanceof Number) {
                return ((Number) obj).longValue();
            }
            if (obj instanceof String) {
                return Long.parseLong((String) obj);
            }
            return 0L;
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static final boolean c(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException unused) {
            return false;
        }
    }

    public static final double d(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    public static final String e(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Object f(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.opt(str);
    }

    public static final JSONObject g(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final JSONArray h(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONArray(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static JSONArray a(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        return jSONArray.optJSONArray(i);
    }

    public static final String b(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getString(i);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final long c(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getLong(i);
        } catch (JSONException unused) {
            return 0L;
        }
    }

    public static final JSONObject d(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public static final void a(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
        } catch (JSONException unused) {
        }
    }

    public static final void a(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (Exception unused) {
        }
    }

    public static final void a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        try {
            jSONObject.put(str, jSONObject2);
        } catch (JSONException unused) {
        }
    }

    public static final void a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        try {
            jSONObject.put(str, jSONArray);
        } catch (JSONException unused) {
        }
    }

    public static final void a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONArray jSONArray, Object obj) {
        try {
            jSONArray.put(obj);
        } catch (Exception unused) {
        }
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap;
        HashMap hashMap2 = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            hashMap = new HashMap();
        } catch (Throwable th) {
            th = th;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } catch (Throwable th2) {
            th = th2;
            hashMap2 = hashMap;
            th.printStackTrace();
            return hashMap2;
        }
    }
}
