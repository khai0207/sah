package com.netease.nimlib;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.ipc.NIMContentProvider;
import com.netease.nimlib.ipc.cp.provider.PreferenceContentProvider;
import com.netease.nimlib.o.n;
import com.netease.nimlib.service.NimService;
import com.netease.nimlib.service.ResponseService;

/* compiled from: SDKManifestCheck.java */
/* loaded from: classes.dex */
public class e {
    public static void a(Context context, boolean z) {
        b(context, z);
        com.netease.nimlib.log.b.N("SDK AndroidManifest.xml mustCheck success !");
    }

    public static void a(Context context) {
        b(context);
        c(context);
        com.netease.nimlib.log.b.N("SDK AndroidManifest.xml check success !");
    }

    private static void b(Context context, boolean z) {
        com.netease.nimlib.log.b.N("SDK check PreferenceContentProvider");
        n.a b = n.b(context, PreferenceContentProvider.class);
        if (!b.c()) {
            if (z) {
                throw new IllegalStateException("can't find PreferenceContentProvider in AndroidManifest.xml, please check it !");
            }
            com.netease.nimlib.log.b.f("SDKManifestCheck", "can't find PreferenceContentProvider in AndroidManifest.xml, please check it !");
            com.netease.nimlib.log.b.f("SDKManifestCheck", "can't find PreferenceContentProvider in AndroidManifest.xml, please check it !");
            com.netease.nimlib.log.b.f("SDKManifestCheck", "can't find PreferenceContentProvider in AndroidManifest.xml, please check it !");
        }
        if (!(context.getPackageName() + ".ipc.provider.preference").equals(b.b())) {
            if (z) {
                throw new IllegalStateException("PreferenceContentProvider config of authority in AndroidManifest.xml is invalid, please check it !");
            }
            com.netease.nimlib.log.b.f("SDKManifestCheck", "PreferenceContentProvider config of authority in AndroidManifest.xml is invalid, please check it !");
            com.netease.nimlib.log.b.f("SDKManifestCheck", "PreferenceContentProvider config of authority in AndroidManifest.xml is invalid, please check it !");
            com.netease.nimlib.log.b.f("SDKManifestCheck", "PreferenceContentProvider config of authority in AndroidManifest.xml is invalid, please check it !");
        }
        String a = b.a();
        if (!TextUtils.isEmpty(a) && TextUtils.equals(a, context.getPackageName())) {
            return;
        }
        if (z) {
            throw new IllegalStateException("PreferenceContentProvider config of processName in AndroidManifest.xml should be empty, please check it !");
        }
        com.netease.nimlib.log.b.f("SDKManifestCheck", "PreferenceContentProvider config of processName in AndroidManifest.xml should be empty, please check it !");
        com.netease.nimlib.log.b.f("SDKManifestCheck", "PreferenceContentProvider config of processName in AndroidManifest.xml should be empty, please check it !");
        com.netease.nimlib.log.b.f("SDKManifestCheck", "PreferenceContentProvider config of processName in AndroidManifest.xml should be empty, please check it !");
    }

    private static void b(Context context) {
        n.a b = n.b(context, NIMContentProvider.class);
        if (!b.c()) {
            throw new IllegalStateException("can't find NIMContentProvider in AndroidManifest.xml, please check it !");
        }
        if (!(context.getPackageName() + ".ipc.provider").equals(b.b())) {
            throw new IllegalStateException("NIMContentProvider config of authority in AndroidManifest.xml is invalid, please check it !");
        }
        String a = b.a();
        n.a b2 = n.b(context, NimService.class);
        if (!b2.c()) {
            throw new IllegalStateException("can't find NimService in AndroidManifest.xml, please check it !");
        }
        if (!b2.a().equals(a)) {
            throw new IllegalStateException("NIMContentProvider config of processName in AndroidManifest.xml should be the same as NimService, please check it !");
        }
    }

    private static void c(Context context) {
        if (!n.b(context, ResponseService.class).c()) {
            throw new IllegalStateException("can't find ResponseService in AndroidManifest.xml, please check it !");
        }
    }
}
