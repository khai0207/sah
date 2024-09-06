package com.unionpay;

import com.unionpay.tsmservice.data.Constant;
import com.unionpay.utils.UPUtils;

/* loaded from: classes.dex */
final class s implements ab {
    final /* synthetic */ UPPayWapActivity a;

    s(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ab
    public final void a(String str, ac acVar) {
        String b;
        UPUtils.b(this.a, str);
        if (acVar != null) {
            b = UPPayWapActivity.b("0", Constant.CASH_LOAD_SUCCESS, null);
            acVar.a(b);
        }
    }
}
