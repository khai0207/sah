package com.iflytek.cloud.util.a;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.iflytek.cloud.util.ContactManager;

/* loaded from: classes.dex */
public class b extends ContactManager {
    private static b a = null;
    private static Context b = null;
    private static int c = 4;
    private static com.iflytek.cloud.util.a.c.a d;
    private static com.iflytek.cloud.util.a.a e;
    private static C0020b f;
    private static a g;
    private static ContactManager.ContactListener i;
    private HandlerThread h;
    private Handler j;
    private long k = 0;
    private long l = 0;

    /* loaded from: classes.dex */
    private class a extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "CallLogObserver | onChange");
            if (System.currentTimeMillis() - b.this.l < 5000) {
                com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "onChange too much");
                return;
            }
            b.this.l = System.currentTimeMillis();
            b.this.c();
        }
    }

    /* renamed from: com.iflytek.cloud.util.a.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private class C0020b extends ContentObserver {
        public C0020b(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "ContactObserver_Contact| onChange");
            if (System.currentTimeMillis() - b.this.k < 5000) {
                com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "onChange too much");
                return;
            }
            b.this.k = System.currentTimeMillis();
            b.this.b();
            b.this.c();
        }
    }

    private b() {
        this.h = null;
        d = Build.VERSION.SDK_INT > c ? new com.iflytek.cloud.util.a.b.b(b) : new com.iflytek.cloud.util.a.b.a(b);
        e = new com.iflytek.cloud.util.a.a(b, d);
        HandlerThread handlerThread = new HandlerThread("ContactManager_worker");
        this.h = handlerThread;
        handlerThread.start();
        this.j = new Handler(this.h.getLooper());
        this.h.setPriority(1);
        f = new C0020b(this.j);
        g = new a(this.j);
    }

    public static b a() {
        return a;
    }

    public static b a(Context context, ContactManager.ContactListener contactListener) {
        i = contactListener;
        b = context;
        if (a == null) {
            a = new b();
            b.getContentResolver().registerContentObserver(d.a(), true, f);
            b.getContentResolver().registerContentObserver(d.f(), true, g);
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            if (i != null && e != null) {
                String a2 = e.a(e.a(), '\n');
                String str = b.getFilesDir().getParent() + "/name.txt";
                String a3 = d.a(str);
                if (a2 == null || a3 == null || !a2.equals(a3)) {
                    d.a(str, a2, true);
                    i.onContactQueryFinish(a2, true);
                } else {
                    com.iflytek.cloud.a.g.a.a.a("iFly_ContactManager", "contact name is not change.");
                    i.onContactQueryFinish(a2, false);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.iflytek.cloud.util.a.a aVar = e;
        if (aVar != null) {
            aVar.a(10);
        }
    }

    @Override // com.iflytek.cloud.util.ContactManager
    public void asyncQueryAllContactsName() {
        this.j.post(new c(this));
    }

    @Override // com.iflytek.cloud.util.ContactManager
    public String queryAllContactsName() {
        if (e == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : e.a()) {
            sb.append(str + '\n');
        }
        return sb.toString();
    }
}
