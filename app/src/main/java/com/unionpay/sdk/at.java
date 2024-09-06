package com.unionpay.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class at extends Handler {
    at(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        ap apVar;
        ap apVar2;
        ap apVar3;
        ap apVar4;
        ap apVar5;
        ap apVar6;
        ap apVar7;
        ap apVar8;
        ap apVar9;
        try {
            if (message.obj == null || !(message.obj instanceof ap)) {
                return;
            }
            ap unused = as.e = (ap) message.obj;
            apVar = as.e;
            int i = apVar.i;
            apVar2 = as.e;
            if (apVar2.f == null) {
                as.a();
                as.c();
                return;
            }
            r.c = false;
            as a = as.a();
            apVar3 = as.e;
            String str = apVar3.a;
            apVar4 = as.e;
            String str2 = apVar4.b;
            apVar5 = as.e;
            String str3 = apVar5.c;
            apVar6 = as.e;
            String str4 = apVar6.e;
            apVar7 = as.e;
            byte[] bArr = apVar7.f;
            apVar8 = as.e;
            Object obj = apVar8.g;
            apVar9 = as.e;
            a.a(str, str2, str3, str4, bArr, obj, i, apVar9.h);
        } catch (Throwable unused2) {
        }
    }
}
