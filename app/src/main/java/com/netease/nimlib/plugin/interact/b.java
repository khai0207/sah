package com.netease.nimlib.plugin.interact;

import java.util.HashMap;
import java.util.Map;

/* compiled from: PluginInteractManager.java */
/* loaded from: classes.dex */
public class b {
    private Map<Class<? extends com.netease.nimlib.plugin.interact.a>, com.netease.nimlib.plugin.interact.a> a = new HashMap(2);

    public synchronized void a(Class<? extends com.netease.nimlib.plugin.interact.a> cls, Class<? extends com.netease.nimlib.plugin.interact.a> cls2) {
        try {
            this.a.put(cls, cls2.newInstance());
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.z("can't new instance ChatRoomInteract, e=" + th.getMessage());
        }
    }

    public synchronized <T> T a(Class<? extends com.netease.nimlib.plugin.interact.a> cls) {
        T t;
        t = (T) this.a.get(cls);
        if (t == null) {
            t = null;
        }
        return t;
    }

    public static b a() {
        return a.a;
    }

    /* compiled from: PluginInteractManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final b a = new b();
    }
}
