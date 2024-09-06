package com.netease.nimlib.ipc;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.ipc.cp.provider.AbsContentProvider;

/* loaded from: classes.dex */
public class NIMContentProvider extends AbsContentProvider {
    private final c a = new c();

    @Override // com.netease.nimlib.ipc.cp.provider.AbsContentProvider
    public com.netease.nimlib.ipc.cp.b.b a(Context context, String str) {
        if (TextUtils.equals("NIM", str)) {
            return this.a;
        }
        return null;
    }
}
