package u.aly;

import u.aly.cs;

/* compiled from: TProtocolUtil.java */
/* loaded from: classes.dex */
public class db {
    private static int a = Integer.MAX_VALUE;

    public static void a(int i) {
        a = i;
    }

    public static void a(cy cyVar, byte b) throws cf {
        a(cyVar, b, a);
    }

    public static void a(cy cyVar, byte b, int i) throws cf {
        if (i <= 0) {
            throw new cf("Maximum skip depth exceeded");
        }
        int i2 = 0;
        switch (b) {
            case 2:
                cyVar.t();
                return;
            case 3:
                cyVar.u();
                return;
            case 4:
                cyVar.y();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                cyVar.v();
                return;
            case 8:
                cyVar.w();
                return;
            case 10:
                cyVar.x();
                return;
            case 11:
                cyVar.A();
                return;
            case 12:
                cyVar.j();
                while (true) {
                    ct l = cyVar.l();
                    if (l.b != 0) {
                        a(cyVar, l.b, i - 1);
                        cyVar.m();
                    } else {
                        cyVar.k();
                        return;
                    }
                }
            case 13:
                cv n = cyVar.n();
                while (i2 < n.c) {
                    int i3 = i - 1;
                    a(cyVar, n.a, i3);
                    a(cyVar, n.b, i3);
                    i2++;
                }
                cyVar.o();
                return;
            case 14:
                dc r = cyVar.r();
                while (i2 < r.b) {
                    a(cyVar, r.a, i - 1);
                    i2++;
                }
                cyVar.s();
                return;
            case 15:
                cu p = cyVar.p();
                while (i2 < p.b) {
                    a(cyVar, p.a, i - 1);
                    i2++;
                }
                cyVar.q();
                return;
        }
    }

    public static da a(byte[] bArr, da daVar) {
        if (bArr[0] > 16) {
            return new cs.a();
        }
        return (bArr.length <= 1 || (bArr[1] & 128) == 0) ? daVar : new cs.a();
    }
}
