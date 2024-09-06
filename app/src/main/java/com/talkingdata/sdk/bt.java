package com.talkingdata.sdk;

import com.talkingdata.sdk.bs;
import java.util.Comparator;

/* compiled from: td */
/* loaded from: classes.dex */
class bt implements Comparator {
    final /* synthetic */ bs a;

    bt(bs bsVar) {
        this.a = bsVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(bs.d dVar, bs.d dVar2) {
        if (dVar.c == dVar2.c) {
            return 0;
        }
        return dVar.c < dVar2.c ? 1 : -1;
    }
}
