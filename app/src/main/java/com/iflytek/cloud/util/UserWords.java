package com.iflytek.cloud.util;

import android.text.TextUtils;
import com.alipay.sdk.m.h.c;
import com.iflytek.cloud.a.g.a.a;
import com.unionpay.sdk.OttoBus;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes.dex */
public class UserWords {
    private Hashtable<String, ArrayList<String>> a;

    public UserWords() {
        this.a = null;
        this.a = new Hashtable<>();
    }

    public UserWords(String str) {
        this.a = null;
        this.a = new Hashtable<>();
        a(str);
    }

    private String a() {
        try {
            if (this.a == null) {
                a.a("mwords is null...");
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<String, ArrayList<String>> entry : this.a.entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    jSONArray2.put(it.next());
                }
                jSONObject2.put("words", jSONArray2);
                jSONObject2.put(c.e, entry.getKey());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("userword", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                a.a("userword is null...");
                return;
            }
            JSONArray jSONArray = new JSONObject(new JSONTokener(str)).getJSONArray("userword");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                JSONArray jSONArray2 = jSONObject.getJSONArray("words");
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    String obj = jSONArray2.get(i2).toString();
                    if (!arrayList.contains(obj)) {
                        arrayList.add(obj);
                    }
                }
                this.a.put(jSONObject.get(c.e).toString(), arrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean a(ArrayList<String> arrayList, String str) {
        if (arrayList == null || arrayList.contains(str)) {
            return false;
        }
        arrayList.add(str);
        return true;
    }

    private boolean a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList == null || arrayList2 == null) {
            return false;
        }
        Iterator<String> it = arrayList2.iterator();
        while (it.hasNext()) {
            a(arrayList, it.next());
        }
        return true;
    }

    public ArrayList<String> getKeys() {
        Hashtable<String, ArrayList<String>> hashtable = this.a;
        if (hashtable == null || hashtable.isEmpty()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = this.a.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public ArrayList<String> getWords() {
        return getWords(OttoBus.DEFAULT_IDENTIFIER);
    }

    public ArrayList<String> getWords(String str) {
        return this.a.get(str);
    }

    public boolean hasKey(String str) {
        return this.a.containsKey(str);
    }

    public boolean putWord(String str) {
        return putWord(OttoBus.DEFAULT_IDENTIFIER, str);
    }

    public boolean putWord(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        ArrayList<String> words = getWords(str);
        if (words != null) {
            a(words, str2);
            return true;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        a(arrayList, str2);
        this.a.put(str, arrayList);
        return true;
    }

    public boolean putWords(String str, ArrayList<String> arrayList) {
        if (TextUtils.isEmpty(str) || arrayList == null || arrayList.size() <= 0) {
            return false;
        }
        ArrayList<String> words = getWords(str);
        if (words != null) {
            a(words, arrayList);
            return true;
        }
        a(new ArrayList<>(), arrayList);
        this.a.put(str, arrayList);
        return true;
    }

    public boolean putWords(ArrayList<String> arrayList) {
        return putWords(OttoBus.DEFAULT_IDENTIFIER, arrayList);
    }

    public String toString() {
        return a();
    }
}
