package com.talkingdata.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: td */
/* loaded from: classes.dex */
final class ba extends BroadcastReceiver {
    BroadcastReceiver a;
    final /* synthetic */ Object b;
    final /* synthetic */ Context c;

    ba(Object obj, Context context) {
        this.b = obj;
        this.c = context;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a = this;
        bb bbVar = new bb(this);
        bbVar.setName("t-scan");
        bbVar.start();
    }
}
