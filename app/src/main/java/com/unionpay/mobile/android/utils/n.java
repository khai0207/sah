package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Message;
import com.iflytek.cloud.SpeechConstant;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class n extends UPPayEngine implements com.unionpay.mobile.android.fully.a {
    private Context a;

    public n(Context context) {
        super(context);
        this.a = context;
    }

    @Override // com.unionpay.mobile.android.nocard.utils.UPPayEngine, com.unionpay.mobile.android.fully.a
    public final String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("reqtm", h());
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        j.c("uppay", "post message = " + str);
        d().a(f(str));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, f());
        d().a(hashMap);
        com.unionpay.mobile.android.net.c cVar = new com.unionpay.mobile.android.net.c(d(), this.a);
        int a = cVar.a();
        String c = cVar.c();
        if (a != 0) {
            Message obtainMessage = e().obtainMessage(2);
            obtainMessage.arg1 = a;
            e().sendMessage(obtainMessage);
            return null;
        }
        String g = g(c);
        j.a("uppay", "[ response msg ] " + g);
        return g;
    }
}
