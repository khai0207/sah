package u.aly;

/* compiled from: TProtocolException.java */
/* loaded from: classes.dex */
public class cz extends cf {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static final long h = 1;
    protected int g;

    public cz() {
        this.g = 0;
    }

    public cz(int i) {
        this.g = 0;
        this.g = i;
    }

    public cz(int i, String str) {
        super(str);
        this.g = 0;
        this.g = i;
    }

    public cz(String str) {
        super(str);
        this.g = 0;
    }

    public cz(int i, Throwable th) {
        super(th);
        this.g = 0;
        this.g = i;
    }

    public cz(Throwable th) {
        super(th);
        this.g = 0;
    }

    public cz(String str, Throwable th) {
        super(str, th);
        this.g = 0;
    }

    public cz(int i, String str, Throwable th) {
        super(str, th);
        this.g = 0;
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
