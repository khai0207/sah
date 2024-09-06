package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
final class i implements AdapterView.OnItemClickListener {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        int i5;
        int i6;
        ArrayList arrayList2;
        ArrayList arrayList3;
        int i7;
        i2 = this.a.o;
        view.setTag(Integer.valueOf(i2));
        i3 = this.a.n;
        i4 = this.a.d;
        if (i3 != i4) {
            arrayList3 = this.a.g;
            i7 = this.a.n;
            Object obj = arrayList3.get(i7);
            if (obj instanceof c) {
                ((c) obj).a(-1);
            }
        }
        arrayList = this.a.g;
        i5 = this.a.o;
        Object obj2 = arrayList.get(i5);
        if (obj2 instanceof c) {
            ((c) obj2).a(i);
        }
        h hVar = this.a;
        i6 = hVar.o;
        hVar.n = i6;
        this.a.p = i;
        arrayList2 = this.a.t;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            ((AdapterView.OnItemClickListener) it.next()).onItemClick(adapterView, view, i, j);
        }
    }
}
