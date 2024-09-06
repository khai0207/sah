package com.unionpay;

import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
final class t implements ab {
    final /* synthetic */ UPPayWapActivity a;

    t(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ab
    public final void a(String str, ac acVar) {
        String b;
        Boolean.parseBoolean(str);
        this.a.a.setVisibility(r1 ? 0 : 8);
        if (acVar != null) {
            b = UPPayWapActivity.b("0", Constant.CASH_LOAD_SUCCESS, null);
            acVar.a(b);
        }
    }
}
