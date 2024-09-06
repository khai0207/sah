package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.auth.OnlineClient;

/* compiled from: AuthServiceRemote.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.i.j implements AuthService {
    @Override // com.netease.nimlib.sdk.auth.AuthService
    public AbortableFuture login(LoginInfo loginInfo) {
        com.netease.nimlib.biz.i.a().a(b(), loginInfo);
        return new com.netease.nimlib.i.h<LoginInfo>(loginInfo) { // from class: com.netease.nimlib.biz.f.a.1
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                a.this.logout();
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public void logout() {
        com.netease.nimlib.biz.i.a().h();
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public InvocationFuture<Void> kickOtherClient(OnlineClient onlineClient) {
        if (!(onlineClient instanceof com.netease.nimlib.biz.f)) {
            return null;
        }
        com.netease.nimlib.biz.d.h.a aVar = new com.netease.nimlib.biz.d.h.a(((com.netease.nimlib.biz.f) onlineClient).a());
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public int getKickedClientType() {
        return com.netease.nimlib.h.i();
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public int getKickedCustomClientType() {
        return com.netease.nimlib.h.j();
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public boolean openLocalCache(String str) {
        return com.netease.nimlib.biz.i.a().b(str);
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public String getDeviceID() {
        return com.netease.nimlib.push.b.c();
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public void killUI() {
        System.exit(-163);
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public void killCore() {
        com.netease.nimlib.biz.i.a().i();
    }

    @Override // com.netease.nimlib.sdk.auth.AuthService
    public void exit() {
        killCore();
        killUI();
    }
}
