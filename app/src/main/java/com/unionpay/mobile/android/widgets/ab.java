package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.unionpay.mobile.android.widgets.z;

/* loaded from: classes.dex */
final class ab implements TextWatcher {
    final /* synthetic */ z a;

    ab(z zVar) {
        this.a = zVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.a.a(editable);
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a.b.hasFocus() && TextUtils.isEmpty(this.a.b.b())) {
            z zVar = this.a;
            zVar.a(zVar.c, this.a.v() + this.a.d());
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        z.a aVar;
        z.a aVar2;
        aVar = this.a.n;
        if (aVar != null) {
            aVar2 = this.a.n;
            aVar2.a(this.a.b, charSequence.toString());
        }
    }
}
