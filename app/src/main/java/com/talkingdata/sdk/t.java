package com.talkingdata.sdk;

import android.app.Activity;
import android.content.Context;
import java.lang.reflect.Method;

/* compiled from: td */
/* loaded from: classes.dex */
class t implements bf {
    final /* synthetic */ Context a;
    final /* synthetic */ zz b;

    @Override // com.talkingdata.sdk.bf
    public void a(Object obj, Method method, Object[] objArr, Object obj2) {
    }

    t(zz zzVar, Context context) {
        this.b = zzVar;
        this.a = context;
    }

    @Override // com.talkingdata.sdk.bf
    public void a(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (this.a instanceof Activity) {
            if (name.equalsIgnoreCase("activityPaused")) {
                ae.b((Activity) this.a, a.d);
            } else if (name.equalsIgnoreCase("activityIdle")) {
                ae.a((Activity) this.a, a.d);
            }
        }
    }
}
