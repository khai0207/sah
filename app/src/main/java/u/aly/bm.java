package u.aly;

/* compiled from: SDKType.java */
/* loaded from: classes.dex */
public enum bm implements cd {
    ANDROID(0),
    IOS(1),
    WINDOWS_PHONE(2),
    WINDOWS_RT(3);

    private final int e;

    bm(int i) {
        this.e = i;
    }

    @Override // u.aly.cd
    public int a() {
        return this.e;
    }

    public static bm a(int i) {
        if (i == 0) {
            return ANDROID;
        }
        if (i == 1) {
            return IOS;
        }
        if (i == 2) {
            return WINDOWS_PHONE;
        }
        if (i != 3) {
            return null;
        }
        return WINDOWS_RT;
    }
}
