package com.talkingdata.sdk;

import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: td */
/* loaded from: classes.dex */
public class dc extends da {
    public static final String a = "accounts";

    public void b() {
        a(a, bg.d(ab.f));
    }

    public void setUserAccount(cw cwVar) {
        if (cwVar == null || cwVar.a_() == null) {
            return;
        }
        if (this.b.isNull(a)) {
            new JSONArray().put(cwVar.a_());
            a(a, cwVar.a_());
            return;
        }
        try {
            this.b.getJSONArray(a).put(cwVar.a_());
        } catch (JSONException e) {
            ce.postSDKError(e);
            e.printStackTrace();
        }
    }
}
