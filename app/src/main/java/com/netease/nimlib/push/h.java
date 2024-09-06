package com.netease.nimlib.push;

import java.util.ArrayList;
import java.util.List;

/* compiled from: UserSession.java */
/* loaded from: classes.dex */
public class h {
    private static ArrayList<com.netease.nimlib.biz.f> a;

    public static synchronized void a() {
        synchronized (h.class) {
            boolean z = a != null && a.size() > 0;
            a = null;
            if (z) {
                c();
            }
        }
    }

    public static ArrayList<com.netease.nimlib.biz.f> b() {
        return a;
    }

    public static synchronized void a(ArrayList<com.netease.nimlib.biz.f> arrayList) {
        synchronized (h.class) {
            a = arrayList;
            c();
        }
    }

    public static synchronized void a(List<com.netease.nimlib.biz.f> list) {
        synchronized (h.class) {
            if (list == null) {
                return;
            }
            c(list);
            c();
        }
    }

    public static synchronized void b(List<com.netease.nimlib.biz.f> list) {
        synchronized (h.class) {
            if (list == null) {
                return;
            }
            c(list);
            if (a == null) {
                a = new ArrayList<>();
            }
            a.addAll(list);
            c();
        }
    }

    private static synchronized void c(List<com.netease.nimlib.biz.f> list) {
        synchronized (h.class) {
            if (a == null) {
                return;
            }
            a.removeAll(list);
            if (a.size() == 0) {
                a = null;
            }
        }
    }

    private static void c() {
        com.netease.nimlib.ipc.e.c();
    }
}
