package okhttp3;

import okhttp3.internal.Util;

/* loaded from: classes.dex */
public final class Challenge {
    private final String realm;
    private final String scheme;

    public Challenge(String str, String str2) {
        this.scheme = str;
        this.realm = str2;
    }

    public String scheme() {
        return this.scheme;
    }

    public String realm() {
        return this.realm;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Challenge) {
            Challenge challenge = (Challenge) obj;
            if (Util.equal(this.scheme, challenge.scheme) && Util.equal(this.realm, challenge.realm)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.realm;
        int hashCode = (899 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.scheme;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return this.scheme + " realm=\"" + this.realm + "\"";
    }
}
