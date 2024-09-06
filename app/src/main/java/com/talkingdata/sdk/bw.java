package com.talkingdata.sdk;

import com.talkingdata.sdk.bv;
import java.util.Comparator;

/* compiled from: td */
/* loaded from: classes.dex */
class bw implements Comparator {
    final /* synthetic */ bv a;

    bw(bv bvVar) {
        this.a = bvVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(bv.a aVar, bv.a aVar2) {
        if (aVar.c == aVar2.c) {
            return 0;
        }
        return aVar.c < aVar2.c ? 1 : -1;
    }
}
