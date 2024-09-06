package com.netease.nimlib.l.a;

import android.app.RemoteInput;
import com.netease.nimlib.l.a.m;

/* compiled from: RemoteInputCompatApi20.java */
/* loaded from: classes.dex */
class l {
    static RemoteInput[] a(m.a[] aVarArr) {
        if (aVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            m.a aVar = aVarArr[i];
            remoteInputArr[i] = new RemoteInput.Builder(aVar.a()).setLabel(aVar.b()).setChoices(aVar.c()).setAllowFreeFormInput(aVar.e()).addExtras(aVar.f()).build();
        }
        return remoteInputArr;
    }
}
