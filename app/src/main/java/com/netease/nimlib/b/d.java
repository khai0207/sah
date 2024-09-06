package com.netease.nimlib.b;

import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LocalAntiSpamThesaurus.java */
/* loaded from: classes.dex */
public class d {
    private String a;
    private int b;
    private int c;
    private String d;

    public d(JSONObject jSONObject, List<a> list, List<a> list2, List<a> list3) {
        try {
            this.a = jSONObject.optString(com.alipay.sdk.m.h.c.e);
            this.c = jSONObject.optInt("operate");
            this.b = jSONObject.optInt("match");
            this.d = jSONObject.optString("config");
            JSONArray jSONArray = jSONObject.getJSONArray("keys");
            if (jSONArray == null) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    a aVar = new a(jSONObject2, this.b, this.c, this.d);
                    int c = aVar.c();
                    if (c == 1) {
                        list.add(aVar);
                    } else if (c == 2) {
                        list2.add(aVar);
                    } else if (c == 3) {
                        list3.add(aVar);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int a() {
        return this.c;
    }

    /* compiled from: LocalAntiSpamThesaurus.java */
    /* loaded from: classes.dex */
    static class a {
        private String a;
        private int b;
        private int c;
        private String d;

        public a(JSONObject jSONObject, int i, int i2, String str) {
            this.b = 0;
            this.c = 0;
            this.d = "";
            try {
                this.a = jSONObject.getString(TransferTable.COLUMN_KEY);
                this.b = jSONObject.optInt("match");
                this.c = jSONObject.optInt("operate");
                this.d = jSONObject.optString("config");
                if (this.b != 0) {
                    i = this.b;
                }
                this.b = i;
                if (this.c != 0) {
                    i2 = this.c;
                }
                this.c = i2;
                if (!TextUtils.isEmpty(this.d)) {
                    str = this.d;
                }
                this.d = str;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public String a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }
}
