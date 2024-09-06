package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
final class m implements View.OnClickListener {
    final /* synthetic */ h a;

    m(h hVar) {
        this.a = hVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i5;
        i = this.a.n;
        i2 = this.a.d;
        if (i != i2) {
            arrayList2 = this.a.g;
            i5 = this.a.n;
            Object obj = arrayList2.get(i5);
            if (obj instanceof c) {
                ((c) obj).a(-1);
            }
        }
        h hVar = this.a;
        i3 = hVar.o;
        hVar.n = i3;
        h hVar2 = this.a;
        i4 = hVar2.d;
        hVar2.p = i4;
        arrayList = this.a.x;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
