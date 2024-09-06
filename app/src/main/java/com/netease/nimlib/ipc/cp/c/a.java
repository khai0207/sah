package com.netease.nimlib.ipc.cp.c;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.util.SparseArray;

/* compiled from: Protocol.java */
/* loaded from: classes.dex */
public final class a {
    public static final String[] a = {"value"};
    private static UriMatcher b;
    private static SparseArray<String> c;

    private static void a(Context context) {
        b = new UriMatcher(-1);
        c = new SparseArray<>();
        a(context.getPackageName() + ".ipc.provider.preference");
        String str = context.getPackageName() + ".ipc.provider";
        a(str);
        b(str);
    }

    private static void a(String str) {
        b.addURI(str, "string/*/*", 1);
        b.addURI(str, "boolean/*/*", 2);
        b.addURI(str, "integer/*/*", 3);
        b.addURI(str, "long/*/*", 4);
        b.addURI(str, "float/*/*", 5);
        b.addURI(str, "void/*/*", 6);
    }

    private static void b(String str) {
        c.put(1, "content://" + str + "/string/");
        c.put(2, "content://" + str + "/boolean/");
        c.put(3, "content://" + str + "/integer/");
        c.put(4, "content://" + str + "/long/");
        c.put(5, "content://" + str + "/float/");
        c.put(6, "content://" + str + "/void/");
    }

    public static Uri a(Context context, String str, String str2, int i) {
        if (b == null || c == null) {
            if (context == null) {
                return null;
            }
            a(context);
        }
        return Uri.parse(c.get(i) + str + "/" + str2);
    }

    public static int a(Context context, Uri uri) {
        if (b == null || c == null) {
            if (context == null) {
                return 0;
            }
            a(context);
        }
        return b.match(uri);
    }

    /* compiled from: Protocol.java */
    /* renamed from: com.netease.nimlib.ipc.cp.c.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0036a {
        private String a;
        private String b;

        public C0036a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String toString() {
            return "KeyInfo{name='" + this.a + "', key='" + this.b + "'}";
        }
    }
}
