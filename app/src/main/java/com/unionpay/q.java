package com.unionpay;

import com.unionpay.tsmservice.data.Constant;
import com.unionpay.utils.UPUtils;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class q implements ab {
    final /* synthetic */ UPPayWapActivity a;

    q(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ab
    public final void a(String str, ac acVar) {
        String b;
        String b2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                UPUtils.a(this.a, jSONObject.getString(next), next);
            }
            if (acVar != null) {
                b2 = UPPayWapActivity.b("0", Constant.CASH_LOAD_SUCCESS, null);
                acVar.a(b2);
            }
        } catch (Exception e) {
            if (acVar != null) {
                b = UPPayWapActivity.b("1", e.getMessage(), null);
                acVar.a(b);
            }
        }
    }
}
