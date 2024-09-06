package com.netease.nimlib.o;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;

/* compiled from: ManifestHelper.java */
/* loaded from: classes.dex */
public class n {
    public static boolean a(Context context, Class cls) {
        return b(context, cls).a;
    }

    public static a b(Context context, Class cls) {
        ComponentInfo componentInfo = null;
        a aVar = new a();
        if (cls == null) {
            com.netease.nimlib.log.b.f("ManifestHelper", "getComponentInfoByName className is null");
            return aVar;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context.getPackageName(), cls.getName());
        com.netease.nimlib.log.b.N("ComponentName = " + componentName);
        try {
            if (Service.class.isAssignableFrom(cls)) {
                com.netease.nimlib.log.b.N(cls + " is Service");
                componentInfo = packageManager.getServiceInfo(componentName, 1152);
            } else if (BroadcastReceiver.class.isAssignableFrom(cls)) {
                com.netease.nimlib.log.b.N(cls + " is BroadcastReceiver");
                componentInfo = packageManager.getReceiverInfo(componentName, 1152);
            } else if (Activity.class.isAssignableFrom(cls)) {
                com.netease.nimlib.log.b.N(cls + " is Activity");
                componentInfo = packageManager.getActivityInfo(componentName, 1152);
            } else if (ContentProvider.class.isAssignableFrom(cls)) {
                com.netease.nimlib.log.b.N(cls + " is ContentProvider");
                componentInfo = packageManager.getProviderInfo(componentName, 1152);
            }
            com.netease.nimlib.log.b.N("ComponentInfo = " + componentInfo);
            aVar.a(componentInfo);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ManifestHelper", "getComponentInfoByName throw exception", e);
            aVar.a = false;
        }
        return aVar;
    }

    /* compiled from: ManifestHelper.java */
    /* loaded from: classes.dex */
    public static class a {
        private boolean a;
        private boolean b;
        private boolean c;
        private String d;
        private String e;
        private String f;

        private a() {
            this.a = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(ComponentInfo componentInfo) {
            if (componentInfo == null) {
                this.a = false;
                return;
            }
            this.a = true;
            this.b = componentInfo.enabled;
            this.c = componentInfo.exported;
            this.e = componentInfo.processName;
            if (componentInfo instanceof ServiceInfo) {
                this.d = ((ServiceInfo) componentInfo).permission;
            } else if (componentInfo instanceof ActivityInfo) {
                this.d = ((ActivityInfo) componentInfo).permission;
            } else if (componentInfo instanceof ProviderInfo) {
                this.f = ((ProviderInfo) componentInfo).authority;
            }
        }

        public String a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }

        public boolean c() {
            return this.a;
        }
    }
}
