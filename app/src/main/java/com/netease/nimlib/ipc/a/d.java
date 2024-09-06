package com.netease.nimlib.ipc.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.netease.nimlib.biz.e.a;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PacketData.java */
/* loaded from: classes.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.netease.nimlib.ipc.a.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            d a2 = b.a(parcel);
            if (a2 != null) {
                a2.d.flip();
            }
            return a2;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return new d[i];
        }
    };
    public int a;
    private int b;
    private ByteBuffer c;
    private ByteBuffer d;
    private boolean e;
    private int f;
    private long g;
    private long h;
    private long i;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private d() {
    }

    public d(com.netease.nimlib.biz.d.a aVar) throws Exception {
        this.a = a.a();
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        aVar.i().a(bVar);
        this.c = bVar.b();
        com.netease.nimlib.push.packet.c.b a2 = aVar.a();
        if (a2 != null) {
            this.d = a2.b();
        } else {
            this.d = ByteBuffer.allocate(0);
        }
        this.b = aVar.k();
        this.e = aVar.l();
    }

    public d(a.C0029a c0029a) {
        this.a = a.a();
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        c0029a.a.a(bVar);
        this.c = bVar.b();
        if (c0029a.b != null) {
            this.d = c0029a.b.b();
        } else {
            this.d = ByteBuffer.allocate(0);
        }
        this.b = c0029a.c;
        this.g = c0029a.a.p();
        this.h = c0029a.a.q();
        this.i = c0029a.a.r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int i() {
        return this.d.remaining();
    }

    public List<d> a() {
        return b.a(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        b.a(parcel, this);
    }

    public com.netease.nimlib.push.packet.a b() {
        if (this.c == null) {
            return null;
        }
        com.netease.nimlib.push.packet.a aVar = new com.netease.nimlib.push.packet.a();
        aVar.a(new com.netease.nimlib.push.packet.c.f(this.c));
        return aVar;
    }

    public ByteBuffer c() {
        return this.d;
    }

    public void a(com.netease.nimlib.push.packet.c.b bVar) {
        if (bVar != null) {
            this.d = bVar.b();
        } else {
            this.d = ByteBuffer.allocate(0);
        }
    }

    public boolean d() {
        return this.e;
    }

    public int e() {
        return this.b;
    }

    public long f() {
        return this.g;
    }

    public long g() {
        return this.h;
    }

    public long h() {
        return this.i;
    }

    /* compiled from: PacketData.java */
    /* loaded from: classes.dex */
    private static class b {
        private static SparseArray<d> a = new SparseArray<>();

        static List<d> a(d dVar) {
            dVar.f = dVar.i();
            int i = ((dVar.f - 1) / 122880) + 1;
            ArrayList arrayList = new ArrayList(i);
            arrayList.add(dVar);
            for (int i2 = 1; i2 < i; i2++) {
                d dVar2 = new d();
                dVar2.a = dVar.a;
                dVar2.b = dVar.b;
                dVar2.g = dVar.g;
                dVar2.h = dVar.h;
                dVar2.i = dVar.i;
                dVar2.d = dVar.d.duplicate();
                dVar2.d.position(dVar.d.position() + (i2 * 122880));
                dVar2.e = dVar.e;
                arrayList.add(dVar2);
            }
            return arrayList;
        }

        static d a(Parcel parcel) {
            d b = b(parcel);
            if (b(b)) {
                return b;
            }
            if (b.f > 0) {
                a.put(b.a, b);
                return null;
            }
            d dVar = a.get(b.a);
            if (dVar == null) {
                return null;
            }
            dVar.d.put(b.d);
            if (!b(dVar)) {
                return null;
            }
            a.remove(dVar.a);
            return dVar;
        }

        static void a(Parcel parcel, d dVar) {
            parcel.writeInt(dVar.a);
            parcel.writeInt(dVar.b);
            parcel.writeLong(dVar.g);
            parcel.writeLong(dVar.h);
            parcel.writeLong(dVar.i);
            if (dVar.c != null && dVar.c.remaining() > 0) {
                parcel.writeInt(dVar.c.limit());
                parcel.writeByteArray(dVar.c.array(), 0, dVar.c.limit());
            } else {
                parcel.writeInt(0);
            }
            parcel.writeInt(dVar.f);
            if (dVar.d.remaining() > 0) {
                int min = Math.min(dVar.d.remaining(), 122880);
                parcel.writeInt(min);
                parcel.writeByteArray(dVar.d.array(), dVar.d.position(), min);
            } else {
                parcel.writeInt(0);
            }
            parcel.writeInt(dVar.e ? 1 : 0);
        }

        static d b(Parcel parcel) {
            d dVar = new d();
            dVar.a = parcel.readInt();
            dVar.b = parcel.readInt();
            dVar.g = parcel.readLong();
            dVar.h = parcel.readLong();
            dVar.i = parcel.readLong();
            if (parcel.readInt() > 0) {
                dVar.c = ByteBuffer.wrap(parcel.createByteArray());
            }
            dVar.f = parcel.readInt();
            int readInt = parcel.readInt();
            if (readInt <= 0) {
                dVar.d = ByteBuffer.allocate(0);
            } else {
                byte[] createByteArray = parcel.createByteArray();
                if (dVar.f > 0) {
                    if (dVar.f == readInt) {
                        dVar.d = ByteBuffer.wrap(createByteArray);
                        dVar.d.position(readInt);
                    } else {
                        dVar.d = ByteBuffer.allocate(dVar.f);
                        dVar.d.put(createByteArray);
                    }
                } else {
                    dVar.d = ByteBuffer.wrap(createByteArray, 0, readInt);
                }
            }
            dVar.e = parcel.readInt() > 0;
            return dVar;
        }

        private static boolean b(d dVar) {
            return dVar.d.capacity() == 0 || (dVar.f > 0 && dVar.d.position() == dVar.f);
        }
    }

    /* compiled from: PacketData.java */
    /* loaded from: classes.dex */
    private static final class a {
        private static AtomicInteger a = new AtomicInteger(0);

        public static int a() {
            return a.incrementAndGet();
        }
    }
}
