package com.iflytek.cloud.b;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class a {
    HashMap<String, String> a = new HashMap<>();

    public a() {
    }

    public a(String str, String[][] strArr) {
        a(str);
        a(strArr);
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replaceAll("[,\n ]", "|");
    }

    public int a(String str, int i) {
        String str2 = this.a.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return Integer.parseInt(str2);
        } catch (Exception unused) {
            return i;
        }
    }

    public void a() {
        this.a.clear();
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.a.putAll(aVar.c());
        }
    }

    public void a(a aVar, String str) {
        if (aVar == null) {
            return;
        }
        a(str, aVar.e(str));
    }

    public void a(String str) {
        this.a.clear();
        b(str);
    }

    public void a(String str, String str2) {
        a(str, str2, true);
    }

    public void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (z || !this.a.containsKey(str)) {
            this.a.put(str, str2);
        }
    }

    public void a(String[][] strArr) {
        if (strArr == null) {
            return;
        }
        for (String[] strArr2 : strArr) {
            if (this.a.containsKey(strArr2[0])) {
                String str = this.a.get(strArr2[0]);
                this.a.remove(strArr2[0]);
                for (int i = 1; i < strArr2.length; i++) {
                    this.a.put(strArr2[i], str);
                }
            }
        }
    }

    public boolean a(String str, boolean z) {
        String str2 = this.a.get(str);
        if (str2 == null) {
            return z;
        }
        if (str2.equals("true") || str2.equals("1")) {
            return true;
        }
        if (str2.equals("false") || str2.equals("0")) {
            return false;
        }
        return z;
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a clone() {
        a aVar = new a();
        aVar.a = (HashMap) this.a.clone();
        return aVar;
    }

    public String b(String str, String str2) {
        String str3 = this.a.get(str);
        return str3 == null ? str2 : str3;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(",")) {
            int indexOf = str2.indexOf("=");
            if (indexOf > 0 && indexOf < str2.length()) {
                this.a.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
            }
        }
    }

    public Boolean c(String str) {
        return Boolean.valueOf(this.a.remove(str) != null);
    }

    public HashMap<String, String> c() {
        return this.a;
    }

    public String d(String str) {
        return this.a.remove(str);
    }

    public void d() {
        for (Map.Entry<String, String> entry : this.a.entrySet()) {
            entry.setValue(f(entry.getValue()));
        }
    }

    public String e(String str) {
        return this.a.get(str);
    }

    public boolean g(String str) {
        return this.a.containsKey(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.a.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(entry.getValue());
            stringBuffer.append(",");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        String stringBuffer2 = stringBuffer.toString();
        com.iflytek.cloud.a.g.a.a.c(stringBuffer2);
        return stringBuffer2;
    }
}
