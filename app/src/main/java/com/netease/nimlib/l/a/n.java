package com.netease.nimlib.l.a;

import android.os.Bundle;
import com.netease.nimlib.l.a.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* compiled from: RemoteInputCompatJellybean.java */
/* loaded from: classes.dex */
class n {
    static Bundle a(m.a aVar) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", aVar.a());
        bundle.putCharSequence("label", aVar.b());
        bundle.putCharSequenceArray("choices", aVar.c());
        bundle.putBoolean("allowFreeFormInput", aVar.e());
        bundle.putBundle("extras", aVar.f());
        Set<String> d = aVar.d();
        if (d != null && !d.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>(d.size());
            Iterator<String> it = d.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    static Bundle[] a(m.a[] aVarArr) {
        if (aVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            bundleArr[i] = a(aVarArr[i]);
        }
        return bundleArr;
    }
}
