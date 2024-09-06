package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.unionpay.mobile.android.nocard.views.o;
import java.util.List;

/* loaded from: classes.dex */
final class ad implements AdapterView.OnItemClickListener {
    final /* synthetic */ o.b a;

    ad(o.b bVar) {
        this.a = bVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PopupWindow popupWindow;
        List list;
        o.a aVar;
        o.a aVar2;
        com.unionpay.mobile.android.upwidget.c cVar;
        com.unionpay.mobile.android.upwidget.c cVar2;
        List list2;
        com.unionpay.mobile.android.upwidget.c cVar3;
        int i2;
        popupWindow = this.a.b;
        popupWindow.dismiss();
        list = this.a.j;
        if (list != null) {
            cVar2 = this.a.c;
            int c = i - cVar2.c();
            list2 = this.a.j;
            if (c < list2.size()) {
                this.a.g = i;
                cVar3 = this.a.c;
                i2 = this.a.g;
                cVar3.a(i2);
            }
        }
        aVar = this.a.k;
        if (aVar != null) {
            aVar2 = this.a.k;
            cVar = this.a.c;
            aVar2.a(i - cVar.c());
        }
    }
}
