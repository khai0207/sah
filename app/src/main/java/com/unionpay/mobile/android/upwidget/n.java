package com.unionpay.mobile.android.upwidget;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.upwidget.h;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
final class n implements View.OnClickListener {
    final /* synthetic */ h a;

    n(h hVar) {
        this.a = hVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i;
        boolean z;
        h.a[] aVarArr;
        h.a[] aVarArr2;
        ArrayList arrayList;
        h.a[] aVarArr3;
        int intValue = ((Integer) view.getTag()).intValue();
        i = this.a.o;
        if (intValue == i) {
            return;
        }
        this.a.a(intValue);
        z = this.a.e;
        if (z) {
            aVarArr = this.a.f;
            if (!TextUtils.isEmpty(aVarArr[intValue].d)) {
                aVarArr2 = this.a.f;
                view.setTag(aVarArr2[intValue].d);
                arrayList = this.a.w;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((View.OnClickListener) it.next()).onClick(view);
                }
                h hVar = this.a;
                aVarArr3 = hVar.f;
                hVar.a((LinearLayout) aVarArr3[intValue].c, true, "正在查询。。。", null, null);
                h.o(this.a);
            }
        }
        view.setTag(Integer.valueOf(intValue));
    }
}
