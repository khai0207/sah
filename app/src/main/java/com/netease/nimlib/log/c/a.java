package com.netease.nimlib.log.c;

import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.log.LogWs;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: LogBase.java */
/* loaded from: classes.dex */
public abstract class a {
    private static a g;
    int a;
    int b;
    String c;
    private InterfaceC0041a e;
    private int d = 3;
    private final Executor f = Executors.newSingleThreadExecutor();

    /* compiled from: LogBase.java */
    /* renamed from: com.netease.nimlib.log.c.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0041a {
        boolean a();
    }

    abstract void a(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract void b(boolean z);

    abstract void c();

    public static a a() {
        if (g == null) {
            g = new a() { // from class: com.netease.nimlib.log.c.a.1
                @Override // com.netease.nimlib.log.c.a
                void a(String str) {
                }

                @Override // com.netease.nimlib.log.c.a
                /* renamed from: a */
                void b(boolean z) {
                }

                @Override // com.netease.nimlib.log.c.a
                void c() {
                }
            };
        }
        return g;
    }

    public void a(String str, String str2, int i, int i2, int i3, final boolean z, InterfaceC0041a interfaceC0041a) {
        this.c = com.netease.nimlib.log.c.a.a.b(str, str2);
        this.d = i;
        this.e = interfaceC0041a;
        this.a = i2;
        this.b = i3;
        if (i2 <= 0) {
            this.a = 16777216;
        }
        if (this.b <= 0) {
            this.b = 8388608;
        }
        a(new Runnable() { // from class: com.netease.nimlib.log.c.-$$Lambda$a$r0QqNW4aD3cwAf7qUPBBjdrzOIA
            @Override // java.lang.Runnable
            public final void run() {
                a.this.b(z);
            }
        });
    }

    public void b() {
        if (TextUtils.isEmpty(this.c)) {
            return;
        }
        a(new Runnable() { // from class: com.netease.nimlib.log.c.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.this.c();
            }
        });
    }

    public void a(final int i, final String str, final String str2, final Throwable th) {
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(str2)) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long id = Thread.currentThread().getId();
        try {
            a(new Runnable() { // from class: com.netease.nimlib.log.c.a.3
                @Override // java.lang.Runnable
                public void run() {
                    String a = b.a(currentTimeMillis);
                    if (com.netease.nimlib.log.b.a.a()) {
                        Log.println(i, str, id + "/" + str2 + '\n' + Log.getStackTraceString(th));
                    }
                    if (a.this.d <= i) {
                        if (a.this.e == null || a.this.e.a()) {
                            String a2 = b.a(str, a, str2, th);
                            LogWs.sendLog(a2);
                            a.this.a(a2);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Runnable runnable) {
        this.f.execute(runnable);
    }

    public void a(String str, String str2) {
        a(str, str2, null);
    }

    public void a(String str, String str2, Throwable th) {
        a(4, str, str2, th);
    }

    public void b(String str, String str2) {
        b(str, str2, null);
    }

    public void b(String str, String str2, Throwable th) {
        a(2, str, str2, th);
    }

    public void c(String str, String str2) {
        c(str, str2, null);
    }

    public void c(String str, String str2, Throwable th) {
        a(6, str, str2, th);
    }

    public void d(String str, String str2) {
        d(str, str2, null);
    }

    public void d(String str, String str2, Throwable th) {
        a(3, str, str2, th);
    }

    public void e(String str, String str2) {
        e(str, str2, null);
    }

    public void e(String str, String str2, Throwable th) {
        a(5, str, str2, th);
    }
}
