package com.netease.nimlib.push.net;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.biz.d.a;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.push.net.d;
import com.netease.nimlib.push.packet.c.f;
import com.netease.nimlib.push.packet.c.g;
import com.netease.nimlib.push.packet.symmetry.SymmetryType;
import com.netease.nimlib.sdk.NimHandshakeType;
import java.nio.ByteBuffer;
import java.security.PublicKey;

/* compiled from: PackagePacker.java */
/* loaded from: classes.dex */
public class e {
    private Context a;
    private b b;
    private boolean c;
    private a.C0028a d;
    private com.netease.nimlib.push.packet.asymmetric.b e;
    private c f;
    private d g;
    private d.b h;
    private String i;
    private boolean j = false;
    private com.netease.nimlib.push.net.lbs.b k;

    /* compiled from: PackagePacker.java */
    /* loaded from: classes.dex */
    public interface b {
        void a();

        void a(a.C0028a c0028a, boolean z);
    }

    /* compiled from: PackagePacker.java */
    /* loaded from: classes.dex */
    private class a {
        private final byte[] b;
        private final com.netease.nimlib.push.packet.symmetry.c c;
        private final com.netease.nimlib.push.packet.symmetry.c d;
        private final SymmetryType e;

        a(SymmetryType symmetryType) {
            this.e = symmetryType;
            byte[] a = com.netease.nimlib.push.packet.symmetry.d.a(symmetryType);
            this.b = a;
            this.c = com.netease.nimlib.push.packet.symmetry.d.a(symmetryType, a);
            this.d = com.netease.nimlib.push.packet.symmetry.d.b(symmetryType, this.b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public com.netease.nimlib.push.packet.c.b a(com.netease.nimlib.push.packet.c.b bVar) {
            byte[] b = this.c.b(bVar.b().array(), 0, bVar.a());
            if (b == null) {
                return bVar;
            }
            com.netease.nimlib.push.packet.c.b bVar2 = new com.netease.nimlib.push.packet.c.b();
            bVar2.a(com.netease.nimlib.push.packet.c.d.a(b.length));
            bVar2.a(b);
            return bVar2;
        }

        public byte[] a(byte[] bArr, int i, int i2) {
            byte[] c = this.d.c(bArr, i, i2);
            return c == null ? bArr : c;
        }
    }

    /* compiled from: PackagePacker.java */
    /* loaded from: classes.dex */
    private class c {
        private final a b;
        private final PublicKey c;
        private final int d;

        public c(a aVar, PublicKey publicKey, int i) {
            this.b = aVar;
            this.c = publicKey;
            this.d = i;
        }

        public a.C0028a a(a.C0028a c0028a) {
            try {
                com.netease.nimlib.push.a.b.a aVar = new com.netease.nimlib.push.a.b.a(this.d, c(c0028a));
                return new a.C0028a(aVar.i(), aVar.a().b());
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d("core", "pack first pack error", th);
                throw th;
            }
        }

        public com.netease.nimlib.push.packet.c.b a(a.C0028a c0028a, boolean z) {
            com.netease.nimlib.push.packet.c.b a = e.this.a(c0028a, true);
            e.this.a("send " + c0028a.a);
            return !z ? this.b.a(a) : a;
        }

        private byte[] c(a.C0028a c0028a) {
            if (c0028a == null) {
                return null;
            }
            com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
            bVar.b(this.b.b);
            bVar.a(e.this.a(c0028a, false).b());
            return com.netease.nimlib.push.packet.asymmetric.d.a(this.c, bVar.b().array(), 0, bVar.a());
        }

        private byte[] d(a.C0028a c0028a) {
            if (c0028a == null) {
                return null;
            }
            com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a((Integer) 0, this.b.b);
            bVar.a(cVar);
            bVar.a(e.this.a(c0028a, false).b());
            return e.this.e.g().a(this.c, bVar.b().array(), 0, bVar.a());
        }

        public com.netease.nimlib.push.packet.c.b b(a.C0028a c0028a) {
            com.netease.nimlib.push.a.b.e eVar = new com.netease.nimlib.push.a.b.e(a(), d(c0028a));
            com.netease.nimlib.push.packet.c.b a = e.this.a(new a.C0028a(eVar.i(), eVar.a().b()), true);
            if (c0028a != null) {
                e.this.a("sendNew " + c0028a.a);
            }
            return a;
        }

        private com.netease.nimlib.push.packet.b.c a() {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(0, 91700);
            cVar.a(1, 0);
            cVar.a(2, e.this.e.f());
            cVar.a(3, e.this.e.a().getValue());
            cVar.a(4, e.this.e.b().getValue());
            cVar.a(7, 0);
            cVar.a(8, TextUtils.isEmpty(e.this.i) ? com.netease.nimlib.c.g() : e.this.i);
            return cVar;
        }
    }

    /* compiled from: PackagePacker.java */
    /* loaded from: classes.dex */
    private class d {
        private final a b;
        private int d;
        private final byte[] c = new byte[4];
        private int e = 0;
        private int f = 0;

        d(a aVar) {
            this.b = aVar;
            a();
        }

        void a() {
            this.d = -1;
            this.e = 0;
            this.f = 0;
        }

        byte[] a(ByteBuffer byteBuffer) {
            if (AnonymousClass1.a[this.b.e.ordinal()] == 1) {
                return b(byteBuffer);
            }
            return c(byteBuffer);
        }

        private byte[] b(ByteBuffer byteBuffer) throws g {
            if (this.d <= 5) {
                if (byteBuffer.remaining() < 4) {
                    return null;
                }
                byteBuffer.get(this.c);
                com.netease.nimlib.push.net.a.c.b().a(this.c);
                this.b.a(this.c, 0, 4);
                int a = com.netease.nimlib.push.packet.c.d.a(this.c);
                this.d = a;
                if (a <= 5) {
                    a();
                    throw new g();
                }
                this.d = a + com.netease.nimlib.push.packet.c.d.b(a);
            }
            com.netease.nimlib.log.b.b("core", String.format("received packetSize: %d, readableBytes: %d", Integer.valueOf(this.d), Integer.valueOf(byteBuffer.remaining())));
            int i = this.d - 4;
            if (byteBuffer.remaining() < i) {
                return null;
            }
            byte[] bArr = new byte[this.d];
            System.arraycopy(this.c, 0, bArr, 0, 4);
            byteBuffer.get(bArr, 4, i);
            com.netease.nimlib.push.net.a.c.b().a(this.d, bArr);
            this.b.a(bArr, 4, i);
            com.netease.nimlib.push.net.a.c.b().b(this.d, bArr);
            a();
            return bArr;
        }

        private byte[] c(ByteBuffer byteBuffer) {
            if (this.d <= 17) {
                if (byteBuffer.remaining() < 4) {
                    return null;
                }
                byteBuffer.get(this.c);
                int a = com.netease.nimlib.push.packet.c.d.a(this.c);
                this.f = a;
                int length = com.netease.nimlib.push.packet.c.d.a(a).length;
                this.e = length;
                int i = length + this.f;
                this.d = i;
                if (i < 17) {
                    a();
                    throw new g();
                }
            }
            com.netease.nimlib.log.b.b("core", String.format("received packetSize: %d, readableBytes: %d", Integer.valueOf(this.d), Integer.valueOf(byteBuffer.remaining())));
            if (byteBuffer.remaining() < this.d - 4) {
                return null;
            }
            byte[] bArr = new byte[this.f];
            byte[] bArr2 = this.c;
            int i2 = this.e;
            System.arraycopy(bArr2, i2, bArr, 0, 4 - i2);
            byteBuffer.get(bArr, 4 - this.e, this.d - 4);
            byte[] a2 = this.b.a(bArr, 0, this.f);
            a();
            return a2;
        }
    }

    /* compiled from: PackagePacker.java */
    /* renamed from: com.netease.nimlib.push.net.e$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SymmetryType.values().length];
            a = iArr;
            try {
                iArr[SymmetryType.RC4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SymmetryType.AES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SymmetryType.SM4.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public e(Context context, d.b bVar, String str, b bVar2) {
        this.a = context.getApplicationContext();
        this.b = bVar2;
        this.h = bVar;
        this.i = str;
        a(com.netease.nimlib.d.g.n() == NimHandshakeType.V0);
    }

    public void a(com.netease.nimlib.push.net.lbs.b bVar) {
        this.k = bVar;
    }

    private void a(boolean z) {
        this.f = null;
        this.g = null;
        this.c = false;
        this.j = z;
    }

    public void a() {
        com.netease.nimlib.push.packet.asymmetric.b a2 = com.netease.nimlib.push.packet.asymmetric.b.a(this.a);
        this.e = a2;
        this.c = false;
        if (this.j) {
            a2.d();
            a aVar = new a(this.e.b());
            this.f = new c(aVar, this.e.e(), this.e.f());
            this.g = new d(aVar);
            return;
        }
        a2.c();
        a aVar2 = new a(SymmetryType.RC4);
        this.f = new c(aVar2, this.e.h(), this.e.i());
        this.g = new d(aVar2);
    }

    public final com.netease.nimlib.push.packet.c.b a(a.C0028a c0028a) {
        if (this.j) {
            return b(c0028a);
        }
        return c(c0028a);
    }

    private com.netease.nimlib.push.packet.c.b b(a.C0028a c0028a) {
        if (this.c) {
            return this.f.a(c0028a, false);
        }
        this.c = true;
        this.d = c0028a;
        return this.f.b(c0028a);
    }

    private com.netease.nimlib.push.packet.c.b c(a.C0028a c0028a) {
        if (this.c) {
            return this.f.a(c0028a, false);
        }
        this.c = true;
        this.d = c0028a;
        return this.f.a(this.f.a(c0028a), true);
    }

    public final a.C0029a a(ByteBuffer byteBuffer) throws g {
        byte[] a2;
        d dVar = this.g;
        if (dVar == null || (a2 = dVar.a(byteBuffer)) == null) {
            return null;
        }
        a.C0029a a3 = a(a2);
        if (a3.a.i() == 1) {
            byte j = a3.a.j();
            if (j == 5) {
                a(a3);
                return null;
            }
            if (j == 1) {
                b(a3);
                return null;
            }
        }
        if (a3.a.k() < 0 || a3.a.m() < 0) {
            throw new g("invalid headers, connection may be corrupted");
        }
        return a3;
    }

    public void b() {
        d dVar = this.g;
        if (dVar != null) {
            dVar.a();
        }
    }

    private void a(a.C0029a c0029a) {
        com.netease.nimlib.push.a.c.d dVar = new com.netease.nimlib.push.a.c.d();
        dVar.a(c0029a.a);
        short r = dVar.r();
        try {
            if (r == 201) {
                dVar.a(c0029a.b, this.e.a());
                this.e.g().a(dVar.a(), dVar.b(), dVar.c(), dVar.d());
                a(true);
                com.netease.nimlib.log.b.d("core", "public key updated to: " + dVar.a());
                if (this.b != null) {
                    this.b.a(this.d, true);
                }
            } else if (r != 200) {
                com.netease.nimlib.log.b.d("core", "Handshake fail[code=" + ((int) dVar.j().l()) + "]");
                this.e.j();
                if (this.b != null) {
                    this.b.a();
                }
            } else if (this.b != null) {
                this.b.a(this.d, false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.d = null;
    }

    private void b(a.C0029a c0029a) {
        com.netease.nimlib.push.a.c.a aVar = new com.netease.nimlib.push.a.c.a();
        aVar.a(c0029a.a);
        short r = aVar.r();
        try {
            if (r == 201) {
                aVar.a(c0029a.b);
                this.e.a(aVar.a(), aVar.b(), aVar.c());
                a(false);
                com.netease.nimlib.log.b.d("core", "public key updated to: " + aVar.a());
                if (this.b != null) {
                    this.b.a(this.d, true);
                }
            } else if (r != 200) {
                com.netease.nimlib.log.b.d("core", "Handshake fail[code=" + ((int) aVar.j().l()) + "]");
                this.e.j();
                if (this.b != null) {
                    this.b.a();
                }
            } else if (this.b != null) {
                this.b.a(this.d, false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.netease.nimlib.push.packet.c.b a(a.C0028a c0028a, boolean z) {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        int limit = c0028a.b.limit();
        ByteBuffer byteBuffer = c0028a.b;
        if (z && limit >= 1024 && !c0028a.a.c()) {
            byteBuffer = com.netease.nimlib.push.packet.c.e.a(c0028a.b);
            limit = byteBuffer.limit();
            c0028a.a.e();
        }
        c0028a.a.a(c0028a.a.h() + limit);
        bVar.a(c0028a.a);
        bVar.a(byteBuffer);
        return bVar;
    }

    private a.C0029a a(byte[] bArr) {
        f fVar = new f(bArr);
        com.netease.nimlib.push.packet.a aVar = new com.netease.nimlib.push.packet.a();
        fVar.a(aVar);
        a("received " + aVar);
        if (aVar.c()) {
            f fVar2 = new f(com.netease.nimlib.push.packet.c.e.a(fVar));
            aVar.g();
            fVar = fVar2;
        }
        a.C0029a c0029a = new a.C0029a();
        c0029a.a = aVar;
        c0029a.b = fVar;
        return c0029a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.netease.nimlib.push.net.d.a(str, this.h);
    }
}
