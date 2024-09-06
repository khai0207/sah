package com.unionpay.sdk;

import com.unionpay.sdk.au;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
final class av implements HostnameVerifier {
    final /* synthetic */ au.b a;

    av(au.b bVar) {
        this.a = bVar;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
