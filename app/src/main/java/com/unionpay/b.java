package com.unionpay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import com.unionpay.utils.UPUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class b implements Handler.Callback {
    b() {
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Context context;
        JSONArray jSONArray;
        int i;
        boolean z;
        String str;
        Context context2;
        Context context3;
        String str2;
        Context context4;
        String str3;
        boolean z2;
        JSONArray b;
        String str4;
        int i2 = message.what;
        if (i2 == 1001) {
            UPPayAssistEx.l();
        } else {
            if (i2 != 1002) {
                return true;
            }
            try {
                if (message.obj != null) {
                    JSONObject jSONObject = new JSONObject((String) message.obj);
                    String a = com.unionpay.utils.f.a(jSONObject, "sign");
                    int i3 = 0;
                    try {
                        str4 = UPPayAssistEx.x;
                        i3 = Integer.parseInt(str4);
                    } catch (NumberFormatException unused) {
                    }
                    String str5 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                    StringBuilder sb = new StringBuilder();
                    sb.append(str5);
                    str = UPPayAssistEx.f35u;
                    sb.append(str);
                    if (UPUtils.forConfig(i3, a).equals(com.unionpay.utils.b.a(UPUtils.a(sb.toString())))) {
                        context2 = UPPayAssistEx.t;
                        UPUtils.a(context2, (String) message.obj, "configs");
                        context3 = UPPayAssistEx.t;
                        str2 = UPPayAssistEx.x;
                        UPUtils.a(context3, str2, "mode");
                        context4 = UPPayAssistEx.t;
                        str3 = UPPayAssistEx.f35u;
                        UPUtils.a(context4, str3, "or");
                        z2 = UPPayAssistEx.C;
                        if (!z2) {
                            b = UPPayAssistEx.b(new JSONArray(str5), "sort");
                            JSONArray unused2 = UPPayAssistEx.I = b;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            z = UPPayAssistEx.C;
            if (z) {
                return true;
            }
        }
        context = UPPayAssistEx.t;
        jSONArray = UPPayAssistEx.I;
        i = UPPayAssistEx.B;
        UPPayAssistEx.a(context, jSONArray, i);
        return true;
    }
}
