package u.aly;

/* compiled from: Gender.java */
/* loaded from: classes.dex */
public enum ay implements cd {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);

    private final int d;

    ay(int i) {
        this.d = i;
    }

    @Override // u.aly.cd
    public int a() {
        return this.d;
    }

    public static ay a(int i) {
        if (i == 0) {
            return MALE;
        }
        if (i == 1) {
            return FEMALE;
        }
        if (i != 2) {
            return null;
        }
        return UNKNOWN;
    }
}
