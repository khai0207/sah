package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.widget.AdapterView;

/* loaded from: classes.dex */
final class e implements AdapterView.OnItemClickListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.c(i);
    }
}
