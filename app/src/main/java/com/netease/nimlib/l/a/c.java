package com.netease.nimlib.l.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.netease.nimlib.l.a.d;
import com.netease.nimlib.l.a.e;
import com.netease.nimlib.l.a.f;
import com.netease.nimlib.l.a.g;
import com.netease.nimlib.l.a.h;
import com.netease.nimlib.l.a.i;
import com.netease.nimlib.l.a.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NotificationCompat.java */
/* loaded from: classes.dex */
public class c {
    static final o a;

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    public static class b extends p {
        Bitmap a;
        Bitmap b;
        boolean c;
    }

    /* compiled from: NotificationCompat.java */
    /* renamed from: com.netease.nimlib.l.a.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0038c extends p {
        CharSequence a;
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    public static class f extends p {
        ArrayList<CharSequence> a = new ArrayList<>();
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    interface o {
        Notification a(d dVar, e eVar);
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    public static abstract class p {
        CharSequence d;
        CharSequence e;
        boolean f = false;

        public void a(Bundle bundle) {
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    protected static class e {
        protected e() {
        }

        public Notification a(d dVar, com.netease.nimlib.l.a.b bVar) {
            Notification b = bVar.b();
            if (dVar.E != null) {
                b.contentView = dVar.E;
            }
            return b;
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class n implements o {
        n() {
        }

        /* compiled from: NotificationCompat.java */
        /* loaded from: classes.dex */
        public static class a implements com.netease.nimlib.l.a.b {
            private Notification.Builder a;

            a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z) {
                boolean z2 = true;
                Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
                if ((notification.flags & 128) == 0) {
                    z2 = false;
                }
                this.a = deleteIntent.setFullScreenIntent(pendingIntent2, z2).setLargeIcon(bitmap).setNumber(i).setProgress(i2, i3, z);
            }

            @Override // com.netease.nimlib.l.a.b
            public Notification.Builder a() {
                return this.a;
            }

