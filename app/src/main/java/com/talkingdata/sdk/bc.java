package com.talkingdata.sdk;

import java.util.Comparator;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
final class bc implements Comparator {
    bc() {
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            return jSONObject2.getInt("level") - jSONObject.getInt("level");
        } catch (Throwable unused) {
            return 0;
        }
    }
}
