package u.aly;

/* compiled from: TApplicationException.java */
/* loaded from: classes.dex */
public class by extends cf {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final dd j = new dd("TApplicationException");
    private static final ct k = new ct("message", (byte) 11, 1);
    private static final ct l = new ct("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i;

    public by() {
        this.i = 0;
    }

    public by(int i) {
        this.i = 0;
        this.i = i;
    }

    public by(int i, String str) {
        super(str);
        this.i = 0;
        this.i = i;
    }

    public by(String str) {
        super(str);
        this.i = 0;
    }

    public int a() {
        return this.i;
    }

    public static by a(cy cyVar) throws cf {
        cyVar.j();
        String str = null;
        int i = 0;
        while (true) {
            ct l2 = cyVar.l();
            if (l2.b != 0) {
                short s = l2.c;
                if (s != 1) {
                    if (s == 2) {
                        if (l2.b == 8) {
                            i = cyVar.w();
                        } else {
                            db.a(cyVar, l2.b);
                        }
                    } else {
                        db.a(cyVar, l2.b);
                    }
                } else if (l2.b == 11) {
                    str = cyVar.z();
                } else {
                    db.a(cyVar, l2.b);
                }
                cyVar.m();
            } else {
                cyVar.k();
                return new by(i, str);
            }
        }
    }

    public void b(cy cyVar) throws cf {
        cyVar.a(j);
        if (getMessage() != null) {
            cyVar.a(k);
            cyVar.a(getMessage());
            cyVar.c();
        }
        cyVar.a(l);
        cyVar.a(this.i);
        cyVar.c();
        cyVar.d();
        cyVar.b();
    }
}
