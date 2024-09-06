package com.unionpay.mobile.android.widgets;

import android.view.View;

/* loaded from: classes.dex */
final class aw implements View.OnClickListener {
    final /* synthetic */ UPWidget a;

    aw(UPWidget uPWidget) {
        this.a = uPWidget;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i;
        int i2;
        long j;
        long j2;
        int unused;
        int id = view.getId();
        i = UPWidget.r;
        if (id == 10) {
            com.unionpay.mobile.android.utils.j.c("kb", " [ FIN ]");
            UPWidget.b(this.a);
            return;
        }
        if (id == 20) {
            com.unionpay.mobile.android.utils.j.c("kb", " [ DEL ]");
            if (i > 0) {
                UPWidget uPWidget = this.a;
                j2 = uPWidget.o;
                uPWidget.deleteOnce(j2);
                UPWidget.o();
                String substring = this.a.b.b().toString().substring(0, i - 1);
                this.a.b.c(substring);
                this.a.b.b(substring.length());
            }
            unused = UPWidget.r;
            return;
        }
        i2 = UPWidget.r;
        if (i2 == 6) {
            com.unionpay.mobile.android.utils.j.c("kb", " [ FIN ]");
            return;
        }
        UPWidget uPWidget2 = this.a;
        j = uPWidget2.o;
        uPWidget2.appendOnce(j, Integer.toString(id));
        String str = "*";
        if (i != 0) {
            str = this.a.b.b() + "*";
        }
        this.a.b.c(str);
        this.a.b.b(str.length());
        UPWidget.p();
    }
}