            @Override // com.netease.nimlib.l.a.b
            public Notification b() {
                return this.a.getNotification();
            }
        }

        @Override // com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            return eVar.a(dVar, new a(dVar.a, dVar.L, dVar.d(), dVar.c(), dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r));
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class h extends n {
        h() {
        }

        @Override // com.netease.nimlib.l.a.c.n, com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            Bundle a;
            i.a aVar = new i.a(dVar.a, dVar.L, dVar.d(), dVar.c(), dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r, dVar.l, dVar.j, dVar.n, dVar.w, dVar.A, dVar.s, dVar.t, dVar.f25u, dVar.E, dVar.F);
            c.a(aVar, dVar.v);
            c.a(aVar, dVar.m);
            Notification a2 = eVar.a(dVar, aVar);
            if (dVar.m != null && (a = c.a(a2)) != null) {
                dVar.m.a(a);
            }
            return a2;
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class i extends h {
        i() {
        }

        @Override // com.netease.nimlib.l.a.c.h, com.netease.nimlib.l.a.c.n, com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            j.a aVar = new j.a(dVar.a, dVar.L, dVar.d(), dVar.c(), dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r, dVar.k, dVar.l, dVar.j, dVar.n, dVar.w, dVar.M, dVar.A, dVar.s, dVar.t, dVar.f25u, dVar.E, dVar.F);
            c.a(aVar, dVar.v);
            c.a(aVar, dVar.m);
            return eVar.a(dVar, aVar);
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class j extends i {
        j() {
        }

        @Override // com.netease.nimlib.l.a.c.i, com.netease.nimlib.l.a.c.h, com.netease.nimlib.l.a.c.n, com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            d.a aVar = new d.a(dVar.a, dVar.L, dVar.d(), dVar.c(), dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r, dVar.k, dVar.l, dVar.j, dVar.n, dVar.w, dVar.M, dVar.A, dVar.s, dVar.t, dVar.f25u, dVar.E, dVar.F, dVar.N);
            c.a(aVar, dVar.v);
            c.a(aVar, dVar.m);
            Notification a = eVar.a(dVar, aVar);
            if (dVar.m != null) {
                dVar.m.a(c.a(a));
            }
            return a;
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class k extends j {
        k() {
        }

        @Override // com.netease.nimlib.l.a.c.j, com.netease.nimlib.l.a.c.i, com.netease.nimlib.l.a.c.h, com.netease.nimlib.l.a.c.n, com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            e.a aVar = new e.a(dVar.a, dVar.L, dVar.d(), dVar.c(), dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r, dVar.k, dVar.l, dVar.j, dVar.n, dVar.w, dVar.z, dVar.M, dVar.A, dVar.B, dVar.C, dVar.D, dVar.s, dVar.t, dVar.f25u, dVar.E, dVar.F, dVar.G, dVar.N);
            c.a(aVar, dVar.v);
            c.a(aVar, dVar.m);
            Notification a = eVar.a(dVar, aVar);
            if (dVar.m != null) {
                dVar.m.a(c.a(a));
            }
            return a;
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class l extends k {
        l() {
        }

        @Override // com.netease.nimlib.l.a.c.k, com.netease.nimlib.l.a.c.j, com.netease.nimlib.l.a.c.i, com.netease.nimlib.l.a.c.h, com.netease.nimlib.l.a.c.n, com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            f.a aVar = new f.a(dVar.a, dVar.L, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r, dVar.k, dVar.l, dVar.j, dVar.n, dVar.w, dVar.z, dVar.M, dVar.A, dVar.B, dVar.C, dVar.D, dVar.s, dVar.t, dVar.f25u, dVar.o, dVar.E, dVar.F, dVar.G, dVar.N);
            c.a(aVar, dVar.v);
            c.b(aVar, dVar.m);
            Notification a = eVar.a(dVar, aVar);
            if (dVar.m != null) {
                dVar.m.a(c.a(a));
            }
            return a;
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    static class m extends l {
        m() {
        }

        @Override // com.netease.nimlib.l.a.c.l, com.netease.nimlib.l.a.c.k, com.netease.nimlib.l.a.c.j, com.netease.nimlib.l.a.c.i, com.netease.nimlib.l.a.c.h, com.netease.nimlib.l.a.c.n, com.netease.nimlib.l.a.c.o
        public Notification a(d dVar, e eVar) {
            g.a aVar = new g.a(dVar.a, dVar.L, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.p, dVar.q, dVar.r, dVar.k, dVar.l, dVar.j, dVar.n, dVar.w, dVar.z, dVar.M, dVar.A, dVar.B, dVar.C, dVar.D, dVar.s, dVar.t, dVar.f25u, dVar.o, dVar.E, dVar.F, dVar.G, dVar.H, dVar.I, dVar.J, dVar.K, dVar.x, dVar.y, dVar.N);
            c.a(aVar, dVar.v);
            c.b(aVar, dVar.m);
            Notification a = eVar.a(dVar, aVar);
            if (dVar.m != null) {
                dVar.m.a(c.a(a));
            }
            return a;
        }
    }

    static void a(com.netease.nimlib.l.a.a aVar, ArrayList<a> arrayList) {
        Iterator<a> it = arrayList.iterator();
        while (it.hasNext()) {
            aVar.a(it.next());
        }
    }

    static void a(com.netease.nimlib.l.a.b bVar, p pVar) {
        if (pVar != null) {
            if (pVar instanceof C0038c) {
                C0038c c0038c = (C0038c) pVar;
                com.netease.nimlib.l.a.i.a(bVar, c0038c.d, c0038c.f, c0038c.e, c0038c.a);
            } else if (pVar instanceof f) {
                f fVar = (f) pVar;
                com.netease.nimlib.l.a.i.a(bVar, fVar.d, fVar.f, fVar.e, fVar.a);
            } else if (pVar instanceof b) {
                b bVar2 = (b) pVar;
                com.netease.nimlib.l.a.i.a(bVar, bVar2.d, bVar2.f, bVar2.e, bVar2.a, bVar2.b, bVar2.c);
            }
        }
    }

    static void b(com.netease.nimlib.l.a.b bVar, p pVar) {
        if (pVar != null) {
            if (pVar instanceof g) {
                g gVar = (g) pVar;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                for (g.a aVar : gVar.c) {
                    arrayList.add(aVar.a());
                    arrayList2.add(Long.valueOf(aVar.b()));
                    arrayList3.add(aVar.c());
                    arrayList4.add(aVar.d());
                    arrayList5.add(aVar.e());
                }
                com.netease.nimlib.l.a.f.a(bVar, gVar.a, gVar.b, arrayList, arrayList2, arrayList3, arrayList4, arrayList5);
                return;
            }
            a(bVar, pVar);
        }
    }

    static {
        if (com.netease.nimlib.l.a.a.a.a()) {
            a = new m();
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            a = new l();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            a = new k();
            return;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            a = new j();
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            a = new i();
        } else if (Build.VERSION.SDK_INT >= 16) {
            a = new h();
        } else {
            a = new n();
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    public static class d {
        Bundle A;
        int B;
        int C;
        Notification D;
        RemoteViews E;
        RemoteViews F;
        RemoteViews G;
        String H;
        int I;
        String J;
        long K;
        public Notification L;
        public ArrayList<String> M;
        private int N;
        public Context a;
        public CharSequence b;
        public CharSequence c;
        PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        public Bitmap g;
        public CharSequence h;
        public int i;
        int j;
        boolean k;
        public boolean l;
        public p m;
        public CharSequence n;
        public CharSequence[] o;
        int p;
        int q;
        boolean r;
        String s;
        boolean t;

        /* renamed from: u, reason: collision with root package name */
        String f25u;
        public ArrayList<a> v;
        boolean w;
        boolean x;
        boolean y;
        String z;

        public d(Context context, String str) {
            this.k = true;
            this.v = new ArrayList<>();
            this.w = false;
            this.B = 0;
            this.C = 0;
            this.I = 0;
            this.N = 0;
            Notification notification = new Notification();
            this.L = notification;
            this.a = context;
            this.H = str;
            notification.when = System.currentTimeMillis();
            this.L.audioStreamType = -1;
            this.j = 0;
            this.M = new ArrayList<>();
        }

        @Deprecated
        public d(Context context) {
            this(context, null);
        }

        public d a(long j) {
            this.L.when = j;
            return this;
        }

        public d a(int i) {
            this.L.icon = i;
            return this;
        }

        public d a(CharSequence charSequence) {
            this.b = d(charSequence);
            return this;
        }

        public d b(CharSequence charSequence) {
            this.c = d(charSequence);
            return this;
        }

        public d a(PendingIntent pendingIntent) {
            this.d = pendingIntent;
            return this;
        }

        public d c(CharSequence charSequence) {
            this.L.tickerText = d(charSequence);
            return this;
        }

        public d a(Bitmap bitmap) {
            this.g = bitmap;
            return this;
        }

        public d a(Uri uri) {
            this.L.sound = uri;
            this.L.audioStreamType = -1;
            return this;
        }

        public d a(int i, int i2, int i3) {
            this.L.ledARGB = i;
            this.L.ledOnMS = i2;
            this.L.ledOffMS = i3;
            int i4 = (this.L.ledOnMS == 0 || this.L.ledOffMS == 0) ? 0 : 1;
            Notification notification = this.L;
            notification.flags = i4 | (notification.flags & (-2));
            return this;
        }

        public d a(boolean z) {
            a(16, z);
            return this;
        }

        public d a(String str) {
            this.z = str;
            return this;
        }

        public d b(int i) {
            this.L.defaults = i;
            if ((i & 4) != 0) {
                this.L.flags |= 1;
            }
            return this;
        }

        private void a(int i, boolean z) {
            if (z) {
                Notification notification = this.L;
                notification.flags = i | notification.flags;
            } else {
                Notification notification2 = this.L;
                notification2.flags = (i ^ (-1)) & notification2.flags;
            }
        }

        public d c(int i) {
            this.B = i;
            return this;
        }

        public Notification a() {
            return c.a.a(this, b());
        }

        protected e b() {
            return new e();
        }

        protected static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        protected CharSequence c() {
            return this.c;
        }

        protected CharSequence d() {
            return this.b;
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    public static class g extends p {
        CharSequence a;
        CharSequence b;
        List<a> c = new ArrayList();

        g() {
        }

        @Override // com.netease.nimlib.l.a.c.p
        public void a(Bundle bundle) {
            super.a(bundle);
            CharSequence charSequence = this.a;
            if (charSequence != null) {
                bundle.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, charSequence);
            }
            CharSequence charSequence2 = this.b;
            if (charSequence2 != null) {
                bundle.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, charSequence2);
            }
            if (this.c.isEmpty()) {
                return;
            }
            bundle.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, a.a(this.c));
        }

        /* compiled from: NotificationCompat.java */
        /* loaded from: classes.dex */
        public static final class a {
            private final CharSequence a;
            private final long b;
            private final CharSequence c;
            private Bundle d;
            private String e;
            private Uri f;

            public CharSequence a() {
                return this.a;
            }

            public long b() {
                return this.b;
            }

            public CharSequence c() {
                return this.c;
            }

            public String d() {
                return this.e;
            }

            public Uri e() {
                return this.f;
            }

            private Bundle f() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.a;
                if (charSequence != null) {
                    bundle.putCharSequence(SpeechConstant.TEXT, charSequence);
                }
                bundle.putLong("time", this.b);
                CharSequence charSequence2 = this.c;
                if (charSequence2 != null) {
                    bundle.putCharSequence("sender", charSequence2);
                }
                String str = this.e;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.f;
                if (uri != null) {
                    bundle.putParcelable("uri", uri);
                }
                Bundle bundle2 = this.d;
                if (bundle2 != null) {
                    bundle.putBundle("extras", bundle2);
                }
                return bundle;
            }

            static Bundle[] a(List<a> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = list.get(i).f();
                }
                return bundleArr;
            }
        }
    }

    /* compiled from: NotificationCompat.java */
    /* loaded from: classes.dex */
    public static class a extends h.a {
        public static final h.a.InterfaceC0039a e = new h.a.InterfaceC0039a() { // from class: com.netease.nimlib.l.a.c.a.1
        };
        final Bundle a;
        public int b;
        public CharSequence c;
        public PendingIntent d;
        private final com.netease.nimlib.l.a.k[] f;
        private final com.netease.nimlib.l.a.k[] g;
        private boolean h;

        @Override // com.netease.nimlib.l.a.h.a
        public int a() {
            return this.b;
        }

        @Override // com.netease.nimlib.l.a.h.a
        public CharSequence b() {
            return this.c;
        }

        @Override // com.netease.nimlib.l.a.h.a
        public PendingIntent c() {
            return this.d;
        }

        @Override // com.netease.nimlib.l.a.h.a
        public Bundle d() {
            return this.a;
        }

        @Override // com.netease.nimlib.l.a.h.a
        public boolean e() {
            return this.h;
        }

        @Override // com.netease.nimlib.l.a.h.a
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public com.netease.nimlib.l.a.k[] i() {
            return this.f;
        }

        @Override // com.netease.nimlib.l.a.h.a
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public com.netease.nimlib.l.a.k[] h() {
            return this.g;
        }
    }

    public static Bundle a(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return com.netease.nimlib.l.a.i.a(notification);
        }
        return null;
    }
}
