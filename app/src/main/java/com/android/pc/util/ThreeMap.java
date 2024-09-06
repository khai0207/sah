package com.android.pc.util;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ThreeMap {
    private static Map<String, Map<String, String>> threeMap = new HashMap();
    public static final String type = "t";
    public static final String type_boolean = "b";
    public static final String type_float = "f";
    public static final String type_int = "i";
    public static final String type_long = "l";
    public static final String type_string = "s";
    public static final String value = "v";

    public ThreeMap() {
    }

    public ThreeMap(Map<String, Object> map) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof String) {
                setString(String.valueOf(str), String.valueOf(obj));
            } else if (obj instanceof Integer) {
                setInt(String.valueOf(str), Integer.parseInt(obj.toString()));
            } else if (obj instanceof Boolean) {
                setBoolean(String.valueOf(str), Boolean.parseBoolean(obj.toString()));
            } else {
                setString(String.valueOf(str), String.valueOf(obj));
            }
        }
    }

    public void setInt(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(type, type_int);
        hashMap.put(value, String.valueOf(i));
        threeMap.put(str, hashMap);
    }

    public void setLong(String str, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put(type, type_long);
        hashMap.put(value, String.valueOf(j));
        threeMap.put(str, hashMap);
    }

    public void setString(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(type, type_string);
        hashMap.put(value, str2);
        threeMap.put(str, hashMap);
    }

    public void setFloat(String str, float f) {
        HashMap hashMap = new HashMap();
        hashMap.put(type, type_float);
        hashMap.put(value, String.valueOf(f));
        threeMap.put(str, hashMap);
    }

    public void setBoolean(String str, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(type, type_boolean);
        hashMap.put(value, String.valueOf(z));
        threeMap.put(str, hashMap);
    }

    public boolean addValueInEditor(SharedPreferences.Editor editor) {
        if (editor == null || threeMap.size() == 0) {
            return false;
        }
        for (String str : threeMap.keySet()) {
            String valueOf = String.valueOf(threeMap.get(str).get(type));
            if (valueOf.equals(type_int)) {
                editor.putInt(str, Integer.parseInt(threeMap.get(str).get(value)));
            } else if (valueOf.equals(type_long)) {
                editor.putLong(str, Long.parseLong(threeMap.get(str).get(value)));
            } else if (valueOf.equals(type_string)) {
                editor.putString(str, threeMap.get(str).get(value));
            } else if (valueOf.equals(type_float)) {
                editor.putFloat(str, Float.parseFloat(threeMap.get(str).get(value)));
            } else if (valueOf.equals(type_boolean)) {
                editor.putBoolean(str, Boolean.parseBoolean(threeMap.get(str).get(value)));
            }
        }
        return true;
    }

    public String toString() {
        return threeMap.toString();
    }

    public int getLength() {
        return threeMap.size();
    }

    public void remove(String str) {
        threeMap.remove(str);
    }
}
