package com.unionpay;

import android.content.Context;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
final class p implements ab {
    final /* synthetic */ UPPayWapActivity a;

    p(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ab
    public final void a(String str, ac acVar) {
        String b;
        String a = UPPayAssistEx.a((Context) this.a, true);
        if (acVar != null) {
            b = UPPayWapActivity.b("0", Constant.CASH_LOAD_SUCCESS, a);
            acVar.a(b);
        }
    }
}
