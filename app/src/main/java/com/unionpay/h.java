package com.unionpay;

import java.util.Comparator;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class h implements Comparator {
    String a;

    h(String str) {
        this.a = "";
        this.a = str;
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        long optLong = ((JSONObject) obj).optLong(this.a);
        long optLong2 = ((JSONObject) obj2).optLong(this.a);
        if (optLong < optLong2) {
            return -1;
        }
        return optLong > optLong2 ? 1 : 0;
    }
}
