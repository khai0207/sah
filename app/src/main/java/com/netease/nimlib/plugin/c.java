package com.netease.nimlib.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.i.j;
import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: PluginLoader.java */
/* loaded from: classes.dex */
public class c {
    private static c c = new c();
    private com.netease.nimlib.biz.b.c a;
    private List<a> b = new ArrayList(8);

    public static c a() {
        return c;
    }

    public void a(Context context) {
        a(context, false);
    }

    public void a(Context context, boolean z) {
        List<String> f = f();
        if (f == null || f.size() == 0) {
            return;
        }
        for (String str : f) {
            try {
                Class<?> loadClass = context.getClassLoader().loadClass(str);
                Annotation annotation = loadClass.getAnnotation(d.class);
                if (!z || annotation != null) {
                    if (a.class.isAssignableFrom(loadClass)) {
                        com.netease.nimlib.log.b.d("PluginLoader", "plugin [" + str + "] found");
                        try {
                            a aVar = (a) loadClass.newInstance();
                            this.b.add(aVar);
                            if (TextUtils.equals("com.netease.nimlib.fusionstorage.plugin.Plugin", str) && (aVar instanceof com.netease.nimlib.biz.b.c)) {
                                this.a = (com.netease.nimlib.biz.b.c) aVar;
                                com.netease.nimlib.log.b.d("PluginLoader", "FCSPlugin [" + this.a + "] found");
                            }
                        } catch (Throwable th) {
                            com.netease.nimlib.log.b.e("PluginLoader", "unable to load plugin " + loadClass.getName(), th);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        f(context);
    }

    private List<a> e() {
        return this.b;
    }

    public com.netease.nimlib.biz.b.c b() {
        return this.a;
    }

    public Map<Class<? extends com.netease.nimlib.biz.e.a>, com.netease.nimlib.biz.c.a> c() {
        HashMap hashMap = new HashMap();
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            Map<Class<? extends com.netease.nimlib.biz.e.a>, com.netease.nimlib.biz.c.a> b = it.next().b();
            if (b != null) {
                hashMap.putAll(b);
            }
        }
        return hashMap;
    }

    public Map<Class<?>, Class<? extends j>> d() {
        HashMap hashMap = new HashMap();
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            Map<Class<?>, Class<? extends j>> a = it.next().a();
            if (a != null) {
                hashMap.putAll(a);
            }
        }
        return hashMap;
    }

    public MsgAttachment a(int i, String str) {
        if (i == MsgTypeEnum.text.getValue()) {
            return null;
        }
        JSONObject a = k.a(str);
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            MsgAttachment a2 = it.next().a(i, a);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    private void f(Context context) {
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            it.next().a(context);
        }
    }

    public void b(Context context) {
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            it.next().b(context);
        }
    }

    public void c(Context context) {
        a(context, true);
    }

    public void d(Context context) {
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            it.next().c(context);
        }
    }

    public void e(Context context) {
        Iterator<a> it = e().iterator();
        while (it.hasNext()) {
            it.next().d(context);
        }
    }

    private static List<String> f() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add("com.netease.nimlib.qchat.plugin.Plugin");
        arrayList.add("com.netease.nimlib.chatroom.plugin.Plugin");
        arrayList.add("com.netease.nimlib.avchat.plugin.Plugin");
        arrayList.add("com.netease.nimlib.rts.plugin.Plugin");
        arrayList.add("com.netease.nimlib.fts.plugin.Plugin");
        arrayList.add("com.netease.nimlib.lucene.plugin.Plugin");
        arrayList.add("com.netease.nimlib.sf.plugin.Plugin");
        arrayList.add("com.netease.nimlib.document.plugin.Plugin");
        arrayList.add("com.netease.nimlib.mixpush.plugin.Plugin");
        arrayList.add("com.netease.nimlib.avsignalling.plugin.Plugin");
        arrayList.add("com.netease.nimlib.superteam.plugin.Plugin");
        arrayList.add("com.netease.nimlib.ysf.plugin.Plugin");
        arrayList.add("com.netease.nimlib.fusionstorage.plugin.Plugin");
        return arrayList;
    }
}
