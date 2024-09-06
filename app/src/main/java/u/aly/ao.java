package u.aly;

/* compiled from: AccessType.java */
/* loaded from: classes.dex */
public enum ao implements cd {
    ACCESS_TYPE_UNKNOWN(0),
    ACCESS_TYPE_2G_3G(1),
    ACCESS_TYPE_WIFI(2),
    ACCESS_TYPE_ETHERNET(3);

    private final int e;

    ao(int i) {
        this.e = i;
    }

    @Override // u.aly.cd
    public int a() {
        return this.e;
    }

    public static ao a(int i) {
        if (i == 0) {
            return ACCESS_TYPE_UNKNOWN;
        }
        if (i == 1) {
            return ACCESS_TYPE_2G_3G;
        }
        if (i == 2) {
            return ACCESS_TYPE_WIFI;
        }
        if (i != 3) {
            return null;
        }
        return ACCESS_TYPE_ETHERNET;
    }
}
