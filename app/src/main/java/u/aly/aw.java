package u.aly;

/* compiled from: ErrorSource.java */
/* loaded from: classes.dex */
public enum aw implements cd {
    LEGIT(1),
    ALIEN(2);

    private final int c;

    aw(int i) {
        this.c = i;
    }

    @Override // u.aly.cd
    public int a() {
        return this.c;
    }

    public static aw a(int i) {
        if (i == 1) {
            return LEGIT;
        }
        if (i != 2) {
            return null;
        }
        return ALIEN;
    }
}
